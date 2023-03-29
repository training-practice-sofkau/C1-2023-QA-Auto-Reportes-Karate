package com.sofkau.stepdefinitions;
import com.sofkau.setup.ApiSetUp;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import net.serenitybdd.rest.SerenityRest;
import org.apache.log4j.Logger;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.junit.Assert;

import java.util.List;

import static com.sofkau.tasks.DoGet.doGet;
import static com.sofkau.utils.ReqresResources.*;

public class ListarProductosStepDefinition extends ApiSetUp {
    public static org.apache.log4j.Logger LOGGER = Logger.getLogger(String.valueOf(ListarProductosStepDefinition.class));

    @Given("que tengo acceso a la API Fake Store")
    public void queTengoAccesoALaAPIFakeStore() {
        try{
            setUp(LISTAR_PRODUCTOS_BASE_URL.getValue());
            LOGGER.info("inicio de la automatizacion " );

        } catch (Exception e){
            LOGGER.info("se ha producido un error" + e);
        }

    }

    @When("hago una peticion de tipo GET para obtener la lista con un {string}  de productos")
    public void hagoUnaPeticionDeTipoGETParaObtenerLaListaConUnDeProductos(String limite) {
        try{
            actor.attemptsTo(
                    doGet().withTheResource(RESOURCE_LISTAR_PRODUCTOS.getValue() + limite)
            );
            System.out.println(SerenityRest.lastResponse().body().asString());

        } catch (Exception e){
            LOGGER.info("ocurrio un error haciendo la peticion" + e);
        }

    }

    @Then("debo obtener un codigo de estado {int} y el JSON de respuesta correctamente")
    public void deboObtenerUnCodigoDeEstadoYElJSONDeRespuestaCorrectamente(Integer expectedStatusCode) {

        try {
            SerenityRest.lastResponse().then().statusCode(expectedStatusCode);
            LOGGER.info("/////////////Se ha validado correctamente el codigo de estado ////////////// :"+ expectedStatusCode);

            // Parsear el JSON de respuesta
            String responseBody = SerenityRest.lastResponse().getBody().asString();
            JSONParser parser = new JSONParser();
            JSONArray jsonArray = (JSONArray) parser.parse(responseBody);

            // Validar que el campo "id" no sea nulo
            for (Object obj : jsonArray) {
                JSONObject jsonObj = (JSONObject) obj;
                Object idObj = jsonObj.get("id");

                Assert.assertNotNull("El campo 'id'  no deberia ser nulo", idObj);
                if (idObj != null) {
                    String id = idObj.toString();
                    LOGGER.info("El id " + id + " no es nulo");
                } else {
                    LOGGER.warn("El campo 'id' es nulo");
                }

            }

            // Validar que el precio sea > 0
            for (Object obj : jsonArray) {
                JSONObject jsonObj = (JSONObject) obj;
                Float price = Float.parseFloat(jsonObj.get("price").toString());
                boolean isPriceValid = price > 0;

                Assert.assertTrue("El precio deberia ser mayor que cero", isPriceValid);

                if (!isPriceValid) {
                    LOGGER.warn("El precio " + price + " debe ser mayor que cero");
                } else {
                    LOGGER.info("El precio " + price + " si es valido");
                }
            }

        } catch (Exception e) {
            LOGGER.error("Ha ocurrido un error: " + e.getMessage());
        }

    }


}
