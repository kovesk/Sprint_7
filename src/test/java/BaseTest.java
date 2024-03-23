import static apifunctions.constants.*;
import io.restassured.RestAssured;
import org.junit.Before;
public class BaseTest {
    @Before
    public void setUP(){
        RestAssured.baseURI = BASE_URI;
    }
}