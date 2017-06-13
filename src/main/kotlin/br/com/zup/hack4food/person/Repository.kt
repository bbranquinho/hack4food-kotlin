package br.com.zup.hack4food.person

interface Repository {

    fun findAll(): List<Person>

    fun add(person: Person): Person

    fun findOne(id: Int): Person?

}