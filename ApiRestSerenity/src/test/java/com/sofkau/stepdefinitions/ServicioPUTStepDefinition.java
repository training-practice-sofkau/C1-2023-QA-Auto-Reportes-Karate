package com.sofkau.stepdefinitions;


import com.sofkau.models.User;
import com.sofkau.setup.ApiSetUp;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.response.Response;
import org.apache.http.HttpStatus;
import org.apache.log4j.Logger;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.junit.jupiter.api.Assertions;


import static com.sofkau.questions.ReturnGetResponse.returnGetResponse;
import static com.sofkau.tasks.DoPut.doPut;
import static com.sofkau.utils.ReqresResources.DUMMYJSON_BASE_URL;
import static com.sofkau.utils.ReqresResources.DUMMYJSON_PUT_RESOURCE;
import static net.serenitybdd.screenplay.GivenWhenThen.seeThat;
import static net.serenitybdd.screenplay.rest.questions.ResponseConsequence.seeThatResponse;
import static org.hamcrest.CoreMatchers.notNullValue;



public class ServicioPUTStepDefinition extends ApiSetUp {
    JSONObject responseBody = null;
    JSONParser parser = new JSONParser();
    public static Logger LOGGER= Logger.getLogger(ServicioPUTStepDefinition.class);
    User user = new User();

    @Given("que estoy en la pagina principal de dummyjson")
    public void queEstoyEnLaPaginaPrincipalDeDummyjson() {
        try {
            setUp(DUMMYJSON_BASE_URL.getValue());
            LOGGER.info("Empezando peticion");
        }catch (Exception e){
            LOGGER.warn(e.getMessage());
        }

    }

    @When("envio una solicitud para actualizar al usuario con ID {string}, con el nombre {string}, apellido {string}, correo electronico {string} y edad {int}")
    public void envioUnaSolicitudParaActualizarAlUsuarioConIDConElNombreApellidoCorreoElectronicoYEdad(String  userId, String firstName, String lastName, String email, Integer age) {
        try {
            user.setFirstName(firstName);
            user.setLastName(lastName);
          user.setEmail(email);
            user.setAge(age);

            actor.attemptsTo(
                    doPut()
                            .withTheResource(DUMMYJSON_PUT_RESOURCE.getValue() + userId)
                            .andTheRequestBody(user)
            );
            LOGGER.info("Peticion realizada");
       }catch (Exception e){
            LOGGER.warn(e.getMessage());
        }
    }

    @Then("deberia recibir una respuesta con el codigo de estado {int} y su json de respuesta actualizado")
    public void deberiaRecibirUnaRespuestaConElCodigoDeEstadoYSuJsonDeRespuestaActualizado(Integer code) {
        try {
            Response actualResponse = returnGetResponse().answeredBy(actor);
            responseBody = (JSONObject) parser.parse(actualResponse.getBody().asString());
            actor.should(
                    seeThatResponse("El codigo de respuesta es: " + HttpStatus.SC_OK,
                            response -> response.statusCode(code))
            );

            actor.should(
                    seeThat("El campo 'id' no es nulo", response -> responseBody.get("id"), notNullValue()),
                    seeThat("El campo 'firstName' no es nulo", response -> responseBody.get("firstName"), notNullValue()),
                    seeThat("El campo 'age' no es nulo", response -> responseBody.get("age"), notNullValue())
            );

            LOGGER.info(" Asercion exitosa");
        } catch (Exception e){
            LOGGER.warn(e.getMessage());
        }


    }

    @When("envia una solicitud para actualizar al usuario con ID incorrecto {string}, con el nombre {string}, apellido {string}, correo electronico {string} y edad {int}")
    public void enviaUnaSolicitudParaActualizarAlUsuarioConIDIncorrectoConElNombreApellidoCorreoElectronicoYEdad(String id, String firstname, String lastname, String correo, Integer edad) {

        try {
            user.setFirstName(firstname);
            user.setLastName(lastname);
            user.setEmail(correo);
            user.setAge(edad);

            actor.attemptsTo(
                    doPut()
                            .withTheResource(DUMMYJSON_PUT_RESOURCE.getValue() + id)
                            .andTheRequestBody(user)
            );
            LOGGER.info(" Peticion realizada");
        }catch (Exception e){
            LOGGER.warn(e.getMessage());
        }
    }

    @Then("deberia recibir una respuesta con el codigo de estado {int} y un mensaje de error")
    public void deberiaRecibirUnaRespuestaConElCodigoDeEstadoYUnMensajeDeError(Integer code) {
        try {
            Response actualResponse = returnGetResponse().answeredBy(actor);
            responseBody = (JSONObject) parser.parse(actualResponse.getBody().asString());
            actor.should(
                    seeThatResponse("El codigo de respuesta es: " + HttpStatus.SC_NOT_FOUND,
                            response -> response.statusCode(code))
            );

            LOGGER.info("  CUMPLE EL CODIGO DE ESTADO");
        } catch (Exception e){
            LOGGER.info("  Error al realizar la comparacion");
            LOGGER.warn(e.getMessage());
            Assertions.fail();
        }
    }


}
