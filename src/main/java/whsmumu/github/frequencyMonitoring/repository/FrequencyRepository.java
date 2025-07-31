package whsmumu.github.frequencyMonitoring.repository;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import whsmumu.github.frequencyMonitoring.model.Frequency;
import java.time.LocalDate;


public interface FrequencyRepository extends JpaRepository<Frequency, UUID>{

    List<Frequency> findByData(LocalDate data);

     @Query("SELECT f FROM Frequency f WHERE MONTH(f.data) = :mes AND YEAR(f.data) = :ano")
    List<Frequency> findByMonthAndYear(int mes, int ano);
    
    @Query("SELECT f FROM Frequency f WHERE YEAR(f.data) = :ano")
    List<Frequency> findByYear(int ano);

    List<Frequency> findByDataAndIdNot(LocalDate data, UUID id);

    

}
