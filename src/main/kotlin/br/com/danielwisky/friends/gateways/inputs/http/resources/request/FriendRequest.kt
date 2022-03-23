package br.com.danielwisky.friends.gateways.inputs.http.resources.request

import br.com.danielwisky.friends.domains.Friend
import javax.validation.constraints.NotBlank

data class FriendRequest(
    @field:NotBlank
    var name: String? = null,
    var cellphone: String? = null,
    var email: String? = null
) {

    fun toDomain(): Friend {
        return Friend(
            name = this.name,
            cellphone = this.cellphone,
            email = this.email
        )
    }
}
