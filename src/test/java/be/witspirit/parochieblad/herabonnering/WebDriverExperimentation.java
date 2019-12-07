package be.witspirit.parochieblad.herabonnering;

import be.witspirit.parochieblad.herabonnering.webklas.WebKlasConfig;
import be.witspirit.parochieblad.herabonnering.webklas.ui.MainPage;
import be.witspirit.parochieblad.herabonnering.webklas.ui.WebKlas;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class WebDriverExperimentation {

    @Autowired
    private WebKlasConfig klas;

    @Test
    void navigateToWebKlas() {
        System.setProperty("webdriver.chrome.driver", klas.getChromeDriverPath());
        WebDriver driver = new ChromeDriver();

        WebKlas webKlas = new WebKlas(klas.getUrl(), driver);
        MainPage mainPage = webKlas.login(klas.getEmail(), klas.getEdition(), klas.getPassword());

    }
}
