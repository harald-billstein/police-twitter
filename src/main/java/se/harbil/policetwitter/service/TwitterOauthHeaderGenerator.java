package se.harbil.policetwitter.service;

import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.time.Instant;
import java.util.Base64;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Random;
import java.util.stream.Collectors;
import javax.crypto.Mac;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class TwitterOauthHeaderGenerator {
    private static final String OAUTH_CONSUMER_KEY = "oauth_consumer_key";
    private static final String OAUTH_TOKEN = "oauth_token";
    private static final String OAUTH_SIGNATURE_METHOD = "oauth_signature_method";
    private static final String OAUTH_TIMESTAMP = "oauth_timestamp";
    private static final String OAUTH_NONCE = "oauth_nonce";
    private static final String OAUTH_VERSION = "oauth_version";
    private static final String OAUTH_SIGNATURE = "oauth_signature";
    private static final String HMAC_SHA1 = "HmacSHA1";
    private final String version = "1.0";
    private final String signatureMethod = "HMAC-SHA1";

    @Value("${app.twitter.authentication.consumer-key}")
    private String consumerKey;
    @Value("${app.twitter.authentication.consumer-secret}")
    private String consumerSecret;
    @Value("${app.twitter.authentication.token}")
    private String token;
    @Value("${app.twitter.authentication.token-secret}")
    private String tokenSecret;

    public String generateHeader(final Map<String, String> requestParams) {
        StringBuilder base = new StringBuilder();
        base.append("OAuth ");
        append(base, OAUTH_CONSUMER_KEY, consumerKey);
        append(base, OAUTH_TOKEN, token);
        append(base, OAUTH_SIGNATURE_METHOD, signatureMethod);
        String timestamp = getTimestamp();
        append(base, OAUTH_TIMESTAMP, timestamp);
        String nonce = getNonce();
        append(base, OAUTH_NONCE, nonce);
        append(base, OAUTH_VERSION, version);
        String baseSignatureString = generateSignatureBaseString(requestParams, nonce, timestamp);
        append(base, OAUTH_SIGNATURE, encryptUsingHmacSha1(baseSignatureString));
        base.deleteCharAt(base.length() - 1);
        log.debug("header : " + base);
        return base.toString();
    }


    private String generateSignatureBaseString(final Map<String, String> requestParams, final String nonce, final String timestamp) {
        Map<String, String> params = new HashMap<>();
        requestParams.forEach((key, value) -> put(params, key, value));
        put(params, OAUTH_CONSUMER_KEY, consumerKey);
        put(params, OAUTH_NONCE, nonce);
        put(params, OAUTH_SIGNATURE_METHOD, signatureMethod);
        put(params, OAUTH_TIMESTAMP, timestamp);
        put(params, OAUTH_TOKEN, token);
        put(params, OAUTH_VERSION, version);
        Map<String, String> sortedParams = params.entrySet().stream().sorted(Map.Entry.comparingByKey())
            .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, (oldValue, newValue) -> oldValue, LinkedHashMap::new));
        StringBuilder base = new StringBuilder();
        sortedParams.forEach((key, value) -> base.append(key).append("=").append(value).append("&"));
        base.deleteCharAt(base.length() - 1);
        return "POST&" + encode("https://api.twitter.com/2/tweets") + "&" + encode(base.toString());
    }

    private String encryptUsingHmacSha1(final String input) {
        String secret = encode(consumerSecret) + "&" + encode(tokenSecret);
        byte[] keyBytes = secret.getBytes(StandardCharsets.UTF_8);
        SecretKey key = new SecretKeySpec(keyBytes, HMAC_SHA1);
        Mac mac;
        try {
            mac = Mac.getInstance(HMAC_SHA1);
            mac.init(key);
        } catch (NoSuchAlgorithmException | InvalidKeyException e) {
            log.warn(e.getMessage());
            return null;
        }
        byte[] signatureBytes = mac.doFinal(input.getBytes(StandardCharsets.UTF_8));
        return new String(Base64.getEncoder().encode(signatureBytes), StandardCharsets.UTF_8);
    }

    private String encode(final String value) {
        String encoded = "";
        try {
            encoded = URLEncoder.encode(value, StandardCharsets.UTF_8);
        } catch (Exception e) {
            log.warn(e.getMessage());
        }
        StringBuilder sb = new StringBuilder();
        char focus;
        for (int i = 0; i < encoded.length(); i++) {
            focus = encoded.charAt(i);
            if (focus == '*') {
                sb.append("%2A");
            } else if (focus == '+') {
                sb.append("%20");
            } else if (focus == '%' && i + 1 < encoded.length() && encoded.charAt(i + 1) == '7' && encoded.charAt(i + 2) == 'E') {
                sb.append('~');
                i += 2;
            } else {
                sb.append(focus);
            }
        }
        return sb.toString();
    }

    private void put(final Map<String, String> map, final String key, final String value) {
        map.put(encode(key), encode(value));
    }

    private void append(final StringBuilder builder, final String key, final String value) {
        builder.append(encode(key)).append("=\"").append(encode(value)).append("\",");
    }

    private String getNonce() {
        int leftLimit = 48; // numeral '0'
        int rightLimit = 122; // letter 'z'
        int targetStringLength = 10;
        Random random = new Random();

        return random
            .ints(leftLimit, rightLimit + 1)
            .filter(i -> (i <= 57 || i >= 65) && (i <= 90 || i >= 97))
            .limit(targetStringLength)
            .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
            .toString();
    }

    private String getTimestamp() {
        return String.valueOf(Instant.now().getEpochSecond());
    }

}
