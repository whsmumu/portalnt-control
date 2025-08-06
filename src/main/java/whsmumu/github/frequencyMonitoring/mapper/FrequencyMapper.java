package whsmumu.github.frequencyMonitoring.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import whsmumu.github.frequencyMonitoring.dto.frequency.FrequencyRequestDTO;
import whsmumu.github.frequencyMonitoring.dto.frequency.FrequencyResponseDTO;
import whsmumu.github.frequencyMonitoring.model.Frequency;

@Mapper(componentModel = "spring")
public interface FrequencyMapper {
    

    FrequencyResponseDTO toResponseDTO(Frequency frequency);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "dataFrequenciaCriacao", ignore = true)
    @Mapping(target = "dataUltimaAtualizacao", ignore = true)
    Frequency toEntity(FrequencyRequestDTO requestDTO);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "dataFrequenciaCriacao", ignore = true)
    @Mapping(target = "dataUltimaAtualizacao", ignore = true)
    void updateFromDTO(FrequencyRequestDTO dto, @MappingTarget Frequency frequency);
}