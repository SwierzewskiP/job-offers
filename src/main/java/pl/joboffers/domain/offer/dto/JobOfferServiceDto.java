package pl.joboffers.domain.offer.dto;

import lombok.Builder;

@Builder
public record JobOfferServiceDto(
    String title,
    String company,
    String salary,
    String offerUrl) {
}