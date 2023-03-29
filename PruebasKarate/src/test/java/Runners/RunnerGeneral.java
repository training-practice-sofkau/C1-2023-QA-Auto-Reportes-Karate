package Runners;
import com.intuit.karate.junit5.Karate;

public class RunnerGeneral {

        @Karate.Test
        Karate testSample() {
            return Karate.run("src/test/java/features");
        }

    }

