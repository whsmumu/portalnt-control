package whsmumu.github.frequencyMonitoring.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import whsmumu.github.frequencyMonitoring.dto.celula.CelulaRequestDTO;
import whsmumu.github.frequencyMonitoring.dto.celula.CelulaResponseDTO;
import whsmumu.github.frequencyMonitoring.model.Celula;

@Mapper(componentModel = "spring", uses = {MembroMapper.class, LiderMapper.class})
public interface CelulaMapper {

    CelulaResponseDTO toResponseDTO(Celula celula);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "lider", ignore = true)
    @Mapping(target = "membros", ignore = true)
    @Mapping(target = "dataCriacao", ignore = true)
    Celula toEntity(CelulaRequestDTO requestDTO);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "lider", ignore = true)
    @Mapping(target = "membros", ignore = true)
    @Mapping(target = "dataCriacao", ignore = true)
    void updateFromDTO(CelulaRequestDTO dto, @MappingTarget Celula celula);
}