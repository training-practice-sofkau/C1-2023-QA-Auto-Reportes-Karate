package com.sofkau.questions;

import com.sofkau.models.RegistrarUsuario;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Question;

public class ReturnRegistrarUsuarioSuccessfulJsonResponse implements Question<RegistrarUsuario> {
    @Override
    public RegistrarUsuario answeredBy(Actor actor) {
        return null;
    }

    public static ReturnRegistrarUsuarioSuccessfulJsonResponse returnRegistrarUsuario(){
        return new ReturnRegistrarUsuarioSuccessfulJsonResponse();
    }
}
