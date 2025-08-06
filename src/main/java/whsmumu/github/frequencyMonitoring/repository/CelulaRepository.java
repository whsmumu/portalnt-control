package whsmumu.github.frequencyMonitoring.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import whsmumu.github.frequencyMonitoring.enums.CategoriaCelula;
import whsmumu.github.frequencyMonitoring.model.Celula;
import java.util.List;
import java.util.Optional;


public interface CelulaRepository extends JpaRepository <Celula, Long> {

    List<Celula> findByNameAndCategoriaCelula(String name, CategoriaCelula categoriaCelula); // busca
    Optional<Celula> findFirstByNameAndCategoriaCelula(String name, CategoriaCelula categoriaCelula); // validação
    Optional<Celula> findFirstByNameAndCategoriaCelulaAndIdNot(String name, CategoriaCelula categoriaCelula, Long id); // validação update

    List<Celula> findByName(String name);
    List<Celula> findByCategoriaCelula(CategoriaCelula categoriaCelula);

    boolean existsByLiderId(Long liderId);

}
