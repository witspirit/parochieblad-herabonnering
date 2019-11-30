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
                Arguments.of("K EN L 2020 ABONR 40502563", "40502563"), // Clean
                Arguments.of("ANR 40502520", "40502520"), // Shortened
                Arguments.of("KEN L 2020 - ABONR.40502572", "40502572") // Attached to other content
                );
    }

    @ParameterizedTest
    @MethodSource("samples")
    void successfulExtraction(String input, String expectedExtraction) {
        Optional<String> subscriptionNr = SubscriptionNrExtractor.extract(input);
        assertThat(subscriptionNr).get().isEqualTo(expectedExtraction);
    }

    @Test
    void missing() {
        String note = "ABONNEMENT";
        Optional<String> subscriptionNr = SubscriptionNrExtractor.extract(note);
        assertThat(subscriptionNr).isEmpty();
    }

}
