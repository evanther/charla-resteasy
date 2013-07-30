package evanther.resteasy;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

public class UserRepository {
    private static final Map<Long, User> table;

    private UserRepository() {
    }

    static {
        table = new HashMap<Long, User>();
        table.put(1l, new User(1l, "Mario"));
        table.put(2l, new User(2l, "Pepe"));
        table.put(3l, new User(3l, "Alberto"));
    }

    @XmlRootElement(name = "user")
    public static final class User {

        // Me obliga el serializer de XML
        public User() {
        }

        private Long id;
        private String name;

        public User(Long id, String name) {
            this.id = id;
            this.name = name;
        }

        @XmlElement
        public Long getId() {
            return id;
        }
        public void setId(Long id) {
            this.id = id;
        }

        @XmlElement
        public String getName() {
            return name;
        }
        public void setName(String name) {
            this.name = name;
        }
    }

    @XmlRootElement(name = "users")
    public static final class UserList {
        private List<User> users;

        // Me obliga el serializer de XML
        public UserList() {
        }

        public UserList(Collection<User> users) {
            this.users = new ArrayList<User>(users);
        }

        @XmlElement(name = "user")
        public List<User> getUsers() {
            return users;
        }

        public void setUsers(List<User> users) {
            this.users = users;
        }
    }

    public static final Boolean exists(Long id) {
        return table.containsKey(id);
    }

    public static final UserList list() {
        return new UserList(table.values());
    }

    public static final void add(User user) {
        Long nextId;
        if (table.isEmpty()) {
            nextId = 1l;
        } else {
            nextId = Collections.max(table.keySet()) + 1;
        }
        user.setId(nextId);
        table.put(nextId, user);
    }

    public static final void update(User user) {
        if (!table.containsKey(user.getId())) {
            throw new RuntimeException("UserId not found");
        }
        table.put(user.getId(), user);
    }

    public static final void remove(Long id) {
        if (!table.containsKey(id)) {
            throw new RuntimeException("UserId not found");
        }
        table.remove(id);
    }

    public static final User get(Long id) {
        if (!table.containsKey(id)) {
            throw new RuntimeException("UserId not found");
        }
        return table.get(id);
    }
}
