package com.example.plannerapp.dto;

public class LoginValidationDTO {
    private LoginBadRequest errors;
    private int status;

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public LoginBadRequest getErrors() {
        return errors;
    }

    public void setErrors(LoginBadRequest errors) {
        this.errors = errors;
    }
}
