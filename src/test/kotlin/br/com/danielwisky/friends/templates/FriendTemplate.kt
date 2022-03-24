package br.com.danielwisky.friends.templates

import br.com.danielwisky.friends.domains.Friend
import java.time.LocalDateTime

class FriendTemplate {

    companion object {
        fun valid(): Friend {
            return Friend(
                id = "623b6de056c0d152b447f64e",
                name = "João da Silva",
                cellphone = "+5511999999999",
                email = "joaosilva@gmail.com",
                createdDate = LocalDateTime.of(2022, 3, 20, 12, 0),
                lastModifiedDate = LocalDateTime.of(2022, 3, 20, 12, 0)
            )
        }
    }
}