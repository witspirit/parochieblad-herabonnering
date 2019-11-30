package be.witspirit.parochieblad.herabonnering;

import com.opencsv.bean.CsvToBeanBuilder;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.FileReader;
import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

@SpringBootApplication
public class HerabonneringApplication implements CommandLineRunner {

    public static void main(String[] args) {
        SpringApplication.run(HerabonneringApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        // NOTE: Had to add an extra ; to the header as all the other rows are also terminated with a ; Which in fact creates an empty final column.
        FileReader fileReader = new FileReader("data/BE83736051764015_02-06-2019_tot_23-11-2019.csv");
        List<PaymentStatement> paymentStatements = new CsvToBeanBuilder<PaymentStatement>(fileReader)
                .withSeparator(';')
                .withType(PaymentStatement.class)
                .build().parse();
        System.out.println("Read CSV with "+paymentStatements.size()+" entries");

        List<PaymentStatement> candidateSubscriptions = paymentStatements.stream()
                .filter(paymentStatement -> paymentStatement.getAmount().compareTo(BigDecimal.ZERO) > 0)
                .collect(Collectors.toList());

        List<PaymentStatement> cleanCandidates = candidateSubscriptions.stream()
                .filter(s -> SubscriptionNrExtractor.extract(s.getFreeNote()).isPresent())
                .collect(Collectors.toList());

        List<PaymentStatement> dirtyCandidates = candidateSubscriptions.stream()
                .filter(s -> SubscriptionNrExtractor.extract(s.getFreeNote()).isEmpty())
                .collect(Collectors.toList());

        System.out.println("***********************   Clean Candidates   **************************");
        cleanCandidates.forEach(System.out::println);

        System.out.println("***********************   Dirty Candidates   **************************");
        dirtyCandidates.forEach(System.out::println);

        System.out.println(cleanCandidates.size()+"/"+candidateSubscriptions.size()+" Clean ; "+dirtyCandidates.size()+"/"+candidateSubscriptions.size()+" Dirty");


    }
}
