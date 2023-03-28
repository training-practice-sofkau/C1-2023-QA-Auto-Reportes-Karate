package com.sofkau.tasks;

import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.rest.interactions.Get;
public class DoGet implements Task{
    private String numero;
    private String resource;
    public DoGet withTheResource(String resource){
        this.resource=resource;
        return this;
    }
    public DoGet andWithTheNumber(String numero){
        this.numero=numero;
        return this;
    }
    @Override
    public <T extends Actor> void performAs(T actor) {
        actor.attemptsTo(
                Get.resource(resource+numero)
        );
    }
    public static DoGet doGet(){
        return new DoGet();
    }
}
