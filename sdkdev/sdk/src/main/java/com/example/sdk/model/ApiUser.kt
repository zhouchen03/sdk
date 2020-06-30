package com.example.sdk.model

import com.fasterxml.jackson.annotation.JsonProperty

class ApiUser {
    @JsonProperty("id")
    var id: Int = 0

    @JsonProperty("name")
    val name: String? = null

    @JsonProperty("avatar")
    val avatar: String? = null
}