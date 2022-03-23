package br.com.danielwisky.friends.gateways.inputs.http

import br.com.danielwisky.friends.domains.exceptions.BusinessValidationException
import br.com.danielwisky.friends.domains.exceptions.ResourceNotFoundException
import br.com.danielwisky.friends.gateways.inputs.http.resources.response.ErrorResponse
import org.apache.commons.lang3.StringUtils.isNotBlank
import org.springframework.http.HttpEntity
import org.springframework.http.HttpHeaders
import org.springframework.http.HttpStatus.BAD_REQUEST
import org.springframework.http.HttpStatus.NOT_FOUND
import org.springframework.http.MediaType.APPLICATION_JSON_VALUE
import org.springframework.http.ResponseEntity
import org.springframework.validation.FieldError
import org.springframework.web.bind.MethodArgumentNotValidException
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice
import java.util.stream.Collectors

@RestControllerAdvice
class CustomExceptionHandler {

    @ExceptionHandler(ResourceNotFoundException::class)
    fun handlerResourceNotFoundException(ex: ResourceNotFoundException): HttpEntity<ErrorResponse> {
        return ResponseEntity(createMessage(ex), createHeaders(), NOT_FOUND)
    }

    @ExceptionHandler(BusinessValidationException::class)
    fun handlerBusinessValidationException(ex: BusinessValidationException): HttpEntity<ErrorResponse> {
        return ResponseEntity(createMessage(ex), createHeaders(), BAD_REQUEST)
    }

    @ExceptionHandler(MethodArgumentNotValidException::class)
    fun handlerMethodArgumentNotValidException(ex: MethodArgumentNotValidException): HttpEntity<ErrorResponse> {
        val bindingResult = ex.bindingResult
        val fieldErrors = bindingResult.fieldErrors
        val message: ErrorResponse = processFieldErrors(fieldErrors)
        return ResponseEntity(message, createHeaders(), BAD_REQUEST)
    }

    private fun createHeaders(): HttpHeaders {
        val responseHeaders = HttpHeaders()
        responseHeaders.add(HttpHeaders.CONTENT_TYPE, APPLICATION_JSON_VALUE)
        return responseHeaders
    }

    private fun createMessage(ex: Throwable): ErrorResponse? {
        var message: ErrorResponse? = null
        if (isNotBlank(ex.message.orEmpty())) {
            message = ErrorResponse(listOf(ex.message.orEmpty()))
        }
        return message
    }

    private fun processFieldErrors(fieldErrors: List<FieldError>): ErrorResponse {
        val errors = fieldErrors.stream()
            .map { a: FieldError -> String.format("%s: %s", a.field, a.defaultMessage) }
            .collect(Collectors.toList())
        return ErrorResponse(errors)
    }
}