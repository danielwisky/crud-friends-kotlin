package br.com.danielwisky.friends.usecases

import br.com.danielwisky.friends.UnitTest
import br.com.danielwisky.friends.gateways.FriendDataGateway
import br.com.danielwisky.friends.templates.FriendTemplate
import io.mockk.every
import io.mockk.impl.annotations.InjectMockKs
import io.mockk.impl.annotations.MockK
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test

class CreateFriendTest : UnitTest() {

    @InjectMockKs
    lateinit var createFriend: CreateFriend

    @MockK
    lateinit var friendDataGateway: FriendDataGateway

    @Test
    fun `should create friend`() {
        val friend = FriendTemplate.valid()

        every { friendDataGateway.save(any()) } returns friend

        val friendCreated = createFriend.execute(friend)

        Assertions.assertNotNull(friendCreated)
    }
}