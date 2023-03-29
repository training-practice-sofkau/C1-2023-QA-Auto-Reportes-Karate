package com.sofkau.tasks;

import io.restassured.http.ContentType;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.rest.interactions.Get;


public class DoGet implements Task {
    private String resource;
    private Object requestBody;

    public DoGet withTheResource(String resource){
        this.resource=resource;
        return this;
    }

    public DoGet andTheRequestBody(Object requestBody){
        this.requestBody=requestBody;
        return this;
    }

    @Override
    public <T extends Actor> void performAs(T actor) {
        actor.attemptsTo(
                Get.resource(resource)
        );
    }
    public static DoGet doGet(){
        return new DoGet();
    }
}
