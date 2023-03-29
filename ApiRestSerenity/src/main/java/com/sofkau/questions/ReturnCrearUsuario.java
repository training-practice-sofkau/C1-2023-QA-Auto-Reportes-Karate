package com.sofkau.questions;
import com.sofkau.models.ResponseCrearUsuario;
import com.sofkau.models.UsuarioResponse;
import net.serenitybdd.rest.SerenityRest;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Question;

public class ReturnCrearUsuario implements Question<ResponseCrearUsuario>{



    @Override
    public ResponseCrearUsuario answeredBy(Actor actor) {

        return SerenityRest.lastResponse().as(ResponseCrearUsuario.class);
    }

    public static ReturnCrearUsuario returnCrearUsuario() {
        return new ReturnCrearUsuario();
    }

}
