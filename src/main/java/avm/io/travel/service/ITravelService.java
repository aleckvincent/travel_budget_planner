package avm.io.travel.service;

import avm.io.travel.dto.TravelDTO;

import java.util.List;

public interface ITravelService {

    public TravelDTO save(TravelDTO travel);

    public TravelDTO show(long id);

    public List<TravelDTO> list();
}
