package ru.mxk.router_rebooter.converter

import com.fasterxml.jackson.databind.ObjectMapper
import org.springframework.http.HttpInputMessage
import org.springframework.http.HttpOutputMessage
import org.springframework.http.MediaType
import org.springframework.http.converter.HttpMessageConverter
import org.springframework.stereotype.Component
import ru.mxk.router_rebooter.model.TokenResponse
import java.nio.charset.StandardCharsets

@Component
class TokenResponseMessageConverter(private val objectMapper: ObjectMapper) : HttpMessageConverter<TokenResponse> {

    override fun getSupportedMediaTypes(): List<MediaType> {
        return listOf(MediaType.TEXT_HTML)
    }

    override fun canRead(clazz: Class<*>, mediaType: MediaType?): Boolean = true

    override fun read(clazz: Class<out TokenResponse>, inputMessage: HttpInputMessage): TokenResponse {
        val text = String(inputMessage.body.readAllBytes(), StandardCharsets.UTF_8)
        return objectMapper.readValue(text, TokenResponse::class.java)
    }

    override fun canWrite(clazz: Class<*>, mediaType: MediaType?): Boolean = false

    override fun write(t: TokenResponse, contentType: MediaType?, outputMessage: HttpOutputMessage) = throw NotImplementedError()
}
