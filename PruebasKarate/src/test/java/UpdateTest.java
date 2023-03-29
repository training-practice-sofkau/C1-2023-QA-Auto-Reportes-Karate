import com.intuit.karate.junit5.Karate;

public class UpdateTest {

    @Karate.Test
    Karate updateTest() {
        return Karate.run("src/test/java").tags("@Update");
    }
}