package be.witspirit.parochieblad.herabonnering;

public enum PaymentBehavior {
    STANDARD, // The normal fee has been payed
    SUPPORT,  // More than the normal fee has been payed
    ANOMALY   // Less than standard fee or other kind of anomaly
    ;
}
