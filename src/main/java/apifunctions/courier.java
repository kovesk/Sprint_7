package apifunctions;

import reqfunctions.courierLogReq;
import reqfunctions.courierCreateReq;
import io.qameta.allure.Step;
import io.restassured.response.Response;


import static apifunctions.constants.*;
import static apifunctions.generalApi.*;

public class courier {

    @Step("Создание курьера")
    public static Response createCourier(courierCreateReq body){
        return comReq(body, CREATE_COURIER_ENDPOINT);
    }

    @Step("Вход под логином и паролем курьера")
    public static Response courierLogin (courierLogReq body){
        return comReq(body, COURIER_LOGIN_ENDPOINT);
    }
    @Step("Удаление курьера")
    public static void delCourier (String id){
        delReq(DELETE_COURIER_ENDPOINT + id);
    }
}
