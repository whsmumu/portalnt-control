package whsmumu.github.frequencyMonitoring.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;
import whsmumu.github.frequencyMonitoring.dto.celula.CelulaRequestDTO;
import whsmumu.github.frequencyMonitoring.enums.CategoriaCelula;
import whsmumu.github.frequencyMonitoring.exceptions.RecordDuplicateException;
import whsmumu.github.frequencyMonitoring.exceptions.RecordNotFoundException;
import whsmumu.github.frequencyMonitoring.mapper.CelulaMapper;
import whsmumu.github.frequencyMonitoring.model.Celula;
import whsmumu.github.frequencyMonitoring.model.Lider;
import whsmumu.github.frequencyMonitoring.model.Membro;
import whsmumu.github.frequencyMonitoring.repository.CelulaRepository;
import whsmumu.github.frequencyMonitoring.repository.LiderRepository;
import whsmumu.github.frequencyMonitoring.validator.CelulaValidator;

@Service
@RequiredArgsConstructor
public class CelulaService {

    private final CelulaRepository celulaRepository;
    private final CelulaValidator celulaValidator;
    private final LiderRepository liderRepository;
    private final CelulaMapper celulaMapper;

     @Transactional
    public Celula create(CelulaRequestDTO requestDTO) {

        if (celulaRepository.existsByLiderId(requestDTO.liderId())) {
            throw new RecordDuplicateException("Este líder já está vinculado a outra célula.");
        }

        celulaRepository.findFirstByNameAndCategoriaCelula(requestDTO.name(), requestDTO.categoriaCelula())
            .ifPresent(c -> {
                throw new RecordDuplicateException("Já existe uma célula com este nome nesta categoria.");
            });

        Lider lider = liderRepository.findById(requestDTO.liderId())
                .orElseThrow(() -> new RecordNotFoundException("Líder com ID " + requestDTO.liderId() + " não encontrado."));

        Celula novaCelula = celulaMapper.toEntity(requestDTO);
    
        novaCelula.setLider(lider);
        lider.setCelula(novaCelula);

        return celulaRepository.save(novaCelula);
        
    }

    @Transactional(readOnly = true)
    public Celula findById(Long id) {
        return celulaRepository.findById(id)
                .orElseThrow(() -> new RecordNotFoundException("Célula com ID " + id + " não encontrada."));
    }

    @Transactional(readOnly = true)
    public List<Celula> findAllFiltered(String name, CategoriaCelula categoriaCelula) {
        if (name == null && categoriaCelula != null) {
            return celulaRepository.findByCategoriaCelula(categoriaCelula);
        } else if (name != null && categoriaCelula == null) {
            return celulaRepository.findByName(name);
        } else if (name != null && categoriaCelula != null) {
            return celulaRepository.findByNameAndCategoriaCelula(name, categoriaCelula);
        } else {
            return celulaRepository.findAll();
        }
    }

    @Transactional
    public void delete(Long id) {
        Celula celulaParaDeletar = celulaRepository.findById(id)
                .orElseThrow(() -> new RecordNotFoundException("Célula com ID " + id + " não encontrada."));

        for (Membro membro : List.copyOf(celulaParaDeletar.getMembros())) {
            membro.setCelula(null);
        }

        Lider liderAssociado = celulaParaDeletar.getLider();
        if (liderAssociado != null) {
            liderAssociado.setCelula(null);
        }
        celulaRepository.delete(celulaParaDeletar);
    }

    @Transactional
    public Celula update(Long id, CelulaRequestDTO celulaRequestDTO) {
        Celula celulaExistente = this.findById(id);

  
        if (!celulaExistente.getLider().getId().equals(celulaRequestDTO.liderId())) {
            
            if (celulaRepository.existsByLiderId(celulaRequestDTO.liderId())) {
                throw new RecordDuplicateException("O novo líder selecionado já está vinculado a outra célula.");
            }
        }

        celulaMapper.updateFromDTO(celulaRequestDTO, celulaExistente);

        if (!celulaExistente.getLider().getId().equals(celulaRequestDTO.liderId())) {

            Lider liderAntigo = celulaExistente.getLider();
            if (liderAntigo != null) {
                liderAntigo.setCelula(null);
            }

            Lider novoLider = liderRepository.findById(celulaRequestDTO.liderId())
                    .orElseThrow(() -> new RecordNotFoundException(
                            "Novo líder com ID " + celulaRequestDTO.liderId() + " não encontrado."));

            celulaExistente.setLider(novoLider);
            novoLider.setCelula(celulaExistente);
        }

        celulaValidator.validate(celulaExistente);
        return celulaRepository.save(celulaExistente);
    }

}
