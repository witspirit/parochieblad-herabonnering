package be.witspirit.parochieblad.herabonnering;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
@ConfigurationProperties(prefix = "subscription")
public class SubscriptionConfig {

    /**
     * The path to the payment statement CSV export from the bank. (Rekening uitreksel)
     */
    private String inputFilepath;

    /**
     * The standard subscription fee in Euro
     */
    private BigDecimal standardSubscriptionFee;


    public String getInputFilepath() {
        return inputFilepath;
    }

    public SubscriptionConfig setInputFilepath(String inputFilepath) {
        this.inputFilepath = inputFilepath;
        return this;
    }

    public BigDecimal getStandardSubscriptionFee() {
        return standardSubscriptionFee;
    }

    public SubscriptionConfig setStandardSubscriptionFee(BigDecimal standardSubscriptionFee) {
        this.standardSubscriptionFee = standardSubscriptionFee;
        return this;
    }
}
