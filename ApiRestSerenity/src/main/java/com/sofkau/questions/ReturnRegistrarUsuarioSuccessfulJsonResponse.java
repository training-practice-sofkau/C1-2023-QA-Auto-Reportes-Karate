package com.sofkau.questions;

import com.sofkau.models.RegistrarUsuario;
import net.serenitybdd.rest.SerenityRest;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Question;

public class ReturnRegistrarUsuarioSuccessfulJsonResponse implements Question<RegistrarUsuario> {
    @Override
    public RegistrarUsuario answeredBy(Actor actor) {
        return SerenityRest.lastResponse().as(RegistrarUsuario.class);
    }

    public static ReturnRegistrarUsuarioSuccessfulJsonResponse returnRegistrarUsuario(){
        return new ReturnRegistrarUsuarioSuccessfulJsonResponse();
    }
}
