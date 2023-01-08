import io.qameta.allure.Step;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import io.restassured.response.ValidatableResponse;
import org.junit.After;

public class LoginValidCourierTest {
    private CourierUser courierUser;
    private Courier courier;
    private ValidatableResponse responseLogin;


    @Before
    @Step("Preparing data to creating new valid courier")
    public void setUp() {
        courierUser = new CourierUser();
        courier = GeneratorOfCourier.getNewValidCourier();
    }

    @Test
    @Step("Creating new valid courier and login")
    public void validCourierCanLogin(){
        ValidatableResponse responseCreate = courierUser.create(courier);
        int actualStatusCodeCreate = responseCreate.extract().statusCode();
        boolean isOkInMessageTrueCreate = responseCreate.extract().path("ok");
        assertEquals(201, actualStatusCodeCreate);
        assertTrue(isOkInMessageTrueCreate);
        responseLogin = courierUser.login(CourierCredentials.from(courier));
        int actualStatusCodeLogin = responseLogin.extract().statusCode();
        int messageLogin = responseLogin.extract().path("id");
        assertEquals(200, actualStatusCodeLogin);
        assertNotNull(messageLogin);

    }

    @After
    @Step("Deleting courier")
    public void cleanUp() {
        int id = responseLogin.extract().path("id");
        courierUser.delete(id);
    }
}
