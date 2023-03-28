package com.sofkau.setup;

import io.restassured.RestAssured;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.rest.abilities.CallAnApi;
import org.apache.log4j.PropertyConfigurator;

import static com.sofkau.utils.Log4jValues.LOG4J_PROPERTIES_FILE_PATH;

public class ApiSetUp {
    protected Actor actor = new Actor("Juanes");

    private static final String BASE_URI = "https://jsonplaceholder.typicode.com";

    protected void setUp(String urlBase){
       setUpLog4j();
        //BasicConfigurator.configure();
        actorCallAnApi(urlBase);
    }

    protected void generalSetUp(){
        //setUpLog4j2();
        setUpBases();
    }

    private void actorCallAnApi(String urlBase){
        actor.can(CallAnApi.at(urlBase));
    }
    private void actorCanCallAnApi(String urlBase){
        actor.can(CallAnApi.at(urlBase));
    }

    private void setUpLog4j(){
        PropertyConfigurator.configure(LOG4J_PROPERTIES_FILE_PATH.getValue());
    }

    private void setUpBases(){
        RestAssured.baseURI = BASE_URI;
    }

    private void actorCanCallApi(String urlBase) {
        actor.can(CallAnApi.at(urlBase));
    }
}












