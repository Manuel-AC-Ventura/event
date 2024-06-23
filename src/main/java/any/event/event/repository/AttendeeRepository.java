package any.event.event.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import any.event.event.model.Attendee;

public interface AttendeeRepository extends JpaRepository<Attendee, Integer>{

}
