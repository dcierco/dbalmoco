package com.dbserver.dbalmoco.Api.Restaurante;

import com.dbserver.dbalmoco.Api.BaseTest;
import com.dbserver.dbalmoco.models.Restaurante;

import io.restassured.response.Response;
import org.apache.http.HttpStatus;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;

import static io.restassured.RestAssured.basePath;
import static io.restassured.RestAssured.given;

public class RestauranteGetTest extends BaseTest {

    @Test
    public void deveListarRestaurantes(){

        Response resp = given().
                basePath(basePath).
                when().log().all().
                get("/api/restaurantes").
                then().log().all().
                assertThat().
                statusCode(HttpStatus.SC_OK).extract().response();

        List<Restaurante> listaRestauranteModels = resp.jsonPath().getList("", Restaurante.class);

        //TODO:Editar o nome pra se adequar as entradas do banco.
        Assert.assertEquals(listaRestauranteModels.get(0).getNome(), "nome vai aqui");
    }

    @Test
    public void deveriaPesquisarRestauranteIdValido(){
        given().
                basePath(basePath).
                when().log().all().
                get("/api/Restaurante/{id}", 1).
                then().log().all().
                assertThat().
                statusCode(HttpStatus.SC_OK).
                extract().
                response();

        //TODO: Inserir entrada no banco para teste ser possivel.
    }

    @Test
    public void deveriaPesquisarRestauranteIdInvalido(){
        Response resp =
                given().
                        basePath(basePath).
                        when().log().all().
                        get("/api/restaurante/{id}", Integer.MAX_VALUE).
                        then().log().all().
                        assertThat().
                        statusCode(HttpStatus.SC_NOT_FOUND).
                        extract().
                        response();
        Assert.assertEquals(resp.getBody().asString(), "Não existe no banco de dados restaurante com o id:" + Integer.MAX_VALUE);
    }
}