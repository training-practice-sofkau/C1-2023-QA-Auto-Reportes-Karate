package com.sofkau.tasks;

import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.rest.interactions.Get;

public class DoGetPokemon implements Task {
    private String resource;
    private String pokemon;

    public DoGetPokemon conElRecurso(String resource){
        this.resource = resource;
        return this;
    }
    public DoGetPokemon yConElPokemon(String pokemon){
        this.pokemon = pokemon;
        return this;
    }
    @Override
    public <T extends Actor> void performAs(T actor) {
        actor.attemptsTo(
                Get.resource(resource+pokemon)
        );
    }
    public static DoGetPokemon doGetPokemon(){
        return new DoGetPokemon();
    }





}