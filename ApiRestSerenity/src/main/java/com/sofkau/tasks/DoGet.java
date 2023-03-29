package com.sofkau.tasks;

import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.rest.interactions.Get;
import org.apache.http.HttpHeaders;
import org.apache.http.entity.ContentType;

public class DoGet implements Task {
    private String resource;

    public DoGet withTheResource(String resource){
        this.resource=resource;
        return this;
    }

    @Override
    public <T extends Actor> void performAs(T actor) {
        actor.attemptsTo(
            Get.resource(resource)
                    .with(requestSpecification -> requestSpecification
                            .header(HttpHeaders.CONTENT_TYPE, ContentType.APPLICATION_JSON.getMimeType())
                    )
        );
    }

    public static DoGet doGet() {
        return new DoGet();
    }

}
