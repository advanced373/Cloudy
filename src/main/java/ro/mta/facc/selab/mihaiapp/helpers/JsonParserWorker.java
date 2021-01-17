package ro.mta.facc.selab.mihaiapp.helpers;
import com.eclipsesource.json.Json;
import com.eclipsesource.json.JsonArray;
import com.eclipsesource.json.JsonObject;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
/**
 * author: Stoica Mihai
 */
public class JsonParserWorker {
    /**
     *
     * @param stringBuffer json object received from API
     * @return a JsonInformation with relevant information for app
     */
    public JsonInformation parse(StringBuffer stringBuffer)
    {
        if(stringBuffer == null)
        {
            return null;
        }
        JsonObject value= Json.parse(String.valueOf(stringBuffer)).asObject();
        String city = value.get("name").asString();
        JsonArray weather = value.get("weather").asArray();
        JsonObject weatherFirstValue = weather.get(0).asObject();
        String weatherName = weatherFirstValue.get("main").toString();
        JsonObject mainValue = value.get("main").asObject();
        Integer pressure = mainValue.get("pressure").asInt();
        Integer humidity = mainValue.get("humidity").asInt();
        Double temperature = mainValue.get("temp").asDouble();
        JsonObject wind = value.get("wind").asObject();
        Double windSpeed = wind.get("speed").asDouble();
        JsonInformation jsonInformation = new JsonInformation(new SimpleIntegerProperty(humidity),new SimpleIntegerProperty(pressure), new SimpleDoubleProperty(temperature),new SimpleDoubleProperty(windSpeed),new SimpleStringProperty(weatherName));
        return jsonInformation;
    }

}
