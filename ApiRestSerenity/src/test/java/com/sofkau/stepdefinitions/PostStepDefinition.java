package com.sofkau.stepdefinitions;

import com.sofkau.models.Post;
import com.sofkau.models.Response;
import com.sofkau.setup.ApiSetUp;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import net.serenitybdd.rest.SerenityRest;
import org.apache.log4j.Logger;
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
        setUp(BASE_JSON_URL.getValue());
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
        }
    }

    @Then("me debe devolver el {int} del resultado exitoso")
    public void meDebeDevolverElCodigoDelResultadoExitoso(Integer codigo) {
        try {
            Response actualResponse = returnPostSuccessfulJsonResponse().answeredBy(actor);
            actor.should(
                    seeThatResponse("El codigo de respuesta es: " + codigo,
                            response -> response.statusCode(codigo)),
                    seeThat("Retorna información",
                            act -> actualResponse, notNullValue())
            );
            LOGGER.info("Nuevo post creado con código exitoso: " + codigo);
        } catch (Exception e) {
            LOGGER.error("Error al crear el nuevo post: " + e.getMessage());
        }
    }
}