package com.sofkau.stepdefinitions;

import com.sofkau.models.Post;
import com.sofkau.models.Response;
import com.sofkau.setup.ApiSetUp;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import net.serenitybdd.rest.SerenityRest;
import net.serenitybdd.screenplay.ensure.Ensure;
import org.apache.log4j.Logger;
import org.junit.jupiter.api.Assertions;
import static com.sofkau.questions.ReturnPostSuccessfulJsonResponse.returnPostSuccessfulJsonResponse;
import static com.sofkau.tasks.DoPost.doPost;
import static com.sofkau.utils.ReqresResources.BASE_JSON_URL;
import static com.sofkau.utils.ReqresResources.POST_RESOURCE;
import static net.serenitybdd.screenplay.GivenWhenThen.seeThat;
import static net.serenitybdd.screenplay.rest.questions.ResponseConsequence.seeThatResponse;
import static org.hamcrest.Matchers.notNullValue;

public class PostStepDefinition extends ApiSetUp {
    Post post = new Post();

    private Logger LOGGER = Logger.getLogger(PostStepDefinition.class);

    @Given("estoy en la pagina de post de jsonplaceholder")
    public void estoyEnLaPaginaDePostDeJsonplaceholder() {
        try {
            setUp(BASE_JSON_URL.getValue());
            LOGGER.info("INICIA LA AUTOMATIZACION");
        } catch (Exception e) {
            LOGGER.info("Fallo la configuracion inicial");
            LOGGER.warn(e.getMessage());
            Assertions.fail();
        }
    }

    @When("creo un post con la informacion {int}, {int}, {string}, {string}")
    public void creoUnPostConLaInformacion(Integer userId, Integer id, String title, String body) {
        try {
            post.setUserId(userId);
            post.setId(id);
            post.setTitle(title);
            post.setBody(body);
            actor.attemptsTo(
                    doPost()
                            .withResource(POST_RESOURCE.getValue())
                            .andRequestBody(post)
            );
            LOGGER.info("Response code: " + SerenityRest.lastResponse().getStatusCode());
            LOGGER.info("Response body: " + SerenityRest.lastResponse().asString());
        } catch (Exception e) {
            LOGGER.error("Error al crear el nuevo post: " + e.getMessage());
            Assertions.fail();
        }
    }

    @Then("me debe devolver el {int} de respuesta y el {string} creado anteriormente")
    public void meDebeDevolverElDeRespuestaYElCreadoAnteriormente(Integer codigo, String title) {
        try {
            Response actualResponse = returnPostSuccessfulJsonResponse().answeredBy(actor);
            actor.should(
                    seeThatResponse("El codigo de respuesta es: " + codigo,
                            response -> response.statusCode(codigo)),
                    seeThat("Retorna información",
                            act -> actualResponse, notNullValue())
            );
            actor.attemptsTo(
                    Ensure.that(actualResponse.getTitle()).isEqualTo(title)
            );
            LOGGER.info("Nuevo post creado con codigo exitoso: " + codigo);
        } catch (Exception e) {
            LOGGER.error("Error al crear el nuevo post: " + e.getMessage());
            Assertions.fail();
        }
    }
}