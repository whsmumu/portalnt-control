package whsmumu.github.frequencyMonitoring.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;
import whsmumu.github.frequencyMonitoring.dto.membro.MembroRequestDTO;
import whsmumu.github.frequencyMonitoring.exceptions.RecordNotFoundException;
import whsmumu.github.frequencyMonitoring.mapper.MembroMapper;
import whsmumu.github.frequencyMonitoring.model.Celula;
import whsmumu.github.frequencyMonitoring.model.Membro;
import whsmumu.github.frequencyMonitoring.repository.CelulaRepository;
import whsmumu.github.frequencyMonitoring.repository.MembroRepository;
import whsmumu.github.frequencyMonitoring.validator.MembroValidator;

@Service
@RequiredArgsConstructor
public class MembroService {

    private final MembroRepository membroRepository;
    private final MembroMapper membroMapper;
    private final MembroValidator membroValidator;
    private final CelulaRepository celulaRepository;

     @Transactional
    public Membro save(MembroRequestDTO membroRequestDTO) {

        Celula celula = celulaRepository.findById(membroRequestDTO.celulaId())
                .orElseThrow(() -> new RecordNotFoundException("Célula com ID " + membroRequestDTO.celulaId() + " não encontrada."));
        Membro novoMembro = membroMapper.toEntity(membroRequestDTO);
        
        novoMembro.setCelula(celula);

        membroValidator.validate(novoMembro);
        
        return membroRepository.save(novoMembro);
    }

    @Transactional(readOnly = true)
    public Optional<Membro> findById(Long id) {
        return membroRepository.findById(id);
    }

    @Transactional(readOnly = true)
    public List<Membro> findAllFiltered(String name, String phone) {
        if (name == null && phone != null) {
            return membroRepository.findByPhone(phone);
        } else if (name != null && phone == null) {
            return membroRepository.findByName(name);
        } else if (name != null && phone != null) {
            return membroRepository.findByNameAndPhone(name, phone);
        } else {
            return membroRepository.findAll();
        }

    }

    @Transactional
    public void deleteById(Long id) {
        if (!membroRepository.existsById(id)) {
            throw new RecordNotFoundException("Membro com ID não encontrada.");
        }

        membroRepository.deleteById(id);
    }

    @Transactional
    public Membro update(Long id, MembroRequestDTO membroRequestDTO) {
        Membro membroExistente = membroRepository.findById(id)
                .orElseThrow(() -> new RecordNotFoundException("Membro com ID " + id + " não encontrado."));

        membroMapper.updateFromDTO(membroRequestDTO, membroExistente);
        membroValidator.validate(membroExistente);
        return membroRepository.save(membroExistente);
    }

}
