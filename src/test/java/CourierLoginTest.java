import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.Response;
import org.apache.commons.lang3.RandomStringUtils;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import reqfunctions.courierLogReq;
import reqfunctions.courierCreateReq;
import respfunctions.courierLogResp;

import static apifunctions.courier.*;
import static apifunctions.constants.*;
import static org.junit.Assert.*;

import static org.apache.http.HttpStatus.SC_NOT_FOUND;
import static org.apache.http.HttpStatus.SC_OK;

public class CourierLoginTest extends BaseTest{
    private courierLogReq courierLoginReq;

    @Before
    public void createCourierForTest(){
        courierCreateReq createCourierReq = new courierCreateReq(
                "Log" + RandomStringUtils.randomAlphabetic(3),
                "Pass" + RandomStringUtils.randomNumeric(3),
                "FN" + RandomStringUtils.randomNumeric(3)
        );

        courierLoginReq = new courierLogReq(
                createCourierReq.getLogin(),
                createCourierReq.getPassword());

        createCourier(createCourierReq);
    }

    @Test
    @DisplayName("Метод проверки входа под созданной учетной записью курьера")
    @Description("Метод создает экземпляр курьера и проверяет возможность войти под его данными и проверяет ответ метода")
    public void checkCourierCanLogin(){
        Response response = courierLogin(courierLoginReq);

        response.then()
                .assertThat()
                .and()
                .statusCode(SC_OK); // status code 200
        assertNotEquals("Метод вернул 0",0,response.as(courierLogResp.class).getId());
    }

    @Test
    @DisplayName("Метод проверки входа с несуществующей УЗ")
    @Description("Метод проверяет корректность ответа метода при попытке войти под несуществующей УЗ")
    public void checkCourierInvalidData(){
        courierLogReq courierLoginInvalidReq = new courierLogReq(
                courierLoginReq.getLogin() + RandomStringUtils.randomNumeric(3),
                courierLoginReq.getPassword() + RandomStringUtils.randomAlphabetic(3)
        );
        Response response = courierLogin(courierLoginInvalidReq);
        response.then()
                .assertThat()
                .statusCode(SC_NOT_FOUND); // status code 404

        assertEquals("Сообщение",COURIER_LOGIN_NOT_FOUND,response.as(courierLogResp.class).getMessage());

    }

    @After
    @DisplayName("Метод удаления курьера")
    @Description("Удаляет созданного курьера после теста")
    public void deleteCourierAfterTest(){
        Response response = courierLogin(courierLoginReq);
        delCourier(response.jsonPath().get("id").toString());
    }
}

