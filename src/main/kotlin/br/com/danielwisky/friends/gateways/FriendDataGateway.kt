package br.com.danielwisky.friends.gateways

import br.com.danielwisky.friends.domains.Friend
import java.util.*

interface FriendDataGateway {

    fun save(friend: Friend): Friend

    fun findById(id: String): Optional<Friend>
}
