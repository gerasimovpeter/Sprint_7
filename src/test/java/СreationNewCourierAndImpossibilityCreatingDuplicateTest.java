import io.qameta.allure.Step;
import io.restassured.response.ValidatableResponse;
import org.junit.After;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import org.junit.Before;
import org.junit.Test;


public class СreationNewCourierAndImpossibilityCreatingDuplicateTest {
    private CourierUser courierUser;
    private Courier newCourier;
    private Courier courierWithExistingLogin;


    @Before
    @Step("Preparing data for creating a courier")
    public void setUp() {
        courierUser = new CourierUser();
        newCourier = GeneratorOfCourier.getNewValidCourier();
        courierWithExistingLogin = GeneratorOfCourier.getCourierWithExistingLogin();

    }

    @Test
    @Step("Creating a new courier and a courier with duplicate information")
    public void courierWithExistingLoginCanNotBeCreated(){
        ValidatableResponse responseFirstCreate = courierUser.create(newCourier);
        int actualStatusCodeFirstCreate = responseFirstCreate.extract().statusCode();
        boolean isOkInMessageTrueFirstCreate = responseFirstCreate.extract().path("ok");
        assertEquals(201, actualStatusCodeFirstCreate);
        assertTrue(isOkInMessageTrueFirstCreate);
        ValidatableResponse responseSecondCreate = courierUser.create(courierWithExistingLogin);
        int actualStatusCodeSecondCreate = responseSecondCreate.extract().statusCode();
        String messageOfSecondCreate = responseSecondCreate.extract().path("message");
        assertEquals(409, actualStatusCodeSecondCreate);
        assertEquals("Этот логин уже используется. Попробуйте другой.", messageOfSecondCreate);

    }

    @After
    @Step("Login and delete of courier")
    public void cleanUp() {
        ValidatableResponse responseLogin = courierUser.login(CourierCredentials.from(newCourier));
        int id = responseLogin.extract().path("id");
        courierUser.delete(id);
    }

}
