package be.witspirit.parochieblad.herabonnering.webklas;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties("webklas")
public class WebKlasConfig {
    /**
     * The start URL for WebKlas
     */
    private String url;

    /**
     * The e-mail address to use for login
     */
    private String email;

    /**
     * The edition number to use for login
     */
    private String edition;

    /**
     * The password to use for login
     */
    private String password;

    /**
     * That path to the chromedriver executable
     */
    private String chromeDriverPath;

    public String getUrl() {
        return url;
    }

    public WebKlasConfig setUrl(String url) {
        this.url = url;
        return this;
    }

    public String getEmail() {
        return email;
    }

    public WebKlasConfig setEmail(String email) {
        this.email = email;
        return this;
    }

    public String getEdition() {
        return edition;
    }

    public WebKlasConfig setEdition(String edition) {
        this.edition = edition;
        return this;
    }

    public String getPassword() {
        return password;
    }

    public WebKlasConfig setPassword(String password) {
        this.password = password;
        return this;
    }

    public String getChromeDriverPath() {
        return chromeDriverPath;
    }

    public WebKlasConfig setChromeDriverPath(String chromeDriverPath) {
        this.chromeDriverPath = chromeDriverPath;
        return this;
    }
}
