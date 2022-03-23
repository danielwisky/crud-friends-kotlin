package br.com.danielwisky.friends.domains

import java.time.LocalDateTime

data class Friend(
    var id: String? = null,
    var name: String? = null,
    var cellphone: String? = null,
    var email: String? = null,
    var createdDate: LocalDateTime? = null,
    var lastModifiedDate: LocalDateTime? = null
)
