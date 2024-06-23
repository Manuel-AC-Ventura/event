package any.event.event.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import jakarta.persistence.Table;
import java.text.Normalizer;
import java.time.LocalDate;
import java.util.UUID;

@Entity
@Table(name = "events")
public class Event {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private UUID id;

  @Column(nullable = false)
  private String title;

  @Column(nullable = false)
  private String details;

  @Column(nullable = false)
  private String slug;

  @Column(nullable = false)
  private int maxAttendees;

  @Column(nullable = false)
  private LocalDate startDate;

  @Column(nullable = false)
  private LocalDate endDate;

  @Column(nullable = false)
  private String location;

  @Column(nullable = false)
  private LocalDate createdAt;

  public Event() {
    this.id = UUID.randomUUID();
    this.createdAt = LocalDate.now();
  }


  public UUID getId() {
    return id;
  }


  public void setId(UUID id) {
    this.id = id;
  }


  public String getTitle() {
    return title;
  }


  public void setTitle(String title) {
    this.title = title;
  }


  public String getDetails() {
    return details;
  }


  public void setDetails(String details) {
    this.details = details;
  }


  public String getSlug() {
    return slug;
  }


  public void setSlug(String slug) {
    this.slug = slug;
  }


  public int getMaxAttendees() {
    return maxAttendees;
  }


  public void setMaxAttendees(int maxAttendees) {
    this.maxAttendees = maxAttendees;
  }


  public LocalDate getStartDate() {
    return startDate;
  }


  public void setStartDate(LocalDate startDate) {
    this.startDate = startDate;
  }


  public LocalDate getEndDate() {
    return endDate;
  }


  public void setEndDate(LocalDate endDate) {
    this.endDate = endDate;
  }


  public String getLocation() {
    return location;
  }


  public void setLocation(String location) {
    this.location = location;
  }


  public LocalDate getCreatedAt() {
    return createdAt;
  }


  public void setCreatedAt(LocalDate createdAt) {
    this.createdAt = createdAt;
  }


  @PrePersist
  @PreUpdate
  private void prepareSlug() {
    this.slug = generateSlug(this.title);
  }

  private String generateSlug(String input) {
    if (input == null || input.isEmpty()) {
      return "";
    }
    String noAccent = Normalizer.normalize(input, Normalizer.Form.NFD)
                   .replaceAll("[^\\p{ASCII}]", "");
    String slug = noAccent.toLowerCase()
                .replaceAll("[^a-z0-9\\s]", "")
                .replaceAll("\\s+", "-");
    return slug;
  }
}