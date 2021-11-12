package com.mql5;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.WebDriverRunner;
import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;
import io.qameta.allure.Allure;
import io.qameta.allure.Feature;
import org.apache.log4j.PropertyConfigurator;
import org.testng.Reporter;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Parameters;
import utils.CustomDriverProvider;

import java.util.List;

import static com.codeborne.selenide.Selenide.*;

@CucumberOptions(
        plugin = {
                "pretty",
                "html:target/cucumber-reports/cucumber.html",
                "json:target/cucumber-reports/cucumber.json"
        },
        features = {"src/test/resources/features"},
        glue = {"com.mql5.step_definitions"}
)
@Feature("Event Calendar")
public class TestRunner extends AbstractTestNGCucumberTests {

        @BeforeSuite
        void beforeAll() {
                Configuration.pageLoadTimeout = 60000;
                Configuration.timeout = 30000;
                Configuration.browser = CustomDriverProvider.class.getName();
        }

        @AfterMethod
        protected void postAction() {
                clearBrowserCookies();
                clearBrowserLocalStorage();
        }

        @AfterSuite(alwaysRun = true)
        void tearDown() {
                closeWebDriver();
        }
}
