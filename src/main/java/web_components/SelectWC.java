package web_components;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.By;
import web_components.interfaces.Select;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.$;

@Slf4j
public class SelectWC implements Select {

    @Getter private SelenideElement root;
    protected final ElementsCollection filters;

    public SelectWC(By rootLocator) {
        root = $(rootLocator);
        filters = root.$$("li");
    }

    @Override
    public void select(String frame) {
        filters.filter(text(frame)).first()
                .scrollIntoView("{behavior: \"instant\", block: \"center\", inline: \"center\"}")
                .click();
    }

    @Override
    public void dump() {
        log.debug("TODO:");
    }
}
