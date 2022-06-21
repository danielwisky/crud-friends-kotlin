package br.com.danielwisky.friends.gateways.outputs.mongodb.repositories

import br.com.danielwisky.friends.gateways.outputs.mongodb.documents.FriendDocument
import org.springframework.data.mongodb.repository.MongoRepository

interface FriendDocumentRepository :
    MongoRepository<FriendDocument, String>, FriendDocumentCustomRepository
