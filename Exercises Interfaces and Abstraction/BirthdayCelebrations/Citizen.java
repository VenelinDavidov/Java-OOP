package Exercise_InterfacesAndAbstraction.BirthdayCelebrations;




public class Citizen implements Identifiable, Birthable,Person {

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


        public String getName() {
            return this.name;
        }


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


    }

