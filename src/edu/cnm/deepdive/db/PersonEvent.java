package edu.cnm.deepdive.db;

import java.util.Objects;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "PERSON_EVENT", schema = "APP", catalog = "")
public class PersonEvent {

  private long id;
  private boolean confirmed;
  private Person personByPersonId;
  private Event eventByEventId;

  @Id
  @Column(name = "ID", nullable = false)
  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }

  @Basic
  @Column(name = "CONFIRMED", nullable = false)
  public boolean isConfirmed() {
    return confirmed;
  }

  public void setConfirmed(boolean confirmed) {
    this.confirmed = confirmed;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    PersonEvent that = (PersonEvent) o;
    return id == that.id &&
        confirmed == that.confirmed;
  }

  @Override
  public int hashCode() {

    return Objects.hash(id, confirmed);
  }

  @ManyToOne
  @JoinColumn(name = "PERSON_ID", referencedColumnName = "ID", nullable = false)
  public Person getPersonByPersonId() {
    return personByPersonId;
  }

  public void setPersonByPersonId(Person personByPersonId) {
    this.personByPersonId = personByPersonId;
  }

  @ManyToOne
  @JoinColumn(name = "EVENT_ID", referencedColumnName = "ID", nullable = false)
  public Event getEventByEventId() {
    return eventByEventId;
  }

  public void setEventByEventId(Event eventByEventId) {
    this.eventByEventId = eventByEventId;
  }
}
