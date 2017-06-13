package br.com.zup.hack4food.person.controller

import br.com.zup.hack4food.person.Person
import br.com.zup.hack4food.person.Repository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import java.util.*

@RestController
@RequestMapping("/person")
class Controller @Autowired constructor(val repository: Repository) {

    @GetMapping
    fun findAll() =
            ResponseEntity(repository.findAll().toRepresentation(), HttpStatus.OK)

    @GetMapping("/{id}")
    fun findById(@PathVariable("id") id: Int): ResponseEntity<PersonRepresentation> {
        val person = repository.findOne(id)

        return if (person?.toRepresentation() != null)
            ResponseEntity(person.toRepresentation(), HttpStatus.OK)
        else
            ResponseEntity(HttpStatus.NOT_FOUND)
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

}

data class PersonRepresentation(val id: Int?, val name: String, val money: String)
