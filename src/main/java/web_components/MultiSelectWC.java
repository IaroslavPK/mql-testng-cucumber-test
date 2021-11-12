package web_components;

import io.qameta.allure.Step;
import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.By;

import java.util.ArrayList;
import java.util.List;

@Slf4j
public class MultiSelectWC extends SelectWC {

    public MultiSelectWC(By rootLocator) {
        super(rootLocator);
    }

    public List<String> getSelected() {
        List<String> checked = new ArrayList<>();
        filters.forEach(item -> {
            if (item.$("input").isSelected()) checked.add(item.text());
        });
        log.debug(checked.size() + " checked options found");
        return checked;
    }

    @Step("Unselecting each checkbox except {value}")
    public void selectOnly(String value) {
        filters.forEach(item -> {
            log.debug(item.text());
            if (item.text().equals(value) && !item.$("input").isSelected()) {
                item.$("label").scrollIntoView("{behavior: \"instant\", block: \"center\", inline: \"center\"}").click();
            } else if (!item.text().equals(value) && item.$("input").isSelected()) {
                item.$("label").scrollIntoView("{behavior: \"instant\", block: \"center\", inline: \"center\"}").click();
            }
        });
    }

}
