package ru.mxk.router_rebooter.runner

import org.apache.commons.codec.digest.DigestUtils
import org.springframework.boot.CommandLineRunner
import org.springframework.http.HttpEntity
import org.springframework.http.HttpHeaders
import org.springframework.http.MediaType
import org.springframework.stereotype.Component
import org.springframework.web.client.RestTemplate
import ru.mxk.router_rebooter.config.RebootingProperties
import ru.mxk.router_rebooter.model.TokenResponse
import java.lang.IllegalArgumentException
import java.time.Instant


private const val MAIN_PAGE_URL_PART = "/web/home"

@Component
class ApplicationStartRunner(
    private val config: RebootingProperties,
    private val restTemplate: RestTemplate
) : CommandLineRunner {

    override fun run(vararg args: String) {
        val requestEntity = getEntity()

        val response = restTemplate.postForObject(
            config.url + config.loginUri,
            requestEntity,
            TokenResponse::class.java
        )

        println(response)

        val tokenUrl = response?.url?.replace(MAIN_PAGE_URL_PART, "") ?: throw IllegalArgumentException()
        val rebootInfo = restTemplate.getForObject(config.url + tokenUrl + config.rebootUri, String::class.java)

        println(rebootInfo)
    }

    private fun getEntity(): HttpEntity<String> {
        val time = Instant.now().epochSecond
        val nonce = "${config.type}_${config.deviceId}_${time}_${config.random}"
        val hashedPass = DigestUtils.sha1Hex(config.password + config.key)
        val doubleHashedPass = DigestUtils.sha1Hex(nonce + hashedPass)

        val headers = HttpHeaders().apply { contentType = MediaType.APPLICATION_FORM_URLENCODED }
        val tokenRequest = "username=${config.userName}&password=$doubleHashedPass&logtype=${config.logType}&nonce=$nonce"
        return HttpEntity(tokenRequest, headers)
    }
}
