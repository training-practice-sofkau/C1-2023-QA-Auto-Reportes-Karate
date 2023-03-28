package com.sofkau.questions;

import com.sofkau.models.GetUser;
import net.serenitybdd.rest.SerenityRest;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Question;

public class ReturnGetReqresResponse implements Question<GetUser> {
    @Override
    public GetUser answeredBy(Actor actor) {
        return SerenityRest.lastResponse().as(GetUser.class);
    }

    public static ReturnGetReqresResponse returnGetReqresResponse(){
        return new ReturnGetReqresResponse();
    }
}