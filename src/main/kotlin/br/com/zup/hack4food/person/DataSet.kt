package br.com.zup.hack4food.person

import java.math.BigDecimal
import java.util.concurrent.atomic.AtomicInteger

object DataSet {

    private val id = AtomicInteger(1)

    val data: MutableSet<Person> = mutableSetOf(
            Person(id = newId(), name = "Person 1", money = Money(amount = BigDecimal("1273.28"), currency = "R$")),
            Person(id = newId(), name = "Person 2", money = Money(amount = BigDecimal("2012.75"), currency = "$"))
    )

    fun newId(): Int =
            id.getAndIncrement()

}