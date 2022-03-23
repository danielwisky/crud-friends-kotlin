package br.com.danielwisky.friends.gateways.outputs.mongodb.documents

import br.com.danielwisky.friends.domains.Friend
import org.springframework.data.annotation.CreatedDate
import org.springframework.data.annotation.Id
import org.springframework.data.annotation.LastModifiedDate
import org.springframework.data.mongodb.core.mapping.Document
import java.time.LocalDateTime

@Document("friends")
data class FriendDocument(

    @field:Id
    var id: String? = null,
    var name: String? = null,
    var cellphone: String? = null,
    var email: String? = null,
    @field:CreatedDate
    var createdDate: LocalDateTime? = null,
    @field:LastModifiedDate
    var lastModifiedDate: LocalDateTime? = null,
) {

    constructor(friend: Friend) : this(
        id = friend.id,
        name = friend.name,
        cellphone = friend.cellphone,
        email = friend.email,
        createdDate = friend.createdDate,
        lastModifiedDate = friend.lastModifiedDate
    )

    fun toDomain(): Friend {
        return Friend(
            id = this.id,
            name = this.name,
            cellphone = this.cellphone,
            email = this.email,
            createdDate = this.createdDate,
            lastModifiedDate = this.lastModifiedDate
        )
    }
}