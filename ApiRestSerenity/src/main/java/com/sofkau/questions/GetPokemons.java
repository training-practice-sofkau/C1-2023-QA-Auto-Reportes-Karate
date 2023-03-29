package com.sofkau.questions;

import com.sofkau.models.Pokemon;
import net.serenitybdd.rest.SerenityRest;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Question;

public class GetPokemons implements Question<Pokemon>{
    @Override
    public Pokemon answeredBy(Actor actor)
    {
        return SerenityRest.lastResponse().as(Pokemon.class);
    }
    public static GetPokemons getPokemons(){
        return new GetPokemons();
    }
}



