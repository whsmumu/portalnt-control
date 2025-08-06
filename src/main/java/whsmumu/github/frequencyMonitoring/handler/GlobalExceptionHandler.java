package whsmumu.github.frequencyMonitoring.handler;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import whsmumu.github.frequencyMonitoring.dto.error.ErrorField;
import whsmumu.github.frequencyMonitoring.dto.error.ErrorResponse;
import whsmumu.github.frequencyMonitoring.exceptions.RecordDuplicateException;
import whsmumu.github.frequencyMonitoring.exceptions.RecordNotFoundException;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.UNPROCESSABLE_ENTITY)
    public ErrorResponse handleMethodArgumentNotValidException(MethodArgumentNotValidException error) {
        List<FieldError> fieldError = error.getFieldErrors();
        List<ErrorField> errorList = fieldError
                .stream()
                .map(fe -> new ErrorField(fe.getField(), fe.getDefaultMessage())).collect(Collectors.toList());

        return new ErrorResponse(HttpStatus.UNPROCESSABLE_ENTITY.value(), "Erro de validação do campo", errorList);
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorResponse handleHttpMessageNotReadableException(HttpMessageNotReadableException error) {
        return ErrorResponse
                .defaultAnswer("O corpo da requisição JSON está malformado ou contém tipos de dados inválidos.");
    }

    @ExceptionHandler(RecordDuplicateException.class)
    @ResponseStatus(HttpStatus.CONFLICT)
    public ErrorResponse handleRecordDuplicateException(RecordDuplicateException error) {
        return ErrorResponse.conflict(error.getMessage());
    }

    @ExceptionHandler(RecordNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ErrorResponse handleRecordNotFoundException(RecordNotFoundException error) {
        return ErrorResponse.defaultAnswer(error.getMessage());
    }


    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorResponse handleMethodArgumentTypeMismatchException(MethodArgumentTypeMismatchException ex) {
        String message = "O parâmetro '" + ex.getName() + "' possui um formato inválido. Valor recebido: '" + ex.getValue() + "'.";
        return ErrorResponse.defaultAnswer(message);
    }
}
