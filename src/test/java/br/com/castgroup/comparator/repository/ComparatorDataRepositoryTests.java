package br.com.castgroup.comparator.repository;

import static org.junit.Assert.assertEquals;

import java.util.Optional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import br.com.castgroup.comparator.entity.ComparatorData;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ComparatorDataRepositoryTests {
	
	@Autowired
	private ComparatorDataRepository repository;

	@Test
	public void testCriarElemento() {
		ComparatorData data = new ComparatorData(1, "blah", "blah");
		repository.save(data);
		
		Optional<ComparatorData> optionalData = repository.findById(Long.parseLong("1"));
		
		assertEquals(data, optionalData.get());		
	}
}
