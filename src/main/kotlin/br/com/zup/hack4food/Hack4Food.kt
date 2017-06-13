package br.com.zup.hack4food

import org.springframework.boot.SpringApplication

fun main(args: Array<String>) {
    println("Hello, Hack4Food!")
    SpringApplication.run(Hack4FoodConfig::class.java, *args)
}
