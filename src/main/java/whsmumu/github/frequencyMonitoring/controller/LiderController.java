package whsmumu.github.frequencyMonitoring.controller;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import whsmumu.github.frequencyMonitoring.dto.lider.LiderRequestDTO;
import whsmumu.github.frequencyMonitoring.dto.lider.LiderResponseDTO;
import whsmumu.github.frequencyMonitoring.mapper.LiderMapper;
import whsmumu.github.frequencyMonitoring.model.Lider;
import whsmumu.github.frequencyMonitoring.service.LiderService;

@RestController
@RequestMapping("/lideres")
@RequiredArgsConstructor
public class LiderController {

    private final LiderService liderService;
    private final LiderMapper liderMapper;

    @PostMapping
    public ResponseEntity<LiderResponseDTO> create(@RequestBody @Valid LiderRequestDTO requestDTO) {
        Lider novoLider = liderService.create(requestDTO);
        LiderResponseDTO responseDTO = liderMapper.toResponseDTO(novoLider);

        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(novoLider.getId())
                .toUri();

        return ResponseEntity.created(location).body(responseDTO);
    }

    @GetMapping("/{id}")
    public ResponseEntity<LiderResponseDTO> getById(@PathVariable Long id) {
        Lider lider = liderService.findById(id)
                .orElseThrow(() -> new whsmumu.github.frequencyMonitoring.exceptions.RecordNotFoundException("Líder com ID " + id + " não encontrado."));
        return ResponseEntity.ok(liderMapper.toResponseDTO(lider));
    }

    @GetMapping
    public ResponseEntity<List<LiderResponseDTO>> getAll(
            @RequestParam(required = false) String name,
            @RequestParam(required = false) String phone) {
        
        List<Lider> lideres = liderService.find(name, phone);
        List<LiderResponseDTO> responseList = lideres.stream()
                .map(liderMapper::toResponseDTO)
                .collect(Collectors.toList());
        
        return ResponseEntity.ok(responseList);
    }

    @PutMapping("/{id}")
    public ResponseEntity<LiderResponseDTO> update(@PathVariable Long id, @RequestBody @Valid LiderRequestDTO requestDTO) {
        Lider liderAtualizado = liderService.update(id, requestDTO);
        return ResponseEntity.ok(liderMapper.toResponseDTO(liderAtualizado));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        liderService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}