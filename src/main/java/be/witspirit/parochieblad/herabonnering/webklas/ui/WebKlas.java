package be.witspirit.parochieblad.herabonnering.webklas.ui;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

public class WebKlas {
    private final String url;
    private final WebDriver driver;

    private final WebDriverWait wait;

    public WebKlas(String url, WebDriver driver) {
        this.url = url;
        this.driver = driver;
        this.wait = new WebDriverWait(driver, 10);
    }

    public MainPage login(String email, String edition, String password) {
        new Login(this).login(email, edition, password);

        return new MainPage(this);
    }

    WebDriver driver() {
        return driver;
    }

    WebDriverWait doWait() {
        return wait;
    }

    String url() {
        return url;
    }
}
