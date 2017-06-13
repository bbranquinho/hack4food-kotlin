package br.com.zup.hack4food.person

import java.math.BigDecimal

data class Person(val id: Int?, val name: String, val money: Money)

class Money(amount: BigDecimal, currency: String) {
    var amount: BigDecimal = amount
        get() {
            return field
        }
        set(value) {
            field = value
        }

    val currency: String = currency
}
