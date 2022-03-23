package br.com.danielwisky.friends.gateways.inputs.http

import br.com.danielwisky.friends.gateways.inputs.http.resources.response.ErrorResponse
import org.springframework.http.*
import org.springframework.validation.FieldError
import org.springframework.web.bind.MethodArgumentNotValidException
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice
import java.util.stream.Collectors

@RestControllerAdvice
class CustomExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException::class)
    fun handlerMethodArgumentNotValidException(ex: MethodArgumentNotValidException): HttpEntity<ErrorResponse> {
        val bindingResult = ex.bindingResult
        val fieldErrors = bindingResult.fieldErrors
        val message: ErrorResponse = processFieldErrors(fieldErrors)
        return ResponseEntity(message, createHeaders(), HttpStatus.BAD_REQUEST)
    }

    private fun createHeaders(): HttpHeaders {
        val responseHeaders = HttpHeaders()
        responseHeaders.add(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
        return responseHeaders
    }

    private fun processFieldErrors(fieldErrors: List<FieldError>): ErrorResponse {
        val errors = fieldErrors.stream()
            .map { a: FieldError -> String.format("%s: %s", a.field, a.defaultMessage) }
            .collect(Collectors.toList())
        return ErrorResponse(errors)
    }
}