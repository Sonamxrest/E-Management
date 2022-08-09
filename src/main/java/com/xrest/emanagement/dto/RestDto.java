package com.xrest.emanagement.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RestDto {
    private String message;
    private Object detail;

    @JsonIgnore
    private int code;

    public ResponseEntity<?> successModel(Object object) {
        return new ResponseEntity<>(object, HttpStatus.OK);
    }
    public ResponseEntity<?> failureModel(String message) {
        RestDto r = new RestDto();
        r.setMessage(message);
        return new ResponseEntity<>(r, HttpStatus.BAD_REQUEST);
    }
}
