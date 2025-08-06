package whsmumu.github.frequencyMonitoring.dto.lider;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import whsmumu.github.frequencyMonitoring.enums.Genero;

public record LiderRequestDTO(
    @NotBlank(message = "O nome do líder é obrigatório")
    String name,

    @NotBlank(message = "O telefone do líder é obrigatório")
    @Pattern(regexp ="^(?:\\(?([1-9][1-9])\\)?\\s?)?(?:((?:9\\d|[2-9])\\d{3})-?(\\d{4}))$|^\\d{11}$", message = "Formato de número de celular inválido")
    String phone,

    @NotNull(message = "O gênero do líder é obrigatório")
    Genero genero
) {

}