package pl.joboffers.domain.offer;

import pl.joboffers.domain.offer.dto.JobOfferServiceDto;

import java.util.List;

public class InMemoryFetcherTestImpl implements OfferFetchable {

    List<JobOfferServiceDto> listOfOffers;

    public InMemoryFetcherTestImpl(List<JobOfferServiceDto> listOfOffers) {
        this.listOfOffers = listOfOffers;
    }

    @Override
    public List<JobOfferServiceDto> fetchOffers() {
        return listOfOffers;
    }
}
