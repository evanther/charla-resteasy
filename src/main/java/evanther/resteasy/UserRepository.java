package evanther.resteasy;

import java.util.HashMap;
import java.util.Map;

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
	
	public static final class User {
		private Long id;
		private String name;
		
		public User(Long id, String name) {
			this.id = id;
			this.name = name;
		}
		
		public Long getId() {
			return id;
		}
		public void setId(Long id) {
			this.id = id;
		}
		public String getName() {
			return name;
		}
		public void setName(String name) {
			this.name = name;
		}
	}
	
	public static final Boolean exists(Long id) {
		return table.containsKey(id);
	}
	
	public static final User get(Long id) {
		if (!table.containsKey(id)) {
			throw new RuntimeException("El id no existe");
		}
		return table.get(id);
	}
}
