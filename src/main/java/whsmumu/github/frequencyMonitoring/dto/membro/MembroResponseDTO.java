package whsmumu.github.frequencyMonitoring.dto.membro;

import whsmumu.github.frequencyMonitoring.enums.CategoriaMembro;
import whsmumu.github.frequencyMonitoring.enums.Genero;


public record MembroResponseDTO(
    Long id,
    String name,
    String phone,
    CategoriaMembro categoriaMembro,
    Genero genero,
    Long celulaId
) {}
