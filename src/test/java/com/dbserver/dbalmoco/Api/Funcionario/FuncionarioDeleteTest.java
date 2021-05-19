package com.dbserver.dbalmoco.Api.Funcionario;

import org.apache.http.HttpStatus;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

import com.dbserver.dbalmoco.Api.BaseTest;

public class FuncionarioDeleteTest extends BaseTest {

    // TODO: Garantir que exista usuario 10 antes da execução dessa suite de testes.
    @Test
    public void deveriaExcluirFuncionarioExistente() {
        given().when().delete("/api/Funcionario/{id}", 10).then().log().all().assertThat().statusCode(HttpStatus.SC_OK);
    }

    @Test
    public void naoDeveriaExcluirFuncionarioNaoExistente() {
        given().when().delete("/api/Funcionario/{id}", 10).then().log().all().assertThat()
                .statusCode(HttpStatus.SC_NOT_FOUND);
    }

}