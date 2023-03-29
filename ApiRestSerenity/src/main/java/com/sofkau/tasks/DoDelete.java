package com.sofkau.tasks;

import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.rest.interactions.Delete;
import net.serenitybdd.screenplay.rest.interactions.Get;

public class DoDelete implements Task {
    private String numero;
    private String resource;
    public DoDelete withTheResource(String resource){
        this.resource=resource;
        return this;
    }
    public DoDelete andWithTheNumber(String numero){
        this.numero=numero;
        return this;
    }
    @Override
    public <T extends Actor> void performAs(T actor) {
        actor.attemptsTo(
                Delete.from(resource+numero)
        );
    }
    public static DoDelete doDelete(){
        return new DoDelete();
    }
}
