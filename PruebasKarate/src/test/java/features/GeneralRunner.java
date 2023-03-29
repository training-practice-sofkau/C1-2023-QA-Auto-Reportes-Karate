package features;

import com.intuit.karate.junit5.Karate;

class GeneralRunner {
    @Karate.Test
    Karate  createPostTest(){
        return Karate.run("src/test/java/features");
    }
}
