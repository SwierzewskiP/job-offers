package pl.joboffers;

public interface SampleJobOfferResponse {

    default String bodyWithZeroOffersJson() {
        return "[]";
    }
}
