package br.com.danielwisky.friends.gateways

import br.com.danielwisky.friends.domains.Friend
import br.com.danielwisky.friends.domains.FriendFilter
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import java.util.*

interface FriendDataGateway {

    fun save(friend: Friend): Friend

    fun delete(id: String)

    fun findById(id: String): Optional<Friend>

    fun search(filter: FriendFilter, pageable: Pageable): Page<Friend>
}
