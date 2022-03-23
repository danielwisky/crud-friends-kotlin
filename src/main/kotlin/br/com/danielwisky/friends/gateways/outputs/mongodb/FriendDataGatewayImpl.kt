package br.com.danielwisky.friends.gateways.outputs.mongodb

import br.com.danielwisky.friends.domains.Friend
import br.com.danielwisky.friends.domains.FriendFilter
import br.com.danielwisky.friends.gateways.FriendDataGateway
import br.com.danielwisky.friends.gateways.outputs.mongodb.documents.FriendDocument
import br.com.danielwisky.friends.gateways.outputs.mongodb.repositories.FriendCustomRepository
import br.com.danielwisky.friends.gateways.outputs.mongodb.repositories.FriendRepository
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.stereotype.Component
import java.util.*

@Component
class FriendDataGatewayImpl(
    private val friendRepository: FriendRepository,
    private val friendCustomRepository: FriendCustomRepository
) : FriendDataGateway {

    override fun save(friend: Friend): Friend {
        return friendRepository.save(FriendDocument(friend)).toDomain()
    }

    override fun findById(id: String): Optional<Friend> {
        return friendRepository.findById(id).map(FriendDocument::toDomain)
    }

    override fun search(filter: FriendFilter, pageable: Pageable): Page<Friend> {
        return friendCustomRepository.search(filter, pageable).map(FriendDocument::toDomain)
    }
}
