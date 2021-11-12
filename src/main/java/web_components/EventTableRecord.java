package web_components;

import com.codeborne.selenide.SelenideElement;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.StaleElementReferenceException;
import web_components.annotations.TableColumn;
import web_components.interfaces.TableRecord;

@Slf4j
@Getter
public class EventTableRecord extends TableRecord {

    private SelenideElement root;
    @TableColumn("Currency") private SelenideElement currency;
    @TableColumn("Event") private SelenideElement event;
    @TableColumn("Actual") private SelenideElement actual;
    @TableColumn("Forecast") private SelenideElement forecast;
    @TableColumn("Previous")  private SelenideElement previous;

    public EventTableRecord(SelenideElement rowElement) {
        root = rowElement;
        event = root.$(".ec-table__col_event");
    }

    @Override
    public String getId() {
        return event.text();
    }

    public void select() {
        event.$("a").scrollIntoView("{behavior: \"instant\", block: \"center\", inline: \"center\"}").click();
    }

    public void dump() {
        try {
            String message = new StringBuilder()
                    .append(event.getText()).append(" | ").append("\n").toString();
            log.debug(message);
        } catch (StaleElementReferenceException strEx) {
            log.debug("stale element found");
        }
    }

    @Override
    public Object[] getRecordData() {
        return new Object[] {currency.text(), event.text(), actual.text(), forecast.text(), previous.text()};
    }
}
