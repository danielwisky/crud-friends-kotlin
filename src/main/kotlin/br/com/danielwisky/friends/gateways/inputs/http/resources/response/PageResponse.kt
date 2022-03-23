package br.com.danielwisky.friends.gateways.inputs.http.resources.response

import com.fasterxml.jackson.annotation.JsonInclude

@JsonInclude(JsonInclude.Include.NON_EMPTY)
data class PageResponse<T>(
    
    var content: List<T>? = null,
    var totalElements: Long,
    var totalPages: Int,
    var size: Int,
    var page: Int
)