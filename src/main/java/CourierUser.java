import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import io.restassured.response.ValidatableResponse;
import static io.restassured.RestAssured.given;

public class CourierUser extends User {
    private static final String DELETE_PATH = "/api/v1/courier/";
    private static final String CREATE_PATH = "/api/v1/courier";
    private static final String LOGIN_PATH = "/api/v1/courier/login";

    public ValidatableResponse delete(int id){
        Gson deleteGson = new GsonBuilder().setPrettyPrinting().create();
        DeleteCourier deleteCourier = new DeleteCourier(String.valueOf(id));
        String deleteJson = deleteGson.toJson(deleteCourier);
        return given()
                .spec(getSpec())
                .body(deleteJson)
                .when()
                .delete(DELETE_PATH+String.valueOf(id))
                .then();

    }
    public ValidatableResponse login(CourierCredentials courierCredentials){
        return given()
                .spec(getSpec())
                .body(courierCredentials)
                .when()
                .post(LOGIN_PATH)
                .then();

    }

    public ValidatableResponse create(Courier courier){
        return given()
                .spec(getSpec())
                .body(courier)
                .when()
                .post(CREATE_PATH)
                .then();

    }

}
