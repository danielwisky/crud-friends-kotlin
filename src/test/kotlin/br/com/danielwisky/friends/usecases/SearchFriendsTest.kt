package br.com.danielwisky.friends.usecases

import br.com.danielwisky.friends.UnitTest
import br.com.danielwisky.friends.domains.FriendFilter
import br.com.danielwisky.friends.gateways.FriendDataGateway
import br.com.danielwisky.friends.templates.FriendTemplate
import io.mockk.every
import io.mockk.impl.annotations.InjectMockKs
import io.mockk.impl.annotations.MockK
import org.junit.jupiter.api.Assertions.assertNotNull
import org.junit.jupiter.api.Test
import org.springframework.data.domain.PageImpl
import org.springframework.data.domain.PageRequest

class SearchFriendsTest : UnitTest() {

    @InjectMockKs
    lateinit var searchFriends: SearchFriends

    @MockK
    lateinit var friendDataGateway: FriendDataGateway

    @Test
    fun `should search friends`() {

        val filter = FriendFilter()
        val pageable = PageRequest.of(0, 20)

        val friend = FriendTemplate.valid()

        every { friendDataGateway.search(filter, pageable) } returns PageImpl(mutableListOf(friend))

        val friends = searchFriends.execute(filter, pageable)

        assertNotNull(friends)
    }
}
