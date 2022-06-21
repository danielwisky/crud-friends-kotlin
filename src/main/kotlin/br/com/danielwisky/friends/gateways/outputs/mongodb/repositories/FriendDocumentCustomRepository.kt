package br.com.danielwisky.friends.gateways.outputs.mongodb.repositories

import br.com.danielwisky.friends.domains.FriendFilter
import br.com.danielwisky.friends.gateways.outputs.mongodb.documents.FriendDocument
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable

interface FriendDocumentCustomRepository {

    fun search(filter: FriendFilter, pageable: Pageable): Page<FriendDocument>
}
