package br.com.zup.hack4food

import br.com.zup.hack4food.person.Money
import br.com.zup.hack4food.person.Person
import br.com.zup.hack4food.pessoa.controller.jsonToObject
import br.com.zup.hack4food.pessoa.controller.objectToJson
import org.junit.Test
import org.junit.runner.RunWith
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.ContextConfiguration
import org.springframework.test.context.junit4.SpringRunner
import java.math.BigDecimal

@SpringBootTest
@RunWith(SpringRunner::class)
@ContextConfiguration(classes = arrayOf(Hack4FoodConfig::class))
class Hack4FoodTest {

    @Test
    fun findAllTest() {
        val person = Person(id = 10, name = "Name", money = Money(amount = BigDecimal("10.23"), currency = "R$"))

        val json: String = person.objectToJson()

        println("JSON: ${json}")

        val obj = json.jsonToObject(Person::class.java)

        println("OBJ: ${obj}")
    }

    @Test
    fun augustoBranquinho() {
        val augusto = Person(id = 1, name =  "Augusto", money = Money(amount = BigDecimal(1000), currency = "R$"))
        val branquinho = Person(id = 1, name =  "Augusto", money = Money(amount = BigDecimal(1000), currency = "R$"))

        if (augusto == branquinho) {
            println("Same values")
        } else {
            println("Houston, we have a problem!")
        }

        if (augusto === branquinho) {
            println("Houston, we have a problem!")
        } else {
            println("Equals")
        }

        if ((augusto == augusto) && (augusto === augusto)) {
            println("Ok")
        } else {
            println("Houston, we have a problem!")
        }

        println("Person[id=" + augusto.id + ", name='" + augusto.name + "', money=" + augusto.money.currency + " " + augusto.money.amount + "]")

        println("Person[id=${augusto.id}, name='${augusto.name}', money=${augusto.money.currency} ${augusto.money.amount}]")

        println("PersonMutableJava[\n" +
                "    id=" + augusto.id + ",\n" +
                "    name=" + augusto.name + ",\n" +
                "    money=" + augusto.money.currency + " " + augusto.money.amount + "\n" +
                "]")

        println(
"""PersonMutableJava[
    id=${augusto.id},
    name='${augusto.name}',
    money=${augusto.money.currency} ${augusto.money.amount}
]"""
        )

    }

}
