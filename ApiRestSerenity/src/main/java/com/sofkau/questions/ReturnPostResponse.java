package com.sofkau.questions;

import com.sofkau.models.Auth;
import net.serenitybdd.rest.SerenityRest;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Question;

public class ReturnPostResponse implements Question<Auth> {
    @Override
    public Auth answeredBy(Actor actor) {
        return SerenityRest.lastResponse().as(Auth.class);
    }

    public static ReturnPostResponse returnPostResponse() {
        return new ReturnPostResponse();
    }
}
