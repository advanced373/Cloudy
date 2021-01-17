package ro.mta.facc.selab.mihaiapp.helpers;

import javafx.beans.property.DoubleProperty;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * author: Stoica Mihai
 */
public class APIWorker {
        private String appId;

    public String getAppId() {
        return appId;
    }

    /**
     * appId is api id from my account on OpenWeatherMap
     * @param appId
     */
    public void setAppId(String appId) {
        this.appId = appId;
    }

    /**
     *
     * @param latitude
     * @param longitude
     * @return
     * @throws Exception
     */
    public StringBuffer getWeatherInformation(Double latitude, Double longitude) throws Exception {
        BufferedReader in;
        HttpURLConnection con;
        StringBuffer content = new StringBuffer();
            URL url = new URL("https://api.openweathermap.org/data/2.5/weather?lat=" + latitude + "&lon=" + longitude + "&appid=" + this.appId + "&units=metric");
            con = (HttpURLConnection) url.openConnection();
            con.setRequestMethod("GET");
            int status = con.getResponseCode();
            if (status != 200) {
                throw new Exception("Status: "+status);
            }
            in= new BufferedReader(new InputStreamReader(con.getInputStream()));
            String inputLine;
            while ((inputLine = in.readLine()) != null) {
                content.append(inputLine);
            }
            in.close();
            con.disconnect();

        return content;
    }
}
