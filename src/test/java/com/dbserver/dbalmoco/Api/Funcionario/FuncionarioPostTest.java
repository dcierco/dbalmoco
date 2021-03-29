package com.dbserver.dbalmoco.Api.Funcionario;

import com.dbserver.dbalmoco.Api.BaseTest;
import com.dbserver.dbalmoco.Factory.FuncionarioFactory;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.apache.http.HttpStatus;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

import java.util.Random;

public class FuncionarioPostTest extends BaseTest {

    private FuncionarioFactory funcionario;

    @BeforeClass
    public void setup(){
       funcionario = new FuncionarioFactory();
    }


    @Test
    public void deveriaCadastrarUmNovoFuncionario() {

         given().
                contentType(ContentType.JSON).
                body(funcionario.factory(new Random().nextInt(Integer.MAX_VALUE-1) ,"Teste", "teste@teste.com", "teste123")).
                post("/api/funcionario").
                then().log().all().
                assertThat().
                statusCode(HttpStatus.SC_OK);
    }

    @Test
    public void naoDeveriaCadastrarUmNovoFuncionarioEmailInvalido() {

        Response response = given().
                contentType(ContentType.JSON).
                body(funcionario.factory(new Random().nextInt(Integer.MAX_VALUE-1), "Teste", "teste", "teste123")).
                post("/api/funcionario").
                then().log().all().
                assertThat().
                statusCode(HttpStatus.SC_BAD_REQUEST).
                extract().
                response();

        Assert.assertEquals(response.getBody().asString(), "[\"Email deve ser válido!\"]");
    }

    @Test
    public void naoDeveriaCadastrarUmNovoFuncionarioEmailNull() {

        Response response = given().
                contentType(ContentType.JSON).
                body(funcionario.factory(new Random().nextInt(Integer.MAX_VALUE-1), "Teste", "teste", "teste123")).
                post("/api/funcionario").
                then().log().all().
                assertThat().
                statusCode(HttpStatus.SC_BAD_REQUEST).
                extract().
                response();

        Assert.assertEquals(response.getBody().asString(), "[\"Email deve ser válido!\"]");
    }

    @Test
    public void naoDeveriaCadastrarUmNovoFuncionarioSemNome() {
       Response response =  given().
                contentType(ContentType.JSON).
                body(funcionario.factory(new Random().nextInt(Integer.MAX_VALUE-1), null, "teste@teste.com", "teste123")).
                post("/api/funcionario").
                then().log().all().
                assertThat().
                statusCode(HttpStatus.SC_BAD_REQUEST).
                extract().
                response();

        Assert.assertEquals(response.getBody().asString(), "[\"O Nome do funcionário não pode estar vazio!\"]");
    }

    @Test
    public void naoDeveriaCadastrarUmNovoFuncionarioNomeVazio() {
       Response response =  given().
                contentType(ContentType.JSON).
                body(funcionario.factory(new Random().nextInt(Integer.MAX_VALUE-1), "   ", "teste@teste.com", "teste123")).
                post("/api/funcionario").
                then().log().all().
                assertThat().
                statusCode(HttpStatus.SC_BAD_REQUEST).
                extract().
                response();

        Assert.assertEquals(response.getBody().asString(), "[\"O Nome do funcionário não pode estar vazio!\"]");
    }

    @Test
    public void deveriaCadastrarUmNovoFuncionarioSenha5Char(){

        given().
                contentType(ContentType.JSON).
                body(funcionario.factory(new Random().nextInt(Integer.MAX_VALUE-1) ,"Teste", "teste@teste.com", "12345")).
                post("/funcionario").
                then().log().all().
                assertThat().
                statusCode(HttpStatus.SC_OK);
    }

    @Test
    public void naoDeveriaCadastrarUmNovoFuncionarioSenha4Char(){

        Response response = given().
                contentType(ContentType.JSON).
                body(funcionario.factory(new Random().nextInt(Integer.MAX_VALUE-1) ,"Teste", "teste@teste.com", "1234")).
                post("/funcionario").
                then().log().all().
                assertThat().
                statusCode(HttpStatus.SC_OK).
                extract().
                response();

                Assert.assertEquals(response.getBody().asString(), "[\"A senha deve ter entre 5 e 20 caracteres!\"]");
    }

    @Test
    public void naoDeveriaCadastrarUmNovoFuncionarioSenha21Char(){

        Response response = given().
                contentType(ContentType.JSON).
                body(funcionario.factory(new Random().nextInt(Integer.MAX_VALUE-1) ,"Teste", "teste@teste.com", "senha com 21 characte")).
                post("/funcionario").
                then().log().all().
                assertThat().
                statusCode(HttpStatus.SC_OK).
                extract().
                response();

                Assert.assertEquals(response.getBody().asString(), "[\"A senha deve ter entre 5 e 20 caracteres!\"]");
    }

    @Test
    public void deveriaCadastrarUmNovoFuncionarioSenha20Char(){

        given().
                contentType(ContentType.JSON).
                body(funcionario.factory(new Random().nextInt(Integer.MAX_VALUE-1) ,"Teste", "teste@teste.com", "senha com 21 charact")).
                post("/funcionario").
                then().log().all().
                assertThat().
                statusCode(HttpStatus.SC_OK);
    }

    //TODO: Gerar caso de teste se email já existe no banco.
}