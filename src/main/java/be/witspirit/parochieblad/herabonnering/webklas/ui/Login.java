package be.witspirit.parochieblad.herabonnering.webklas.ui;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class Login {

    public static final By dialogTitle = By.className("Caption");
    public static final By emailInput = By.xpath("//div[text() = 'Emailadres']/../..//input");
    public static final By editionInput = By.xpath("//div[text() = 'Editie ID']/../..//input");
    public static final By passwordInput = By.xpath("//div[text() = 'Wachtwoord']/../..//input");
    public static final By loginButton = By.xpath("//button");

    private final WebKlas webKlas;

    Login(WebKlas webKlas) {
        this.webKlas = webKlas;
    }

    void login(String email, String edition, String password) {
        webKlas.driver().navigate().to(webKlas.url());

        WebElement caption = webKlas.doWait().until(d -> d.findElement(Login.dialogTitle));
        if ( !caption.getText().equals("Aanmelden") ) {
            throw new IllegalStateException("Expected to be on the 'Aanmelden' screen, but doesn't seem to be the case");
        }

        webKlas.driver().findElement(Login.emailInput).sendKeys(email);
        webKlas.driver().findElement(Login.editionInput).sendKeys(edition);
        webKlas.driver().findElement(Login.passwordInput).sendKeys(password);
        webKlas.driver().findElement(Login.loginButton).click();
    }

}
