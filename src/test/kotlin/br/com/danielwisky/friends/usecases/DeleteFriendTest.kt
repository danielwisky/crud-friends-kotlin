package br.com.danielwisky.friends.usecases

import br.com.danielwisky.friends.UnitTest
import br.com.danielwisky.friends.gateways.FriendDataGateway
import io.mockk.every
import io.mockk.impl.annotations.InjectMockKs
import io.mockk.impl.annotations.MockK
import io.mockk.verify
import org.junit.jupiter.api.Test

class DeleteFriendTest : UnitTest() {

    @InjectMockKs
    lateinit var deleteFriend: DeleteFriend

    @MockK
    lateinit var friendDataGateway: FriendDataGateway

    @Test
    fun `should delete friend`() {
        var id = "623b6de056c0d152b447f64e"

        every { friendDataGateway.delete(id) } answers {}

        deleteFriend.execute(id)

        verify { friendDataGateway.delete(id) }
    }
}
