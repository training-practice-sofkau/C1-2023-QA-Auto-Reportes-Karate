package com.sofkau.stepdefinitions;

import com.sofkau.setup.ApiSetUp;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.response.Response;
import net.serenitybdd.rest.SerenityRest;
import org.apache.log4j.Logger;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.junit.jupiter.api.Assertions;

import static com.sofkau.questions.ReturnGetAnimeListJinkan.returnGetAnimeListJinkan;
import static com.sofkau.tasks.DoGet.doGet;
import static com.sofkau.utils.JikanAnimeResources.ANIME_JINKAN_BASE_URL;
import static com.sofkau.utils.JikanAnimeResources.ANIME_JINKAN_RESOURCE;
import static net.serenitybdd.rest.SerenityRest.lastResponse;
import static net.serenitybdd.screenplay.GivenWhenThen.seeThat;
import static net.serenitybdd.screenplay.rest.questions.ResponseConsequence.seeThatResponse;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.core.IsEqual.equalTo;

public class ObtenerAnimePorIDJinkanStepDefinition  extends ApiSetUp {

    private static final Logger LOGGER = Logger.getLogger(ObtenerAnimePorIDJinkanStepDefinition.class);
     JSONArray responseBody = null;
    JSONObject firstResponse = null;
    JSONParser parser = new JSONParser();


    @Given("que el usuario esta usando la API de Anime Jikan")
    public void que_el_usuario_esta_usando_la_API_de_Anime_Jikan() {

        try {
            setUp(ANIME_JINKAN_BASE_URL.getValue());
            LOGGER.info("INICIA LA AUTOMATIZACION");
        } catch (Exception e) {
            LOGGER.info(" fallo la configuracion inicial");
            LOGGER.warn(e.getMessage());
            Assertions.fail();
        }
    }


    @When("se envia una peticion para obtener un anime por su {string}")
    public void se_envia_una_peticion_para_obtener_un_anime_por_su(String id) {

        try {

            actor.attemptsTo(
                    doGet()
                            .withTheResource((ANIME_JINKAN_RESOURCE.getValue()+id))
            );
//            System.out.println(SerenityRest.lastResponse().body().asString());


            LOGGER.info("Realiza la peticion");
        } catch (Exception e) {
            LOGGER.info(" fallo al momento de realizar la peticion");
            LOGGER.warn(e.getMessage());
            Assertions.fail();
        }

    }


    @Then("se deberia observar el {int}  junto con la  {string} del anime y el {string} del anime")
    public void se_deberia_observar_el_junto_con_la_del_anime_y_el_del_anime(Integer statusCode, String url, String idAnime) {

        try {
            Response respuestaActual =  returnGetAnimeListJinkan().answeredBy(actor);
            //responseBody = (JSONObject) parser.parse(respuestaActual.getBody().asString());
           // responseBody = (JSONObject) parser.parse(lastResponse().asString());


            Response actualResponse = returnGetAnimeListJinkan().answeredBy(actor);
           // responseBody = (JSONObject) parser.parse(actualResponse.getBody().asString());

            actor.should(
                    seeThatResponse("El codigo de respuesta deberia ser: " + statusCode,
                            response -> response.statusCode(statusCode))

            );

        responseBody = (JSONArray) parser.parse(respuestaActual.getBody().asString());
        firstResponse = (JSONObject) responseBody.get(1);

            actor.should(
                    seeThat("El id de respuesta deberia ser: " ,
                            compararId -> firstResponse.get("mal_id").toString(), equalTo(idAnime))

            );




            //actor.should(
              //      seeThat("Comparar Id esperado",
                //            compararId ->firstResponse.get ("mal_id").toString(),equalTo(idAnime) ));

            //actor.should(
           //         seeThat("Comparar url esperada",
              //              compararUrl ->firstResponse.get ("url").toString(),equalTo(url) ));

            //LOGGER.info(firstResponse.get ("mal_id").toString());
           // LOGGER.info(firstResponse.get ("url").toString());

          //  String actualTitle = (String) responseBody.get("mal_id");

            //Assertions.assertEquals(idAnime,actualTitle);

        }catch (Exception e){
            LOGGER.warn(e.getMessage());
        }

    }




}
