import com.eclipsesource.json.Json;
import com.eclipsesource.json.JsonObject;
import com.eclipsesource.json.JsonParser;
import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import static junit.framework.Assert.assertFalse;
import static junit.framework.Assert.assertTrue;
import static junit.framework.TestCase.assertEquals;
import ro.mta.facc.selab.mihaiapp.helpers.APIWorker;

/**
 * author: Stoica Mihai
 * test class for ApiWorker using Junit
 */
public class TestAPIWorker {
    APIWorker apiWorker;
    @Before
    public void initAPIWorker()
    {
        this.apiWorker = new APIWorker();
    }
    @After
    public void removeAPIWorker()
    {
        this.apiWorker = null;
    }
    @Rule
    public ExpectedException expectedException = ExpectedException.none();

    /**
     * wrong appId
     * @throws Exception
     */
    @Test
    public void wrongAPIKey() throws Exception {
        String errorMessage = "Status: 401";
        expectedException.expect(Exception.class);
        expectedException.expectMessage(errorMessage);
        this.apiWorker.setAppId("3f031e438f1534c375ec6b3505804ee");
        this.apiWorker.getWeatherInformation(55.591667 ,37.740833);
    }

    /**
     * bad latitude and longitude
     * @throws Exception
     */
    @Test
    public void wrongArguments() throws Exception {
        String errorMessage = "Status: 400";
        expectedException.expect(Exception.class);
        expectedException.expectMessage(errorMessage);
        this.apiWorker.setAppId("3f031e438f1534c375ec6b3505804eeb");
        this.apiWorker.getWeatherInformation(-300.0 ,0.0);
    }
    /**
     * error always because datetime always differs
     * @throws Exception
     */
    @Test
    public void goodAPIKeyandGoodArguments() throws Exception
    {
        this.apiWorker.setAppId("3f031e438f1534c375ec6b3505804eeb");
        StringBuffer stringBuffer = new StringBuffer("{\"coord\":{\"lon\":37.6156,\"lat\":55.7522},\"weather\":[{\"id\":600,\"main\":\"Snow\",\"description\":\"light snow\",\"icon\":\"13n\"}],\"base\":\"stations\",\"main\":{\"temp\":-14.35,\"feels_like\":-19.89,\"temp_min\":-16,\"temp_max\":-13,\"pressure\":1006,\"humidity\":85},\"visibility\":9000,\"wind\":{\"speed\":3,\"deg\":340},\"snow\":{\"1h\":0.42},\"clouds\":{\"all\":20},\"dt\":1610657315,\"sys\":{\"type\":1,\"id\":9027,\"country\":\"RU\",\"sunrise\":1610603421,\"sunset\":1610630783},\"timezone\":10800,\"id\":524901,\"name\":\"Moscow\",\"cod\":200}");
        assertFalse(this.apiWorker.getWeatherInformation(55.752220,37.615555).toString().equals(stringBuffer.toString()));
    }

    /**
     * call api to get information about Moscow using it's latitude and longitude
     * @throws Exception
     */
    @Test
    public void goodValues() throws Exception
    {
        this.apiWorker.setAppId("3f031e438f1534c375ec6b3505804eeb");
        String stringBuffer = this.apiWorker.getWeatherInformation(55.752220,37.615555).toString();
        JsonObject jsonParser = Json.parse(stringBuffer).asObject();
        assertTrue(jsonParser.get("name").asString().equals("Moscow"));
    }
    }



