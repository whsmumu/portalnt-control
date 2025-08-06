package whsmumu.github.frequencyMonitoring.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import whsmumu.github.frequencyMonitoring.model.Celula;
import whsmumu.github.frequencyMonitoring.model.Membro;

public interface MembroRepository extends JpaRepository<Membro, Long> {

    List<Membro> findByPhone(String phone);

    List<Membro> findByPhoneAndIdNot(String phone, Long id);

    List<Membro> findByName(String name);

    List<Membro> findByNameAndPhone(String name, String phone);

    @Modifying
    @Query("UPDATE Membro m SET m.celula = null WHERE m.celula.id = :celulaId")
    void desvincularMembrosDaCelula(@Param("celulaId") Long celulaId);

    List<Membro> findByCelula(Celula celula);

}
