package avm.io.travel.service.impl;

import avm.io.travel.dto.TravelDTO;
import avm.io.travel.exception.BadEndDateException;
import avm.io.travel.persistance.entity.Travel;
import avm.io.travel.persistance.repository.ITravelRepository;
import avm.io.travel.service.ITravelService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TravelService implements ITravelService {

    @Autowired
    private ITravelRepository repository;

    @Autowired
    private ModelMapper modelMapper;


    @Override
    public TravelDTO save(TravelDTO travel) throws BadEndDateException {

        Travel entity = modelMapper.map(travel, Travel.class);

        entity = repository.save(entity);

        if (travel.getEndDate().before(travel.getStartDate())) {
            throw new BadEndDateException("La date de fin ne doit pas être antérieur à la date de départ");
        }
        return modelMapper.map(entity, TravelDTO.class);
    }
}
