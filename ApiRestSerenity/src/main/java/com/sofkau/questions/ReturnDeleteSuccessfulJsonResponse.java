package com.sofkau.questions;

import com.sofkau.models.Response;
import net.serenitybdd.rest.SerenityRest;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Question;

public class ReturnDeleteSuccessfulJsonResponse implements Question<Response> {
    @Override
    public Response answeredBy(Actor actor) {
        return (Response) SerenityRest.lastResponse();
    }

    public static ReturnDeleteSuccessfulJsonResponse returnDeleteSuccessfulJsonResponse() {
        return new ReturnDeleteSuccessfulJsonResponse();
    }

}
