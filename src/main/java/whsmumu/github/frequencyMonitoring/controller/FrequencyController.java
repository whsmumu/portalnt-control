package whsmumu.github.frequencyMonitoring.controller;

import java.net.URI;
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.format.annotation.DateTimeFormat;
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
import whsmumu.github.frequencyMonitoring.controller.dto.FrequencyDTO;
import whsmumu.github.frequencyMonitoring.exceptions.RecordNotFoundException;
import whsmumu.github.frequencyMonitoring.model.Frequency;
import whsmumu.github.frequencyMonitoring.service.FrequencyService;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PutMapping;

@RestController
@RequiredArgsConstructor
@RequestMapping("/frequencias")
public class FrequencyController {

    private final FrequencyService frequenciaService;

    @PostMapping()
    public ResponseEntity<Object> save(@RequestBody @Valid FrequencyDTO frequenciaDTO) {
        Frequency frequenciaEntidade = frequenciaDTO.mapearFrequencia();
        frequenciaService.save(frequenciaEntidade);

        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(frequenciaEntidade.getId())
                .toUri();

        return ResponseEntity.created(location).build();

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable UUID id) {
        Frequency frequency = frequenciaService.findById(id)
                .orElseThrow(() -> new RecordNotFoundException("Frequência não encontrada."));

        frequenciaService.delete(frequency);
        return ResponseEntity.noContent().build();
    }

    @GetMapping
    public ResponseEntity<List<FrequencyDTO>> findByData(
            @RequestParam(required = false) @DateTimeFormat(pattern = "dd/MM/yyyy") LocalDate data,
            @RequestParam(required = false) Integer mes,
            @RequestParam(required = false) Integer ano) {

        List<Frequency> resultado;

        if (data != null) {
            resultado = frequenciaService.findByData(data);

        } else if (ano != null && mes != null) {
            resultado = frequenciaService.findByMonthAndYear(mes, ano);
        } else if (ano != null) {
            resultado = frequenciaService.findByYear(ano);

        } else {
            resultado = frequenciaService.findAll();
        }

        List<FrequencyDTO> lista = resultado
                .stream()
                .map(frequencia -> new FrequencyDTO(frequencia.getId(), frequencia.getData(),
                        frequencia.getQuantidadeMembrosHomem(),
                        frequencia.getQuantidadeMembrosMulheres(), frequencia.getQuantidadeVisitantesHomem(),
                        frequencia.getQuantidadeVisitantesMulher(), frequencia.getQuantidadeKids(),
                        frequencia.getQuantidadeBaby()))
                .collect(Collectors.toList());

        return ResponseEntity.ok(lista);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> update(@PathVariable UUID id, @RequestBody @Valid FrequencyDTO frequenciaDTO) {
        Frequency frequencia = frequenciaService.findById(id)
                .orElseThrow(() -> new RecordNotFoundException("Frequência não encontrada para atualização."));

        frequencia.setData(frequenciaDTO.data());
        frequencia.setQuantidadeMembrosHomem(frequenciaDTO.quantidadeMembrosHomem());
        frequencia.setQuantidadeMembrosMulheres(frequenciaDTO.quantidadeMembrosMulheres());
        frequencia.setQuantidadeVisitantesHomem(frequenciaDTO.quantidadeVisitantesHomem());
        frequencia.setQuantidadeVisitantesMulher(frequenciaDTO.quantidadeVisitantesMulher());
        frequencia.setQuantidadeBaby(frequenciaDTO.quantidadeBaby());
        frequencia.setQuantidadeKids(frequenciaDTO.quantidadeKids());

        frequenciaService.update(frequencia);
        return ResponseEntity.noContent().build();

    }
}