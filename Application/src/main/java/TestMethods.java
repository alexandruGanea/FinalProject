import persistence.dao.*;
import persistence.entities.*;

import java.util.HashSet;
import java.util.Set;

public class TestMethods {
/*
    public void testDB() {

        // instantiere entitati DAO
        ContinentDAO continentDAO = new ContinentDAO();
        CountryDAO countryDAO = new CountryDAO();
        CityDAO cityDAO = new CityDAO();
        AirportDAO airportDAO = new AirportDAO();
        FlightDAO flightDAO = new FlightDAO();
        HotelDAO hotelDAO = new HotelDAO();
        RoomDAO roomDAO = new RoomDAO();
        TravelPackageDAO travelPackageDAO = new TravelPackageDAO();

        // inseram continentul 1
        Continent america = new Continent("America");
        continentDAO.insertContinent(america);

        // inseram tara 1
        Country sua = new Country("SUA");
        Set<Country> countriesInAmerica = new HashSet<>();
        countriesInAmerica.add(sua);
        america.setCountriesOnContinent(countriesInAmerica);
        sua.setContinent(america);
        countryDAO.insertCountry(sua);

        // inseram orasul 1
        City newYork = new City("New York");
        Set<City> citiesInSUA = new HashSet<>();
        citiesInSUA.add(newYork);
        sua.setCitiesInCountry(citiesInSUA);
        newYork.setCountry(sua);
        cityDAO.insertCity(newYork);

        // inseram hotelul 1
        Hotel marriott = new Hotel("Marriott", 5, "The fucking Marriott");
        Set<Hotel> hotelsInNY = new HashSet<>();
        hotelsInNY.add(marriott);
        newYork.setHotelsInCity(hotelsInNY);
        marriott.setCityContainingHotel(newYork);
        hotelDAO.insertHotel(marriott);

        // inseram camera 1
        Room doubleRoom = new Room("double", 200, 10, false);
        roomDAO.insertRoom(doubleRoom);
        Set<Room> roomsInMarriott = new HashSet<>();
        roomsInMarriott.add(doubleRoom);
        marriott.setRoomsInHotel(roomsInMarriott);
        Set<Hotel> hotelsWithDoubleRoom = new HashSet<>();
        hotelsWithDoubleRoom.add(marriott);
        doubleRoom.setHotelsWithRoom(hotelsWithDoubleRoom);


        // inseram aeroportul 1
        Airport jfk = new Airport("JFK");
        Set<Airport> airportsInNY = new HashSet<>();
        newYork.setAirportsInCity(airportsInNY);
        jfk.setCityContainingAirport(newYork);
        airportDAO.insertAirport(jfk);

        // inseram continentul 2
        Continent europe = new Continent("Europe");
        continentDAO.insertContinent(europe);

        // inseram tara 2
        Country romania = new Country("Romania");
        Set<Country> countriesInEurope = new HashSet<>();
        countriesInEurope.add(romania);
        europe.setCountriesOnContinent(countriesInEurope);
        romania.setContinent(europe);
        countryDAO.insertCountry(romania);

        // inseram orasul 2
        City bucharest = new City("Bucharest");
        Set<City> citiesInRomania = new HashSet<>();
        citiesInRomania.add(bucharest);
        bucharest.setCountry(romania);
        romania.setCitiesInCountry(citiesInRomania);
        cityDAO.insertCity(bucharest);

        // inseram aeroportul 2
        Airport otp = new Airport("OTP");
        Set<Airport> airportsInBucharest = new HashSet<>();
        airportsInBucharest.add(otp);
        bucharest.setAirportsInCity(airportsInBucharest);
        otp.setCityContainingAirport(bucharest);
        airportDAO.insertAirport(otp);

        // inseram zbor dus
        Flight otp_jfk = new Flight("AGX2207",otp,"2020-11-29 14:00:00", jfk, "2020-11-30 10:00:00", 1500, 100);
        flightDAO.insertFlight(otp_jfk);

        // inseram zbor intors
        Flight jfk_otp = new Flight("JGA69", jfk,"2020-11-30 18:00:00", otp, "2020-12-02 14:30:00", 1500, 100);
        flightDAO.insertFlight(jfk_otp);


        // inseram un pachet de calatorie
        TravelPackage travelPackage = new TravelPackage();
        travelPackage.setDepartureAirport(otp);
        travelPackage.setDestinationAirport(jfk);
        travelPackage.setDepartureDate(otp_jfk.getDeparture());
        travelPackage.setReturnDate(jfk_otp.getArrival());
        travelPackage.setHotel(marriott);
        travelPackage.setPackageType("AI");
        travelPackage.setAdultPrice((doubleRoom.getGuestPrice()) + otp_jfk.getSeatPrice() + jfk_otp.getSeatPrice());
        travelPackage.setChildPrice((doubleRoom.getGuestPrice()) + otp_jfk.getSeatPrice() + jfk_otp.getSeatPrice());
        travelPackage.setPromoted(false);
        travelPackage.setAvailablePackages(Math.min(jfk_otp.getAvailableSeats(), otp_jfk.getAvailableSeats()));
        travelPackageDAO.insertTravelPackage(travelPackage);
    }
}

 */
}
