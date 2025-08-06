package whsmumu.github.frequencyMonitoring.dto.celula;

import java.time.OffsetDateTime;
import java.util.List;
import whsmumu.github.frequencyMonitoring.dto.lider.LiderResponseDTO;
import whsmumu.github.frequencyMonitoring.dto.membro.MembroResponseDTO;
import whsmumu.github.frequencyMonitoring.enums.CategoriaCelula;

public record CelulaResponseDTO(
    Long id,
    String name,
    String bairro,
    CategoriaCelula categoriaCelula,
    OffsetDateTime dataCriacao, 
    LiderResponseDTO lider, 
    List<MembroResponseDTO> membros
) {}