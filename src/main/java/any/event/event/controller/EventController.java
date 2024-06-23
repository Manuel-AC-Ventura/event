package any.event.event.controller;

import java.util.List;
import java.util.Objects;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import any.event.event.model.Event;
import any.event.event.repository.EventRepository;

@RestController
@RequestMapping("/api/event")
public class EventController {

  @Autowired
  private EventRepository eventRepository;

  @GetMapping("/get/{id}")
  public String getEvent(@PathVariable UUID id) {
    if(eventRepository.existsById(id)){
      return eventRepository.findById(id).toString();
    } else {
      return "Event not found";
    }
  }

  @GetMapping("/all")
  public List<Event> getAllEvents() {
    return eventRepository.findAll();
  }

  @PostMapping("/add")
  public ResponseEntity<?> addEvent(@RequestBody Event event) {
    if (event.getId() != null && eventRepository.existsById(event.getId())) {
      return ResponseEntity.badRequest().body("Evento com o ID fornecido já existe.");
    }
    Event savedEvent = eventRepository.save(event);
    return ResponseEntity.ok(savedEvent);
  }

  @PutMapping("/update")
  public String updateEvent(@RequestBody Event event) {
    if (event == null) {
      return "Invalid event data";
    }
    return eventRepository.findById(event.getId())
      .map(existingEvent -> {
        if (event.getTitle() != null) existingEvent.setTitle(event.getTitle());
        if (event.getDetails() != null) existingEvent.setDetails(event.getDetails());
        // Slug geralmente é gerado automaticamente com base no título, então considere se você realmente quer permitir a atualização manual do slug
        if (event.getSlug() != null) existingEvent.setSlug(event.getSlug());
        if (Objects.equals(event.getMaxAttendees(), null) && event.getMaxAttendees() != 0) existingEvent.setMaxAttendees(event.getMaxAttendees());
        if (event.getStartDate() != null) existingEvent.setStartDate(event.getStartDate());
        if (event.getEndDate() != null) existingEvent.setEndDate(event.getEndDate());
        if (event.getLocation() != null) existingEvent.setLocation(event.getLocation());
        // Não atualiza id e createdAt
        eventRepository.save(existingEvent);
        return "Event updated successfully";
      }).orElse("Event not found");
  }

  @DeleteMapping("/delete/{id}")
  public String deleteEvent(@PathVariable UUID id) {
    if(eventRepository.existsById(id)){
      eventRepository.deleteById(id);
      return "Event deleted successfully";
    } else {
      return "Event not found";
    }
  }
}
