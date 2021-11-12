package pages;

import lombok.Getter;

@Getter
public abstract class Page {

    protected String errorMessage = "";

    public abstract boolean checkPageLoaded();
}
