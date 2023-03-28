package com.sofkau.stepdefinitions;

import com.sofkau.setup.ApiSetUp;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.response.Response;
import org.apache.log4j.Logger;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.junit.jupiter.api.Assertions;

import static com.sofkau.utils.ReqresResources.REQRES_BASE_URL;

public class PostCrearUsuario extends ApiSetUp {

    JSONParser parser = new JSONParser();
    JSONObject responseBody = null;
    private Response response;
    public static Logger LOGGER = Logger.getLogger(PostCrearUsuario.class);


    @Given("que el usuario se encuentra en la página crear usuario")
    public void queElUsuarioSeEncuentraEnLaPáginaCrearUsuario() {
        try {
            setUp(REQRES_BASE_URL.getValue());
            LOGGER.info("INICIA LA AUTOMATIZACION");
        } catch (Exception e) {
            LOGGER.info(" fallo la configuracion inicial");
            LOGGER.warn(e.getMessage());
            Assertions.fail();
        }

    }

    @When("el usuario envia la peticion de crea un nuevo usuario con {string}, {string}")
    public void elUsuarioEnviaLaPeticionDeCreaUnNuevoUsuarioCon(String nombre, String job) {

    }

    @Then("el usuario debria ver un mensaje con informacion del nuevoo usuario creado con un estatus {int}")
    public void elUsuarioDebriaVerUnMensajeConInformacionDelNuevooUsuarioCreadoConUnEstatus(Integer code) {

    }


}
