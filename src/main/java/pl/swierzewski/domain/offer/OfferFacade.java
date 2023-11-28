package pl.swierzewski.domain.offer;

import lombok.AllArgsConstructor;
import pl.swierzewski.domain.offer.dto.OfferRequestDto;
import pl.swierzewski.domain.offer.dto.OfferResponseDto;

import java.util.List;

import static java.util.stream.Collectors.toList;

@AllArgsConstructor
public class OfferFacade {

    private final OfferRepository repository;
    private final OfferService service;

    public List<OfferResponseDto> findAllOffers() {
        return repository.findAll()
                .stream()
                .map(OfferMapper::mapFromOfferToOfferDto)
                .collect(toList());
    }

    public List<OfferResponseDto> fetchAllOffersAndSaveAllIfNotExists() {
        return service.fetchAllOffersAndSaveAllIfNotExists()
                .stream()
                .map(OfferMapper::mapFromOfferToOfferDto)
                .toList();
    }

    public OfferResponseDto findOfferById(String id) {
        return repository.findById(id)
                .map(OfferMapper::mapFromOfferToOfferDto)
                .orElseThrow(() -> new OfferNotFoundException(id));
    }

    public OfferResponseDto saveOffer(OfferRequestDto offerDto) {
        final Offer offer = OfferMapper.mapFromOfferDtoToOffer(offerDto);
        final Offer save = repository.save(offer);
        return OfferMapper.mapFromOfferToOfferDto(save);
    }
}