package ro.mta.facc.selab.mihaiapp.model;
import javafx.beans.property.*;

/**
 * author: Stoica Mihai
 */
public class WeatherInformation {
    IntegerProperty humidity;
    IntegerProperty pressure;
    DoubleProperty temperature;
    DoubleProperty windSpeed;
    StringProperty name;
    String logoFileName;

    /**
     * Constructor
     * information about city weather
     * @param humidity
     * @param pressure
     * @param temperature
     * @param windSpeed
     * @param name wheather name : for example Snow, Sun, Rain etc.
     */
    public WeatherInformation(IntegerProperty humidity, IntegerProperty pressure, DoubleProperty temperature, DoubleProperty windSpeed, StringProperty name) {
        this.humidity = humidity;
        this.pressure = pressure;
        this.temperature = temperature;
        this.windSpeed = windSpeed;
        this.name = name;
        String check = this.name.getValue();
        if(check.equals("\"Snow\""))
        {
            this.logoFileName = "src/main/resources/images/snowflake.png";
        }
        else if(check.equals("\"Cloud\""))
        {
            this.logoFileName = "src/main/resources/images/cloud.png";
        }
        else if(check.equals("\"Rain\""))
        {
            this.logoFileName = "src/main/resources/images/rain.png";
        }
        else
        {
            this.logoFileName = "src/main/resources/images/sun.png";
        }
    }

    public int getHumidity() {
        return humidity.get();
    }

    public IntegerProperty humidityProperty() {
        return humidity;
    }

    public void setHumidity(int humidity) {
        this.humidity.set(humidity);
    }

    public int getPressure() {
        return pressure.get();
    }

    public IntegerProperty pressureProperty() {
        return pressure;
    }

    public void setPressure(int pressure) {
        this.pressure.set(pressure);
    }

    public double getTemperature() {
        return temperature.get();
    }

    public DoubleProperty temperatureProperty() {
        return temperature;
    }

    public void setTemperature(double temperature) {
        this.temperature.set(temperature);
    }

    public double getWindSpeed() {
        return windSpeed.get();
    }

    public DoubleProperty windSpeedProperty() {
        return windSpeed;
    }

    public void setWindSpeed(double windSpeed) {
        this.windSpeed.set(windSpeed);
    }

    public String getName() {
        return name.get();
    }

    public StringProperty nameProperty() {
        return name;
    }

    public void setName(String name) {
        this.name.set(name);
    }

    /**
     * name of file in which is logo representing a weather
     * @return
     */
    public String getLogoFileName() {
        return logoFileName;
    }

    public void setLogoFileName(String logoFileName) {
        this.logoFileName = logoFileName;
    }
}
