package mk.ukim.finki.emt.bootstrap;

import jakarta.annotation.PostConstruct;
import mk.ukim.finki.emt.model.Accommodation;
import mk.ukim.finki.emt.model.Country;
import mk.ukim.finki.emt.model.Host;
import mk.ukim.finki.emt.model.enumerations.Category;
import mk.ukim.finki.emt.repository.AccommodationRepository;
import mk.ukim.finki.emt.repository.CountryRepository;
import mk.ukim.finki.emt.repository.HostRepository;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class DataHolder {
    //Lists
    public static List<Country> countries = null;
    public static List<Host> hosts = null;
    public static List<Accommodation> accommodations = null;

    //Repositories
    private final CountryRepository countryRepository;
    private final HostRepository hostRepository;
    private final AccommodationRepository accommodationRepository;

    //PasswordEncoder
//    private final PasswordEncoder passwordEncoder;

    public DataHolder(CountryRepository countryRepository, HostRepository hostRepository, AccommodationRepository accommodationRepository) {
        this.countryRepository = countryRepository;
        this.hostRepository = hostRepository;
        this.accommodationRepository = accommodationRepository;
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
            hosts.add(new Host("John", "Doe", countries.get(0)));  // USA
            hosts.add(new Host("Hans", "Müller", countries.get(1)));  // Germany
            hosts.add(new Host("Pierre", "Dupont", countries.get(2)));  // France
            hosts.add(new Host("Taro", "Yamamoto", countries.get(3)));  // Japan
            hosts.add(new Host("Carlos", "Silva", countries.get(4))); // Brazil
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
    }
}
