package be.witspirit.parochieblad.herabonnering;

import com.opencsv.bean.CsvToBeanBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@SpringBootApplication
public class HerabonneringApplication implements CommandLineRunner {
    private static final Logger log = LoggerFactory.getLogger(HerabonneringApplication.class);

    public static void main(String[] args) {
        SpringApplication.run(HerabonneringApplication.class, args);
    }

    private SubscriptionConfig config;

    public HerabonneringApplication(SubscriptionConfig config) {
        this.config = config;
    }

    @Override
    public void run(String... args) throws Exception {
        List<PaymentStatement> paymentStatements = loadPaymentStatements(config.getInputFilepath());

        SubscriptionReport report = analyze(paymentStatements);


        log.info("***********************   Clean Candidates   **************************");
        report.getAutomatableSubscriptions().forEach(s -> log.info(s.toString()));

        log.info("***********************   Dirty Candidates   **************************");
        report.getIncompleteSubscriptions().forEach(s -> log.info(s.toString()));


        int total = report.nrOfSubscriptions();
        int automatable = report.getAutomatableSubscriptions().size();
        int autoApproved = report.getAutoApprovedSubscriptions().size();
        int incomplete = report.getIncompleteSubscriptions().size();
        int reviewRequired = report.getReviewRequiredSubscriptions().size();

        log.info("*************** Statistics ***********");
        log.info("Auto Approved   = {}/{}", autoApproved, total);
        log.info("Automatable     = {}/{}", automatable, total);
        log.info("Review Required = {}/{}", reviewRequired, total);
        log.info("Incomplete      = {}/{}", incomplete, total);
    }

    private SubscriptionReport analyze(List<PaymentStatement> paymentStatements) {
        return new SubscriptionReport(paymentStatements.stream()
                .map(this::analyze)
                .flatMap(Optional::stream)
                .collect(Collectors.toList())
        );
    }

    private Optional<Subscription> analyze(PaymentStatement ps) {
        if (ps.getAmount().compareTo(BigDecimal.ZERO) <= 0) {
            return Optional.empty();
        }

        Optional<String> subscriptionNr = SubscriptionNrExtractor.extract(ps.getFreeNote());
        PaymentBehavior paymentBehavior = determinePaymentBehavior(ps.getAmount());

        return Optional.of(new Subscription(subscriptionNr, paymentBehavior, ps));
    }

    private PaymentBehavior determinePaymentBehavior(BigDecimal amountPaid) {
        // The BigDecimals make this a bit cumbersome and hard to read :-(

        int standardComparison = amountPaid.compareTo(config.getStandardSubscriptionFee());
        if (standardComparison == 0) {
            return PaymentBehavior.STANDARD;
        }
        if (standardComparison < 0) {
            return PaymentBehavior.ANOMALY;
        }

        // We also establish an anomaly if someone pays much more than expected. Just to ensure we can check intent.
        BigDecimal highBar = config.getStandardSubscriptionFee().multiply(new BigDecimal("1.5"));
        if (amountPaid.compareTo(highBar) > 0) {
            return PaymentBehavior.ANOMALY;
        }

        return PaymentBehavior.SUPPORT;
    }

    private List<PaymentStatement> loadPaymentStatements(String inputFilepath) throws FileNotFoundException {
        // NOTE: Had to add an extra ; to the header as all the other rows are also terminated with a ; Which in fact creates an empty final column.
        FileReader fileReader = new FileReader(inputFilepath);
        List<PaymentStatement> paymentStatements = new CsvToBeanBuilder<PaymentStatement>(fileReader)
                .withSeparator(';')
                .withType(PaymentStatement.class)
                .build().parse();
        log.info("Read CSV with {} entries", paymentStatements.size());
        return paymentStatements;
    }
}
