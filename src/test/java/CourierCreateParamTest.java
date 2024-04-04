import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.Response;
import org.apache.commons.lang3.RandomStringUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import reqfunctions.courierCreateReq;
import respfunctions.courierCreateResp;

import static apifunctions.courier.*;
import static apifunctions.constants.*;
import static org.apache.http.HttpStatus.SC_BAD_REQUEST;
import static org.junit.Assert.assertEquals;

@RunWith(Parameterized.class)
public class CourierCreateParamTest extends BaseTest{

    private final String login;
    private final String password;
    private final String firstName;

    public CourierCreateParamTest(String login, String password, String firstName) {
        this.login = login;
        this.password = password;
        this.firstName = firstName;
    }

    @Parameterized.Parameters()
    public static Object[][] switchBetweenParamsCreateCourier() {
        return new Object[][]{
                //Тело запроса с пустым логином
                {null,
                        "MeGaPaSs123" + RandomStringUtils.randomNumeric(3),
                        "NamelessCourier" + RandomStringUtils.randomNumeric(3)},
                //Тело запроса с пустым паролем
                {"courierlogin" + RandomStringUtils.randomAlphabetic(2),
                        null,
                        "NamelessCourier" + RandomStringUtils.randomNumeric(3)}
        };
    }


    @Test
    @DisplayName("Метод для проверки невозможности создания курьера без одного из параметров (checkCreateCourierWithoutSomeParamsTest)")
    @Description("Метод отправляет запрос без одного из основных параметров и проверяет корректность ответа метода")
    public void checkCreateCourierWithoutSomeParamsTest(){

        courierCreateReq parametrizedCourierReq = new courierCreateReq(login,password,firstName);

        Response response = createCourier(parametrizedCourierReq);
        response.then()
                .assertThat()
                .statusCode(SC_BAD_REQUEST); // status code 400

        assertEquals("Ожидается иной текст ошибки",
                COURIER_CREATE_BAD_REQ,
                response.as(courierCreateResp.class).getMessage());
    }

}

