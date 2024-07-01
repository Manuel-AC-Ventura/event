package any.event.event.controller;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import java.util.Arrays;
import java.util.Optional;
import java.util.UUID;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import any.event.event.model.Event;
import any.event.event.repository.EventRepository;
import com.fasterxml.jackson.databind.ObjectMapper;

public class EventControllerTest {

  private MockMvc mockMvc;

  @Mock
  private EventRepository eventRepository;

  @InjectMocks
  private EventController eventController;

  private final ObjectMapper objectMapper = new ObjectMapper();

  @BeforeEach
  void setUp() {
    mockMvc = MockMvcBuilders.standaloneSetup(eventController).build();
  }

  @Test
  void testAddEvent() throws Exception {
    Event event = new Event();
    event.setId(UUID.randomUUID());
    event.setTitle("Test Event");
    when(eventRepository.existsById(any(UUID.class))).thenReturn(false);
    when(eventRepository.save(any(Event.class))).thenReturn(event);

    mockMvc.perform(post("/api/event/add")
            .contentType(MediaType.APPLICATION_JSON)
            .content(objectMapper.writeValueAsString(event)))
            .andExpect(status().isOk())
            .andExpect(content().string(objectMapper.writeValueAsString(event)));

    verify(eventRepository, times(1)).save(any(Event.class));
  }

  @Test
  void testDeleteEvent() throws Exception {
    UUID id = UUID.randomUUID();
    when(eventRepository.existsById(id)).thenReturn(true);

    mockMvc.perform(delete("/api/event/delete/{id}", id))
            .andExpect(status().isOk())
            .andExpect(content().string("Event deleted successfully"));

    verify(eventRepository, times(1)).deleteById(id);
  }

  @Test
  void testGetAllEvents() throws Exception {
    Event event1 = new Event();
    event1.setTitle("Event 1");
    Event event2 = new Event();
    event2.setTitle("Event 2");
    when(eventRepository.findAll()).thenReturn(Arrays.asList(event1, event2));

    mockMvc.perform(get("/api/event/all"))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$[0].title").value("Event 1"))
            .andExpect(jsonPath("$[1].title").value("Event 2"));

    verify(eventRepository, times(1)).findAll();
  }

  @Test
  void testGetEvent() throws Exception {
    UUID id = UUID.randomUUID();
    Event event = new Event();
    event.setId(id);
    event.setTitle("Test Event");
    when(eventRepository.existsById(id)).thenReturn(true);
    when(eventRepository.findById(id)).thenReturn(Optional.of(event));

    mockMvc.perform(get("/api/event/get/{id}", id))
            .andExpect(status().isOk())
            .andExpect(content().string(event.toString()));

    verify(eventRepository, times(1)).findById(id);
  }

  @Test
  void testUpdateEvent() throws Exception {
    UUID id = UUID.randomUUID();
    Event event = new Event();
    event.setId(id);
    event.setTitle("Updated Event");
    when(eventRepository.findById(id)).thenReturn(Optional.of(event));
    when(eventRepository.save(any(Event.class))).thenReturn(event);

    mockMvc.perform(put("/api/event/update")
            .contentType(MediaType.APPLICATION_JSON)
            .content(objectMapper.writeValueAsString(event)))
            .andExpect(status().isOk())
            .andExpect(content().string("Event updated successfully"));

    verify(eventRepository, times(1)).save(any(Event.class));
  }
}