package whsmumu.github.frequencyMonitoring.validator;

import java.util.List;

import org.springframework.stereotype.Component;

import lombok.RequiredArgsConstructor;
import whsmumu.github.frequencyMonitoring.exceptions.RecordDuplicateException;
import whsmumu.github.frequencyMonitoring.model.Membro;
import whsmumu.github.frequencyMonitoring.repository.MembroRepository;

@Component
@RequiredArgsConstructor
public class MembroValidator {

    private final MembroRepository membroRepository;

    public void validate(Membro membro) {
        if (existsRegisteredMembro(membro)) {
            throw new RecordDuplicateException("Membro já cadastrado com este número de celular.");
        }
        if (membro.getCelula() == null) {
            throw new IllegalArgumentException("Membro deve pertencer a uma célula.");
        }
    }

    private boolean existsRegisteredMembro(Membro membro) {
        if (membro.getPhone() == null || membro.getPhone().isEmpty()) {
            return false;
        }

        List<Membro> membrosFound;

        if (membro.getId() == null) {
            membrosFound = membroRepository.findByPhone(membro.getPhone());
        } else {
            membrosFound = membroRepository.findByPhoneAndIdNot(membro.getPhone(), membro.getId());
        }

        return !membrosFound.isEmpty();
    }

}
