package br.com.danielwisky.friends.usecases

import br.com.danielwisky.friends.domains.Friend
import br.com.danielwisky.friends.domains.exceptions.ResourceNotFoundException
import br.com.danielwisky.friends.gateways.FriendDataGateway
import org.springframework.stereotype.Component

@Component
class UpdateFriend(
    private val friendDataGateway: FriendDataGateway
) {

    fun execute(id: String, friend: Friend): Friend {

        val friendData = friendDataGateway.findById(id)
            .orElseThrow { ResourceNotFoundException("Friend not found.") }

        friend.id = friendData.id
        friend.createdDate = friendData.createdDate

        return friendDataGateway.save(friend)
    }
}
