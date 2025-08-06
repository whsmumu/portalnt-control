package whsmumu.github.frequencyMonitoring.dto.membro;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import whsmumu.github.frequencyMonitoring.enums.CategoriaMembro;
import whsmumu.github.frequencyMonitoring.enums.Genero;

public record MembroRequestDTO(
    @NotBlank(message = "Campo obrigátorio")
    String name,

    @NotBlank(message = "Campo obrigátorio")
    @Pattern(regexp ="^(?:\\(?([1-9][1-9])\\)?\\s?)?(?:((?:9\\d|[2-9])\\d{3})-?(\\d{4}))$|^\\d{11}$", message = "Formato de número de celular inválido")
    String phone,

    @NotNull(message = "Campo obrigatório")
    CategoriaMembro categoriaMembro,

    @NotNull(message = "Campo obrigatório")
    Genero genero,

    @NotNull(message = "Campo obrigatório")
    Long celulaId

) {

}
