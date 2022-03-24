package br.com.danielwisky.friends.usecases

import br.com.danielwisky.friends.UnitTest
import br.com.danielwisky.friends.domains.Friend
import br.com.danielwisky.friends.domains.exceptions.ResourceNotFoundException
import br.com.danielwisky.friends.gateways.FriendDataGateway
import br.com.danielwisky.friends.templates.FriendTemplate
import io.mockk.every
import io.mockk.impl.annotations.InjectMockKs
import io.mockk.impl.annotations.MockK
import io.mockk.slot
import io.mockk.verify
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import java.util.*

class UpdateFriendTest : UnitTest() {

    @InjectMockKs
    lateinit var updateFriend: UpdateFriend

    @MockK
    lateinit var friendDataGateway: FriendDataGateway

    @Test
    fun `should update friend`() {

        val friend = FriendTemplate.valid()
        var id = "623b6de056c0d152b447f64e"

        every { friendDataGateway.findById(id) } returns Optional.of(friend)
        every { friendDataGateway.save(any()) } returns friend

        val friendUpdated = updateFriend.execute(id, Friend())

        val slotFriend = slot<Friend>()
        verify { friendDataGateway.save(capture(slotFriend)) }
        val friendCaptured = slotFriend.captured
        assertEquals(friendUpdated.id, friendCaptured.id)
        assertEquals(friendUpdated.createdDate, friendCaptured.createdDate)
    }

    @Test
    fun `should throw ResouceNotFound when friend not found`() {
        var id = "623b6de056c0d152b447f64e"
        every { friendDataGateway.findById(id) } returns Optional.empty()
        assertThrows<ResourceNotFoundException> { updateFriend.execute(id, Friend()) }
    }
}
