package be.witspirit.parochieblad.herabonnering;

import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class SubscriptionReport {
    private List<Subscription> subscriptions;

    public SubscriptionReport(List<Subscription> subscriptions) {
        this.subscriptions = subscriptions;
    }

    public int nrOfSubscriptions() {
        return subscriptions.size();
    }

    public List<Subscription> getAutomatableSubscriptions() {
        return filterOn(Subscription::isAutomatable);
    }

    public List<Subscription> getAutoApprovedSubscriptions() {
        return filterOn(Subscription::isAutoApproved);
    }

    public List<Subscription> getReviewRequiredSubscriptions() {
        return filterOn(Subscription::isReviewRequired);
    }

    public List<Subscription> getIncompleteSubscriptions() {
        return filterOn(Subscription::isIncomplete);
    }

    private List<Subscription> filterOn(Predicate<? super Subscription> predicate) {
        return subscriptions.stream()
                .filter(predicate)
                .collect(Collectors.toList());
    }
}
