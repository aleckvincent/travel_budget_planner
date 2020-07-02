package avm.io.travel.service;

import avm.io.travel.dto.TravelDTO;

import java.util.List;

public interface ITravelService {

    TravelDTO save(TravelDTO travel);

    TravelDTO show(long id);

    List<TravelDTO> list();

    void deleteById(long id);

    void deleteAll();


}
