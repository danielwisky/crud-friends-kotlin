package br.com.danielwisky.friends.gateways.inputs.http

import br.com.danielwisky.friends.gateways.FriendDataGateway
import br.com.danielwisky.friends.gateways.inputs.http.resources.request.FriendRequest
import br.com.danielwisky.friends.gateways.inputs.http.resources.response.FriendResponse
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*
import javax.validation.Valid

@RestController
@RequestMapping("/api/v1/friends")
class FriendController(
    private val friendDataGateway: FriendDataGateway
) {

    @PostMapping
    @ResponseStatus(HttpStatus.OK)
    fun create(
        @RequestBody @Valid friendRequest: FriendRequest
    ): FriendResponse {
        return FriendResponse(friendDataGateway.save(friendRequest.toDomain()))
    }
}