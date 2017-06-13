package br.com.zup.hack4food.person

import java.math.BigDecimal

data class Person(val id: Int?, val name: String, val money: Money)
data class Money(val amount: BigDecimal, val currency: String)
