import com.intuit.karate.junit5.Karate;

public class AllFeatureTest {

    @Karate.Test
    Karate allTest() {
        return Karate.run("src/test/java");
    }
}