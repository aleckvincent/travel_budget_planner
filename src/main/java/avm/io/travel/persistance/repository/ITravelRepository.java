package avm.io.travel.persistance.repository;

import avm.io.travel.persistance.entity.Travel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ITravelRepository extends JpaRepository<Travel, Long> {
}
