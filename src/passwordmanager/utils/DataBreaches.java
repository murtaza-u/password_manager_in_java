package src.passwordmanager.utils;

import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.Scanner;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

public class DataBreaches {
    private static String getResponse(String field) {
        try {
            URL url = new URL("https://haveibeenpwned.com/api/v3/breach/" + field);

            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.connect();

            // getting the response code
            int responsecode = conn.getResponseCode();

            if (responsecode != 200) {
                return null;
            } else {
                String response = "";
                Scanner sc = new Scanner(url.openStream());

                // write all the JSON data into a string using a scanner
                while (sc.hasNext()) {
                    response += sc.nextLine();
                }

                sc.close();
                return response;
            }
        } catch (Exception e) {
            return null;
        }
    }

    public HashMap<String, String> getData(String field) {
        String response = getResponse(field);

        //Using the JSON simple library parse the string into a json object
        JSONParser parse = new JSONParser();

        try {
            JSONObject obj = (JSONObject) parse.parse(response);

            HashMap<String, String> breachData = new HashMap<String, String>();
            breachData.put("Name", obj.get("Name").toString());
            breachData.put("Domain", obj.get("Domain").toString());
            breachData.put("BreachDate", obj.get("BreachDate").toString());
            breachData.put("PwnCount", obj.get("PwnCount").toString());
            breachData.put("IsVerified", obj.get("IsVerified").toString());
            return breachData;
        } catch (Exception e) {
            return null;
        }
    }
}
