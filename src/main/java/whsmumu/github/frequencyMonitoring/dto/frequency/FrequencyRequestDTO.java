package whsmumu.github.frequencyMonitoring.dto.frequency;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonFormat;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.PositiveOrZero;

public record FrequencyRequestDTO(
        @NotNull(message = "Campo obrigatório") 
        @PastOrPresent(message = "Não pode ser uma data futura") 
        @JsonFormat(pattern = "dd/MM/yyyy") 
        LocalDate data,

        @NotNull(message = "Campo obrigatório") 
        @PositiveOrZero(message = "A quantidade não pode ser negativa") 
        Integer quantidadeMembrosHomem,

        @NotNull(message = "Campo obrigatório") 
        @PositiveOrZero(message = "A quantidade não pode ser negativa") 
        Integer quantidadeMembrosMulheres,

        @NotNull(message = "Campo obrigatório") 
        @PositiveOrZero(message = "A quantidade não pode ser negativa") 
        Integer quantidadeVisitantesHomem,

        @NotNull(message = "Campo obrigatório") 
        @PositiveOrZero(message = "A quantidade não pode ser negativa") 
        Integer quantidadeVisitantesMulher,

        @NotNull(message = "Campo obrigatório") 
        @PositiveOrZero(message = "A quantidade não pode ser negativa") 
        Integer quantidadeKids,

        @NotNull(message = "Campo obrigatório") 
        @PositiveOrZero(message = "A quantidade não pode ser negativa") 
        Integer quantidadeBaby) {
}
