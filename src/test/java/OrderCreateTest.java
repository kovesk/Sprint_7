
import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.Response;
import lombok.AllArgsConstructor;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import reqfunctions.orderCreateReq;
import respfunctions.orderCreateResp;


import static apifunctions.constants.Colours.BLACK;
import static apifunctions.constants.Colours.GREY;
import static apifunctions.order.createOrder;
import static org.apache.http.HttpStatus.SC_CREATED;
import static org.junit.Assert.assertNotEquals;

@RunWith(Parameterized.class)
@AllArgsConstructor
public class OrderCreateTest extends BaseTest{

    private String[] colour;
    private final String comment;


    private final orderCreateReq createOrderReq = new orderCreateReq(
            "Sakura",
            "Haruno",
            "Konoha 3rd avenue",
            "Hokage's monument st.",
            "+7 800 355 35 35",
            120,
            "2024-06-19");


    @Parameterized.Parameters()
    public static Object[][] switchBetweenParamsCreateOrder() {
        return new Object[][]{
                {new String[]{
                        BLACK.toString(),
                        GREY.toString()
                }, "Черно-серого самоката"},
                {new String[]{
                        BLACK.toString()
                }, "Черного самоката"},
                {new String[]{
                        GREY.toString()
                }, "Серого самоката"},
                {new String[]{
                }, "Любого самоката"}
        };
    }



    @Before
    public void setParams(){
        createOrderReq.setColor(this.colour);
        createOrderReq.setComment(this.comment);
    }

    @Test
    @DisplayName("Метод проверяет корректность ответа метода при создании заказа")
    @Description("Метод получает статускод = 200 и проверяет что получен номер заказа не равный 0")
    public void checkCreateOrderColorsSwitcher(){
        Response response = createOrder(createOrderReq);

        response.then().statusCode(SC_CREATED);
        assertNotEquals("Получено недопустимое значение track = 0",
                response.as(orderCreateResp.class).getTrack(),0);

    }

}
