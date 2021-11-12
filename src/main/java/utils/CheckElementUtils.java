package utils;

import com.codeborne.selenide.SelenideElement;

public class CheckElementUtils {

    /**
     * Check element visibility. If element is invisible - return text message with element data.
     * If visible - returns empty string.
     * @param element element to check
     * @return string with message
     */
    public static String isDisplayed(SelenideElement element) {
        if (!element.isDisplayed()) {
            return "element '" + element.getTagName() + "' with text='" + element.getOwnText() + "' is not displayed\n";
        } else {
            return "";
        }
    }
}
