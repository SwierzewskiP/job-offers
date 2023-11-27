package pl.swierzewski.domain.offer;

import pl.swierzewski.domain.offer.dto.JobOfferServiceDto;
import pl.swierzewski.domain.offer.dto.OfferRequestDto;
import pl.swierzewski.domain.offer.dto.OfferResponseDto;

public class OfferMapper {

    public static OfferResponseDto mapFromOfferToOfferDto(Offer offer) {
        return OfferResponseDto.builder()
                .id(offer.id())
                .companyName(offer.companyName())
                .position(offer.position())
                .salary(offer.salary())
                .offerUrl(offer.offerUrl())
                .build();
    }

    public static Offer mapFromOfferDtoToOffer(OfferRequestDto offerDto) {
        return Offer.builder()
                .companyName(offerDto.companyName())
                .position(offerDto.position())
                .salary(offerDto.salary())
                .offerUrl(offerDto.offerUrl())
                .build();
    }

    public static Offer mapFromOfferServiceDtoToOffer(JobOfferServiceDto offerServiceDto) {
        return Offer.builder()
                .offerUrl(offerServiceDto.offerUrl())
                .salary(offerServiceDto.salary())
                .position(offerServiceDto.title())
                .companyName(offerServiceDto.company())
                .build();
    }
}