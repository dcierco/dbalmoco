package com.dbserver.dbalmoco.Api;

import com.aventstack.extentreports.testng.listener.ExtentITestListenerAdapter;

import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.ITestListener;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Listeners;

import static io.restassured.RestAssured.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
@Listeners({ExtentITestListenerAdapter.class})
public class BaseTest extends AbstractTestNGSpringContextTests implements ITestListener {

    @BeforeClass
    public void setup(){
        baseURI = "http://localhost:8080/";
        basePath = "api/";
    }
}