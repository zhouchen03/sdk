package com.example.spoofer.api.handlers

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonProperty


@JsonIgnoreProperties(ignoreUnknown = true)
class UserRS {
    @JsonProperty("users")
    var users: List<User>? = null
}

class User {
    @JsonProperty("id")
    var id: Int = 0

    @JsonProperty("name")
    var name: String? = null

    @JsonProperty("avatar")
    var avatar: String? = null
}
