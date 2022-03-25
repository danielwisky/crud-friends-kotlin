package br.com.danielwisky.friends.gateways.outputs.mongodb

import br.com.danielwisky.friends.UnitTest
import br.com.danielwisky.friends.domains.FriendFilter
import br.com.danielwisky.friends.gateways.outputs.mongodb.documents.FriendDocument
import br.com.danielwisky.friends.gateways.outputs.mongodb.repositories.FriendCustomRepository
import br.com.danielwisky.friends.gateways.outputs.mongodb.repositories.FriendRepository
import br.com.danielwisky.friends.templates.FriendTemplate
import io.mockk.every
import io.mockk.impl.annotations.InjectMockKs
import io.mockk.impl.annotations.MockK
import io.mockk.verify
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import org.springframework.data.domain.PageImpl
import org.springframework.data.domain.PageRequest
import java.util.*

class FriendDataGatewayImplTest : UnitTest() {

    @InjectMockKs
    lateinit var friendDataGateway: FriendDataGatewayImpl

    @MockK
    lateinit var friendRepository: FriendRepository

    @MockK
    lateinit var friendCustomRepository: FriendCustomRepository

    @Test
    fun `should save`() {
        val friend = FriendTemplate.valid()
        val friendDocument = FriendDocument(friend)

        every { friendRepository.save(friendDocument) } returns friendDocument

        val friendSaved = friendDataGateway.save(friend)

        assertNotNull(friendSaved)
    }

    @Test
    fun `should delete`() {
        var id = "623b6de056c0d152b447f64e"

        every { friendRepository.deleteById(id) } answers {}

        friendDataGateway.delete(id)

        verify { friendRepository.deleteById(id) }
    }

    @Test
    fun `should find`() {
        val friend = FriendTemplate.valid()
        val friendDocument = FriendDocument(friend)
        val id = friendDocument.id!!

        every { friendRepository.findById(id) } returns Optional.of(friendDocument)

        val friendReturned = friendDataGateway.findById(id)

        assertTrue(friendReturned.isPresent)
    }

    @Test
    fun `should return empty when friend not found`() {
        val friend = FriendTemplate.valid()
        val id = friend.id!!

        every { friendRepository.findById(id) } returns Optional.empty()

        val friendReturned = friendDataGateway.findById(id)

        assertFalse(friendReturned.isPresent)
    }

    @Test
    fun `should search`() {
        val friend = FriendTemplate.valid()
        val filter = FriendFilter()
        val pageable = PageRequest.of(0, 20)

        every { friendCustomRepository.search(filter, pageable) } returns PageImpl(
            mutableListOf(
                FriendDocument(friend)
            )
        )

        val friends = friendDataGateway.search(filter, pageable)

        assertNotNull(friends)
        assertEquals(friends.totalElements, 1)
    }
}
