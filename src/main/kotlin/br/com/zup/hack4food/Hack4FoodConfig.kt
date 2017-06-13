package br.com.zup.hack4food

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.context.annotation.ComponentScan
import org.springframework.context.annotation.Configuration

@SpringBootApplication
@Configuration
@ComponentScan(basePackages = arrayOf("br.com.zup.hack4food"))
open class Hack4FoodConfig
