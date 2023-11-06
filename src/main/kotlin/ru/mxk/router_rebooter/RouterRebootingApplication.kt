package ru.mxk.router_rebooter

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.context.properties.EnableConfigurationProperties
import org.springframework.boot.runApplication
import ru.mxk.router_rebooter.config.RebootingProperties

@SpringBootApplication
@EnableConfigurationProperties(RebootingProperties::class)
class RouterRebootingApplication

fun main(args: Array<String>) {
    runApplication<RouterRebootingApplication>(*args)
}
