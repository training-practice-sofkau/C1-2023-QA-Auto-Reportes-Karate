package com.softkau.runners;


import com.intuit.karate.junit5.Karate;

public class Runners {


    @Karate.Test
    Karate testSample() {
        return Karate.run("src/test/java/features");
    }


}
