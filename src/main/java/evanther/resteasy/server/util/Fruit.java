package evanther.resteasy.server.util;

public class Fruit {
    private Integer id;

    public static Fruit valueOf(String value) {
        Fruit f = new Fruit();
        f.id = Integer.valueOf(value);
        return f;
    }

    public String getName() {
        String name;
        if (id == 1) {
            name = "pear";
        } else if (id == 2) {
            name = "apple";
        } else if (id == 3) {
            name = "orange";
        } else {
            name = "other";
        }
        return name;
    }
}
