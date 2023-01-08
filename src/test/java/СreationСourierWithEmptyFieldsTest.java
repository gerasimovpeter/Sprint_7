import io.qameta.allure.Description;
import io.qameta.allure.Step;
import io.restassured.response.ValidatableResponse;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class СreationСourierWithEmptyFieldsTest {
    private CourierUser courierUser;

    @Before
    @Step("Preparing test data")
    public void setUp() {
        courierUser = new CourierUser();
    }


    @Test
    @Step("Creating a new courier without a password")
    public void сourierWithAnEmptyPasswordWillNotBeCreated(){
        Courier courier = GeneratorOfCourier.getCourierWithEmptyPassword();
        ValidatableResponse responseCreate = courierUser.create(courier);
        int actualStatusCodeCreate = responseCreate.extract().statusCode();
        String responseMessage = responseCreate.extract().path("message");
        assertEquals(400, actualStatusCodeCreate);
        assertEquals("Недостаточно данных для создания учетной записи", responseMessage);
    }
    @Test
    @Step("Creating a new courier without a login")
    public void сourierWithAnEmptyLoginWillNotBeCreated(){
        Courier courier = GeneratorOfCourier.getCourierWithEmptyLogin();
        ValidatableResponse responseCreate = courierUser.create(courier);
        int actualStatusCodeCreate = responseCreate.extract().statusCode();
        String responseMessage = responseCreate.extract().path("message");
        assertEquals(400, actualStatusCodeCreate);
        assertEquals("Недостаточно данных для создания учетной записи", responseMessage);
    }
    @Test
    @Description("The test fails due to the fact that you can create a client without a name, and the condition of the task says - \"if one of the fields is missing, the request returns an error;\"")
    @Step("Creating a new courier without a name")
    public void courierWithAnEmptyNameCanNotBeCreated(){
        Courier courier = GeneratorOfCourier.getCourierWithEmptyName();
        ValidatableResponse responseCreate = courierUser.create(courier);
        int actualStatusCodeCreate = responseCreate.extract().statusCode();
        String responseMessage = responseCreate.extract().path("message");
        assertEquals(400, actualStatusCodeCreate);
        assertEquals("Недостаточно данных для создания учетной записи", responseMessage);
    }
}
