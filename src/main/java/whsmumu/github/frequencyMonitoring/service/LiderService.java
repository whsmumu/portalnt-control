package whsmumu.github.frequencyMonitoring.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;
import whsmumu.github.frequencyMonitoring.dto.lider.LiderRequestDTO;
import whsmumu.github.frequencyMonitoring.exceptions.RecordDuplicateException;
import whsmumu.github.frequencyMonitoring.exceptions.RecordNotFoundException;
import whsmumu.github.frequencyMonitoring.mapper.LiderMapper;
import whsmumu.github.frequencyMonitoring.model.Lider;
import whsmumu.github.frequencyMonitoring.repository.LiderRepository;
import whsmumu.github.frequencyMonitoring.validator.LiderValidator;

@Service
@RequiredArgsConstructor
public class LiderService {

    private final LiderRepository liderRepository;
    private final LiderValidator liderValidator;
    private final LiderMapper liderMapper;

    @Transactional
    public Lider create(LiderRequestDTO requestDTO) {
        Lider novoLider = liderMapper.toEntity(requestDTO);
        liderValidator.validate(novoLider);
        return liderRepository.save(novoLider);
    }

    @Transactional(readOnly = true)
    public Optional<Lider> findById(Long id) {
        return liderRepository.findById(id);
    }

    @Transactional(readOnly = true)
    public List<Lider> find(String name, String phone) {
        if (name != null && phone != null) {
            return liderRepository.findByNameAndPhone(name, phone);
        } else if (name != null) {
            return liderRepository.findByName(name);
        } else if (phone != null) {
            return liderRepository.findByPhone(phone);
        } else {
            return liderRepository.findAll();
        }
    }

    @Transactional
    public Lider update(Long id, LiderRequestDTO requestDTO) {
        Lider liderExistente = liderRepository.findById(id)
                .orElseThrow(() -> new RecordNotFoundException("Líder com ID " + id + " não encontrado."));

        liderMapper.updateFromDTO(requestDTO, liderExistente);
        liderValidator.validate(liderExistente);
        return liderRepository.save(liderExistente);
    }

    @Transactional
    public void deleteById(Long id) {
        Lider liderParaDeletar = liderRepository.findById(id)
                .orElseThrow(
                        () -> new RecordNotFoundException("Líder com ID " + id + " não encontrado para exclusão."));

        if (liderParaDeletar.getCelula() != null) {

            throw new RecordDuplicateException("Este líder não pode ser excluído pois está ativo na célula: "
                    + liderParaDeletar.getCelula().getName());
        }

        liderRepository.delete(liderParaDeletar);
    }
}