package ro.mta.facc.selab.mihaiapp.helpers;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.StringProperty;

/**
 * author: Stoica Mihai
 */
public class JsonInformation {
    IntegerProperty humidity;
    IntegerProperty pressure;
    DoubleProperty temperature;
    DoubleProperty windSpeed;
    StringProperty weatherName;

    /**
     * Constructor
     * @param humidity
     * @param pressure
     * @param temperature
     * @param windSpeed
     * @param weatherName
     */
    public JsonInformation(IntegerProperty humidity, IntegerProperty pressure, DoubleProperty temperature, DoubleProperty windSpeed, StringProperty weatherName) {
        this.humidity = humidity;
        this.pressure = pressure;
        this.temperature = temperature;
        this.windSpeed = windSpeed;
        this.weatherName = weatherName;
    }

    public JsonInformation() {
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

    public String getWeatherName() {
        return weatherName.get();
    }

    public StringProperty weatherNameProperty() {
        return weatherName;
    }

    public void setWeatherName(String weatherName) {
        this.weatherName.set(weatherName);
    }
}
