package whsmumu.github.frequencyMonitoring.dto.celula;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import whsmumu.github.frequencyMonitoring.enums.CategoriaCelula;

public record CelulaRequestDTO(

    @NotBlank(message = "O nome é obrigatório")
    String name,

    @NotBlank(message = "O bairro é obrigatório")
    String bairro,

    @NotNull(message = "O ID do líder é obrigatório")
    Long liderId,

    @NotNull(message = "A categoria da célula é obrigatória")
    CategoriaCelula categoriaCelula
) {

}
