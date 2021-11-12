# mql-testng-cucumber-test
###Example of browser test on stack [ java+testng+cucumber ]

Contains 1 test for "Economic calendar, Forex news page" https://www.mql5.com/en/economic-calendar

Used "Selenide" framework for GUI tests.

###Required tools
- Java 8
- Maven 3
- Google Chrome browser

###Structure
- Cucumber 'feature' files in "src/test/java/resources/features"
- Cucumber glue in "com.mql5.step_definitions"
- In 'CustomDriverProvider' class assigns Googlebot User-Agent for ChromeDriver
- TestNg configuration in ...

###How to run
#### Via console
- Execute command `mvn clean test`
- Watch the execution in Chrome, review console logs: cucmber, allure steps.
- Watch logs for step ("Log history for the last 1 year"). And review History table CHF records for the last year.

####Via dev environment (Intellij Idea)
- Create TestNg configuration 
- Set Test Kind "Suite"
- Set Suite ".../src/test/resources/testng.xml"
- Watch the execution in Chrome, review console logs: cucumber + allure steps.
- Watch logs for step ("Log history for the last 1 year"). And review History table CHF records for the last year.

### How to build reports
#### Cucumber default report
- Available in "target/cucumber-reports/"
- Open "cucumber.html" in browser
- Review report


#### Allure report
- Execute command to build Allure report `mvn site`
- Check site built in "target/site"
- Open "target/site/allure-maven.html"
- Review Allure report with cucumber steps