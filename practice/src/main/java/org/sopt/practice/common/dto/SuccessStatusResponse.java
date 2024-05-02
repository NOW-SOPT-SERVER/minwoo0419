package org.sopt.practice.common.dto;

public record SuccessStatusResponse (
        int status,
        Object data,
        String message
){
    public static SuccessStatusResponse of(SuccessMessage successMessage, Object data){
        return new SuccessStatusResponse(successMessage.getStatus(), data, successMessage.getMessage());
    }
}
