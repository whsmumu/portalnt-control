package whsmumu.github.frequencyMonitoring.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import whsmumu.github.frequencyMonitoring.model.Lider;

public interface LiderRepository extends JpaRepository<Lider, Long> {

    List<Lider> findByPhone(String phone);

    List<Lider> findByName(String name);

    List<Lider> findByNameAndPhone(String name, String phone);
    
    List<Lider> findByPhoneAndIdNot(String phone, Long id);

}
