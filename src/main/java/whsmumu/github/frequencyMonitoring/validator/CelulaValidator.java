package whsmumu.github.frequencyMonitoring.validator;

import java.util.Optional;

import org.springframework.stereotype.Component;

import lombok.RequiredArgsConstructor;
import whsmumu.github.frequencyMonitoring.exceptions.RecordDuplicateException;
import whsmumu.github.frequencyMonitoring.model.Celula;
import whsmumu.github.frequencyMonitoring.repository.CelulaRepository;

@Component
@RequiredArgsConstructor
public class CelulaValidator {

    private final CelulaRepository celulaRepository;
    
    public void validate(Celula celula){ 

        findDuplicate(celula).ifPresent(c -> {
            throw new RecordDuplicateException("Já existe uma célula com o este nome nesta categoria.");
        });
    }
    
    private Optional<Celula> findDuplicate(Celula celula){
        if (celula.getId() == null) {
            return celulaRepository.findFirstByNameAndCategoriaCelula(
                    celula.getName(), 
                    celula.getCategoriaCelula());
        } else {
            return celulaRepository.findFirstByNameAndCategoriaCelulaAndIdNot(
                    celula.getName(), 
                    celula.getCategoriaCelula(), 
                    celula.getId());
        }
    }

}
