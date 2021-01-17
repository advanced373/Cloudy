import javafx.beans.property.*;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import ro.mta.facc.selab.mihaiapp.controllers.MainController;
import ro.mta.facc.selab.mihaiapp.helpers.APIWorker;
import ro.mta.facc.selab.mihaiapp.helpers.FileLine;
import ro.mta.facc.selab.mihaiapp.helpers.JsonInformation;
import ro.mta.facc.selab.mihaiapp.helpers.JsonParserWorker;
import ro.mta.facc.selab.mihaiapp.model.City;

import java.io.IOException;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.*;
/**
 * author: Stoica Mihai
 * test class for MainController using Mocking
 */
@RunWith(MockitoJUnitRunner.class)
public class TestMainController {
    APIWorker apiWorker;
    JsonParserWorker jsonParserWorker;
    MainController mainController;
    @Before
    public void setUp() throws Exception
    {
        jsonParserWorker= mock(JsonParserWorker.class);
        apiWorker = mock(APIWorker.class);
        mainController = new MainController(null,null); // because cannot mock ComboBox -> error
        mainController.setApiWorker(apiWorker);
        mainController.setJsonParserWorker(jsonParserWorker);
    }

    /**
     * verify mocks initialization
     */
    @Test
    public void verify() {
        assertTrue(mainController.getApiWorker() != null);
        assertTrue(mainController.getJsonParserWorker() != null);
    }
    /**
     *
     * @throws Exception
     */
    @Test
    public void verifyGetInformations() throws Exception {
        try {
            FileLine fileLine = new FileLine( "Razvilka",55.591667 ,37.740833, "Rusia",819827);
            apiWorker.setAppId("3f031e438f1534c375ec6b3505804eeb");
            when(apiWorker.getWeatherInformation(55.591667 ,37.740833)).thenReturn(null);
            JsonInformation jsonInformation = new JsonInformation(new SimpleIntegerProperty(84),new SimpleIntegerProperty(1016),new SimpleDoubleProperty(-22.01),new SimpleDoubleProperty(1),new SimpleStringProperty("Clouds"));
            Mockito.verify(((mainController.getInformation(fileLine)).toString()).equals(jsonInformation.toString()));
        }
        catch(Exception e)
        {
            System.out.println(e.getMessage());
        }
    }

    /**
     * test mocks call
     * @throws Exception
     */
    @Test
    public void verifyCalls() throws Exception {
        FileLine fileLine = new FileLine( "Razvilka",55.591667 ,37.740833, "Rusia",819827);
        when(apiWorker.getWeatherInformation(55.591667 ,37.740833)).thenReturn(null);
        mainController.getInformation(fileLine);
        Mockito.verify(apiWorker, atLeastOnce()).getWeatherInformation(55.591667 ,37.740833);
        Mockito.verify(jsonParserWorker, atLeastOnce()).parse(null);
    }

}
