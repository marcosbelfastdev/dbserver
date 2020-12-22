package pages;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.util.ArrayList;
import java.util.List;


public class BasePageObject {

    protected final String CURRENCY_SYMBOLS = "$";

    protected WebDriver browser;
    final int COMMON_RESOLVE_TIME = 60;
    private WebElement lastHighlighted;

    public BasePageObject(WebDriver browser) {
        this.browser = browser;
    }


    protected WebElement getInteractableWebElement(By locator) {
        return getInteractableWebElement(locator, COMMON_RESOLVE_TIME);
    }

    protected WebElement getInteractableWebElement(By locator, int resolve) {
        WebElement webElement = null;
        try {
            webElement = new WebDriverWait((browser), resolve)
                    .until(ExpectedConditions.elementToBeClickable(locator));
        } catch (Exception ignore) {

        }
        highlight(webElement);
        return webElement;
    }

    protected WebElement getPresentWebElement(By locator) {
        return getPresentWebElement(locator, COMMON_RESOLVE_TIME);
    }

    protected WebElement getPresentWebElement(By locator, int resolve) {
        WebElement webElement = null;
        try {
            webElement = new WebDriverWait((browser), resolve)
                    .until(ExpectedConditions.presenceOfElementLocated(locator));
        } catch (Exception ignore) {

        }
        highlight(webElement);
        return webElement;
    }

    protected boolean exists(By locator, int resolve) {
        boolean elementExists = false;
        WebElement webElement = null;
        try {
            webElement = new WebDriverWait((browser), resolve)
                    .until(ExpectedConditions.presenceOfElementLocated(locator));
            elementExists = true;
        } catch (Exception ignore) {

        }
        highlight(webElement);
        return elementExists;
    }

    protected boolean exists(By locator) {
        return exists(locator, COMMON_RESOLVE_TIME);
    }

//    protected List<WebElement> getListOfWebElementsPresent(WebElement parent, By locator) {
//        return getListOfWebElementsPresent(parent, locator, COMMON_RESOLVE_TIME);
//    }
//
//    protected List<WebElement> getListOfWebElementsPresent(WebElement parent, By locator, int resolve) {
//        List<WebElement> webElementList = new ArrayList<WebElement>();
//        try {
//            webElementList = parent.findElements(locator);
//        } catch (Exception ignore) {
//
//        }
//
//        return webElementList;
//    }

    protected void highlight(WebElement webElement) {
        try {
            unhighlight();
            ((JavascriptExecutor) browser).executeScript("arguments[0].setAttribute('style', arguments[1]);",
                    webElement, "border: 2px solid springgreen; border-radius: 5px;");
            lastHighlighted = webElement;
        } catch (Exception ignore) { }
    }

    protected void unhighlight() {
        if (lastHighlighted != null)
            ((JavascriptExecutor) browser).executeScript("arguments[0].setAttribute('style', arguments[1]);",
                    lastHighlighted, "");
    }

    protected void click(WebElement webElement) {
        try {
            webElement.click();
            return;
        } catch (Exception e) {
                ((JavascriptExecutor) browser).executeScript("arguments[0].click();", webElement);
        }
    }

}
