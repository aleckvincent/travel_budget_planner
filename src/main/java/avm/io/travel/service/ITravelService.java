package avm.io.travel.service;

import avm.io.travel.dto.TravelDTO;

import java.util.List;

/**
 * Created by Aleck VINCENT MINATCHY
 */
public interface ITravelService {

    /**
     * Create a new travel in database
     * @param travel
     * @return the travel created
     */
    TravelDTO save(TravelDTO travel);

    /**
     * Display a travel by his id
     * @param id
     * @return the travel found
     */
    TravelDTO show(long id);

    /**
     * Retrieve all travels in database
     * @return an array with all travels
     */
    List<TravelDTO> list();

    /**
     * Remove a travel from database thanks to his id
     * @param id
     */
    void deleteById(long id);

    /**
     * Remove all travel from database
     */
    void deleteAll();


}
