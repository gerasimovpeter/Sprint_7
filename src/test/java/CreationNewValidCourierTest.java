import io.qameta.allure.Step;
import io.restassured.response.ValidatableResponse;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;


public class CreationNewValidCourierTest {
    private CourierUser courierUser;
    private Courier courier;


    @Before
    @Step("Preparing data for creating a courier")
    public void setUp() {
        courierUser = new CourierUser();
        courier = GeneratorOfCourier.getNewValidCourier();
    }

    @Test
    @Step("Creating a new valid courier")
    public void courierCanBeCreated(){
        ValidatableResponse responseCreate = courierUser.create(courier);
        int actualStatusCodeCreate = responseCreate.extract().statusCode();
        boolean isOkInMessageTrueCreate = responseCreate.extract().path("ok");
        assertEquals(201, actualStatusCodeCreate);
        assertTrue(isOkInMessageTrueCreate);
    }

    @After
    @Step("Login and delete courier")
    public void cleanUp() {
        ValidatableResponse responseLogin = courierUser.login(CourierCredentials.from(courier));
        int id = responseLogin.extract().path("id");
        courierUser.delete(id);
    }

}
