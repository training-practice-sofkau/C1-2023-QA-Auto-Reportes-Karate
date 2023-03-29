package com.sofkau.tasks;

import io.restassured.http.ContentType;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.rest.interactions.Post;

public class CreateUser implements Task {
    private String resource;
    private String requestBody;

    public CreateUser inTheResource(String resource) {
        this.resource = resource;
        return this;
    }

    public CreateUser andTheBody(String requestBody) {
        this.requestBody = requestBody;
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

    public static CreateUser createUser() {
        return new CreateUser();
    }
}