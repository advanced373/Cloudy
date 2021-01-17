package ro.mta.facc.selab.mihaiapp.model;
import javafx.beans.property.*;

/**
 * author: Stoica Mihai
 */
public class City {
    IntegerProperty id;
    StringProperty name;
    StringProperty country;
    DoubleProperty latitude;
    DoubleProperty longitude;
    WeatherInformation weatherInformation;

    /**
     * Constructor
     * @param id city id
     * @param name city name
     * @param country name of the city's country
     * @param latitude
     * @param longitude
     */
    public City(IntegerProperty id, StringProperty name, StringProperty country, DoubleProperty latitude, DoubleProperty longitude) {
        this.id = id;
        this.name = name;
        this.country = country;
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public WeatherInformation getWeatherInformation() {
        return weatherInformation;
    }

    /**
     * set information get from API's about the city
     * @param weatherInformation
     */
    public void setWeatherInformation(WeatherInformation weatherInformation) {
        this.weatherInformation = weatherInformation;
    }

    public int getId() {
        return id.get();
    }

    public IntegerProperty idProperty() {
        return id;
    }

    public void setId(int id) {
        this.id.set(id);
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

    public String getCountry() {
        return country.get();
    }

    public StringProperty countryProperty() {
        return country;
    }

    public void setCountry(String country) {
        this.country.set(country);
    }

    public double getLatitude() {
        return latitude.get();
    }

    public DoubleProperty latitudeProperty() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude.set(latitude);
    }

    public double getLongitude() {
        return longitude.get();
    }

    public DoubleProperty longitudeProperty() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude.set(longitude);
    }
}
