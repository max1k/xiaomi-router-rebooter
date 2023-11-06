package ru.mxk.router_rebooter.config

import org.springframework.boot.context.properties.ConfigurationProperties

@ConfigurationProperties("application")
data class RebootingProperties(
    val url: String,
    val userName: String,
    val password: String,
    val key: String,
    val loginUri: String,
    val rebootUri: String,
    val type: Int,
    val logType: Int,
    val deviceId: String,
    val random: Int
)