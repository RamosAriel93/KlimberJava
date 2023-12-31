package Tests;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.*;

public class BackendTest {

    private static final String BASE_URL = "https://restful-booker.herokuapp.com";
    private static String authToken;
    private static int bookingId;

    @BeforeTest
    public void getToken() {
        System.out.println("----Iniciando Tests----");
        Response response = RestAssured.given()
                .contentType("application/json")
                .body("{\"username\": \"admin\", \"password\": \"password123\"}")
                .post(BASE_URL + "/auth");

        authToken = response.jsonPath().getString("token");

        int statusCode = response.getStatusCode();
        String responseBody = response.getBody().asString();

        System.out.println("Status Code: " + statusCode);
        System.out.println("Response Body: " + responseBody);
    }
    @AfterMethod
    public void beforeMethod(){
        System.out.println("---Iniciando Test---");

    }@AfterMethod
    public void afterMethod(){
        System.out.println("---Finalizando Test---");

    }
    @AfterTest
    public void beforeTests(){
        System.out.println("----Finalizando Tests----");
    }

    @Test(priority = 1)
    public void createReservation() {
        System.out.println("**Creando reserva**");
        String requestBody = "{\n" +
                "    \"firstname\" : \"Jim\",\n" +
                "    \"lastname\" : \"Brown\",\n" +
                "    \"totalprice\" : 111,\n" +
                "    \"depositpaid\" : true,\n" +
                "    \"bookingdates\" : {\n" +
                "        \"checkin\" : \"2018-01-01\",\n" +
                "        \"checkout\" : \"2019-01-01\"\n" +
                "    },\n" +
                "    \"additionalneeds\" : \"Breakfast\"\n" +
                "}";

        Response response = RestAssured.given()
                .header("Authorization", "Bearer " + authToken)
                .contentType("application/json")
                .body(requestBody)
                .post(BASE_URL + "/booking");
        bookingId = response.jsonPath().getInt("bookingid");

        int statusCode = response.getStatusCode();
        String responseBody = response.getBody().asString();

        System.out.println("Status Code: " + statusCode);
        System.out.println("Response Body: " + responseBody);
        System.out.println("BookingID: " + bookingId);
        Assert.assertEquals(statusCode, 200, "El código de estado no es el esperado");
    }

    @Test(priority = 2)
    public void getReservationDetails() {
        System.out.println("**Consultando reserva**");
        RestAssured.given()
                .contentType("application/json")
                .header("Authorization", "Bearer " + authToken)
                .get(BASE_URL + "/booking/" + bookingId)
                .then()
                .log().body()
                .log().status();
    }
    @Test(priority = 3)
    public void updateReservation() {
        System.out.println("**Editando Reserva**");
        String requestBody = "{\n" +
                "    \"firstname\" : \"Jamess\",\n" +
                "    \"lastname\" : \"Brown\",\n" +
                "    \"totalprice\" : 222,\n" +
                "    \"depositpaid\" : true,\n" +
                "    \"bookingdates\" : {\n" +
                "        \"checkin\" : \"2018-01-01\",\n" +
                "        \"checkout\" : \"2022-01-01\"\n" +
                "    },\n" +
                "    \"additionalneeds\" : \"Breakfast\"\n" +
                "}";

        Response response = RestAssured.given()
                .contentType("application/json")
                .accept("application/json")
                .header("Cookie", "token=" + authToken)
                .body(requestBody)
                .put(BASE_URL + "/booking/" + bookingId);

        int statusCode = response.getStatusCode();
        String responseBody = response.getBody().asString();

        System.out.println("Status Code: " + statusCode);
        System.out.println("Response Body: " + responseBody);
        Assert.assertEquals(statusCode, 200, "El código de estado no es el esperado");
    }

    @Test(priority = 4)
    public void patchReservation() {
        System.out.println("**Editando Parcialmente la Reserva**");
        String requestBody = "{\n" +
                "    \"firstname\" : \"James\",\n" +
                "    \"lastname\" : \"Jons\"\n" +
                "}";

        Response response = RestAssured.given()
                .contentType("application/json")
                .accept("application/json")
                .header("Cookie", "token=" + authToken)
                .body(requestBody)
                .patch(BASE_URL + "/booking/" + bookingId);

        int statusCode = response.getStatusCode();
        String responseBody = response.getBody().asString();

        System.out.println("Status Code: " + statusCode);
        System.out.println("Response Body: " + responseBody);
        Assert.assertEquals(statusCode, 200, "El código de estado no es el esperado");
    }

    @Test(priority = 5)
    public void deleteReservation() {
        System.out.println("**Eliminando la Reserva**");
        Response response = RestAssured.given()
                .contentType("application/json")
                .accept("application/json")
                .header("Cookie", "token=" + authToken)
                .delete(BASE_URL + "/booking/" + bookingId);

        int statusCode = response.getStatusCode();
        String responseBody = response.getBody().asString();

        System.out.println("Status Code: " + statusCode);
        System.out.println("Response Body: " + responseBody);
        Assert.assertEquals(statusCode, 201, "El código de estado no es el esperado");
    }
}

