package com.sofkau.tasks;

import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.rest.interactions.Get;


public class DoGet implements Task {

    private String resource;

    public DoGet withTheResource(String resource) {
        this.resource = resource;
        return this;
    }

    @Override
    public <T extends Actor> void performAs(T actor) {
        actor.attemptsTo(
                Get.resource(resource)
                        .with(
                                requestSpecification -> requestSpecification.relaxedHTTPSValidation()

                        )
        );
    }

    public static DoGet doGet() {
        return new DoGet();
    }
}
