package edu.cnm.deepdive.db;

import java.util.Collection;
import java.util.Objects;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Person {

  private long id;
  private String lastName;
  private String firstName;
  private Collection<Event> eventsById;
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
  @Column(name = "LAST_NAME", nullable = false, length = 100)
  public String getLastName() {
    return lastName;
  }

  public void setLastName(String lastName) {
    this.lastName = lastName;
  }

  @Basic
  @Column(name = "FIRST_NAME", nullable = false, length = 100)
  public String getFirstName() {
    return firstName;
  }

  public void setFirstName(String firstName) {
    this.firstName = firstName;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Person person = (Person) o;
    return id == person.id &&
        Objects.equals(lastName, person.lastName) &&
        Objects.equals(firstName, person.firstName);
  }

  @Override
  public int hashCode() {

    return Objects.hash(id, lastName, firstName);
  }

  @OneToMany(mappedBy = "personByOrganizerId")
  public Collection<Event> getEventsById() {
    return eventsById;
  }

  public void setEventsById(Collection<Event> eventsById) {
    this.eventsById = eventsById;
  }

  @OneToMany(mappedBy = "personByPersonId")
  public Collection<PersonEvent> getPersonEventsById() {
    return personEventsById;
  }

  public void setPersonEventsById(Collection<PersonEvent> personEventsById) {
    this.personEventsById = personEventsById;
  }
}
