package be.witspirit.parochieblad.herabonnering;

import com.opencsv.bean.CsvBindByName;

import java.math.BigDecimal;

public class PaymentStatement {

    @CsvBindByName(column="Rekeningnummer")
    private String accountNr;

    @CsvBindByName(column = "Rubrieknaam")
    private String rubric;

    @CsvBindByName(column = "Naam")
    private String name;

    @CsvBindByName(column = "Munt")
    private String currency;

    @CsvBindByName(column = "Afschriftnummer")
    private String statementNr;

    @CsvBindByName(column = "Datum")
    private String date;

    @CsvBindByName(column = "Omschrijving")
    private String description;

    @CsvBindByName(column = "Valuta")
    private String valueDate;

    @CsvBindByName(column = "Bedrag", locale = "nl-BE")
    private BigDecimal amount;

    @CsvBindByName(column = "Saldo", locale = "nl-BE")
    private BigDecimal balance;

    @CsvBindByName(column = "Credit", locale = "nl-BE")
    private BigDecimal credit;

    @CsvBindByName(column = "Debet", locale = "nl-BE")
    private BigDecimal debit;

    @CsvBindByName(column = "Rekening tegenpartij")
    private String counterPartyAccountNr;

    @CsvBindByName(column = "BIC code tegenpartij")
    private String counterPartyBic;

    @CsvBindByName(column = "Naam tegenpartij")
    private String counterPartyName;

    @CsvBindByName(column = "Adres tegenpartij")
    private String counterPartyAddress;

    @CsvBindByName(column = "gestructureerde mededeling")
    private String structuredNote;

    @CsvBindByName(column = "vrije mededeling")
    private String freeNote;

    public String getAccountNr() {
        return accountNr;
    }

    public String getRubric() {
        return rubric;
    }

    public String getName() {
        return name;
    }

    public String getCurrency() {
        return currency;
    }

    public String getStatementNr() {
        return statementNr;
    }

    public String getDate() {
        return date;
    }

    public String getDescription() {
        return description;
    }

    public String getValueDate() {
        return valueDate;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public BigDecimal getCredit() {
        return credit;
    }

    public BigDecimal getDebit() {
        return debit;
    }

    public String getCounterPartyAccountNr() {
        return counterPartyAccountNr;
    }

    public String getCounterPartyBic() {
        return counterPartyBic;
    }

    public String getCounterPartyName() {
        return counterPartyName;
    }

    public String getCounterPartyAddress() {
        return counterPartyAddress;
    }

    public String getStructuredNote() {
        return structuredNote;
    }

    public String getFreeNote() {
        return freeNote;
    }

    @Override
    public String toString() {
        // Relevant fields only
        return String.format("%-8s ; %-10s ; %-4s ; %-40s ; %-60s ; %-60s ; %s",
                statementNr,
                date,
                amount,
                counterPartyName,
                counterPartyAddress,
                freeNote,
                description
                );
    }
}
