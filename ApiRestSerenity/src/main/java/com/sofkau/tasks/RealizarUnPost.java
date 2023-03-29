package com.sofkau.tasks;


import io.restassured.http.ContentType;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.rest.interactions.Post;

public class RealizarUnPost implements Task {

    private String resource;
    private Object requestBody;



    public RealizarUnPost withTheResource(String resource){
        this.resource=resource;
        return this;
    }

    public RealizarUnPost andTheRequestBody(Object requestBody){
        this.requestBody=requestBody;
        return this;
    }

    @Override
    public <T extends Actor> void performAs(T actor) {
        actor.attemptsTo(
                Post.to(resource)
                        .with(
                                requestSpecification -> requestSpecification.relaxedHTTPSValidation()
                                        .contentType(ContentType.JSON)
                                        .body(requestBody)
                        )
        );
    }

    public static RealizarUnPost realizarUnPost(){
        return new RealizarUnPost();
    }
}
