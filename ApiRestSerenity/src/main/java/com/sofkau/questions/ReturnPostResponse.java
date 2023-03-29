package com.sofkau.questions;
import com.sofkau.models.PostModel;
import net.serenitybdd.rest.SerenityRest;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Question;

public class ReturnPostResponse implements Question<PostModel> {
    @Override
    public PostModel answeredBy(Actor actor) {
        return SerenityRest.lastResponse().as(PostModel.class);
    }

    public static ReturnPostResponse returnPostResponse() {
        return new ReturnPostResponse();
    }
}
