package be.witspirit.parochieblad.herabonnering;

import java.util.Optional;

public class Subscription {

    private final Optional<String> subscriptionNr;
    private final PaymentBehavior paymentBehavior;
    private final PaymentStatement paymentStatement;

    public Subscription(Optional<String> subscriptionNr, PaymentBehavior paymentBehavior, PaymentStatement ps) {
        this.subscriptionNr = subscriptionNr;
        this.paymentBehavior = paymentBehavior;
        this.paymentStatement = ps;
    }

    public boolean isAutomatable() {
        return subscriptionNr.isPresent();
    }

    public boolean isAutoApproved() {
        return !isReviewRequired() && !isIncomplete();
    }

    public boolean isReviewRequired() {
        return paymentBehavior == PaymentBehavior.ANOMALY;
    }

    public boolean isIncomplete() {
        return subscriptionNr.isEmpty();
    }

    @Override
    public String toString() {
        return String.format("%-20s ; %-10s ; %s",
                subscriptionNr,
                paymentBehavior,
                paymentStatement
        );
    }
}
