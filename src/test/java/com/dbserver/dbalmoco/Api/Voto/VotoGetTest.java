package com.dbserver.dbalmoco.Api.Voto;

import com.dbserver.dbalmoco.Api.BaseTest;
import com.dbserver.dbalmoco.models.Voto;

import io.restassured.response.Response;
import org.apache.http.HttpStatus;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;

import static io.restassured.RestAssured.basePath;
import static io.restassured.RestAssured.given;

public class VotoGetTest extends BaseTest {

    @Test
    public void deveListarVotos() {

        Response resp = given().basePath(basePath).when().log().all().get("/api/votos").then().log().all().assertThat()
                .statusCode(HttpStatus.SC_OK).extract().response();

        List<Voto> listaVoto = resp.jsonPath().getList("", Voto.class);

        // TODO:Editar o nome pra se adequar as entradas do banco.
        Assert.assertEquals(listaVoto.get(0).getFuncionario().getNome(), "nome vai aqui");
    }

    @Test
    public void deveriaPesquisarVotoIdValido() {
        Response resp = given().basePath(basePath).when().log().all().get("/api/voto/{id}", 1).then().log().all()
                .assertThat().statusCode(HttpStatus.SC_OK).extract().response();

        Assert.assertEquals(resp.as(Voto.class).getFuncionario().getNome(), "nome vai aqui");

        // TODO: Inserir entrada no banco para teste ser possivel.
    }

    @Test
    public void deveriaPesquisarVotoIdInvalido() {
        Response resp = given().basePath(basePath).when().log().all().get("/api/voto/{id}", Integer.MAX_VALUE).then()
                .log().all().assertThat().statusCode(HttpStatus.SC_NOT_FOUND).extract().response();
        Assert.assertEquals(resp.getBody().asString(),
                "Não existe no banco de dados voto com o id:" + Integer.MAX_VALUE);
    }
}