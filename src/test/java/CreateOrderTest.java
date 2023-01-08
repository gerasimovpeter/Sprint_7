import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import io.qameta.allure.Step;
import io.restassured.response.ValidatableResponse;
import static org.junit.Assert.*;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import java.util.List;


@RunWith(Parameterized.class)
public class CreateOrderTest {
    private Order order;
    private OrderUser orderUser;
    private int actualTrackNumber;

    private final List<String> color;

    public CreateOrderTest(List<String> color){
        this.color = color;
    }

    @Parameterized.Parameters
    public static Object[][] getColor() {
        return new Object[][]{
                {List.of("BLACK")},
                {List.of("GREY")},
                {List.of("BLACK", "GREY")},
                {List.of()}
        };
    }


    @Before
    @Step("Preparing data for creating an order")
    public void setUp() {
        orderUser = new OrderUser();
        order = GeneratorOfOrder.getNewOrder(color);
    }

    @Test
    @Step("Creating a new order")
    public void orderCanBeCreated(){
        ValidatableResponse responseCreate = orderUser.create(order);
        int actualStatusCodeCreate = responseCreate.extract().statusCode();
        actualTrackNumber = responseCreate.extract().path("track");
        assertEquals(201, actualStatusCodeCreate);
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        OrderCreateResponse orderCreateResponse = responseCreate.extract().body().as(OrderCreateResponse.class);
        String responseBody = gson.toJson(orderCreateResponse);
        assertTrue(responseBody.contains("track"));

    }

    @After
    @Step("Order cancellation")
    public void cleanUp() {
        orderUser.cancel(actualTrackNumber);
    }
}
