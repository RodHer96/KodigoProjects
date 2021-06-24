package models;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.HashMap;
import java.util.Map;

public class Weather {
	

    private String meteorology;

    public static Map<String, Object> jsonToMap(String str){
        Map<String, Object> map = new Gson().fromJson(str, new TypeToken<HashMap<String,Object>>() {}.getType());
        return map;
    }

    public void askForWeather() {

    }

    public static String generateOutput() {
        String API_KEY ="2ae267fbb98b8623e4e013fa076ade6b";
        String Location = "San Salvador";
        String urlString = "http://api.openweathermap.org/data/2.5/weather?q="+Location+"&appid="+API_KEY+"&units=imperial";

        try {
            StringBuilder result = new StringBuilder();
            URL url = new URL(urlString);
            URLConnection conn = url.openConnection();
            BufferedReader rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            String line;

            while ((line = rd.readLine()) != null){
                result.append(line);
            }
            rd.close();
            System.out.println(result);

            Map<String,Object> resMap = jsonToMap(result.toString());
            Map<String,Object> mainMap = jsonToMap(resMap.get("main").toString());
            Map<String,Object> windMap = jsonToMap(resMap.get("wind").toString());

            StringBuilder sb = new StringBuilder();
            sb.append("\nCity: ");
            sb.append(Location);
            sb.append("\nCurrent Temperature: ");
            sb.append(mainMap.get("temp"));
            sb.append("\nCurrent Humidity: ");
            sb.append(mainMap.get("humidity"));
            sb.append("\nCurrent Speeds: ");
            sb.append(mainMap.get("speed"));
            return sb.toString();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
