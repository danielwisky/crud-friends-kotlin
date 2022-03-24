package br.com.danielwisky.friends.gateways.inputs.http

import br.com.danielwisky.friends.gateways.inputs.http.resources.request.FriendFilterRequest
import br.com.danielwisky.friends.gateways.inputs.http.resources.request.FriendRequest
import br.com.danielwisky.friends.gateways.inputs.http.resources.response.FriendResponse
import br.com.danielwisky.friends.gateways.inputs.http.resources.response.PageResponse
import br.com.danielwisky.friends.usecases.CreateFriend
import br.com.danielwisky.friends.usecases.FindFriend
import br.com.danielwisky.friends.usecases.SearchFriends
import br.com.danielwisky.friends.usecases.UpdateFriend
import io.swagger.v3.oas.annotations.Operation
import org.springframework.data.domain.PageRequest.of
import org.springframework.data.domain.Sort.Direction.DESC
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*
import javax.validation.Valid

@RestController
@RequestMapping("/api/v1/friends")
class FriendController(
    private val createFriend: CreateFriend,
    private val updateFriend: UpdateFriend,
    private val findFriend: FindFriend,
    private val searchFriends: SearchFriends
) {

    @PostMapping
    @ResponseStatus(HttpStatus.OK)
    @Operation(description = "Create friend")
    fun create(@RequestBody @Valid friendRequest: FriendRequest): FriendResponse {
        return FriendResponse(createFriend.execute(friendRequest.toDomain()))
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    @Operation(description = "Update friend")
    fun update(
        @PathVariable id: String,
        @RequestBody @Valid friendRequest: FriendRequest
    ): FriendResponse {
        return FriendResponse(updateFriend.execute(id, friendRequest.toDomain()))
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    @Operation(description = "Find friend by id")
    fun get(@PathVariable id: String): FriendResponse {
        return FriendResponse(findFriend.execute(id))
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    @Operation(description = "Search friends by filter")
    fun search(
        request: FriendFilterRequest,
        @RequestParam(defaultValue = "0") page: Int,
        @RequestParam(defaultValue = "20") size: Int
    ): PageResponse<FriendResponse> {
        val friendPage =
            searchFriends.execute(request.toDomain(), of(page, size, DESC, "id"))
        return PageResponse(
            content = friendPage.map(::FriendResponse).toList(),
            totalElements = friendPage.totalElements,
            totalPages = friendPage.totalPages,
            page = friendPage.number,
            size = friendPage.size
        );
    }
}