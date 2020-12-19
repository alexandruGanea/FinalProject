package business.service;

import business.dto.MealDTO;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import persistence.HibernateUtil;
import persistence.dao.HotelDAO;
import persistence.dao.MealDAO;
import persistence.entities.Hotel;
import persistence.entities.Meal;

@Service
public class MealService {

    @Autowired
    MealDAO mealDAO;
    @Autowired
    HotelDAO hotelDAO;
    @Autowired
    HotelService hotelService;

    public void insertMeal(MealDTO mealDTO) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Meal meal = new Meal(mealDTO.getPriceBB(), mealDTO.getPriceHB(), mealDTO.getPriceFB(), mealDTO.getPriceAI());
        meal.setHotelWithMeals(checkForHotel(session, mealDTO));
        mealDAO.insertMeal(session, meal);
        session.close();
    }

    public Meal insertMeal(Session session, MealDTO mealDTO) {
        Meal meal = new Meal(mealDTO.getPriceBB(), mealDTO.getPriceHB(), mealDTO.getPriceFB(), mealDTO.getPriceAI());
        meal.setHotelWithMeals(checkForHotel(session, mealDTO));
        mealDAO.insertMeal(session, meal);
        return meal;
    }

    private Hotel checkForHotel(Session session, MealDTO mealDTO) {
        if (hotelService.isInserted(session, mealDTO.getHotelDTO())) {
            return hotelDAO.findHotelByNameAndCity(session, mealDTO.getHotelDTO().getName(), mealDTO.getHotelDTO().getCityDTO().getName());
        } else {
            return hotelService.insertHotel(session, mealDTO.getHotelDTO());
        }
    }

    private boolean isInserted(Session session, MealDTO mealDTO) {
        Integer hotelId = hotelDAO.findHotelIdByNameAndCity(session, mealDTO.getHotelDTO().getName(), mealDTO.getHotelDTO().getCityDTO().getName());
        Integer foundId = mealDAO.findMealIdByHotelId(session, hotelId);
        return foundId != 0;
    }

    public boolean isInserted(MealDTO mealDTO) {
        Integer hotelId = hotelDAO.findHotelIdByNameAndCity(mealDTO.getHotelDTO().getName(), mealDTO.getHotelDTO().getCityDTO().getName());
        Integer foundId = mealDAO.findMealIdByHotelId(hotelId);
        return foundId != 0;
    }
}
