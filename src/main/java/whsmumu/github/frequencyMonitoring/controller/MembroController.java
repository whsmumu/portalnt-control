package whsmumu.github.frequencyMonitoring.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import whsmumu.github.frequencyMonitoring.dto.membro.MembroRequestDTO;
import whsmumu.github.frequencyMonitoring.dto.membro.MembroResponseDTO;
import whsmumu.github.frequencyMonitoring.exceptions.RecordNotFoundException;
import whsmumu.github.frequencyMonitoring.mapper.MembroMapper;
import whsmumu.github.frequencyMonitoring.model.Membro;
import whsmumu.github.frequencyMonitoring.service.MembroService;

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

@RestController
@RequiredArgsConstructor
@RequestMapping("/membros")
public class MembroController {

    private final MembroService membroService;
    private final MembroMapper membroMapper;

    @GetMapping("/{id}")
    public ResponseEntity<MembroResponseDTO> getById(@PathVariable Long id) {
        Membro membro = membroService.findById(id)
                .orElseThrow(() -> new RecordNotFoundException("Membro com ID " + id + " não encontrado."));
        return ResponseEntity.ok(membroMapper.toResponseDTO(membro));
    }

    @PostMapping()
    public ResponseEntity<MembroResponseDTO> save(@RequestBody @Valid MembroRequestDTO membroRequestDTO) {
        Membro membroSalvo = membroService.save(membroRequestDTO);
        MembroResponseDTO responseDTO = membroMapper.toResponseDTO(membroSalvo);

        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(membroSalvo.getId())
                .toUri();

        return ResponseEntity.created(location).body(responseDTO);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteById(@PathVariable Long id) {
        membroService.deleteById(id);
    }

    @GetMapping
    public ResponseEntity<List<MembroResponseDTO>> findAllFiltered(
            @RequestParam(required = false) String name,
            @RequestParam(required = false) String phone) {

        List<Membro> membros = membroService.findAllFiltered(name, phone);

        List<MembroResponseDTO> responseList = membros.stream()
                .map(membroMapper::toResponseDTO)
                .collect(Collectors.toList());

        return ResponseEntity.ok(responseList);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> update(@PathVariable Long id, @RequestBody @Valid MembroRequestDTO membroRequestDTO) {
        Membro membroAtualizado = membroService.update(id, membroRequestDTO);
        MembroResponseDTO responseDTO = membroMapper.toResponseDTO(membroAtualizado);

        return ResponseEntity.ok(responseDTO);

    }

}
