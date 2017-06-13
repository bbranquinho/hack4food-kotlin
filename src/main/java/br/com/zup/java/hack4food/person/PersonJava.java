package br.com.zup.java.hack4food.person;

import br.com.zup.hack4food.person.Money;

import javax.validation.constraints.NotNull;

public class PersonJava {

    private final Integer id;

    @NotNull
    private final String name;

    @NotNull
    private final Integer age;

    @NotNull
    private final Double salary;

    @NotNull
    private final Money money;

    public PersonJava(Integer id, String name, Integer age, Double salary, Money money) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.salary = salary;
        this.money = money;
    }

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Integer getAge() {
        return age;
    }

    public Double getSalary() {
        return salary;
    }

    public final PersonJava copy(Integer id, @NotNull String name, @NotNull Integer age, @NotNull Double salary, @NotNull Money money) {
        return new PersonJava(id, name, age, salary, money);
    }

    @Override
    public String toString() {
        return "PersonJava{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", age=" + age +
                ", salary=" + salary +
                ", currency=" + money +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;

        if (o == null || getClass() != o.getClass())
            return false;

        PersonJava that = (PersonJava) o;

        if (id != null ? !id.equals(that.id) : that.id != null)
            return false;

        if (!name.equals(that.name))
            return false;

        if (!age.equals(that.age))
            return false;

        if (!salary.equals(that.salary))
            return false;

        return money.equals(that.money);
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;

        result = 31 * result + name.hashCode();
        result = 31 * result + age.hashCode();
        result = 31 * result + salary.hashCode();
        result = 31 * result + money.hashCode();

        return result;
    }
}
