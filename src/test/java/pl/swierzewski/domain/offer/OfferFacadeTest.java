package pl.swierzewski.domain.offer;

import org.assertj.core.api.AssertionsForClassTypes;
import org.junit.jupiter.api.Test;
import pl.swierzewski.domain.offer.dto.JobOfferServiceDto;
import pl.swierzewski.domain.offer.dto.OfferRequestDto;
import pl.swierzewski.domain.offer.dto.OfferResponseDto;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.catchThrowable;

public class OfferFacadeTest {

    @Test
    void should_fetch_jobs_from_remote_and_save_all_offers_when_repository_is_empty() {
        // given
        OfferFacade offerFacade = new OfferFacadeTestConfiguration().offerFacadeForTests();
        assertThat(offerFacade.findAllOffers()).isEmpty();

        // when
        List<OfferResponseDto> result = offerFacade.fetchAllOffersAndSaveAllIfNotExists();

        // then
        assertThat(result).hasSize(6);
    }

    @Test
    void should_save_only_2_offers_when_repository_had_4_added_with_offer_urls() {
        // given
        OfferFacade offerFacade = new OfferFacadeTestConfiguration(
                List.of(
                        new JobOfferServiceDto("id", "company", "5000", "url 1"),
                        new JobOfferServiceDto("aaa", "aaa", "5000", "url 2"),
                        new JobOfferServiceDto("bbb", "bbb", "5000", "url 3"),
                        new JobOfferServiceDto("ccc", "ccc", "5000", "url 4"),
                        new JobOfferServiceDto("Junior", "Comarch", "1000", "https://someurl.pl/5"),
                        new JobOfferServiceDto("Mid", "Finanteq", "2000", "https://someother.pl/6")
                )
        ).offerFacadeForTests();
        offerFacade.saveOffer(new OfferRequestDto("id", "company", "5000", "url 1"));
        offerFacade.saveOffer(new OfferRequestDto("aaa", "aaa", "5000", "url 2"));
        offerFacade.saveOffer(new OfferRequestDto("bbb", "bbb", "5000", "url 3"));
        offerFacade.saveOffer(new OfferRequestDto("ccc", "ccc", "5000", "url 4"));
        assertThat(offerFacade.findAllOffers()).hasSize(4);

        // when
        List<OfferResponseDto> response = offerFacade.fetchAllOffersAndSaveAllIfNotExists();

        // then
        assertThat(List.of(
                response.get(0).offerUrl(),
                response.get(1).offerUrl()
                )
        ).containsExactlyInAnyOrder("https://someurl.pl/5", "https://someother.pl/6");
    }

    @Test
    void should_save_4_offers_when_there_are_no_offers_in_database() {
        // given
        OfferFacade offerFacade = new OfferFacadeTestConfiguration(List.of()).offerFacadeForTests();

        //when
        offerFacade.saveOffer(new OfferRequestDto("id", "company", "5000", "url 1"));
        offerFacade.saveOffer(new OfferRequestDto("aaa", "aaa", "5000", "url 2"));
        offerFacade.saveOffer(new OfferRequestDto("bbb", "bbb", "5000", "url 3"));
        offerFacade.saveOffer(new OfferRequestDto("ccc", "ccc", "5000", "url 4"));

        // then
        assertThat(offerFacade.findAllOffers()).hasSize(4);
    }

    @Test
    void should_find_offer_by_id_when_offer_was_saved() {
        // given

        OfferFacade offerFacade = new OfferFacadeTestConfiguration(List.of()).offerFacadeForTests();
        OfferResponseDto offerResponseDto = offerFacade.saveOffer(
                new OfferRequestDto("id", "company", "5000", "url 1"));

        // when
        OfferResponseDto offerById = offerFacade.findOfferById(offerResponseDto.id());

        // then
        assertThat(offerById).isEqualTo(OfferResponseDto.builder()
                .id(offerResponseDto.id())
                .companyName("id")
                .position("company")
                .salary("5000")
                .offerUrl("url 1")
                .build()
        );
    }

    @Test
    void should_throw_not_found_exception_when_offer_not_found() {
        // given
        OfferFacade offerFacade = new OfferFacadeTestConfiguration(List.of()).offerFacadeForTests();
        assertThat(offerFacade.findAllOffers()).isEmpty();

        // when
        Throwable thrown = catchThrowable(() -> offerFacade.findOfferById("200"));

        // then
        AssertionsForClassTypes.assertThat(thrown)
                .isInstanceOf(OfferNotFoundException.class)
                .hasMessage("Offer with id 200 not found");
    }

    @Test
    void should_throw_duplicate_key_exception_when_offer_with_given_url_exists() {
        // given
        OfferFacade offerFacade = new OfferFacadeTestConfiguration(List.of()).offerFacadeForTests();
        OfferResponseDto offerResponseDto = offerFacade.saveOffer(
                new OfferRequestDto("aaa", "bbb", "3000", "url.pl"));
        String savedId = offerResponseDto.id();
        assertThat(offerFacade.findOfferById(savedId).id()).isEqualTo(savedId);

        // when
        Throwable thrown = catchThrowable(() -> offerFacade.saveOffer(
                new OfferRequestDto("cx", "ceo", "10000", "url.pl")));

        // then
        AssertionsForClassTypes.assertThat(thrown)
                .isInstanceOf(OfferDuplicateException.class)
                .hasMessage("Offer with offerUrl [url.pl] already exists");
    }
}