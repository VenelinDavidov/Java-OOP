package Exercises_Inheritance.PlayersAndMonsters;

public class Hero {
    //fileds
    private String username;
    private int level;

    // constructor
    public Hero(String username, int level) {
        this.username = username;
        this.level = level;
    }

    // getter
    public String getUsername() {
        return username;
    }

    public int getLevel() {
        return level;
    }
    // to String
    @Override
    public String toString() {
        return String.format("Type: %s Username: %s Level: %d",
                this.getClass().getName(),
                this.getUsername(),
                this.getLevel());
    }
}
