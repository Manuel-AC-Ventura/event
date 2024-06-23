package any.event.event.model;

import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class Attendee {

  @Id
  private int id;

  @Column(nullable = false)
  private String name;

  @Column(nullable = false)
  private String email;

  @Column(nullable = false)
  private LocalDate createdAt;


  public Attendee() {
    this.createdAt = LocalDate.now();
  }


  public int getId() {
    return id;
  }


  public void setId(int id) {
    this.id = id;
  }


  public String getName() {
    return name;
  }


  public void setName(String name) {
    this.name = name;
  }


  public String getEmail() {
    return email;
  }


  public void setEmail(String email) {
    this.email = email;
  }


  public LocalDate getCreatedAt() {
    return createdAt;
  }


  public void setCreatedAt(LocalDate createdAt) {
    this.createdAt = createdAt;
  }

  
}
