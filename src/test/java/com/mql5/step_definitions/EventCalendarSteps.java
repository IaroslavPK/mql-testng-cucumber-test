package com.mql5.step_definitions;

import com.ibm.jvm.dtfjview.Output;
import dnl.utils.text.table.TextTable;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.qameta.allure.Allure;
import io.qameta.allure.Step;
import org.junit.jupiter.api.Assertions;
import pages.EconomicCalendarPage;
import pages.EventDetailsPage;
import web_components.EventTableRecord;
import web_components.HistoryTableRecord;

import java.util.List;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.open;

public class EventCalendarSteps {

    EconomicCalendarPage economicCalendarPage = new EconomicCalendarPage();
    EventDetailsPage eventDetailsPage = new EventDetailsPage();

    @Given("Open page {string}")
    @Step("Open page '{url}'")
    public void openPage(String url) {
        open(url);
        Assertions.assertTrue(economicCalendarPage.checkPageLoaded(), "some key elements weren't found");
    }

    @Then("Filter by period {string}")
    @Step("Filter by period '{timeFrame}'")
    public void filterByPeriod(String timeFrame) {
        economicCalendarPage.getTimeFilter().select(timeFrame);
    }

    @Then("Filter by importance {string}")
    @Step("Filter by importance '{importance}'")
    public void filterByImportance(String importance) {
        economicCalendarPage.getImportanceFilter().selectOnly(importance);
    }

    @Then("Filter by currency {string}")
    @Step("Filter by currency {currency}")
    public void filterByCurrency(String currency) {
        economicCalendarPage.getCurrencyFilter().selectOnly(currency);
    }

    @Then("Open first {string} currency event from filtered result")
    @Step("Open first '{currency}' currency event from filtered result")
    public void openFirstActionFromFilteredResult(String currency) {
        economicCalendarPage.getEventsTable().refresh();
        economicCalendarPage.getEventsTable().filter(text("CHF"));
        EventTableRecord selectedEvent = economicCalendarPage.getEventsTable().getFirst();
        economicCalendarPage.getEventsTable().dump();
        economicCalendarPage.getEventsTable().selectRow(selectedEvent.getId());
    }

    @And("Verify action priority is {string}")
    @Step("Verify action priority is '{priority}'")
    public void verifyActionPriorityIs(String priority) {
        Assertions.assertEquals(
                priority.toLowerCase(),
                eventDetailsPage.getEventImportance().text().toLowerCase(),
                "incorrect importance");
    }

    @And("Verify country is {string}")
    @Step("Verify country is '{country}'")
    public void verifyCountryIs(String country) {
        Assertions.assertEquals(country, eventDetailsPage.getCountryLink().text(), "incorrect country shown");

    }

    @And("Log history for the last {int} year")
    @Step("Log history for the last {years} year")
    public void logHistoryForTheLastYear(int years) {
        eventDetailsPage.getHistoryTab().click();
        List<HistoryTableRecord> historyTableRecords = eventDetailsPage.getHistoryTable().filterLastYears(years);
        TextTable tableLog = eventDetailsPage.getHistoryTable().logRecords(historyTableRecords);
        Allure.addAttachment("table with requited data", tableLog.toString());
//        tableLog.toCsv(new Output())
    }
}
