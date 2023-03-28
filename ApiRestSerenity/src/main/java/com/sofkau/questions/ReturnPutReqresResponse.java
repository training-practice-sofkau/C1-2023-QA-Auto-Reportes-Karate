package com.sofkau.questions;

import com.sofkau.models.User;
import net.serenitybdd.rest.SerenityRest;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Question;

public class ReturnPutReqresResponse implements Question<User> {
    @Override
    public User answeredBy(Actor actor) {
        return SerenityRest.lastResponse().as(User.class);
    }

    public static ReturnPutReqresResponse returnPutReqresResponse(){
        return new ReturnPutReqresResponse();
    }
}