package com.sofkau.stepdefinitions;

import com.sofkau.setup.ApiSetUp;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.jupiter.api.Assertions;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import java.util.logging.Logger;
import io.restassured.response.Response;
import static com.sofkau.tasks.DoGet.doGet;
import static com.sofkau.utils.ReqresResources.REQRES_BASE_URL_BAYAS;
import static com.sofkau.utils.ReqresResources.RESOURCE_BAYAS;
import static net.serenitybdd.screenplay.GivenWhenThen.seeThat;
import static net.serenitybdd.screenplay.rest.questions.ResponseConsequence.seeThatResponse;
import static com.sofkau.questions.ReturnBayaJsonResponse.returnBayaJsonResponse;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.notNullValue;

public class BayaStepDefinition extends ApiSetUp {
    int codigoRespuesta;
    String nombre;
    JSONObject resBody = null;
    JSONParser parser = new JSONParser();
    JSONArray jsonArray = null;
    public static Response actualResponse;
    public static Logger LOGGER = Logger.getLogger(String.valueOf(BayaStepDefinition.class));
    @Given("que estoy apuntando con un endpoint a la api de bayas de la pokeapi")
    public void queEstoyApuntandoConUnEndpointALaApiDeBayasDeLaPokeapi() {
        setUp(REQRES_BASE_URL_BAYAS.getValue());
        LOGGER.info("Inicio de la automatizacion");
    }

    @When("envio la peticion get con el {string} de la baya")
    public void envioLaPeticionGetConElDeLaBaya(String id) {
        try{
            actor.attemptsTo(
                    doGet()
                            .withTheResource(RESOURCE_BAYAS.getValue()+id)
            );
        }catch (Exception e){
            LOGGER.info("FALLO EN AGREGANDO EL RECURSO");
            LOGGER.warning(e.getMessage());
        }
    }

    @Then("recibo un {int} de respuesta")
    public void reciboUnDeRespuesta(Integer codigo) {

        try{
            codigoRespuesta=codigo;
            actualResponse= returnBayaJsonResponse().answeredBy(actor);
            actor.should(
                    seeThatResponse("El codigo de respuesta es: "+ actualResponse.getStatusCode(),
                            code -> code.statusCode(codigo))
            );
        }catch (Exception e){
            LOGGER.info("FALLO COMPARANDO LOS CODIGOS DE RESPUESTA");
            Assertions.fail();
            LOGGER.warning(e.getMessage());
        }
    }

    @Then("la {string} acerca de la baya")
    public void laAcercaDeLaBaya(String informacion) {
      if(codigoRespuesta==200){
          try{
              LOGGER.info("Se revisa la respuesta de los codigos 200");
              resBody= (JSONObject) parser.parse(actualResponse.getBody().asString());

              nombre=resBody.get("name").toString();
              JSONArray flavors=(JSONArray) resBody.get("flavors");
              actor.should(

                      seeThat("Retorna informacion",
                              info-> actualResponse,notNullValue()),
                      seeThat("El nombre obtenido es",
                              nombres-> nombre, equalTo(informacion)),
                      seeThat("El tamanio total del json es",
                              tamanio-> resBody.size(), equalTo(12)),
                      seeThat("El tamanio de flavors es",
                              flav-> flavors.size(),equalTo(5))

              );
          }catch (Exception e){
              LOGGER.warning(e.getMessage());
              Assertions.fail();
          }
      }else if(codigoRespuesta==404){
          try{
              LOGGER.info("Se revisa la respuesta de los codigos 400");
              actor.should(
                      seeThat("Retorna informacion",
                              info-> actualResponse.getBody().asString(),equalTo(informacion))
              );

          }catch (Exception e){

              LOGGER.warning(e.getMessage());
              Assertions.fail();
          }
      }
      LOGGER.info("FIN DE LA AUTOMATIZACION");

    }
}
