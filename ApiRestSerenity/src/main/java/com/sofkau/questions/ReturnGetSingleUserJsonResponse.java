package com.sofkau.questions;

import com.sofkau.models.ResponseLogin;
import com.sofkau.models.getmodel.ResponseUser;
import net.serenitybdd.rest.SerenityRest;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Question;

public class ReturnGetSingleUserJsonResponse implements Question<ResponseUser> {
    @Override
    public ResponseUser answeredBy(Actor actor) {
        return SerenityRest.lastResponse().as(ResponseUser.class);
    }

    public static ReturnGetSingleUserJsonResponse returnGetSingleUserJsonResponse() {
        return new ReturnGetSingleUserJsonResponse();
    }
}
