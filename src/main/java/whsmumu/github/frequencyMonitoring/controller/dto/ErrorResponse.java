package whsmumu.github.frequencyMonitoring.controller.dto;

import java.util.List;

import org.springframework.http.HttpStatus;

public record ErrorResponse(int stauts, String message, List<ErrorField> error) {

    public static ErrorResponse defaultAnswer(String message){
        return new ErrorResponse(HttpStatus.BAD_REQUEST.value(), message, List.of());
    }

    public static ErrorResponse conflict(String message){
        return new ErrorResponse(HttpStatus.CONFLICT.value(), message, List.of());
    }
}
