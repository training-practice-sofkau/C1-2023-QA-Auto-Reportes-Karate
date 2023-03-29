package runners;

import com.intuit.karate.junit5.Karate;

public class TestKarate {
    @Karate.Test
    Karate testSample(){
        return Karate.run("src/test/java/features");
    }
}
