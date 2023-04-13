package com.sofkau.questions;

import net.serenitybdd.rest.SerenityRest;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Question;
import com.sofkau.models.ResponseUpdateUser;

public class ReturnUpdateJsonResponse implements Question<ResponseUpdateUser> {
    @Override
    public ResponseUpdateUser answeredBy(Actor actor) {
        return SerenityRest.lastResponse().as(ResponseUpdateUser.class);
    }

    public static ReturnUpdateJsonResponse returnUpdateJsonResponse(){
        return new ReturnUpdateJsonResponse();
    }
}
