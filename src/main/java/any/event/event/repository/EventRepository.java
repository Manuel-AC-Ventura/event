package any.event.event.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import any.event.event.model.Event;

public interface EventRepository extends JpaRepository<Event, UUID>{

}
