import com.intuit.karate.junit5.Karate;

public class DeleteTest {

    @Karate.Test
    Karate deleteTest() {
        return Karate.run("src/test/java").tags("@Delete");
    }
}