package apifunctions;

public class constants {

    public static final String BASE_URI = "https://qa-scooter.praktikum-services.ru";

    //Endpoints
    //Эндпоинты для метода CreateCourier
    public static final String CREATE_COURIER_ENDPOINT = "/api/v1/courier";
    public static final String COURIER_LOGIN_ENDPOINT = "/api/v1/courier/login";
    public static final String DELETE_COURIER_ENDPOINT = "/api/v1/courier/";


    //Эндпоинты для метода CreateOrder
    public static final String CREATE_ORDER_ENDPOINT = "/api/v1/orders";

    //Сообщения при ошибках
    public static final String COURIER_LOGIN_BAD_REQ = "Недостаточно данных для входа";
    public static final String COURIER_LOGIN_NOT_FOUND = "Учетная запись не найдена";
    public static final String COURIER_CREATE_GEMINI = "Этот логин уже используется. Попробуйте другой.";
    public static final String COURIER_CREATE_BAD_REQ = "Недостаточно данных для создания учетной записи";

    //Список с цветами
    public enum Colours {
        BLACK,
        GREY
    }
}
