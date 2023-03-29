package com.sofkau.questions;

import net.serenitybdd.rest.SerenityRest;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Question;

public class RetornarJsonCovid implements Question<Integer> {
    @Override
    public Integer answeredBy(Actor actor) {
        return  SerenityRest.lastResponse().statusCode();
    }
    public static RetornarJsonCovid retornarJsonCovid(){
        return new RetornarJsonCovid();
    }
}
