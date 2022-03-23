package br.com.danielwisky.friends.gateways.inputs.http.resources.response

import com.fasterxml.jackson.annotation.JsonInclude
import com.fasterxml.jackson.annotation.JsonInclude.Include.NON_EMPTY

@JsonInclude(NON_EMPTY)
data class ErrorResponse(
    var errors: List<String>? = null
)
