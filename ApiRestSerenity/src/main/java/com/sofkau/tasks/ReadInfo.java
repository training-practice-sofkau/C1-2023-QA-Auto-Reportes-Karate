package com.sofkau.tasks;

import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.rest.interactions.Get;

public class ReadInfo implements Task {
    private String resource;

    public ReadInfo ofResource(String resource) {
        this.resource = resource;
        return this;
    }

    @Override
    public <T extends Actor> void performAs(T actor) {
        actor.attemptsTo(
                Get.resource(resource)
        );
    }

    public static ReadInfo readInfo() {
        return new ReadInfo();
    }
}