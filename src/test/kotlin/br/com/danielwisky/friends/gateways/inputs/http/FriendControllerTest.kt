package br.com.danielwisky.friends.gateways.inputs.http

import br.com.danielwisky.friends.templates.FriendRequestTemplate
import br.com.danielwisky.friends.templates.FriendTemplate
import br.com.danielwisky.friends.usecases.*
import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import com.ninjasquad.springmockk.MockkBean
import io.mockk.every
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.data.domain.PageImpl
import org.springframework.data.domain.PageRequest
import org.springframework.http.MediaType.APPLICATION_JSON
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.*

@WebMvcTest
class FriendControllerTest(@Autowired val mockMvc: MockMvc) {

    companion object {
        const val URL = "/api/v1/friends"
        const val URL_WITH_PARAM = "/api/v1/friends/%s"
    }

    @MockkBean
    lateinit var createFriend: CreateFriend

    @MockkBean
    lateinit var updateFriend: UpdateFriend

    @MockkBean
    lateinit var deleteFriend: DeleteFriend

    @MockkBean
    lateinit var findFriend: FindFriend

    @MockkBean
    lateinit var searchFriends: SearchFriends

    private val mapper = jacksonObjectMapper()

    @Test
    fun `should create friend`() {

        val friendRequest = FriendRequestTemplate.valid()
        val friend = friendRequest.toDomain()

        every { createFriend.execute(friend) } returns friend

        mockMvc.perform(
            post(URL).contentType(APPLICATION_JSON)
                .content(mapper.writeValueAsString(friendRequest))
        ).andExpect(status().isOk).andExpect(content().contentType(APPLICATION_JSON))
            .andExpect(jsonPath("$.name").value(friend.name))
            .andExpect(jsonPath("$.cellphone").value(friend.cellphone))
            .andExpect(jsonPath("$.email").value(friend.email))
    }

    @Test
    fun `should update friend`() {

        val friendRequest = FriendRequestTemplate.valid()
        val friend = FriendTemplate.valid()
        val id = friend.id!!

        every { updateFriend.execute(id, friendRequest.toDomain()) } returns friend

        mockMvc.perform(
            put(String.format(URL_WITH_PARAM, id)).contentType(APPLICATION_JSON)
                .content(mapper.writeValueAsString(friendRequest))
        ).andExpect(status().isOk).andExpect(content().contentType(APPLICATION_JSON))
            .andExpect(jsonPath("$.name").value(friend.name))
            .andExpect(jsonPath("$.cellphone").value(friend.cellphone))
            .andExpect(jsonPath("$.email").value(friend.email))
    }

    @Test
    fun `should delete friend`() {
        var id = "623b6de056c0d152b447f64e"

        every { deleteFriend.execute(id) } answers {}

        mockMvc.perform(delete(String.format(URL_WITH_PARAM, id)))
            .andExpect(status().isNoContent)
    }

    @Test
    fun `should find friend`() {

        val friend = FriendTemplate.valid()
        val id = friend.id!!

        every { findFriend.execute(id) } returns friend

        mockMvc.perform(get(String.format(URL_WITH_PARAM, id))).andExpect(status().isOk)
            .andExpect(content().contentType(APPLICATION_JSON))
            .andExpect(jsonPath("$.id").value(friend.id))
            .andExpect(jsonPath("$.name").value(friend.name))
            .andExpect(jsonPath("$.cellphone").value(friend.cellphone))
            .andExpect(jsonPath("$.email").value(friend.email))
    }

    @Test
    fun `should search friend`() {
        val friend = FriendTemplate.valid()

        every { searchFriends.execute(any(), any()) } returns PageImpl(
            mutableListOf(friend), PageRequest.of(0, 20), 1
        )

        mockMvc.perform(get(URL)).andExpect(status().isOk)
            .andExpect(jsonPath("$.content[0].id").value(friend.id))
            .andExpect(jsonPath("$.content[0].name").value(friend.name))
            .andExpect(jsonPath("$.content[0].cellphone").value(friend.cellphone))
            .andExpect(jsonPath("$.content[0].email").value(friend.email))
            .andExpect(jsonPath("$.page").value(0)).andExpect(jsonPath("$.size").value(20))
            .andExpect(jsonPath("$.totalPages").value(1))
            .andExpect(jsonPath("$.totalElements").value(1))
    }
}
