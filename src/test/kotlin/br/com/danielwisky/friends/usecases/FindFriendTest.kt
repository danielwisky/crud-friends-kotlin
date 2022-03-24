package br.com.danielwisky.friends.usecases

import br.com.danielwisky.friends.UnitTest
import br.com.danielwisky.friends.domains.exceptions.ResourceNotFoundException
import br.com.danielwisky.friends.gateways.FriendDataGateway
import br.com.danielwisky.friends.templates.FriendTemplate
import io.mockk.every
import io.mockk.impl.annotations.InjectMockKs
import io.mockk.impl.annotations.MockK
import org.junit.jupiter.api.Assertions.assertNotNull
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import java.util.*

class FindFriendTest : UnitTest() {

    @InjectMockKs
    lateinit var findFriend: FindFriend

    @MockK
    lateinit var friendDataGateway: FriendDataGateway

    @Test
    fun `should find friend`() {

        val friend = FriendTemplate.valid()
        var id = "623b6de056c0d152b447f64e"

        every { friendDataGateway.findById(id) } returns Optional.of(friend)

        val friendReturned = findFriend.execute(id)

        assertNotNull(friendReturned)
    }

    @Test
    fun `should throw ResourceNotFoundException when friend not found`() {
        var id = "623b6de056c0d152b447f64e"
        every { friendDataGateway.findById(id) } returns Optional.empty()
        assertThrows<ResourceNotFoundException> { findFriend.execute(id) }
    }
}
