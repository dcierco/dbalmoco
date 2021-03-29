package com.dbserver.dbalmoco.Api.Funcionario;

import com.dbserver.dbalmoco.Api.BaseTest;
import com.dbserver.dbalmoco.models.Funcionario;

import io.restassured.response.Response;
import org.apache.http.HttpStatus;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;

import static io.restassured.RestAssured.basePath;
import static io.restassured.RestAssured.given;

public class FuncionarioGetTest extends BaseTest {

    @Test
    public void deveListarFuncionarios(){

        Response resp = given().
                basePath(basePath).
                when().log().all().
                get("/api/funcionarios").
                then().log().all().
                assertThat().
                statusCode(HttpStatus.SC_OK).extract().response();

        List<Funcionario> listaFuncionarioModels = resp.jsonPath().getList("", Funcionario.class);

        //TODO:Editar o email pra se adequar as entradas do banco.
        Assert.assertEquals(listaFuncionarioModels.get(0).getEmail(), "email vai aqui");
    }

    @Test
    public void deveriaPesquisarFuncionarioIdValido(){
        given().
                basePath(basePath).
                when().log().all().
                get("/api/funcionario/{id}", 1).
                then().log().all().
                assertThat().
                statusCode(HttpStatus.SC_OK).
                extract().
                response();

        //TODO: Inserir entrada no banco para teste ser possivel.
    }

    @Test
    public void deveriaPesquisarFuncionarioIdInvalido(){
        Response resp =
                given().
                        basePath(basePath).
                        when().log().all().
                        get("/api/funcionario/{id}", Integer.MAX_VALUE).
                        then().log().all().
                        assertThat().
                        statusCode(HttpStatus.SC_NOT_FOUND).
                        extract().
                        response();
        Assert.assertEquals(resp.getBody().asString(), "Não existe no banco de dados funcionário com o id:" + Integer.MAX_VALUE);
    }
}