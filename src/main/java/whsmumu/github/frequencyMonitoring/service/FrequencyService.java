package whsmumu.github.frequencyMonitoring.service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import whsmumu.github.frequencyMonitoring.model.Frequency;
import whsmumu.github.frequencyMonitoring.repository.FrequencyRepository;
import whsmumu.github.frequencyMonitoring.validator.FrequencyValidator;

@Service
@RequiredArgsConstructor
public class FrequencyService {

    private final FrequencyRepository frequencyRepository;
    private final FrequencyValidator frequencyValidator;

    public Frequency save(Frequency frequency){
        frequencyValidator.validator(frequency);
        return frequencyRepository.save(frequency);

    }

    public List<Frequency> findAll(){
        return frequencyRepository.findAll();
    }

    public Optional<Frequency> findById(UUID id){
        return frequencyRepository.findById(id);
    }

    public void delete(Frequency frequency){
        frequencyRepository.delete(frequency);
    }

    public List<Frequency> findByData (LocalDate data){
        if (data != null) {
            return frequencyRepository.findByData(data);
        }
        return frequencyRepository.findAll();
    }

    public void update(Frequency frequency){
        frequencyValidator.validator(frequency);
        frequencyRepository.save(frequency);
    }

    public List<Frequency> findByMonthAndYear(int mes, int ano) {
        return frequencyRepository.findByMonthAndYear(mes, ano);
    }

    public List<Frequency> findByYear(int ano) {
        return frequencyRepository.findByYear(ano);
    }
}
