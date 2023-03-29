package com.sofkau.interactions;

import com.sofkau.tasks.RealizarUnGet;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.rest.interactions.RestInteraction;
import net.thucydides.core.annotations.Step;

import static net.serenitybdd.screenplay.rest.abilities.CallAnApi.as;
public class PeticionGet extends RestInteraction {
    private final String resource;

    public PeticionGet(String resource) {
        this.resource = resource;
    }



    @Step("{0} executes a GET on the resource #resource")
    @Override
    public <T extends Actor> void performAs(T actor) {
        rest().log().all().get(as(actor).resolve(resource)).then().log().all();
    }

}
