package com.dbserver.dbalmoco.Api.Restaurante;

import com.dbserver.dbalmoco.Api.BaseTest;
import com.dbserver.dbalmoco.Factory.RestauranteFactory;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

import org.apache.http.HttpStatus;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

import java.util.Random;

public class RestaurantePostTest extends BaseTest {

        private RestauranteFactory restaurante;

        @BeforeClass
        public void setup() {
                restaurante = new RestauranteFactory();
        }

        @Test
        public void deveriaCadastrarUmNovoRestaurante() {

                given().contentType(ContentType.JSON)
                                .body(restaurante.factory(10, "Restaurante de Teste",
                                                "Restaurante que serve apenas como teste", "Avenida Rua"))
                                .post("/api/restaurante").then().assertThat().statusCode(HttpStatus.SC_OK);
        }

        @Test
        public void naoDeveriaCadastrarUmNovoRestauranteSemNome() {
                Response response = given().contentType(ContentType.JSON)
                                .body(restaurante.factory(new Random().nextInt(Integer.MAX_VALUE - 1), null,
                                                "Descrição", "Endereço"))
                                .post("/api/restaurante").then().assertThat().statusCode(HttpStatus.SC_BAD_REQUEST)
                                .extract().response();

                Assert.assertEquals(response.getBody().asString(), "[\"O nome do restaurante é obrigatório!\"]");
        }

        @Test
        public void naoDeveriaCadastrarUmNovoRestauranteNomeVazio() {
                Response response = given().contentType(ContentType.JSON)
                                .body(restaurante.factory(new Random().nextInt(Integer.MAX_VALUE - 1), " ", "Descrição",
                                                "Endereço"))
                                .post("/api/restaurante").then().assertThat().statusCode(HttpStatus.SC_BAD_REQUEST)
                                .extract().response();

                Assert.assertEquals(response.getBody().asString(), "[\"O nome do restaurante é obrigatório!\"]");
        }

        @Test
        public void deveriaCadastrarUmNovoRestauranteDescriçãoNull() {
                given().contentType(ContentType.JSON)
                                .body(restaurante.factory(new Random().nextInt(Integer.MAX_VALUE - 1), "Nome", null,
                                                "Endereço"))
                                .post("/api/restaurante").then().assertThat().statusCode(HttpStatus.SC_OK);
        }

        @Test
        public void naoDeveriaCadastrarUmNovoRestauranteEnderecoNull() {

                Response response = given().contentType(ContentType.JSON)
                                .body(restaurante.factory(new Random().nextInt(Integer.MAX_VALUE - 1),
                                                "Nome do Restaurante", "Descrição", null))
                                .post("/api/restaurante").then().assertThat().statusCode(HttpStatus.SC_BAD_REQUEST)
                                .extract().response();

                Assert.assertEquals(response.getBody().asString(), "[\"O endereço do restaurante é obrigatório!\"]");
        }

        @Test
        public void naoDeveriaCadastrarUmNovoRestauranteEnderecoVazio() {

                Response response = given().contentType(ContentType.JSON)
                                .body(restaurante.factory(new Random().nextInt(Integer.MAX_VALUE - 1),
                                                "Nome do Restaurante", "Descrição", "             "))
                                .post("/api/restaurante").then().assertThat().statusCode(HttpStatus.SC_BAD_REQUEST)
                                .extract().response();

                Assert.assertEquals(response.getBody().asString(), "[\"O endereço do restaurante é obrigatório!\"]");
        }

        @Test
        public void deveriaCadastrarUmNovoRestauranteEndereco5Char() {

                given().contentType(ContentType.JSON)
                                .body(restaurante.factory(new Random().nextInt(Integer.MAX_VALUE - 1),
                                                "Nome do Restaurante", "Descrição", "12345"))
                                .post("/api/restaurante").then().assertThat().statusCode(HttpStatus.SC_OK);
        }

        @Test
        public void deveriaCadastrarUmNovoRestauranteEndereco50Char() {

                given().contentType(ContentType.JSON)
                                .body(restaurante.factory(new Random().nextInt(Integer.MAX_VALUE - 1),
                                                "Nome do Restaurante", "Descrição",
                                                "Endereço que tem exatamente 50 caracteres!!!!!!!!!"))
                                .post("/api/restaurante").then().assertThat().statusCode(HttpStatus.SC_OK);
        }

        @Test
        public void naoDeveriaCadastrarUmNovoRestauranteEndereco4Char() {

                Response response = given().contentType(ContentType.JSON)
                                .body(restaurante.factory(new Random().nextInt(Integer.MAX_VALUE - 1),
                                                "Nome do Restaurante", "Descrição", "1234"))
                                .post("/api/restaurante").then().assertThat().statusCode(HttpStatus.SC_BAD_REQUEST)
                                .extract().response();

                Assert.assertEquals(response.getBody().asString(),
                                "[\"O endereço do restaurante deve ter entre 5 e 50 caracteres!\"]");
        }

        @Test
        public void naoDeveriaCadastrarUmNovoRestauranteEndereco51Char() {

                Response response = given().contentType(ContentType.JSON)
                                .body(restaurante.factory(new Random().nextInt(Integer.MAX_VALUE - 1),
                                                "Nome do Restaurante", "Descrição",
                                                "Endereço que claramente tem mais de 50 caracteres!!"))
                                .post("/api/restaurante").then().assertThat().statusCode(HttpStatus.SC_BAD_REQUEST)
                                .extract().response();

                Assert.assertEquals(response.getBody().asString(),
                                "[\"O endereço do restaurante deve ter entre 5 e 50 caracteres!\"]");
        }
}