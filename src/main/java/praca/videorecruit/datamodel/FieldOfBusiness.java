package praca.videorecruit.datamodel;

import javax.persistence.*;
import java.util.List;

@Entity
public class FieldOfBusiness {
    private String name;
    private List<Person> personList;
    private List<Offer> positions;

    @Id
    @Column(name = "name", nullable = false)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        FieldOfBusiness that = (FieldOfBusiness) o;

        if (name != null ? !name.equals(that.name) : that.name != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return name != null ? name.hashCode() : 0;
    }

    @ManyToMany(mappedBy = "fieldOfBusinesses")
    public List<Person> getPersonList() {
        return personList;
    }

    public void setPersonList(List<Person> personList) {
        this.personList = personList;
    }

    @ManyToMany(mappedBy = "positionTypes")
    public List<Offer> getPositionTypesByName() {
        return positions;
    }

    public void setPositionTypesByName(List<Offer> positions) {
        this.positions = positions;
    }
}
