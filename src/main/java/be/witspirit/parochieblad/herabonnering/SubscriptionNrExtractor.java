package be.witspirit.parochieblad.herabonnering;

import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SubscriptionNrExtractor {
    private static final Pattern SUBSCRIPTION_NR_PATTERN = Pattern.compile(".*(405\\d{5}).*");

    public static Optional<String> extract(String note) {
        Matcher subscriptionNrMatcher = SUBSCRIPTION_NR_PATTERN.matcher(note);
        if (subscriptionNrMatcher.matches()) {
            return Optional.of(subscriptionNrMatcher.group(1));
        }
        return Optional.empty();
    }
}
