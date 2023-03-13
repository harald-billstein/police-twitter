package se.harbil.policetwitter.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import se.harbil.policetwitter.model.PoliceEventKafkaModel;

@Slf4j
@Service
public class HashTagCreator {

    public String getHashTag(PoliceEventKafkaModel eventKafkaModel) {
        String location = extractLocation(eventKafkaModel.getLocationName());
        String type = extractHashTag(eventKafkaModel.getType());
        return location + type;
    }

    private String extractLocation(String locationName) {
        return " #" + locationName.replace(" ", "") + " ".toLowerCase();
    }

    private String extractHashTag(String type) {
        return switch (type.toLowerCase()) {
            case "alkohollagen" -> "#" + type;
            case "anträffad död" -> "#anträffad #död";
            case "anträffat gods" -> "#anträffat #gods";
            case "arbetsplatsolycka" -> "#" + type;
            case "bedrägeri" -> "#" + type;
            case "bombhot" -> "#" + type;
            case "brand" -> "#" + type;
            case "brand automatlarm" -> "#brand #automatlarm";
            case "bråk" -> "#" + type;
            case "detonation" -> "#" + type;
            case "djur skadat/omhändertaget" -> "#djur #skadat #omhändertaget";
            case "djur" -> "#djur";
            case "ekobrott" -> "#" + type;
            case "farligt föremål, misstänkt" -> "#misstänkt #farligt #föremål";
            case "fjällräddning" -> "#" + type;
            case "fylleri/lob" -> "#fylleri #LOB";
            case "förfalskningsbrott" -> "#" + type;
            case "försvunnen person" -> "#försvunnen #person";
            case "gränskontroll" -> "#" + type;
            case "häleri" -> "#" + type;
            case "inbrott" -> "#" + type;
            case "inbrott, försök" -> "#inbrottsförsök";
            case "knivlagen" -> "#" + type;
            case "kontroll person/fordon" -> "#kontroll #person #fordon";
            case "lagen om hundar och katter" -> "#hundar #katter";
            case "larm inbrott" -> "#larm #inbrott";
            case "larm överfall" -> "#larm #överfall";
            case "miljöbrott" -> "#" + type;
            case "missbruk av urkund" -> "#missbruk #urkund";
            case "misshandel" -> "#" + type;
            case "misshandel, grov" -> "#misshandel #grov";
            case "mord/dråp" -> "#Mord #drop";
            case "mord/dråp, försök" -> "#mordförsök #dråpförsök";
            case "motorfordon, anträffat stulet" -> "#motorfordon #anträffat #stulet";
            case "motorfordon, stöld" -> "#motorfordon #stöld";
            case "narkotikabrott" -> "#" + type;
            case "naturkatastrof" -> "#" + type;
            case "ofog barn/ungdom" -> "#ofog #barn #ungdom";
            case "ofredande/förargelse" -> "#ofredande #förargelse";
            case "olaga frihetsberövande" -> "#olaga #frihetsberövande";
            case "olaga hot" -> "#olaga #hot";
            case "olaga intrång/hemfridsbrott" -> "#olaga #intrång #hemfridsbrott";
            case "olovlig körning" -> "#olovlig #körning";
            case "ordningslagen" -> "#" + type;
            case "polisinsats/kommendering" -> "#polisinsats #kommendering";
            case "rattfylleri" -> "#" + type;
            case "rån" -> "#" + type;
            case "rån väpnat" -> "#väpnat #Rån";
            case "rån övrigt" -> "#rån #övrigt";
            case "rån, försök" -> "#rån #försök";
            case "räddningsinsats" -> "#" + type;
            case "sammanfattning dag" -> "#sammanfattning #dag";
            case "sammanfattning dygn" -> "#sammanfattning #dygn";
            case "sammanfattning eftermiddag" -> "#sammanfattning #eftermiddag";
            case "sammanfattning förmiddag" -> "#sammanfattning #förmiddag";
            case "sammanfattning helg" -> "#sammanfattning #helg";
            case "sammanfattning kväll" -> "#sammanfattning #kväll";
            case "sammanfattning kväll och natt" -> "#sammanfattning #kväll #natt";
            case "sammanfattning natt" -> "#sammanfattning #natt";
            case "sammanfattning vecka" -> "#sammanfattning #vecka";
            case "sabotage mot blåljusverksamhet" -> "#sabotage #blåljusverksamhet";
            case "sedlighetsbrott" -> "#" + type;
            case "sjukdom/olycksfall" -> "#sjukdom #olycksfall";
            case "sjölagen" -> "#" + type;
            case "skadegörelse" -> "#" + type;
            case "skottlossning" -> "#" + type;
            case "skottlossning, misstänkt" -> "#Skottlossning #misstänkt";
            case "spridning smittsamma kemikalier" -> "spridning #smittsamma #kemikalier";
            case "stöld" -> "#" + type;
            case "stöld, försök" -> "#stöld #försök";
            case "stöld, ringa" -> "#stöld #ringa";
            case "stöld/inbrott" -> "#stöld #inbrott";
            case "tillfälligt obemannat" -> "#tillfälligt #obemannat";
            case "trafikbrott" -> "#" + type;
            case "trafikhinder" -> "#" + type;
            case "trafikkontroll" -> "#" + type;
            case "trafikolycka" -> "#" + type;
            case "trafikolycka, personskada" -> "#trafikolycka #personskada";
            case "trafikolycka, singel" -> "#trafikolycka #singel";
            case "trafikolycka, smitning från" -> "#trafikolycka #smitning";
            case "trafikolycka, vilt" -> "#trafikolycka #vilt";
            case "uppdatering" -> "#" + type;
            case "utlänningslagen" -> "#" + type;
            case "vapenlagen" -> "#" + type;
            case "varningslarm/haveri" -> "#varningslarm #haveri";
            case "våld/hot mot tjänsteman" -> "#våld #hot #tjänsteman";
            case "våldtäkt" -> "#" + type;
            case "våldtäkt, försök" -> "#våldtäkt #försök";
            case "vållande till kroppsskada" -> "#vållande #kroppskada";
            case "olaga intrång" -> "#olaga #intrång";
            case "åldringsbrott" -> "#" + type;
            case "övrigt" -> "#" + type;
            default -> "unknown typ: " + type;
        };
    }
}
