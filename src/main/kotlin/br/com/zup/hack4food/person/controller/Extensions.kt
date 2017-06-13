package br.com.zup.hack4food.person.controller

import br.com.zup.hack4food.person.Money
import br.com.zup.hack4food.person.Person
import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import java.math.BigDecimal

private object JacksonExtension {

        val jacksonObjectMapper: ObjectMapper by lazy {
            jacksonObjectMapper()
        }

}

fun <T> String.jsonToObject(t: Class<T>): T =
        JacksonExtension.jacksonObjectMapper.readValue(this, t)

fun <T> T.objectToJson(): String =
        JacksonExtension.jacksonObjectMapper.writeValueAsString(this)

fun Person.toRepresentation() =
        PersonRepresentation(
                id = this.id,
                name = this.name,
                money = "${this.money.currency} ${this.money.amount}"
        )

fun List<Person>.toRepresentation() =
        this.map { it.toRepresentation() }

infix fun BigDecimal.percentage(percentage: Int) =
        this.multiply(BigDecimal(percentage)).divide(BigDecimal(100))

operator fun Money.plus(value: Money) =
        Money(this.amount + value.amount, this.currency)
