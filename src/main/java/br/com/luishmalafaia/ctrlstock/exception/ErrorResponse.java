package br.com.luishmalafaia.ctrlstock.exception;

public record ErrorResponse(int status, String error, String message) {
}
