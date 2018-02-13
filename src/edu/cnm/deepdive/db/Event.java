package edu.cnm.deepdive.db;

import java.sql.Timestamp;
import java.util.Collection;
import java.util.Objects;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity
public class Event {

  private long id;
  private String name;
  private String description;
  private Timestamp start;
  private Timestamp finish;
  private Person personByOrganizerId;
  private Collection<PersonEvent> personEventsById;

  @Id
  @Column(name = "ID", nullable = false)
  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }

  @Basic
  @Column(name = "NAME", nullable = false, length = 100)
  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  @Basic
  @Column(name = "DESCRIPTION", nullable = true, length = 32700)
  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  @Basic
  @Column(name = "START", nullable = false)
  public Timestamp getStart() {
    return start;
  }

  public void setStart(Timestamp start) {
    this.start = start;
  }

  @Basic
  @Column(name = "FINISH", nullable = false)
  public Timestamp getFinish() {
    return finish;
  }

  public void setFinish(Timestamp finish) {
    this.finish = finish;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Event event = (Event) o;
    return id == event.id &&
        Objects.equals(name, event.name) &&
        Objects.equals(description, event.description) &&
        Objects.equals(start, event.start) &&
        Objects.equals(finish, event.finish);
  }

  @Override
  public int hashCode() {

    return Objects.hash(id, name, description, start, finish);
  }

  @ManyToOne
  @JoinColumn(name = "ORGANIZER_ID", referencedColumnName = "ID")
  public Person getPersonByOrganizerId() {
    return personByOrganizerId;
  }

  public void setPersonByOrganizerId(Person personByOrganizerId) {
    this.personByOrganizerId = personByOrganizerId;
  }

  @OneToMany(mappedBy = "eventByEventId")
  public Collection<PersonEvent> getPersonEventsById() {
    return personEventsById;
  }

  public void setPersonEventsById(Collection<PersonEvent> personEventsById) {
    this.personEventsById = personEventsById;
  }
}
