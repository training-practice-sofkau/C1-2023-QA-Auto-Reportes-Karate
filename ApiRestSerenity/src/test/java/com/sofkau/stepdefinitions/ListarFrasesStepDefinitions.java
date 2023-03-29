package com.sofkau.stepdefinitions;

import com.sofkau.setup.ApiSetUp;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import net.serenitybdd.rest.SerenityRest;
import org.apache.log4j.Logger;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import io.restassured.response.Response;
import org.junit.Assert;


import static com.sofkau.tasks.DoGet.doGet;
import static com.sofkau.utils.ReqresResources.*;

import static net.serenitybdd.rest.SerenityRest.lastResponse;
import static net.serenitybdd.screenplay.GivenWhenThen.seeThat;
import static net.serenitybdd.screenplay.rest.questions.ResponseConsequence.seeThatResponse;

public class ListarFrasesStepDefinitions extends ApiSetUp {
    private Response response;
    public static org.apache.log4j.Logger LOGGER = Logger.getLogger(String.valueOf(ListarFrasesStepDefinitions.class));

    @Given("que tengo acceso a la API dummyJSON")
    public void queTengoAccesoALaAPIDummyJSON() {
        try{
            setUp(LISTAR_FRASES_BASE_URL.getValue());
            LOGGER.info("inicio de la automatizacion " );

        } catch (Exception e){
            LOGGER.info("se ha producido un error" + e);
        }

    }

    @When("hago una peticion de tipo GET para obtener una frase con un {string}  especificado")
    public void hagoUnaPeticionDeTipoGETParaObtenerUnaFraseConUnEspecificado(String ID) {
        try{
            actor.attemptsTo(
                    doGet().withTheResource(RESOURCE_LISTAR_FRASES.getValue() + ID)
            );
            System.out.println(lastResponse().body().asString());

        } catch (Exception e){
            LOGGER.info("ocurrio un error haciendo la peticion" + e);
        }

    }

    @Then("se debe obtener un codigo de estado {int} y el JSON de respuesta correctamente")
    public void seDebeObtenerUnCodigoDeEstadoYElJSONDeRespuestaCorrectamente(Integer expectedStatusCode) {

        try {
            SerenityRest.lastResponse().then().statusCode(expectedStatusCode);
            LOGGER.info("//////Se ha validado correctamente el codigo de estado :" + expectedStatusCode+"///////");

            JSONObject jsonResponse = (JSONObject) new JSONParser().parse(SerenityRest.lastResponse().getBody().asString());
            Assert.assertTrue("La respuesta no contiene los campos esperados", jsonResponse.containsKey("id") && jsonResponse.containsKey("quote") && jsonResponse.containsKey("author"));

            Assert.assertNotNull("El valor del campo 'id' es nulo", jsonResponse.get("id"));
            Assert.assertNotNull("El valor del campo 'quote' es nulo", jsonResponse.get("quote"));
            Assert.assertNotNull("El valor del campo 'author' es nulo", jsonResponse.get("author"));

            LOGGER.info("//////La validacion del codigo de estado y el objeto JSON se ha completado correctamente///////");
        } catch (Exception e) {
            LOGGER.error("Ocurrió un error validando el código de estado o el objeto JSON de respuesta: " + e.getMessage());
            Assert.fail("Ocurrió un error validando el código de estado o el objeto JSON de respuesta: " + e.getMessage());
        }

    }


}
