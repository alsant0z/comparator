package br.com.castgroup.comparator.controller;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;

import java.util.Optional;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.hamcrest.Matchers.containsString;
import br.com.castgroup.comparator.entity.ComparatorData;
import br.com.castgroup.comparator.repository.ComparatorDataRepository;

/**
 * Integration tests
 * 
 * @author alsantos
 *
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class ComparatorControllerIT {
	
	@Autowired
	private MockMvc mvc;
	
	@Autowired
	private ComparatorController controller;
	
	@Autowired
	private ComparatorDataRepository repository;
	
	@Before
	public void setup() throws Exception {
		MockitoAnnotations.initMocks(this);
		this.mvc = MockMvcBuilders.standaloneSetup(controller).build();
	}

	@Test
	public void testLeft() throws Exception {
		mvc.perform(MockMvcRequestBuilders.post("/v1/diff/1/left").accept(MediaType.APPLICATION_JSON)
				.contentType(MediaType.APPLICATION_JSON)
				.content("{\n" + "  \"data\": " + "  \"VGVzdGU=\"" + "}")).andExpect(status().isOk()).andReturn();
		Optional<ComparatorData> data = repository.findById(1L);
		assertNotNull(data.get());
		assertEquals(data.get().getId(), 1L);
		assertEquals(data.get().getLeft(), "VGVzdGU=");
		assertNull(data.get().getRight());
	}
	
	@Test
	public void testRight() throws Exception {
		mvc.perform(MockMvcRequestBuilders.post("/v1/diff/2/right").accept(MediaType.APPLICATION_JSON)
				.contentType(MediaType.APPLICATION_JSON)
				.content("{\n" + "  \"data\": " + "  \"VGVzdGU=\"" + "}")).andExpect(status().isOk()).andReturn();
		Optional<ComparatorData> data = repository.findById(2L);
		assertNotNull(data.get());
		assertEquals(data.get().getId(), 2L);
		assertEquals(data.get().getRight(), "VGVzdGU=");
		assertNull(data.get().getLeft());
	}
	
	@Test
	public void testLeftRight() throws Exception {
		mvc.perform(MockMvcRequestBuilders.post("/v1/diff/3/right").accept(MediaType.APPLICATION_JSON)
				.contentType(MediaType.APPLICATION_JSON)
				.content("{\n" + "  \"data\": " + "  \"VGVzdGU=\"" + "}")).andExpect(status().isOk()).andReturn();
		mvc.perform(MockMvcRequestBuilders.post("/v1/diff/3/left").accept(MediaType.APPLICATION_JSON)
				.contentType(MediaType.APPLICATION_JSON)
				.content("{\n" + "  \"data\": " + "  \"VGVzdGU=\"" + "}")).andExpect(status().isOk()).andReturn();
		Optional<ComparatorData> data = repository.findById(3L);
		assertNotNull(data.get());
		assertEquals(data.get().getId(), 3L);
		assertEquals(data.get().getRight(), "VGVzdGU=");
		assertEquals(data.get().getLeft(), "VGVzdGU=");
	}
	
	@Test
	public void testCompare() throws Exception {
		mvc.perform(MockMvcRequestBuilders.post("/v1/diff/4/right").accept(MediaType.APPLICATION_JSON)
				.contentType(MediaType.APPLICATION_JSON)
				.content("{\n" + "  \"data\": " + "  \"VGVzdGU=\"" + "}")).andExpect(status().isOk()).andReturn();
		mvc.perform(MockMvcRequestBuilders.post("/v1/diff/4/left").accept(MediaType.APPLICATION_JSON)
				.contentType(MediaType.APPLICATION_JSON)
				.content("{\n" + "  \"data\": " + "  \"VGVzdGU=\"" + "}")).andExpect(status().isOk()).andReturn();
		mvc.perform(MockMvcRequestBuilders.get("/v1/diff/4").accept(MediaType.APPLICATION_JSON)
				.contentType(MediaType.APPLICATION_JSON)).andExpect(status().isOk())
        .andExpect(content().string(containsString("true")));
		
		
	}
	
}
