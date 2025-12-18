package com.bcc.cca.dto.response;

import java.time.Instant;
import java.util.List;

public class ErrorResponseDTO {
    
    private Instant timestamp;
    private Integer status;
    private String error;
    private String message;
    private String path;
    private List<FieldErrorDTO> fieldErrors;
    
    public ErrorResponseDTO() {
        this.timestamp = Instant.now();
    }
    
    public ErrorResponseDTO(Integer status, String error, String message, String path) {
        this();
        this.status = status;
        this.error = error;
        this.message = message;
        this.path = path;
    }
    
    public Instant getTimestamp() {
        return timestamp;
    }
    
    public void setTimestamp(Instant timestamp) {
        this.timestamp = timestamp;
    }
    
    public Integer getStatus() {
        return status;
    }
    
    public void setStatus(Integer status) {
        this.status = status;
    }
    
    public String getError() {
        return error;
    }
    
    public void setError(String error) {
        this.error = error;
    }
    
    public String getMessage() {
        return message;
    }
    
    public void setMessage(String message) {
        this.message = message;
    }
    
    public String getPath() {
        return path;
    }
    
    public void setPath(String path) {
        this.path = path;
    }
    
    public List<FieldErrorDTO> getFieldErrors() {
        return fieldErrors;
    }
    
    public void setFieldErrors(List<FieldErrorDTO> fieldErrors) {
        this.fieldErrors = fieldErrors;
    }
    
    public static class FieldErrorDTO {
        private String field;
        private String message;
        private Object rejectedValue;
        
        public FieldErrorDTO(String field, String message, Object rejectedValue) {
            this.field = field;
            this.message = message;
            this.rejectedValue = rejectedValue;
        }
        
        public String getField() {
            return field;
        }
        
        public void setField(String field) {
            this.field = field;
        }
        
        public String getMessage() {
            return message;
        }
        
        public void setMessage(String message) {
            this.message = message;
        }
        
        public Object getRejectedValue() {
            return rejectedValue;
        }
        
        public void setRejectedValue(Object rejectedValue) {
            this.rejectedValue = rejectedValue;
        }
    }
}










