package br.com.danielwisky.friends.usecases

import br.com.danielwisky.friends.domains.Friend
import br.com.danielwisky.friends.domains.FriendFilter
import br.com.danielwisky.friends.gateways.FriendDataGateway
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.stereotype.Component

@Component
class SearchFriends(private val friendDataGateway: FriendDataGateway) {

    fun execute(filter: FriendFilter, pageable: Pageable): Page<Friend> {
        return friendDataGateway.search(filter, pageable)
    }
}
