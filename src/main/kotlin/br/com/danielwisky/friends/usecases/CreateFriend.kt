package br.com.danielwisky.friends.usecases

import br.com.danielwisky.friends.domains.Friend
import br.com.danielwisky.friends.gateways.FriendDataGateway
import org.springframework.stereotype.Component

@Component
class CreateFriend(private val friendDataGateway: FriendDataGateway) {

    fun execute(friend: Friend): Friend {
        return friendDataGateway.save(friend)
    }
}