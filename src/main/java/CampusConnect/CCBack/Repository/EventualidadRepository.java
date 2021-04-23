package CampusConnect.CCBack.Repository;

import CampusConnect.CCBack.Model.Eventualidad;

import java.time.LocalDate;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.repository.CrudRepository;

public interface EventualidadRepository
    extends CrudRepository<Eventualidad, Long> {

       @Modifying
       @Transactional
       public void deleteByFechaBefore(LocalDate expiryDate);

}
