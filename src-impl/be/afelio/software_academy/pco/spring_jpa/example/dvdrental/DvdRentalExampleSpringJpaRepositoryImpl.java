package be.afelio.software_academy.pco.spring_jpa.example.dvdrental;

import java.util.*;

import be.afelio.software_academy.spring_jpa.example.dvdrental.DvdRentalExampleSpringJpaRepository;
import be.afelio.software_academy.spring_jpa.example.dvdrental.beans.*;

public class DvdRentalExampleSpringJpaRepositoryImpl implements DvdRentalExampleSpringJpaRepository {

	@Override
	public Country findOneCountryByName(String name) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<? extends City> findAllCitiesByCountryName(String name) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<? extends Address> findAllStoreAddressesByCountryName(String name) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public City createCity(String cityName, String countryName) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean deleteCity(String cityName, String countryName) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public List<? extends Rental> findAllRentalsByFilmTitle(String title) {
		// TODO Auto-generated method stub
		return null;
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
