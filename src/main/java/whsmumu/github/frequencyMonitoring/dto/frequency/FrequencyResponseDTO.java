package whsmumu.github.frequencyMonitoring.dto.frequency;

import java.time.LocalDate;
import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonFormat;

public record FrequencyResponseDTO(
        UUID id,
        @JsonFormat(pattern = "dd/MM/yyyy") LocalDate data,
        Integer quantidadeMembrosHomem,
        Integer quantidadeMembrosMulheres,
        Integer quantidadeVisitantesHomem,
        Integer quantidadeVisitantesMulher,
        Integer quantidadeKids,
        Integer quantidadeBaby) {

}
