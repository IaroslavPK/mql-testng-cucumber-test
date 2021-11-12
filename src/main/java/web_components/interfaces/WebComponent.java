package web_components.interfaces;

import com.codeborne.selenide.SelenideElement;

public interface WebComponent {

    SelenideElement getRoot();

    void dump();
}
