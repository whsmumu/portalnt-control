package whsmumu.github.frequencyMonitoring.controller;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import whsmumu.github.frequencyMonitoring.dto.celula.CelulaRequestDTO;
import whsmumu.github.frequencyMonitoring.dto.celula.CelulaResponseDTO;
import whsmumu.github.frequencyMonitoring.enums.CategoriaCelula;
import whsmumu.github.frequencyMonitoring.mapper.CelulaMapper;
import whsmumu.github.frequencyMonitoring.model.Celula;
import whsmumu.github.frequencyMonitoring.service.CelulaService;

@RestController
@RequiredArgsConstructor
@RequestMapping("/celulas")
public class CelulaController {

    private final CelulaService celulaService;
    private final CelulaMapper celulaMapper;

    @PostMapping
    public ResponseEntity<CelulaResponseDTO> create(@RequestBody @Valid CelulaRequestDTO requestDTO) {
        Celula celulaSalva = celulaService.create(requestDTO);
        CelulaResponseDTO responseDTO = celulaMapper.toResponseDTO(celulaSalva);

        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(celulaSalva.getId())
                .toUri();

        return ResponseEntity.created(location).body(responseDTO);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CelulaResponseDTO> getById(@PathVariable Long id) {
        Celula celula = celulaService.findById(id);
        return ResponseEntity.ok(celulaMapper.toResponseDTO(celula));
    }

    @GetMapping
    public ResponseEntity<List<CelulaResponseDTO>> getAll(
            @RequestParam(required = false) String name,
            @RequestParam(required = false) CategoriaCelula categoria) {
        
        List<Celula> celulas = celulaService.findAllFiltered(name, categoria);
        List<CelulaResponseDTO> responseList = celulas.stream()
                .map(celulaMapper::toResponseDTO)
                .collect(Collectors.toList());
        
        return ResponseEntity.ok(responseList);
    }

    @PutMapping("/{id}")
    public ResponseEntity<CelulaResponseDTO> update(@PathVariable Long id, @RequestBody @Valid CelulaRequestDTO requestDTO) {
        Celula celulaAtualizada = celulaService.update(id, requestDTO);
        return ResponseEntity.ok(celulaMapper.toResponseDTO(celulaAtualizada));
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        celulaService.delete(id);
        return ResponseEntity.noContent().build();
    }

}
