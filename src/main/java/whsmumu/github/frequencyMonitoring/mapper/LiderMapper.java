package whsmumu.github.frequencyMonitoring.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import whsmumu.github.frequencyMonitoring.dto.lider.LiderRequestDTO;
import whsmumu.github.frequencyMonitoring.dto.lider.LiderResponseDTO;
import whsmumu.github.frequencyMonitoring.model.Lider;

@Mapper(componentModel = "spring")
public interface LiderMapper {

    
    @Mapping(source = "celula.id", target = "celulaId")
    LiderResponseDTO toResponseDTO(Lider lider);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "celula", ignore = true)
    @Mapping(target = "dataCriacao", ignore = true)
    Lider toEntity(LiderRequestDTO requestDTO);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "celula", ignore = true)
    @Mapping(target = "dataCriacao", ignore = true)
    void updateFromDTO(LiderRequestDTO dto, @MappingTarget Lider lider);
}