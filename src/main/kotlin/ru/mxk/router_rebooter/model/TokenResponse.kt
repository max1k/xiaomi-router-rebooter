package ru.mxk.router_rebooter.model

import com.fasterxml.jackson.annotation.JsonProperty

data class TokenResponse(
    @JsonProperty("token")
    val token: String,
    @JsonProperty("url")
    val url: String,
    @JsonProperty("code")
    val code: Int
)
