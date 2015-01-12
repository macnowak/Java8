package pl.net.nowak.java8.lambdas;

/**
 * Author: Maciek
 */
public class Person implements Named, Student {

    private int age;

    public Person(int age) {
        this.age = age;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @Override
    public String getName() {
        return Named.super.getName();
    }
}
