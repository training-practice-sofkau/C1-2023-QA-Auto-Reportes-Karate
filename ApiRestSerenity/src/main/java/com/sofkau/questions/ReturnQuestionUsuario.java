package com.sofkau.questions;

import com.sofkau.models.UsuarioResponse;
import net.serenitybdd.rest.SerenityRest;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Question;

public class ReturnQuestionUsuario implements Question<UsuarioResponse> {

    @Override
    public UsuarioResponse answeredBy(Actor actor) {

        return SerenityRest.lastResponse().as(UsuarioResponse.class);
    }

    public static ReturnQuestionUsuario returnQuestionUsuario() {
        return new ReturnQuestionUsuario();
    }
}
