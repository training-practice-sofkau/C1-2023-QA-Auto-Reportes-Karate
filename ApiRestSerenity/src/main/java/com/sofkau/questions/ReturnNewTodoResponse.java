package com.sofkau.questions;

import com.sofkau.models.Tarea;
import com.sofkau.models.UpdateTareaResponse;
import net.serenitybdd.rest.SerenityRest;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Question;
import org.openqa.selenium.remote.Response;

public class ReturnNewTodoResponse implements Question<UpdateTareaResponse> {

    @Override
    public UpdateTareaResponse answeredBy(Actor actor) {
        return SerenityRest.lastResponse().as(UpdateTareaResponse.class);
    }
    public static ReturnNewTodoResponse returnNewTodoResponse(){
        return new ReturnNewTodoResponse();
    }
}
