package com.example.sdk.model

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonProperty

@JsonIgnoreProperties(ignoreUnknown = true)
class ApiUserRS {
    @JsonProperty("users")
    var users: List<ApiUser>? = null
}

