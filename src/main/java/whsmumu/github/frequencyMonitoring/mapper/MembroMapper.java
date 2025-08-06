package whsmumu.github.frequencyMonitoring.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import whsmumu.github.frequencyMonitoring.dto.membro.MembroRequestDTO;
import whsmumu.github.frequencyMonitoring.dto.membro.MembroResponseDTO;
import whsmumu.github.frequencyMonitoring.model.Membro;

@Mapper(componentModel = "spring")
public interface MembroMapper {

    @Mapping(target = "celulaId", source = "celula.id")
    MembroResponseDTO toResponseDTO(Membro membro);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "celula", ignore = true) 
    @Mapping(target = "dataCriacao", ignore = true)
    Membro toEntity(MembroRequestDTO requestDTO);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "celula", ignore = true)
    @Mapping(target = "dataCriacao", ignore = true)
    void updateFromDTO(MembroRequestDTO dto, @MappingTarget Membro membro);
}