package br.com.zup.hack4food.person

import org.springframework.stereotype.Component

@Component
class RepositoryImpl : Repository {

    override fun findAll() =
        DataSet.data.toList()

    override fun add(person: Person): Person {
        val newPerson = person.copy(id = DataSet.newId())
        DataSet.data.add(newPerson)
        return newPerson
    }

    override fun findOne(id: Int) =
        DataSet.data.filter { it.id == id }.firstOrNull()

}