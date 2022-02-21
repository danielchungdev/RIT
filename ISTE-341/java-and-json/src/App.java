import java.net.*;
import java.io.*;
import java.util.*;
import javax.json.*;
import javax.json.stream.*;
import javax.json.stream.JsonParser.*;

public class App {
    public static void main(String[] args) throws Exception {
        try {
            // URL url = new URL("http://www.ist.rit.edu/~bdfvks/454/nationalParks.php?type=json");
            // InputStream is = url.openStream();
            // JsonReader reader = Json.createReader(is);
            // JsonObject obj = reader.readObject();
            // JsonArray results = obj.getJsonArray("parks");
            // int count = 0;
            // for (JsonObject result : results.getValuesAs(JsonObject.class)){
            //     count += 1;
            //     System.out.println(count);
            // }
            String inStr = "{\"company\": \"dec8768\", \"department\": \"IT\"}";
        }
        catch ( Exception e){
            System.out.println("Error" + e);
        }
    }
}
