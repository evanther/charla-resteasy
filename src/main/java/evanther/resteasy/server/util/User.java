package evanther.resteasy.server.util;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "user")
public class User {

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