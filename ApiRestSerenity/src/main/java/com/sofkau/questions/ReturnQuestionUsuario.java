package com.sofkau.questions;

import com.sofkau.models.Usuario;
import net.serenitybdd.rest.SerenityRest;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Question;

public class ReturnQuestionUsuario implements Question<Usuario> {

    @Override
    public Usuario answeredBy(Actor actor) {

        return SerenityRest.lastResponse().as(Usuario.class);
    }

    public static ReturnQuestionUsuario returnQuestionUsuario() {
        return new ReturnQuestionUsuario();
    }
}
