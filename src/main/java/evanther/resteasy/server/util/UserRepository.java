package evanther.resteasy.server.util;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class UserRepository {
    private static UserRepository instance;

    private Map<Long, User> userTable;

    private UserRepository() {
        userTable = new HashMap<Long, User>();
        userTable.put(1l, new User(1l, "Mario"));
        userTable.put(2l, new User(2l, "Pepe"));
        userTable.put(3l, new User(3l, "Alberto"));
    }

    public static UserRepository getInstance() {
        if (instance == null) {
            instance = new UserRepository();
        }
        return instance;
    }

    public Boolean exists(Long id) {
        return userTable.containsKey(id);
    }

    public List<User> list() {
        return new ArrayList<User>(userTable.values());
    }

    public void add(User user) {
        Long nextId;
        if (userTable.isEmpty()) {
            nextId = 1l;
        } else {
            nextId = Collections.max(userTable.keySet()) + 1;
        }
        user.setId(nextId);
        userTable.put(nextId, user);
    }

    public void update(User user) {
        if (!userTable.containsKey(user.getId())) {
            throw new RuntimeException("User Id not found");
        }
        userTable.put(user.getId(), user);
    }

    public void remove(Long id) {
        if (!userTable.containsKey(id)) {
            throw new RuntimeException("User Id not found");
        }
        userTable.remove(id);
    }

    public User get(Long id) {
        if (!userTable.containsKey(id)) {
            throw new RuntimeException("User Id not found");
        }
        return userTable.get(id);
    }
}
