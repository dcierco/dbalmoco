package com.dbserver.dbalmoco.Api.Voto;

import org.apache.http.HttpStatus;
import org.testng.annotations.Test;


import static io.restassured.RestAssured.given;

import com.dbserver.dbalmoco.Api.BaseTest;

public class VotoDeleteTest extends BaseTest {

    //TODO: Garantir que exista Voto 10 antes da execução dessa suite de testes.
    @Test
    public void deveriaExcluirVotoExistente(){
        given().when().delete("/api/voto/{id}", 10).then().log().all().assertThat().statusCode(HttpStatus.SC_OK);
    }

    @Test
    public void naoDeveriaExcluirVotoNaoExistente(){
        given().when().delete("/api/voto/{id}", 10).then().log().all().assertThat().statusCode(HttpStatus.SC_NOT_FOUND);
    }



}