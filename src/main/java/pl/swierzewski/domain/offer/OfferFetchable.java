package pl.swierzewski.domain.offer;

import pl.swierzewski.domain.offer.dto.JobOfferServiceDto;

import java.util.List;

public interface OfferFetchable {
    List<JobOfferServiceDto> fetchOffers();
}