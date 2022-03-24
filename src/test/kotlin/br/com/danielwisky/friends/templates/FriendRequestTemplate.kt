package br.com.danielwisky.friends.templates

import br.com.danielwisky.friends.gateways.inputs.http.resources.request.FriendRequest

class FriendRequestTemplate {

    companion object {
        fun valid(): FriendRequest {
            return FriendRequest(
                name = "João da Silva",
                cellphone = "+5511999999999",
                email = "joaosilva@gmail.com"
            )
        }
    }
}
