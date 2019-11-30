package be.witspirit.parochieblad.herabonnering;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.Optional;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

public class SubscriptionNrExtractorTest {

    private static Stream<Arguments> samples() {
        return Stream.of(
                Arguments.of("K EN L 2020 ABONR 40502563", "40502563"),
                Arguments.of("ANR 40502520", "40502520"),
                Arguments.of("KEN L 2020 - ABONR.40502572", "40502572")
                );
    }

    @ParameterizedTest
    @MethodSource("samples")
    void successfulExtraction(String input, String expectedExtraction) {
        Optional<String> subscriptionNr = SubscriptionNrExtractor.extract(input);
        assertThat(subscriptionNr).get().isEqualTo(expectedExtraction);
    }

    @Test
    void cleanCase() {
        String note = "K EN L 2020 ABONR 40502563";
        Optional<String> subscriptionNr = SubscriptionNrExtractor.extract(note);
        assertThat(subscriptionNr).get().isEqualTo("40502563");
    }

    @Test
    void shortened() {
        String note = "ANR 40502520";
        Optional<String> subscriptionNr = SubscriptionNrExtractor.extract(note);
        assertThat(subscriptionNr).get().isEqualTo("40502520");
    }

    @Test
    void attached() {
        String note = "KEN L 2020 - ABONR.40502572";
        Optional<String> subscriptionNr = SubscriptionNrExtractor.extract(note);
        assertThat(subscriptionNr).get().isEqualTo("40502572");
    }

    @Test
    void missing() {
        String note = "ABONNEMENT";
        Optional<String> subscriptionNr = SubscriptionNrExtractor.extract(note);
        assertThat(subscriptionNr).isEmpty();
    }


}
