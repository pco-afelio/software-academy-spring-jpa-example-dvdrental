package be.afelio.software_academy.pco.spring_jpa.example.dvdrental;

import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import be.afelio.software_academy.pco.spring_jpa.example.dvdrental.entities.CityEntity;
import be.afelio.software_academy.pco.spring_jpa.example.dvdrental.entities.CountryEntity;
import be.afelio.software_academy.pco.spring_jpa.example.dvdrental.spring_repositories.AddressSpringRepository;
import be.afelio.software_academy.pco.spring_jpa.example.dvdrental.spring_repositories.CitySpringRepository;
import be.afelio.software_academy.pco.spring_jpa.example.dvdrental.spring_repositories.RentalSpringRepository;
import be.afelio.software_academy.spring_jpa.example.dvdrental.DvdRentalExampleSpringJpaRepository;
import be.afelio.software_academy.spring_jpa.example.dvdrental.beans.Address;
import be.afelio.software_academy.spring_jpa.example.dvdrental.beans.City;
import be.afelio.software_academy.spring_jpa.example.dvdrental.beans.Rental;
import be.afelio.software_academy.spring_jpa.example.dvdrental.exceptions.DuplicatedCityException;

@Component
public class DvdRentalExampleSpringJpaRepositoryImpl implements DvdRentalExampleSpringJpaRepository {
	
	// @Autowired
	// private EntityManagerFactory entityManagerFactory;
	
	@PersistenceContext
	private EntityManager em;
	
	@Autowired private CitySpringRepository citySpringRepository;
	@Autowired private AddressSpringRepository addressSpringRepository;
	@Autowired private RentalSpringRepository rentalSpringRepository;
	
	public DvdRentalExampleSpringJpaRepositoryImpl() {
		System.out.println("*********************");
		System.out.println("DvdRentalExampleSpringJpaRepositoryImpl.DvdRentalExampleSpringJpaRepositoryImpl()");
		// System.out.println(entityManagerFactory);
		// em = entityManagerFactory.createEntityManager(); // NullPointerException !
		System.out.println("*********************");
	}
	
	@Override
	public CountryEntity findOneCountryByName(String name) {
		// System.out.println("DvdRentalExampleSpringJpaRepositoryImpl.findOneCountryByName()");
		// System.out.println(entityManagerFactory);
		// EntityManager em = entityManagerFactory.createEntityManager();
		CountryEntity country = null;
		if (name != null && !name.isBlank()) {
			TypedQuery<CountryEntity> query = em.createNamedQuery("findOneCountryByName", CountryEntity.class);
			query.setParameter(1, name);
			try {
				country = query.getSingleResult();
			} catch(NoResultException ignored) {}
		}
		return country;
	}

	@Override
	public List<? extends City> findAllCitiesByCountryName(String name) {
		return citySpringRepository.findAllByCountryName(name);
	}

	@Override
	public List<? extends Address> findAllStoreAddressesByCountryName(String name) {
		System.out.println("**** " + addressSpringRepository);
		return addressSpringRepository.getAllByCityCountryName(name);
	}

	@Override
	@Transactional
	public City createCity(String cityName, String countryName) {
		CityEntity city = null;
		if (cityName != null && !cityName.isBlank() 
				&& countryName != null && !countryName.isBlank()) {
			if (citySpringRepository.findOneByNameAndCountryName(cityName, countryName) != null) {
				throw new DuplicatedCityException();
			}
			CountryEntity country = findOneCountryByName(countryName);
			if (country == null) {
				country = new CountryEntity();
				country.setName(countryName);
				em.persist(country);
			}
			city = new CityEntity();
			city.setCountry(country);
			city.setName(cityName);
			citySpringRepository.save(city);
		}
		return city;
	}

	@Override
	public boolean deleteCity(String cityName, String countryName) {
		boolean deleted = false;
		CityEntity city = citySpringRepository.findOneByNameAndCountryName(cityName, countryName);
		if (city != null) {
			citySpringRepository.delete(city);
			deleted = true;
		}
		return deleted;
	}

	@Override
	public List<? extends Rental> findAllRentalsByFilmTitle(String title) {
		return rentalSpringRepository.findAllByInventoryFilmTitleOrderByRentalDate(title);
	}

	@Override
	public boolean updateRentalReturnDate(int rentalId, Date returnDate) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Rental createAndStoreRental(String filmTitle, String storeAddress, String customerEmail,
			String staffUsername) {
		// TODO Auto-generated method stub
		return null;
	}

}
