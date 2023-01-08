import io.qameta.allure.Step;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import io.restassured.response.ValidatableResponse;
import org.junit.After;


public class LoginСourierWithEmptyFieldsTest {
    private CourierUser courierUser;
    private Courier courier;
    private ValidatableResponse responseLoginForId;


    @Before
    @Step("Prepare data to creating new courier")
    public void setUp() {
        courierUser = new CourierUser();
        courier = GeneratorOfCourier.getNewValidCourier();
    }
    @Test
    @Step("User authorization without login")
    public void courierWithoutLoginCanNotLogin(){
        ValidatableResponse responseCreate = courierUser.create(courier);
        int actualStatusCodeCreate = responseCreate.extract().statusCode();
        boolean isOkInMessageTrueCreate = responseCreate.extract().path("ok");
        assertEquals(201, actualStatusCodeCreate);
        assertTrue(isOkInMessageTrueCreate);
        responseLoginForId = courierUser.login(CourierCredentials.from(courier));
        ValidatableResponse responseLogin = courierUser.login(new CourierCredentials(null, courier.getPassword()));
        int actualStatusCodeLogin = responseLogin.extract().statusCode();
        String messageLogin = responseLogin.extract().path("message");
        assertEquals(400, actualStatusCodeLogin);
        assertEquals("Недостаточно данных для входа",messageLogin);

    }
    @Test
    @Step("User authorization without password")
    public void courierWithoutPasswordCanNotLogin(){
        ValidatableResponse responseCreate = courierUser.create(courier);
        int actualStatusCodeCreate = responseCreate.extract().statusCode();
        boolean isOkInMessageTrueCreate = responseCreate.extract().path("ok");
        assertEquals(201, actualStatusCodeCreate);
        assertTrue(isOkInMessageTrueCreate);
        responseLoginForId = courierUser.login(CourierCredentials.from(courier));
        ValidatableResponse responseLogin = courierUser.login(new CourierCredentials(courier.getLogin(), ""));
        int actualStatusCodeLogin = responseLogin.extract().statusCode();
        String messageLogin = responseLogin.extract().path("message");
        assertEquals(400, actualStatusCodeLogin);
        assertEquals("Недостаточно данных для входа",messageLogin);

    }



    @Test
    @Step("Authorization of a user with a non-existent login and password")
    public void courierWithNotExistingLoginAndPasswordCanNotLogin(){
        ValidatableResponse responseCreate = courierUser.create(courier);
        int actualStatusCodeCreate = responseCreate.extract().statusCode();
        boolean isOkInMessageTrueCreate = responseCreate.extract().path("ok");
        assertEquals(201, actualStatusCodeCreate);
        assertTrue(isOkInMessageTrueCreate);
        responseLoginForId = courierUser.login(CourierCredentials.from(courier));
        ValidatableResponse responseLogin = courierUser.login(new CourierCredentials(courier.getLogin()+"999", courier.getPassword()+"999"));
        int actualStatusCodeLogin = responseLogin.extract().statusCode();
        String messageLogin = responseLogin.extract().path("message");
        assertEquals(404, actualStatusCodeLogin);
        assertEquals("Учетная запись не найдена",messageLogin);

    }

    @After
    @Step("Login and delete courier")
    public void cleanUp() {
        int id = responseLoginForId.extract().path("id");
        courierUser.delete(id);
    }
}

