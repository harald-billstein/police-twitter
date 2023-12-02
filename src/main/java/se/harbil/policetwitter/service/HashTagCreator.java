package se.harbil.policetwitter.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import se.harbil.policetwitter.model.PoliceEventKafkaModel;

@Slf4j
@Service
public class HashTagCreator {
    private final String whiteSpace = " ";

    public String getHashTag(final PoliceEventKafkaModel eventKafkaModel) {
        String location = extractLocation(eventKafkaModel.getLocationName());
        String type = extractHashTag(eventKafkaModel.getType());
        return location + type;
    }

    private String extractLocation(final String locationName) {
        return whiteSpace + "#" + locationName.replace(" ", "") + whiteSpace;
    }

    private String extractHashTag(final String type) {

        return switch (type.toLowerCase()) {
            case "anträffad död" -> "#anträffad #död" + whiteSpace;
            case "anträffat gods" -> "#anträffat #gods" + whiteSpace;
            case "brand automatlarm" -> "#brand #automatlarm" + whiteSpace;
            case "djur skadat/omhändertaget" -> "#djur #skadat #omhändertaget" + whiteSpace;
            case "djur" -> "#djur" + whiteSpace;
            case "farligt föremål, misstänkt" -> "#misstänkt #farligt #föremål" + whiteSpace;
            case "fylleri/lob" -> "#fylleri #LOB" + whiteSpace;
            case "försvunnen person" -> "#försvunnen #person" + whiteSpace;
            case "inbrott, försök" -> "#inbrottsförsök" + whiteSpace;
            case "kontroll person/fordon" -> "#kontroll #person #fordon" + whiteSpace;
            case "lagen om hundar och katter" -> "#hundar #katter" + whiteSpace;
            case "larm inbrott" -> "#larm #inbrott" + whiteSpace;
            case "larm överfall" -> "#larm #överfall" + whiteSpace;
            case "missbruk av urkund" -> "#missbruk #urkund" + whiteSpace;
            case "misshandel, grov" -> "#misshandel #grov" + whiteSpace;
            case "mord/dråp" -> "#Mord #drop" + whiteSpace;
            case "mord/dråp, försök" -> "#mordförsök #dråpförsök" + whiteSpace;
            case "motorfordon, anträffat stulet" -> "#motorfordon #anträffat #stulet" + whiteSpace;
            case "motorfordon, stöld" -> "#motorfordon #stöld" + whiteSpace;
            case "ofog barn/ungdom" -> "#ofog #barn #ungdom" + whiteSpace;
            case "ofredande/förargelse" -> "#ofredande #förargelse" + whiteSpace;
            case "olaga frihetsberövande" -> "#olaga #frihetsberövande" + whiteSpace;
            case "olaga hot" -> "#olaga #hot" + whiteSpace;
            case "olaga intrång/hemfridsbrott" -> "#olaga #intrång #hemfridsbrott" + whiteSpace;
            case "olovlig körning" -> "#olovlig #körning" + whiteSpace;
            case "polisinsats/kommendering" -> "#polisinsats #kommendering" + whiteSpace;
            case "rån väpnat" -> "#väpnat #Rån" + whiteSpace;
            case "rån övrigt" -> "#rån #övrigt" + whiteSpace;
            case "rån, försök" -> "#rån #försök" + whiteSpace;
            case "sammanfattning dag" -> "#sammanfattning #dag" + whiteSpace;
            case "sammanfattning dygn" -> "#sammanfattning #dygn" + whiteSpace;
            case "sammanfattning eftermiddag" -> "#sammanfattning #eftermiddag" + whiteSpace;
            case "sammanfattning förmiddag" -> "#sammanfattning #förmiddag" + whiteSpace;
            case "sammanfattning helg" -> "#sammanfattning #helg" + whiteSpace;
            case "sammanfattning kväll" -> "#sammanfattning #kväll" + whiteSpace;
            case "sammanfattning kväll och natt" -> "#sammanfattning #kväll #natt" + whiteSpace;
            case "sammanfattning natt" -> "#sammanfattning #natt" + whiteSpace;
            case "sammanfattning vecka" -> "#sammanfattning #vecka" + whiteSpace;
            case "sabotage mot blåljusverksamhet" -> "#sabotage #blåljusverksamhet" + whiteSpace;
            case "sjukdom/olycksfall" -> "#sjukdom #olycksfall" + whiteSpace;
            case "skottlossning, misstänkt" -> "#Skottlossning #misstänkt" + whiteSpace;
            case "spridning smittsamma kemikalier" -> "spridning #smittsamma #kemikalier" + whiteSpace;
            case "stöld, försök" -> "#stöld #försök" + whiteSpace;
            case "stöld, ringa" -> "#stöld #ringa" + whiteSpace;
            case "stöld/inbrott" -> "#stöld #inbrott" + whiteSpace;
            case "tillfälligt obemannat" -> "#tillfälligt #obemannat" + whiteSpace;
            case "trafikolycka, personskada" -> "#trafikolycka #personskada" + whiteSpace;
            case "trafikolycka, singel" -> "#trafikolycka #singel" + whiteSpace;
            case "trafikolycka, smitning från" -> "#trafikolycka #smitning" + whiteSpace;
            case "trafikolycka, vilt" -> "#trafikolycka #vilt" + whiteSpace;
            case "varningslarm/haveri" -> "#varningslarm #haveri" + whiteSpace;
            case "våld/hot mot tjänsteman" -> "#våld #hot #tjänsteman" + whiteSpace;
            case "våldtäkt, försök" -> "#våldtäkt #försök" + whiteSpace;
            case "vållande till kroppsskada" -> "#vållande #kroppskada" + whiteSpace;
            case "olaga intrång" -> "#olaga #intrång" + whiteSpace;
            default -> "#" + type + whiteSpace;
        };
    }
}
