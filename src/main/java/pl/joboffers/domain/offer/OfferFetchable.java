package pl.joboffers.domain.offer;

import pl.joboffers.domain.offer.dto.JobOfferServiceDto;

import java.util.List;

public interface OfferFetchable {
    List<JobOfferServiceDto> fetchOffers();
}