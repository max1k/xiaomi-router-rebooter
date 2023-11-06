package ru.mxk.router_rebooter.config

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.web.client.RestTemplate
import ru.mxk.router_rebooter.converter.TokenResponseMessageConverter

@Configuration
class RebootingConfiguration {

    @Bean
    fun rebootingRestTemplate(converter: TokenResponseMessageConverter): RestTemplate {
        return RestTemplate().apply { messageConverters.add(converter) }
    }

}