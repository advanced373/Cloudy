package ro.mta.facc.selab.mihaiapp.controllers;

import com.eclipsesource.json.JsonParser;
import javafx.application.Platform;
import javafx.beans.property.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import ro.mta.facc.selab.mihaiapp.helpers.*;
import ro.mta.facc.selab.mihaiapp.model.City;
import ro.mta.facc.selab.mihaiapp.model.Country;
import ro.mta.facc.selab.mihaiapp.model.WeatherInformation;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
/**
 * author: Stoica Mihai
 * class Controller
 */
public class MainController {
    @FXML
    private ComboBox<String> countries;
    @FXML
    private ComboBox<String> cities;
    @FXML
    private Label windSpeed;
    @FXML
    private Label pressure;
    @FXML
    private Label humidity;
    @FXML
    private Label temperature;
    @FXML
    private Label clock;
    @FXML
    private Label name;
    @FXML
    private ImageView imageView;
    @FXML
    private Label weatherName;
    ObservableList<Country> countriesValue;
    private City currentCity;
    private APIWorker apiWorker;
    private JsonParserWorker jsonParserWorker;

    /**
     * Constructor
     * initialize working objects
     * @throws IOException
     */
    public MainController() throws IOException {
        this.countries = new ComboBox<String>();
        this.cities = new ComboBox<String>();
        this.countriesValue = FXCollections.observableArrayList();
        this.turnOnClock();
    }
    public MainController(ComboBox<String> countries, ComboBox<String> cities) throws IOException {
        this.countries = countries;
        this.cities = cities;
        this.countriesValue = FXCollections.observableArrayList();
        this.turnOnClock();
    }
    /**
     * method call before initialization of app
     * @throws Exception
     */
    @FXML
    private void initialize() throws Exception {
        if(this.apiWorker == null)
        {
            this.apiWorker = new APIWorker();
        }
        if(this.jsonParserWorker == null)
        {
            this.jsonParserWorker = new JsonParserWorker();
        }
        FileWorker fileWorker = new FileWorker();
        fileWorker.read();

        if (fileWorker.getArguments().isEmpty()) {
            throw new Exception("File is empty!");
        }
        try {
            for (FileLine fileLine : fileWorker.getArguments()) {
                JsonInformation jsonInformation = this.getInformation(fileLine);
                City city = new City(new SimpleIntegerProperty(fileLine.getCityId()), new SimpleStringProperty(fileLine.getName()), new SimpleStringProperty(fileLine.getCountry()), new SimpleDoubleProperty(fileLine.getLatitude()), new SimpleDoubleProperty(fileLine.getLongitude()));
                WeatherInformation weatherInformation = new WeatherInformation(jsonInformation.humidityProperty(), jsonInformation.pressureProperty(), jsonInformation.temperatureProperty(), jsonInformation.windSpeedProperty(), jsonInformation.weatherNameProperty());
                city.setWeatherInformation(weatherInformation);
                int flag = 0;
                for (Country country : this.countriesValue) {
                    if (country.getName().equals(fileLine.getCountry())) {
                        country.addCity(city);
                        flag = 1;
                    }
                }
                if (flag == 0) {
                    Country country = new Country(new SimpleStringProperty(fileLine.getCountry()));
                    country.addCity(city);
                    this.countriesValue.add(country);
                }

                ObservableList<String> countriesNames = FXCollections.observableArrayList();
                for (Country country : this.countriesValue) {
                    countriesNames.add(country.getName());
                }
                countries.setItems(FXCollections.observableArrayList(countriesNames));
            }
        } catch (Exception e) {
            throw e;
        }
    }

    /**
     * set api id from OpenWeatherMap account
     * @param fileLine call API to get weather information for every line
     * @return
     * @throws Exception
     */
    public JsonInformation getInformation(FileLine fileLine) throws Exception {
        this.apiWorker.setAppId("3f031e438f1534c375ec6b3505804eeb");
        StringBuffer stringBuffer = this.apiWorker.getWeatherInformation(fileLine.getLatitude(), fileLine.getLongitude());
        return this.jsonParserWorker.parse(stringBuffer);
    }

    /**
     * start a thread to get current time
     */
    private void turnOnClock()
    {
        Thread timerThread = new Thread(() -> {
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm");
            while (true) {
                try {
                    Thread.sleep(1000); //1 second
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                final String time = simpleDateFormat.format(new Date());
                Platform.runLater(() -> {
                    this.clock.setText(time);
                });
            }
        });
        timerThread.start();
    }

    /**
     * after a change, update label values
     */
    private void updateValues()
    {
        this.humidity.setText(Integer.toString(this.currentCity.getWeatherInformation().getHumidity()) + "%");
        this.pressure.setText(Integer.toString(this.currentCity.getWeatherInformation().getPressure()) + "hPa");
        this.temperature.setText(Double.toString(this.currentCity.getWeatherInformation().getTemperature()) + "°C");
        this.windSpeed.setText(Double.toString(this.currentCity.getWeatherInformation().getWindSpeed()) + "m/s");
        this.weatherName.setText(this.currentCity.getWeatherInformation().getName());
        this.name.setText(this.currentCity.getName());
    }

    /**
     * every time a change in one of two ComboBox is made, this function is called
     * @param actionEvent
     * @throws Exception
     */
    public void comboAction(ActionEvent actionEvent) throws Exception {
        String currentCountryName = this.countries.getValue();
        ObservableList<String> cityNames = FXCollections.observableArrayList();
        String currentCityName = this.cities.getValue();
        for (Country country : this.countriesValue) {
            if (country.getName().equals(currentCountryName)) {
                for (City city : country.getCities()) {
                    if (city.getName().equals(currentCityName)) {
                        this.currentCity = city;
                    }
                    cityNames.add(city.getName());
                }
            }
        }
        this.cities.setItems(FXCollections.observableArrayList(cityNames));
        if (this.currentCity != null) {
            this.updateValues();
            File file = new File(this.currentCity.getWeatherInformation().getLogoFileName());
            InputStream image = new FileInputStream(file);
            this.imageView.setImage(new Image(image));
        }

    }

    public void setApiWorker(APIWorker apiWorker) {
        this.apiWorker = apiWorker;
    }

    public void setJsonParserWorker(JsonParserWorker jsonParserWorker) {
        this.jsonParserWorker = jsonParserWorker;
    }

    public APIWorker getApiWorker() {
        return apiWorker;
    }

    public JsonParserWorker getJsonParserWorker() {
        return jsonParserWorker;
    }
}
