package evanther.resteasy.server.util;

public class Drink {
    private Integer id;

    public Drink(String id) {
        this.id = Integer.valueOf(id);
    }

    public String getName() {
        String name;
        if (id == 1) {
            name = "coffee";
        } else if (id == 2) {
            name = "latte";
        } else if (id == 3) {
            name = "tea";
        } else {
            name = "other";
        }
        return name;
    }
}
