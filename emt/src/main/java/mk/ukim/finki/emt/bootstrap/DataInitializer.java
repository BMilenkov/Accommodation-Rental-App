package mk.ukim.finki.emt.bootstrap;

import jakarta.annotation.PostConstruct;
import mk.ukim.finki.emt.model.domain.Accommodation;
import mk.ukim.finki.emt.model.domain.Country;
import mk.ukim.finki.emt.model.domain.Host;
import mk.ukim.finki.emt.model.domain.Review;
import mk.ukim.finki.emt.model.enumerations.Category;
import mk.ukim.finki.emt.model.enumerations.Role;
import mk.ukim.finki.emt.repository.AccommodationRepository;
import mk.ukim.finki.emt.repository.CountryRepository;
import mk.ukim.finki.emt.repository.HostRepository;
import mk.ukim.finki.emt.repository.ReviewRepository;
import mk.ukim.finki.emt.service.domain.ReviewService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class DataInitializer {
    //Lists
    public static List<Country> countries = null;
    public static List<Host> hosts = null;
    public static List<Accommodation> accommodations = null;
    public static List<Review> reviews = null;

    //Repositories
    private final CountryRepository countryRepository;
    private final HostRepository hostRepository;
    private final AccommodationRepository accommodationRepository;
    private final ReviewRepository reviewRepository;

    //PasswordEncoder
    private final PasswordEncoder passwordEncoder;

    public DataInitializer(CountryRepository countryRepository, HostRepository hostRepository, AccommodationRepository accommodationRepository, ReviewRepository reviewRepository, PasswordEncoder passwordEncoder) {
        this.countryRepository = countryRepository;
        this.hostRepository = hostRepository;
        this.accommodationRepository = accommodationRepository;
        this.reviewRepository = reviewRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @PostConstruct
    public void init() {
        //Countries
        countries = new ArrayList<>();
        if (this.countryRepository.count() == 0) {
            countries.add(new Country("United States", "North America"));
            countries.add(new Country("Germany", "Europe"));
            countries.add(new Country("France", "Europe"));
            countries.add(new Country("Japan", "Asia"));
            countries.add(new Country("Brazil", "South America"));
            this.countryRepository.saveAll(countries);
        }
        //Hosts
        hosts = new ArrayList<>();
        if (this.hostRepository.count() == 0) {
            hosts.add(new Host("branko.milenkov", passwordEncoder.encode("bm"),
                    "Branko", "Milenkov", countries.get(0), Role.ROLE_ADMIN));  // USA
            hosts.add(new Host("vase.lazarev", passwordEncoder.encode("vl"),
                    "Vase", "Lazarev", countries.get(1), Role.ROLE_USER));  // Germany
            hosts.add(new Host("hristijan.stoimilov", passwordEncoder.encode("hs"),
                    "Hristijan", "Stoimilov", countries.get(2), Role.ROLE_USER));  // France
            hosts.add(new Host("nikola.serafimov", passwordEncoder.encode("ns"),
                    "Nikola", "Serafimov", countries.get(3), Role.ROLE_USER));  // Japan
            hosts.add(new Host("martin.rozin.sopkov", passwordEncoder.encode("mrs"),
                    "Martin", "Rozin Sopkov", countries.get(4), Role.ROLE_USER)); // Brazil
            this.hostRepository.saveAll(hosts);
        }
        //Accommodations
        accommodations = new ArrayList<>();
        if (this.accommodationRepository.count() == 0) {
            accommodations.add(new Accommodation("Luxury Resort", Category.HOTEL, hosts.get(0), 100));
            accommodations.add(new Accommodation("Cozy Apartment", Category.APARTMENT, hosts.get(1), 3));
            accommodations.add(new Accommodation("Budget Hostel", Category.MOTEL, hosts.get(2), 20));
            accommodations.add(new Accommodation("Traditional Ryokan", Category.HOUSE, hosts.get(3), 15));
            accommodations.add(new Accommodation("Beach Bungalow", Category.FLAT, hosts.get(4), 5));
            this.accommodationRepository.saveAll(accommodations);
        }

        reviews = new ArrayList<>();
        if (this.reviewRepository.count() == 0) {
            reviews.add(new Review("Excellent service", 45, accommodations.get(0)));
            reviews.add(new Review("Not worth the price", 10, accommodations.get(1)));
            reviews.add(new Review("Decent experience", 25, accommodations.get(2)));
            reviews.add(new Review("Could be better", 18, accommodations.get(3)));
            reviews.add(new Review("Highly recommended!", 50, accommodations.get(4)));
            this.reviewRepository.saveAll(reviews);
        }
    }
}
