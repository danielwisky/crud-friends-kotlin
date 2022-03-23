package br.com.danielwisky.friends.gateways.inputs.http

import br.com.danielwisky.friends.gateways.inputs.http.resources.request.FriendRequest
import br.com.danielwisky.friends.gateways.inputs.http.resources.response.FriendResponse
import br.com.danielwisky.friends.usecases.CreateFriend
import br.com.danielwisky.friends.usecases.FindFriend
import br.com.danielwisky.friends.usecases.UpdateFriend
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*
import javax.validation.Valid

@RestController
@RequestMapping("/api/v1/friends")
class FriendController(
    private val createFriend: CreateFriend,
    private val updateFriend: UpdateFriend,
    private val findFriend: FindFriend
) {

    @PostMapping
    @ResponseStatus(HttpStatus.OK)
    fun create(@RequestBody @Valid friendRequest: FriendRequest): FriendResponse {
        return FriendResponse(createFriend.execute(friendRequest.toDomain()))
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    fun update(
        @PathVariable id: String,
        @RequestBody @Valid friendRequest: FriendRequest
    ): FriendResponse {
        return FriendResponse(updateFriend.execute(id, friendRequest.toDomain()))
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    fun get(@PathVariable id: String): FriendResponse {
        return FriendResponse(findFriend.execute(id))
    }
}