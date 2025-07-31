package whsmumu.github.frequencyMonitoring.controller.dto;

import java.time.LocalDate;
import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonFormat;

import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.PositiveOrZero;
import whsmumu.github.frequencyMonitoring.model.Frequency;


public record FrequencyDTO(
    UUID id,
    @NotNull(message = "Campo obrigatório")
    @PastOrPresent(message = "Não pode ser uma data futura")
    @JsonFormat(pattern = "dd/MM/yyyy")
    LocalDate data,
    @NotNull(message = "Campo obrigatório")
    @Digits(integer = 10, fraction = 0, message = "O valor deve ser um número inteiro")
    @PositiveOrZero(message = "A quantidade não pode ser negativa")
    Integer quantidadeMembrosHomem, 
    @NotNull(message = "Campo obrigatório")
    @Digits(integer = 10, fraction = 0, message = "O valor deve ser um número inteiro")
    @PositiveOrZero(message = "A quantidade não pode ser negativa")
    Integer quantidadeMembrosMulheres,
    @NotNull(message = "Campo obrigatório")
    @Digits(integer = 10, fraction = 0, message = "O valor deve ser um número inteiro")
    @PositiveOrZero(message = "A quantidade não pode ser negativa")
    Integer quantidadeVisitantesHomem, 
    @NotNull(message = "Campo obrigatório")
    @Digits(integer = 10, fraction = 0, message = "O valor deve ser um número inteiro")
    @PositiveOrZero(message = "A quantidade não pode ser negativa")
    Integer quantidadeVisitantesMulher, 
    @NotNull(message = "Campo obrigatório")
    @Digits(integer = 10, fraction = 0, message = "O valor deve ser um número inteiro")
    @PositiveOrZero(message = "A quantidade não pode ser negativa")
    Integer quantidadeKids,
    @NotNull(message = "Campo obrigatório")
    @Digits(integer = 10, fraction = 0, message = "O valor deve ser um número inteiro")
    @PositiveOrZero(message = "A quantidade não pode ser negativa")
    Integer quantidadeBaby) {

    public Frequency mapearFrequencia(){
        Frequency frequencia = new Frequency();
        frequencia.setData(this.data);
        frequencia.setQuantidadeMembrosHomem(this.quantidadeMembrosHomem);
        frequencia.setQuantidadeMembrosMulheres(this.quantidadeMembrosMulheres);
        frequencia.setQuantidadeVisitantesHomem(this.quantidadeVisitantesHomem);
        frequencia.setQuantidadeVisitantesMulher(this.quantidadeVisitantesMulher);
        frequencia.setQuantidadeKids(this.quantidadeKids);
        frequencia.setQuantidadeBaby(this.quantidadeBaby);
        return frequencia;
    }

}
