

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.annotations.Test;
import com.google.gson.Gson;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

public class CriarUsuarioTest {

    @Test
    public void testCriarUsuario() {
        RestAssured.baseURI = "https://reqres.in/api/";
        UsuarioModel usuarioModel = new UsuarioModel("morpheus", "leader");
        String parametros = new Gson().toJson(usuarioModel);

        Response response = given()
            .contentType(ContentType.JSON)
            .body(parametros)
        .when()
            .post("/users")
            .then().extract().response();
        String id = response.path("id");
        response.then()
            .log().all()
            .statusCode(201)
            .body("name", equalTo("morpheus"))
            .body("job", equalTo("leader"))
            .body("id", equalTo(id))
            .extract().response();
    }
}




