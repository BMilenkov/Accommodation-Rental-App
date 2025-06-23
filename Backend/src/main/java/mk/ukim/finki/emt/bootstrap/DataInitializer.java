package mk.ukim.finki.emt.bootstrap;

import jakarta.annotation.PostConstruct;
import mk.ukim.finki.emt.model.domain.*;
import mk.ukim.finki.emt.model.enumerations.Category;
import mk.ukim.finki.emt.model.enumerations.Role;
import mk.ukim.finki.emt.repository.*;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class DataInitializer {
    //Lists
    public static List<Country> countries = null;
    public static List<HostProfile> hostProfiles = null;
    public static List<User> users = null;
    public static List<Accommodation> accommodations = null;
    public static List<Review> reviews = null;

    //Repositories
    private final CountryRepository countryRepository;
    private final HostProfileRepository hostProfileRepository;
    private final UserRepository userRepository;
    private final AccommodationRepository accommodationRepository;
    private final ReviewRepository reviewRepository;

    //PasswordEncoder
    private final PasswordEncoder passwordEncoder;

    public DataInitializer(CountryRepository countryRepository, HostProfileRepository hostProfileRepository, UserRepository userRepository, AccommodationRepository accommodationRepository, ReviewRepository reviewRepository, PasswordEncoder passwordEncoder) {
        this.countryRepository = countryRepository;
        this.hostProfileRepository = hostProfileRepository;
        this.userRepository = userRepository;
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

        //Users
        users = new ArrayList<>();
        if (this.userRepository.count() == 0) {
            users.add(new User("branko.milenkov", passwordEncoder.encode("bm"),
                    "Branko", "Milenkov", Role.ROLE_HOST));
            users.add(new User("vase.lazarev", passwordEncoder.encode("vl"),
                    "Vase", "Lazarev", Role.ROLE_HOST));  // Germany
            users.add(new User("hristijan.stoimilov", passwordEncoder.encode("hs"),
                    "Hristijan", "Stoimilov", Role.ROLE_HOST));  // France
            users.add(new User("nikola.serafimov", passwordEncoder.encode("ns"),
                    "Nikola", "Serafimov", Role.ROLE_USER));  // Japan
            users.add(new User("martin.rozin.sopkov", passwordEncoder.encode("mrs"),
                    "Martin", "Rozin Sopkov", Role.ROLE_USER)); // Brazil
            this.userRepository.saveAll(users);
        }

        //HostProfiles
        hostProfiles = new ArrayList<>();
        if (this.hostProfileRepository.count() == 0) {
            hostProfiles.add(new HostProfile(countries.get(0), users.get(0)));  // USA
            hostProfiles.add(new HostProfile(countries.get(1), users.get(1)));  // Germany
            hostProfiles.add(new HostProfile(countries.get(2), users.get(2)));  // France
//            hostProfiles.add(new HostProfile(countries.get(3), users.get(0)));  // Japan
//            hostProfiles.add(new HostProfile(countries.get(4), users.get(0))); // Brazil
            this.hostProfileRepository.saveAll(hostProfiles);
        }

        //Accommodations
        accommodations = new ArrayList<>();
        if (this.accommodationRepository.count() == 0) {
            accommodations.add(new Accommodation("Luxury Resort", Category.HOTEL, hostProfiles.get(0), 100));
            accommodations.add(new Accommodation("Cozy Apartment", Category.APARTMENT, hostProfiles.get(1), 3));
            accommodations.add(new Accommodation("Budget Hostel", Category.MOTEL, hostProfiles.get(2), 20));
            accommodations.add(new Accommodation("Traditional Ryoko", Category.HOUSE, hostProfiles.get(0), 15));
            accommodations.add(new Accommodation("Beach Bungalow", Category.FLAT, hostProfiles.get(1), 5));
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
