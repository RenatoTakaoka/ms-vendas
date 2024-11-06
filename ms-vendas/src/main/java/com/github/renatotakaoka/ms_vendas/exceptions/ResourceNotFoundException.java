package com.github.renatotakaoka.ms_vendas.exceptions;

public class ResourceNotFoundException extends RuntimeException {

    public ResourceNotFoundException (String message) {
        super(message);
    }

}