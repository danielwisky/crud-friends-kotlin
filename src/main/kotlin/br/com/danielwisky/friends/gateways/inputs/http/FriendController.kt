package br.com.danielwisky.friends.gateways.inputs.http

import br.com.danielwisky.friends.gateways.FriendDataGateway
import br.com.danielwisky.friends.gateways.inputs.http.resources.request.FriendRequest
import br.com.danielwisky.friends.gateways.inputs.http.resources.response.FriendResponse
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.web.bind.annotation.*
import javax.validation.Valid

@RestController
@RequestMapping("/api/v1/friends")
class FriendController(
    val friendDataGateway: FriendDataGateway
) {

    @PostMapping(
        consumes = [MediaType.APPLICATION_JSON_VALUE],
        produces = [MediaType.APPLICATION_JSON_VALUE]
    )
    @ResponseStatus(HttpStatus.OK)
    fun create(
        @RequestBody @Valid friendRequest: FriendRequest
    ): FriendResponse {
        return FriendResponse(friendDataGateway.save(friendRequest.toDomain()))
    }
}