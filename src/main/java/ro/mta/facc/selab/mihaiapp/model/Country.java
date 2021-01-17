package ro.mta.facc.selab.mihaiapp.model;

import javafx.beans.property.ListProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.ObservableList;

import java.util.ArrayList;

/**
 * author: Stoica Mihai
 *
 */

public class Country {
    StringProperty name;
    ArrayList<City> cities;

    /**
     * Constructor
     * @param name
     */
    public Country(StringProperty name) {
        this.name = name;
        this.cities = new ArrayList<City>();
    }

    public Country() {

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
     *
     * @return cities from country
     */
    public ArrayList<City> getCities() {
        return cities;
    }

    /**
     * add a new city in country's list
     * @param city
     */
    public void addCity(City city)
    {
        this.cities.add(city);
    }

}
