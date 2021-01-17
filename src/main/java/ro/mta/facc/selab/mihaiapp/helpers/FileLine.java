package ro.mta.facc.selab.mihaiapp.helpers;

/**
 * author: Stoica Mihai
 * class representing a line from input file
 */
public class FileLine {
    private String name;
    private double latitude;
    private double longitude;
    private String country;
    private int cityId;

    /**
     * Constructor
     * @param name
     * @param latitude
     * @param longitude
     * @param country
     * @param cityId
     */
    public FileLine(String name, double latitude, double longitude, String country, int cityId){
    this.cityId = cityId;
    this.country = country;
    this.latitude = latitude;
    this.name = name;
    this.longitude = longitude;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(float latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(float longitude) {
        this.longitude = longitude;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public int getCityId() {
        return cityId;
    }

    public void setCityId(int cityId) {
        this.cityId = cityId;
    }
}
