package praca.videorecruit.datamodel;

import javax.persistence.*;
import java.util.List;

@Entity
public class Privilege {
  
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
 
    private String name;

    public Privilege() {
    }

    public Privilege(String name) {
        this.name=name;
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

    public List<Role> getRoles() {
        return roles;
    }

    public void setRoles(List<Role> roles) {
        this.roles = roles;
    }

    @ManyToMany(mappedBy = "privileges")
    private List<Role> roles;
}