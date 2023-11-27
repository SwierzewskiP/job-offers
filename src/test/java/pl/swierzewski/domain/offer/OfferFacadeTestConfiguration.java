package pl.swierzewski.domain.offer;

import pl.swierzewski.domain.offer.dto.JobOfferServiceDto;

import java.util.List;

public class OfferFacadeTestConfiguration {

    private final InMemoryFetcherTestImpl inMemoryFetcherTest;
    private final InMemoryOfferRepository offerRepository;

    public OfferFacadeTestConfiguration() {
        this.inMemoryFetcherTest = new InMemoryFetcherTestImpl(
                List.of(
                        new JobOfferServiceDto("id", "company", "5000", "url 1"),
                        new JobOfferServiceDto("aaa", "aaa", "5000", "url 2"),
                        new JobOfferServiceDto("bbb", "bbb", "5000", "url 3"),
                        new JobOfferServiceDto("ccc", "ccc", "5000", "url 4"),
                        new JobOfferServiceDto("ddd", "ddd", "5000", "url 5"),
                        new JobOfferServiceDto("eee", "eee", "5000", "url 6")
                )
        );
        this.offerRepository = new InMemoryOfferRepository();
    }

    OfferFacadeTestConfiguration(List<JobOfferServiceDto> remoteClientOffers) {
        this.inMemoryFetcherTest = new InMemoryFetcherTestImpl(remoteClientOffers);
        this.offerRepository = new InMemoryOfferRepository();
    }

    OfferFacade offerFacadeForTests() {
        return new OfferFacade(offerRepository, new OfferService(inMemoryFetcherTest, offerRepository));
    }
}