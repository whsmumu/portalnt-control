package whsmumu.github.frequencyMonitoring.controller;

import java.net.URI;
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import whsmumu.github.frequencyMonitoring.dto.frequency.FrequencyRequestDTO;
import whsmumu.github.frequencyMonitoring.dto.frequency.FrequencyResponseDTO;
import whsmumu.github.frequencyMonitoring.mapper.FrequencyMapper;
import whsmumu.github.frequencyMonitoring.model.Frequency;
import whsmumu.github.frequencyMonitoring.service.FrequencyService;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.PutMapping;

@RestController
@RequiredArgsConstructor
@RequestMapping("/frequencias")
public class FrequencyController {

    private final FrequencyService frequenciaService;
    private final FrequencyMapper frequencyMapper;

    @PostMapping()
    public ResponseEntity<FrequencyResponseDTO> save(@RequestBody @Valid FrequencyRequestDTO frequencyRequestDTO) {
        Frequency frequenciaSalva = frequenciaService.save(frequencyRequestDTO);
        FrequencyResponseDTO responseDTO = frequencyMapper.toResponseDTO(frequenciaSalva);

        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(frequenciaSalva.getId())
                .toUri();

        return ResponseEntity.created(location).body(responseDTO);

    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteById(@PathVariable UUID id) {
        frequenciaService.delete(id);
    }

    @GetMapping
    public ResponseEntity<List<FrequencyResponseDTO>> findAllFiltered(
            @RequestParam(required = false) @DateTimeFormat(pattern = "dd/MM/yyyy") LocalDate data,
            @RequestParam(required = false) Integer mes,
            @RequestParam(required = false) Integer ano) {

        List<Frequency> frequencias = frequenciaService.findAllFiltered(data, mes, ano);

        List<FrequencyResponseDTO> responseList = frequencias.stream()
                .map(frequencyMapper::toResponseDTO)
                .collect(Collectors.toList());

        return ResponseEntity.ok(responseList);
    }

    @PutMapping("/{id}")
    public ResponseEntity<FrequencyResponseDTO> update(@PathVariable UUID id, @RequestBody @Valid FrequencyRequestDTO frequenciaDTO) {
        Frequency frequenciaAtualizada = frequenciaService.update(id, frequenciaDTO);
        FrequencyResponseDTO responseDTO = frequencyMapper.toResponseDTO(frequenciaAtualizada);
        return ResponseEntity.ok(responseDTO);
    }

    @GetMapping("/{id}")
    public ResponseEntity<FrequencyResponseDTO> findById(@PathVariable UUID id) {
        Frequency frequencia = frequenciaService.findById(id);
        return ResponseEntity.ok(frequencyMapper.toResponseDTO(frequencia));
    }
}