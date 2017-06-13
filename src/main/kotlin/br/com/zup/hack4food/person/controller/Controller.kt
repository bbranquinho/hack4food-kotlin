package br.com.zup.hack4food.person.controller

import br.com.zup.hack4food.person.Person
import br.com.zup.hack4food.person.PersonRepresentation
import br.com.zup.hack4food.person.Repository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import java.math.BigDecimal
import java.util.*
import kotlin.coroutines.experimental.buildSequence

@RestController
@RequestMapping("/person")
class Controller @Autowired constructor(val repository: Repository) {

    @GetMapping
    fun findAll() =
            ResponseEntity(repository.findAll().toRepresentation(), HttpStatus.OK)

    @GetMapping("/{id}")
    fun findById(@PathVariable("id") id: Int): ResponseEntity<PersonRepresentation> {
//        val person = repository.findOne(id)

//        return if (person?.toRepresentation() != null)
//            ResponseEntity(person.toRepresentation(), HttpStatus.OK)
//        else
//            ResponseEntity(HttpStatus.NOT_FOUND)

//        return person?.let {
//            println(person)
//            return ResponseEntity(person.toRepresentation(), HttpStatus.OK)
//        } ?: ResponseEntity(HttpStatus.NOT_FOUND)

//        return person?.let {
//            ResponseEntity(person.toRepresentation(), HttpStatus.OK)
//        } ?: ResponseEntity(HttpStatus.NOT_FOUND)

        return Optional.ofNullable(repository.findOne(id))
                .map { ResponseEntity<PersonRepresentation>(it.toRepresentation(), HttpStatus.OK) }
                .orElse(ResponseEntity<PersonRepresentation>(HttpStatus.NOT_FOUND))
    }

    @GetMapping("/percentage/{id}")
    fun percentage(@PathVariable("id") id: Int) =
            Optional.ofNullable(repository.findOne(id))
                    .map { ResponseEntity(it.money.amount percentage 10, HttpStatus.OK) }
                    .orElse(ResponseEntity(HttpStatus.NOT_FOUND))

    @PostMapping
    @ResponseStatus(HttpStatus.OK)
    fun add(@RequestBody person: Person) =
            repository.add(person).toRepresentation()

    @GetMapping("/sum/{firstId}/{secondId}")
    fun sum(@PathVariable("firstId") firstId: Int, @PathVariable("secondId") secondId: Int): ResponseEntity<String> {
        val first = repository.findOne(firstId)
        val second = repository.findOne(secondId)

        if ((first == null) || (second == null))
            return ResponseEntity(HttpStatus.NOT_FOUND)

        val sum = first.money + second.money

        return ResponseEntity("${sum.currency} ${sum.amount}", HttpStatus.OK)
    }

    @GetMapping("/currency/{id}")
    fun currency(@PathVariable("id") id: Int): ResponseEntity<String> {
        when (repository.findOne(id)?.money?.currency) {
            "R$" -> return ResponseEntity("Real", HttpStatus.OK)
            "$" -> return ResponseEntity("Dolar", HttpStatus.OK)
            null -> return ResponseEntity(HttpStatus.NOT_FOUND)
            else -> return ResponseEntity(HttpStatus.NOT_FOUND)
        }
    }

    @GetMapping("/currency2/{id}")
    fun currency2(@PathVariable("id") id: Int) =
            when (repository.findOne(id)?.money?.currency) {
                "R$" -> ResponseEntity("Real", HttpStatus.OK)
                "$" -> ResponseEntity("Dolar", HttpStatus.OK)
                else -> ResponseEntity(HttpStatus.NOT_FOUND)
            }

    @GetMapping("/currency3/{currency}")
    fun findByCurrency(@PathVariable("currency") currency: String): List<BigDecimal> {
        val people = repository.findAll()

        val values = buildSequence {
            for (person in people) {
                if (person.money.currency == currency) {
                    yield(person.money.amount)
                }
            }
        }

        return values.toList()
    }

}
