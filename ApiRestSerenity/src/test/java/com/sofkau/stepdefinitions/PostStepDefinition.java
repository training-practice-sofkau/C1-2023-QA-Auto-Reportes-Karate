package com.sofkau.stepdefinitions;

import com.sofkau.models.Post;
import com.sofkau.models.Response;
import com.sofkau.setup.ApiSetUp;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import net.serenitybdd.rest.SerenityRest;
import net.serenitybdd.screenplay.ensure.Ensure;
import org.apache.http.HttpStatus;
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

    @When("creo un post con la informacion {string}, {string}, {int}")
    public void creoUnPostConLaInformacion(String titulo, String cuerpo, Integer id) {
        try {
            post.setTitle(titulo);
            post.setBody(cuerpo);
            actor.attemptsTo(
                    doPost().withResource(POST_RESOURCE.getValue() + id)
                            .andRequestBody(post)
            );
            LOGGER.info("Response code: " + SerenityRest.lastResponse().getStatusCode());
            LOGGER.info("Response body: " + SerenityRest.lastResponse().asString());
        } catch (Exception e) {
            LOGGER.error("Error new post: " + e.getMessage());
        }
    }

    @Then("me debe devolver el post creado con {int} y {string} nuevo")
    public void meDebeDevolverElPostCreadoConYNuevo(Integer code, String titulo) {
        try {
            Response actualResponse = returnPostSuccessfulJsonResponse().answeredBy(actor);
            actor.should(
                    seeThatResponse("El codigo de respuesta es: " + HttpStatus.SC_OK,
                            response -> response.statusCode(code)),
                    seeThat("Retorna información",
                            act -> actualResponse, notNullValue())
            );
            actor.attemptsTo(
                    Ensure.that(actualResponse.getTitle()).isEqualTo(titulo)
            );
            LOGGER.info("new post title: " + actualResponse.getTitle());
            LOGGER.info("new post body: " + actualResponse.getBody());
        } catch (Exception e) {
            LOGGER.error("Error creando el nuevo post: " + e.getMessage());
        }
    }
}
