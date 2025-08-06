package whsmumu.github.frequencyMonitoring.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import whsmumu.github.frequencyMonitoring.dto.membro.MembroRequestDTO;
import whsmumu.github.frequencyMonitoring.dto.membro.MembroResponseDTO;
import whsmumu.github.frequencyMonitoring.model.Membro;

@Mapper(componentModel = "spring")
public interface MembroMapper {

    // Ensina o MapStruct a mapear o objeto 'celula' para 'celulaId' no DTO
    @Mapping(target = "celulaId", source = "celula.id")
    MembroResponseDTO toResponseDTO(Membro membro);

    // Ignora os campos que não vêm do DTO
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "celula", ignore = true) // A célula é associada no Service
    @Mapping(target = "dataCriacao", ignore = true)
    Membro toEntity(MembroRequestDTO requestDTO);

    // As mesmas regras se aplicam à atualização
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "celula", ignore = true)
    @Mapping(target = "dataCriacao", ignore = true)
    void updateFromDTO(MembroRequestDTO dto, @MappingTarget Membro membro);
}