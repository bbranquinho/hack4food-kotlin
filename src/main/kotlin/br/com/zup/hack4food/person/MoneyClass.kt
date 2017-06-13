package br.com.zup.hack4food.person

import java.math.BigDecimal

class MoneyClass(amount: BigDecimal, currency: String) {

    var amount: BigDecimal = amount
        get() {
            return field
        }
        set(value) {
            field = value
        }

    val currency: String = currency

}
