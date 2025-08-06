package whsmumu.github.frequencyMonitoring.validator;

import java.util.List;

import org.springframework.stereotype.Component;

import lombok.RequiredArgsConstructor;
import whsmumu.github.frequencyMonitoring.exceptions.RecordDuplicateException;
import whsmumu.github.frequencyMonitoring.model.Lider;
import whsmumu.github.frequencyMonitoring.repository.LiderRepository;

@Component
@RequiredArgsConstructor
public class LiderValidator {

    private final LiderRepository liderRepository;

    public void validate(Lider lider) {
        if (existsRegisteredLider(lider)) {
            throw new RecordDuplicateException("Líder já cadastrado com este número de celular.");
        }
    }

    private boolean existsRegisteredLider(Lider lider) {
        if (lider.getPhone() == null || lider.getPhone().isEmpty()) {
            return false;
        }

        List<Lider> lideresFound;
        
        if (lider.getId() == null) {
            lideresFound = liderRepository.findByPhone(lider.getPhone());
        } else {
            lideresFound = liderRepository.findByPhoneAndIdNot(lider.getPhone(), lider.getId());
        }

        return !lideresFound.isEmpty();
    }

}
