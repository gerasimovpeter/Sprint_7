import java.util.HashMap;
import io.qameta.allure.Step;
import io.restassured.response.ValidatableResponse;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import org.junit.Before;
import org.junit.Test;
import java.util.List;


public class ListOrdersReturnedInTheResponseBodyTest {
    private OrderUser orderUser;

    @Before
    @Step("Preparing data for receiving a list of orders")
    public void setUp() {
        orderUser = new OrderUser();
    }

    @Test
    @Step("Getting list of orders without data")
    public void getOrdersList() {
        ValidatableResponse responseCreate = orderUser.get();
        int actualStatusCodeCreate = responseCreate.extract().statusCode();
        List<HashMap> orderBody = responseCreate.extract().path("orders");
        assertEquals(200, actualStatusCodeCreate);
        assertNotNull(orderBody);

    }
}

