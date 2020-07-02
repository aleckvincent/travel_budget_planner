package avm.io.travel.service.impl;

import avm.io.travel.dto.TravelDTO;
import avm.io.travel.exception.BadEndDateException;
import avm.io.travel.exception.NotFoundException;
import avm.io.travel.persistance.entity.Travel;
import avm.io.travel.persistance.repository.ITravelRepository;
import avm.io.travel.service.ITravelService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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

    @Override
    public TravelDTO show(long id) {

        Optional<Travel> result = repository.findById(id);

        if (result.isPresent()) {
            return modelMapper.map(result.get(), TravelDTO.class);
        } else {
            throw new NotFoundException("Travel not found.");
        }

    }

    @Override
    public List<TravelDTO> list() {

        List<Travel> list = repository.findAll();

        List<TravelDTO> travels = new ArrayList<TravelDTO>();

        for (int i = 0; i < list.size(); i++) {
            travels.add(modelMapper.map(list.get(i), TravelDTO.class));
        }
        return travels;
    }
}
