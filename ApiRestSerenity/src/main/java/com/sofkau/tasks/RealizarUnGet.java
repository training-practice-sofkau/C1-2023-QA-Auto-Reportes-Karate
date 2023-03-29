package com.sofkau.tasks;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.rest.interactions.Get;
import net.serenitybdd.screenplay.rest.interactions.Post;

import static com.sofkau.utils.CovidTest.COVID_BASE_PAHT;
import static com.sofkau.utils.CovidTest.COVID_BASE_PAHT2;
import static javax.swing.text.DefaultStyledDocument.ElementSpec.ContentType;

public class RealizarUnGet implements Task {
    String resource;
    public RealizarUnGet conElRecurso(String resource){
        this.resource=resource;
        return this;
    }
    @Override
    public <T extends Actor> void performAs(T actor) {
        actor.attemptsTo(
                Get.resource(COVID_BASE_PAHT.getValue() + resource + COVID_BASE_PAHT2.getValue())
        );
    }
    public static  RealizarUnGet realizarUnGet(){
        return new RealizarUnGet();
    }
}