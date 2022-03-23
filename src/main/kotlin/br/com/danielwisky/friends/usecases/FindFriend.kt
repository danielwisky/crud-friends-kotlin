package br.com.danielwisky.friends.usecases

import br.com.danielwisky.friends.domains.Friend
import br.com.danielwisky.friends.domains.exceptions.ResourceNotFoundException
import br.com.danielwisky.friends.gateways.FriendDataGateway
import org.springframework.stereotype.Component

@Component
class FindFriend(private val friendDataGateway: FriendDataGateway) {

    fun execute(id: String): Friend {
        return friendDataGateway.findById(id)
            .orElseThrow { ResourceNotFoundException("Friend not found.") }
    }
}