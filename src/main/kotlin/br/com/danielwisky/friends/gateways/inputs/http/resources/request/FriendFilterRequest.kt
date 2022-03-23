package br.com.danielwisky.friends.gateways.inputs.http.resources.request

import br.com.danielwisky.friends.domains.FriendFilter

data class FriendFilterRequest(
    var name: String? = null,
    var cellphone: String? = null,
    var email: String? = null
) {

    fun toDomain(): FriendFilter {
        return FriendFilter(
            name = this.name, cellphone = this.cellphone, email = this.email
        )
    }
}
