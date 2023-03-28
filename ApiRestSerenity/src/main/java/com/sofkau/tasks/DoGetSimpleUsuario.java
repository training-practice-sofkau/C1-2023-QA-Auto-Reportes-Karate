package com.sofkau.tasks;

import com.sofkau.interactions.OurGet;
import io.restassured.http.ContentType;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.rest.interactions.Get;


public class DoGetSimpleUsuario implements Task {


    private String resource;
    private Object requestBody;

    public DoGetSimpleUsuario withTheResource(String resource) {
        this.resource = resource;
        return this;
    }

    public DoGetSimpleUsuario andTheRequestBody(Object requestBody) {
        this.requestBody = requestBody;
        return this;
    }

    @Override
    public <T extends Actor> void performAs(T actor) {
        actor.attemptsTo(
                OurGet.to(resource)
                        .with(
                                requestSpecification -> requestSpecification.relaxedHTTPSValidation()
                                        .contentType(ContentType.JSON)
                        )

        );
    }

    public static DoGetSimpleUsuario doGetSimpleUsuario() {
        return new DoGetSimpleUsuario();
    }

}
