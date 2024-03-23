import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.Response;
import org.apache.commons.lang3.RandomStringUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import reqfunctions.courierLogReq;
import reqfunctions.courierCreateReq;
import respfunctions.courierLogResp;

import static apifunctions.constants.*;
import static org.junit.Assert.*;

import static apifunctions.courier.*;
import static org.apache.http.HttpStatus.SC_BAD_REQUEST;

@RunWith(Parameterized.class)
public class CourierLoginParamTest extends BaseTest{

    private final String login;
    private final String password;
    private final String firstName = RandomStringUtils.randomNumeric(5);

    public CourierLoginParamTest(String login, String password) {
        this.login = login;
        this.password = password;
    }

    @Parameterized.Parameters()
    public static Object[][] switchBetweenParamsCourierLogin() {
        return new Object[][]{
                //Запрос с пустым логином
                {null, "MeGaPaSs123" + RandomStringUtils.randomNumeric(3)},
                //Запрос с пустым паролем
                {"courierlogin" + RandomStringUtils.randomAlphabetic(2), ""},
                //полностью пустая пара
                {null, ""}
        };
    }

    @Test
    @DisplayName("Метод для проверки невозможности авторизации курьера без одного из параметров ")
    @Description("На вход передается два набора параметров с пустым логином и паролем и проверяет корректность ответа метода")
    public void checkCourierLoginWithoutSomeParams() {
        courierCreateReq parametrizedCourierCreateReq = new courierCreateReq(login,password,firstName);
        courierLogReq parametrizedCourierLoginReq = new courierLogReq(
                parametrizedCourierCreateReq.getLogin(),
                parametrizedCourierCreateReq.getPassword());


        Response response = courierLogin(parametrizedCourierLoginReq);
        response.then()
                .assertThat()
                .statusCode(SC_BAD_REQUEST); // status code 400

        assertEquals(
                "Сообщение об ошибке отличается",
                COURIER_LOGIN_BAD_REQ,
                response.as(courierLogResp.class).getMessage());

    }

}
