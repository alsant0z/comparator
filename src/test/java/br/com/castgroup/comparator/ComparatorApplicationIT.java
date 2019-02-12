package br.com.castgroup.comparator;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ComparatorApplicationIT {
	
	@Test
	public void testMethod() {
		System.out.println("####Teste integration");
		assertEquals(4, 4);
		LoggerFactory.getLogger(ComparatorApplicationIT.class).debug("##Teste###");
	}

}
