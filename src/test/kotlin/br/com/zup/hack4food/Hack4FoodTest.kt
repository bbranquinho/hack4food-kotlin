package br.com.zup.hack4food

import br.com.zup.hack4food.person.DataSet
import br.com.zup.hack4food.person.Money
import br.com.zup.hack4food.person.Person
import br.com.zup.hack4food.person.controller.jsonToObject
import br.com.zup.hack4food.person.controller.objectToJson
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
    fun whenList() {
        val x = readLine()!!.toInt()
        val validNumbers = arrayListOf(11, 15, 20)

        when (x) {
            in 1..10 -> print("x is in the range")
            in validNumbers -> print("x is valid")
            !in 10..20 -> print("x is outside the range")
            else -> print("none of the above")
        }
    }

    @Test
    fun cast() {
        val people: IPerson = Augusto()

        if (people is Branquinho) {
            people.methodBranquinho()
        }

        when (people) {
            is Augusto -> people.methodAugusto()
            is Branquinho -> people.methodBranquinho()
            else -> throw IllegalArgumentException()
        }
    }

    @Test
    fun compare() {
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
    }

    @Test
    fun concat() {
        val augusto = Person(id = 1, name =  "Augusto", money = Money(amount = BigDecimal(1000), currency = "R$"))

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
]""")
    }

    @Test
    fun listPair() {
        val map: List<Pair<Int?, Person>> = DataSet.data.map { it.id to it }

        val (id, person) = map.first()

        println("${id} ${person}")
    }

}

interface IPerson

class Branquinho: IPerson {
    fun methodBranquinho() {
        println("Branquinho")
    }
}

class Augusto: IPerson {
    fun methodAugusto() {
        println("Augusto")
    }
}
