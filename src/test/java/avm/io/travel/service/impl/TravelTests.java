package avm.io.travel.service.impl;


import avm.io.travel.dto.TravelDTO;
import avm.io.travel.exception.BadEndDateException;
import org.junit.After;
import org.junit.BeforeClass;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import static org.junit.Assert.*;
import java.util.Calendar;
import java.util.List;
import java.util.Set;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TravelTests {

    @Autowired
    TravelService service;

    private static Validator validator;

    @Rule
    public ExpectedException thrown = ExpectedException.none();

    private static TravelDTO travel;
    private static  Calendar startCal;
    private static  Calendar endCal;


    @BeforeClass
    public static void setUp() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();

        travel = new TravelDTO();
        travel.setTitle("London 2K20");
        startCal = Calendar.getInstance();
        startCal.set(2020,10,8);
        travel.setStartDate(startCal);
        endCal = Calendar.getInstance();
        endCal.set(2020,10,13);
        travel.setEndDate(endCal);
    }

    @After
    public void tearDown() throws Exception{
        service.deleteAll();
    }




    @Test
    public void createTravel() {

        TravelDTO result = service.save(travel);
        assertEquals(travel.getTitle(), result.getTitle());
    }

    @Test
    public void createTravelWithEmptyFields() {

        TravelDTO travel = new TravelDTO();
        travel.setTitle("Amsterdam");
        Set<ConstraintViolation<TravelDTO>> constraintViolations = validator.validate(travel);
        assertEquals(2, constraintViolations.size());
        assertEquals("ne doit pas être nul", constraintViolations.iterator().next().getMessage());

    }

    @Test(expected = BadEndDateException.class)
    public void createTravelWithEndDateBeforeStartDate() {

        TravelDTO travel = new TravelDTO();
        travel.setTitle("Syracuse");
        Calendar startCal = Calendar.getInstance();
        startCal.set(2020,10,8);
        travel.setStartDate(startCal);
        Calendar endCal = Calendar.getInstance();
        startCal.set(2020,10,6);
        travel.setEndDate(endCal);
        service.save(travel);

    }

    @Test
    public void testFindOne() {

        TravelDTO toronto = service.save(travel);
        TravelDTO result = service.show(toronto.getId());
        assertEquals(toronto.getId(), result.getId());
    }

    @Test
    public void testDeleteById() {

        TravelDTO saveResult = service.save(travel);
        List<TravelDTO> travelDTOs = service.list();
        assertEquals(1, travelDTOs.size());
        service.deleteById(saveResult.getId());
        travelDTOs = service.list();
        assertEquals(0, travelDTOs.size());

    }

    @Test
    public void testGetTravels() {

        service.save(travel);
        List<TravelDTO> travelDTOs = service.list();
        assertEquals(1, travelDTOs.size());
    }

    @Test
    public void testDeleteAll() {

        service.save(travel);

        travel.setTitle("Montreal 2K22");
        startCal.set(2022,10,8);
        travel.setStartDate(startCal);
        endCal.set(2022,10,11);
        travel.setEndDate(endCal);
        service.save(travel);

        List<TravelDTO> travelDTOs = service.list();
        assertEquals(2, travelDTOs.size());
        service.deleteAll();
        travelDTOs = service.list();
        assertEquals(0, travelDTOs.size());
    }
}
