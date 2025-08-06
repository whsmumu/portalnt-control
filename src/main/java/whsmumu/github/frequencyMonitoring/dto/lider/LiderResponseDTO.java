package whsmumu.github.frequencyMonitoring.dto.lider;

import whsmumu.github.frequencyMonitoring.enums.Genero;

public record LiderResponseDTO(
    Long id,
    String name,
    Genero genero,
    String phone,
    Long celulaId
) {

}
