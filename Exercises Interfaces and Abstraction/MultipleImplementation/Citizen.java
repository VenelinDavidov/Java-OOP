package Exercise_InterfacesAndAbstraction.MultipleImplementation;


public class Citizen implements Person, Birthable, Identifiable {

    private final String name;
    private final int age;
    private final String id;
    private final String birthtDay;

    public Citizen(String name, int age, String id, String birthDay) {
        this.name = name;
        this.age = age;
        this.id = id;
        this.birthtDay = birthDay;
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public int getAge() {
        return this.age;
    }

    @Override
    public String getBirthDate() {
        return this.birthtDay;
    }

    @Override
    public String getId() {
        return this.id;
    }

    @Override
    public String toString() {
        return "Citizen{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", id='" + id + '\'' +
                ", birthtDay='" + birthtDay + '\'' +
                '}';
    }
}


