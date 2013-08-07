package evanther.resteasy.server.util;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BookRepository {
    private static BookRepository instance;

    private Map<Long, Book> bookTable;

    private BookRepository() {
        bookTable = new HashMap<Long, Book>();
        bookTable.put(1l, new Book(1l, "Gabriel Garcia Marquez", "Cien Años de Soledad"));
        bookTable.put(2l, new Book(2l, "Pablo Neruda", "Los enemigos"));
        bookTable.put(3l, new Book(3l, "William Shakespeare", "El mercader de Venecia"));
    }

    public static BookRepository getInstance() {
        if (instance == null) {
            instance = new BookRepository();
        }
        return instance;
    }

    public Boolean exists(Long id) {
        return bookTable.containsKey(id);
    }

    public List<Book> list() {
        return new ArrayList<Book>(bookTable.values());
    }

    public void add(Book user) {
        Long nextId;
        if (bookTable.isEmpty()) {
            nextId = 1l;
        } else {
            nextId = Collections.max(bookTable.keySet()) + 1;
        }
        user.setId(nextId);
        bookTable.put(nextId, user);
    }

    public void update(Book user) {
        if (!bookTable.containsKey(user.getId())) {
            throw new RuntimeException("Book Id not found");
        }
        bookTable.put(user.getId(), user);
    }

    public void remove(Long id) {
        if (!bookTable.containsKey(id)) {
            throw new RuntimeException("Book Id not found");
        }
        bookTable.remove(id);
    }

    public Book get(Long id) {
        if (!bookTable.containsKey(id)) {
            throw new RuntimeException("Book Id not found");
        }
        return bookTable.get(id);
    }
}
