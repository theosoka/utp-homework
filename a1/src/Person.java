public final class Person implements IAggregable<Person, Integer>, IDeeplyCloneable<Person> {
    private int _age;

    public Person() {
    }

    public Person(int age) {
        this._age = age;
    }

    public int age() {
        return this._age;
    }

    public Integer aggregate(Integer intermediateResult) {
        return intermediateResult == null ? this._age : this._age + intermediateResult;
    }

    public Person deepClone() {
        return new Person(this._age);
    }
}