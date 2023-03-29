package com.sofkau.questions;

import com.sofkau.models.ResponseGet;
import net.serenitybdd.rest.SerenityRest;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Question;

public class ReturnGetReqresResponse implements Question<ResponseGet> {
    @Override
    public ResponseGet answeredBy(Actor actor) {
        return SerenityRest.lastResponse().as(ResponseGet.class);
    }

    public static ReturnGetReqresResponse returnGetReqresResponse(){
        return new ReturnGetReqresResponse();
    }
}