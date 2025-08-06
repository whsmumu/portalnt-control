package whsmumu.github.frequencyMonitoring.service;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;
import whsmumu.github.frequencyMonitoring.dto.frequency.FrequencyRequestDTO;
import whsmumu.github.frequencyMonitoring.exceptions.RecordNotFoundException;
import whsmumu.github.frequencyMonitoring.mapper.FrequencyMapper;
import whsmumu.github.frequencyMonitoring.model.Frequency;
import whsmumu.github.frequencyMonitoring.repository.FrequencyRepository;
import whsmumu.github.frequencyMonitoring.validator.FrequencyValidator;

@Service
@RequiredArgsConstructor
public class FrequencyService {

    private final FrequencyRepository frequencyRepository;
    private final FrequencyValidator frequencyValidator;
    private final FrequencyMapper frequencyMapper;

    @Transactional
    public Frequency save(FrequencyRequestDTO requestDTO) {
        Frequency novaFrequencia = frequencyMapper.toEntity(requestDTO);
        frequencyValidator.validate(novaFrequencia);
        return frequencyRepository.save(novaFrequencia);

    }


    @Transactional(readOnly = true)
    public Frequency findById(UUID id) {
        return frequencyRepository.findById(id)
            .orElseThrow(() -> new RecordNotFoundException("Frequência com ID " + id + " não encontrada."));
    }

    @Transactional
    public void delete(UUID id) {
        if (!frequencyRepository.existsById(id)) {
            throw new RecordNotFoundException("Frequência com ID não encontrada.");
        }
    
        frequencyRepository.deleteById(id);
    }

    @Transactional
    public Frequency update(UUID id, FrequencyRequestDTO frequencyRequestDTO) {
        Frequency frequenciaExistente = frequencyRepository.findById(id)
            .orElseThrow(() -> new RecordNotFoundException("Frequência não encontrada."));

        
        frequencyMapper.updateFromDTO(frequencyRequestDTO, frequenciaExistente);
        frequencyValidator.validate(frequenciaExistente);
        return frequencyRepository.save(frequenciaExistente);
    }

    @Transactional(readOnly = true)
    public List<Frequency> findAllFiltered(LocalDate data, Integer mes, Integer ano) {
        if (data != null) {
            return frequencyRepository.findByData(data);
        } else if (ano != null && mes != null) {
            return frequencyRepository.findByMonthAndYear(mes, ano);
        } else if (ano != null) {
            return frequencyRepository.findByYear(ano);
        } else {
            return frequencyRepository.findAll();
        }
    }
}
