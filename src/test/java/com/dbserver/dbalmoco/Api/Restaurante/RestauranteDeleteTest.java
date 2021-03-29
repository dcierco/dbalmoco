package com.dbserver.dbalmoco.Api.Restaurante;

import org.apache.http.HttpStatus;
import org.testng.annotations.Test;


import static io.restassured.RestAssured.given;

import com.dbserver.dbalmoco.Api.BaseTest;

public class RestauranteDeleteTest extends BaseTest {

    //TODO: Garantir que exista restaurante 10 antes da execução dessa suite de testes.
    @Test
    public void deveriaExcluirRestauranteExistente(){
        given().when().delete("/api/restaurante/{id}", 10).then().log().all().assertThat().statusCode(HttpStatus.SC_OK);
    }

    @Test
    public void naoDeveriaExcluirRestauranteNaoExistente(){
        given().when().delete("/api/restaurante/{id}", 10).then().log().all().assertThat().statusCode(HttpStatus.SC_NOT_FOUND);
    }



}