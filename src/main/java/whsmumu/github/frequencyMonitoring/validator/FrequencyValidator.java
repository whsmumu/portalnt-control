package whsmumu.github.frequencyMonitoring.validator;

import java.util.List;

import org.springframework.stereotype.Component;

import lombok.RequiredArgsConstructor;
import whsmumu.github.frequencyMonitoring.exceptions.RecordDuplicateException;
import whsmumu.github.frequencyMonitoring.model.Frequency;
import whsmumu.github.frequencyMonitoring.repository.FrequencyRepository;

@Component
@RequiredArgsConstructor
public class FrequencyValidator {

    private final FrequencyRepository frequencyRepository;

    public void validator(Frequency frequency){
        if (existsRegisteredFrequency(frequency)) {
            throw new RecordDuplicateException("Frequencia já cadastrada.");
            
        }
    }

    public boolean existsRegisteredFrequency(Frequency frequency){
        List<Frequency> frequencyFound;

        if (frequency.getId() == null) {
            frequencyFound = frequencyRepository.findByData(frequency.getData());
        } else{
            frequencyFound = frequencyRepository.findByDataAndIdNot(frequency.getData(), frequency.getId());
        }

        return !frequencyFound.isEmpty();

    }

}
