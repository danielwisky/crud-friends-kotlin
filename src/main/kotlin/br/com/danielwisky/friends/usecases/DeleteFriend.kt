package br.com.danielwisky.friends.usecases

import br.com.danielwisky.friends.gateways.FriendDataGateway
import org.springframework.stereotype.Component

@Component
class DeleteFriend(private val friendDataGateway: FriendDataGateway) {

    fun execute(id: String) {
        friendDataGateway.delete(id)
    }
}
