package br.com.castgroup.comparator.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import br.com.castgroup.comparator.entity.ComparatorData;
import br.com.castgroup.comparator.exception.ComparatorDataNotFoundException;
import br.com.castgroup.comparator.exception.InvalidContentException;
import br.com.castgroup.comparator.exception.InvalidDiffIdException;
import br.com.castgroup.comparator.exception.LeftSideNotFilledException;
import br.com.castgroup.comparator.exception.RightSideNotFilledException;

/**
 * Unit tests
 * @author alsantos
 *
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class ComparatorServiceTests {

	@Autowired
	private ComparatorService service;
	
	private String test1, test2, test3, test4;
	
	@Before
    public void setUp() {
		test1 = "VGVzdGU=";
		test2 = "VGVzdGU";
		test3 = "VGVzdGU=!@";
		test4 = "VGVzdGUV";
    }
	
	@Test(expected = InvalidDiffIdException.class)
    public void testAddLeftExceptionWhenDiffIdIsNull() throws InvalidDiffIdException, InvalidContentException {
        service.addLeft(null, null);
    }
	
	@Test(expected = InvalidDiffIdException.class)
    public void testAddLeftExceptionWhenDiffIdIdIsEmpty() throws InvalidDiffIdException, InvalidContentException {
        service.addLeft("", null);
    }
	
	@Test(expected = InvalidDiffIdException.class)
    public void testAddLeftExceptionWhenDiffIdIdNotLong() throws InvalidDiffIdException, InvalidContentException {
        service.addLeft("A", null);
    }
	
	@Test(expected = InvalidContentException.class)
    public void testAddLeftExceptionWhenContentIsNull() throws InvalidDiffIdException, InvalidContentException {
        service.addLeft("1", null);
    }
	
	@Test(expected = InvalidContentException.class)
    public void testAddLeftExceptionWhenContentIsEmpty() throws InvalidDiffIdException, InvalidContentException {
        service.addLeft("1", "");
    }
	
	@Test(expected = InvalidContentException.class)
    public void testAddLeftExceptionWhenContentIsNotBase64() throws InvalidDiffIdException, InvalidContentException {
        service.addLeft("1", test3);
    }
	
	@Test(expected = InvalidDiffIdException.class)
    public void testAddRightExceptionWhenDiffIdIsNull() throws InvalidDiffIdException, InvalidContentException {
        service.addRight(null, null);
    }
	
	@Test(expected = InvalidDiffIdException.class)
    public void testAddRightExceptionWhenDiffIdIdNotLong() throws InvalidDiffIdException, InvalidContentException {
        service.addRight("A", null);
    }
	@Test(expected = InvalidDiffIdException.class)
    public void testAddRightExceptionWhenDiffIdIdIsEmpty() throws InvalidDiffIdException, InvalidContentException {
        service.addRight("", null);
    }
	
	@Test(expected = InvalidContentException.class)
    public void testAddRightExceptionWhenContentIsNull() throws InvalidDiffIdException, InvalidContentException {
        service.addRight("1", null);
    }
	
	@Test(expected = InvalidContentException.class)
    public void testAddRightExceptionWhenContentIsEmpty() throws InvalidDiffIdException, InvalidContentException {
        service.addRight("1", "");
    }
	
	@Test(expected = InvalidContentException.class)
    public void testAddRightExceptionWhenContentIsNotBase64() throws InvalidDiffIdException, InvalidContentException {
        service.addRight("1", test3);
    }
	
	@Test(expected = ComparatorDataNotFoundException.class)
    public void testCompareWhenDataNotFound() throws InvalidDiffIdException, ComparatorDataNotFoundException, LeftSideNotFilledException, RightSideNotFilledException {
        service.compare("9");
    }
	
	@Test
    public void testAddLeftWithRightSideNull() throws InvalidContentException, InvalidDiffIdException {
		ComparatorData data = service.addLeft("1", test1);
		assertEquals(data.getId(), 1L);
        assertEquals(data.getLeft(), test1);
        assertNull(data.getRight());            
    }
	
	@Test
    public void testAddLeftWithRightSideNotNull() throws InvalidContentException, InvalidDiffIdException {
		ComparatorData data = service.addLeft("2", test1);
		data = service.addRight("2", test2);
		assertEquals(data.getId(), 2L);
        assertEquals(data.getLeft(), test1);
        assertEquals(data.getRight(), test2);            
    }
	
	@Test
    public void testAddRightWithLeftSideNull() throws InvalidContentException, InvalidDiffIdException {
		ComparatorData data = service.addRight("3", test1);
		assertEquals(data.getId(), 3L);
        assertEquals(data.getRight(), test1);
        assertNull(data.getLeft());            
    }
	
	@Test(expected = RightSideNotFilledException.class)
    public void testCompareWhenRightSideIsNull() throws InvalidDiffIdException, ComparatorDataNotFoundException, LeftSideNotFilledException, RightSideNotFilledException, InvalidContentException {
		ComparatorData data = service.addLeft("4", test1);
		assertEquals(data.getId(), 4L);
        assertEquals(data.getLeft(), test1);     
        assertNull(data.getRight());
        service.compare("4");
    }
	
	@Test(expected = RightSideNotFilledException.class)
    public void testCompareWhenLeftSideIsNull() throws InvalidDiffIdException, ComparatorDataNotFoundException, LeftSideNotFilledException, RightSideNotFilledException, InvalidContentException {
		ComparatorData data = service.addLeft("5", test1);
		assertEquals(data.getId(), 5L);
        assertEquals(data.getLeft(), test1);     
        assertNull(data.getRight());
        service.compare("5");
    }
	
	@Test
    public void testCompareEqual() throws InvalidContentException, InvalidDiffIdException, ComparatorDataNotFoundException, LeftSideNotFilledException, RightSideNotFilledException {
		ComparatorData data = service.addRight("6", test1);
		data = service.addLeft("6", test1);
		assertEquals(data.getId(), 6L);
        assertEquals(data.getRight(), test1); 
        assertEquals(data.getLeft(), test1); 
        assertEquals(service.compare("6").getMessage(), "Objects are equal!");
    }
	
	@Test
    public void testCompareNotEqual() throws InvalidContentException, InvalidDiffIdException, ComparatorDataNotFoundException, LeftSideNotFilledException, RightSideNotFilledException {
		ComparatorData data = service.addRight("7", test1);
		data = service.addLeft("7", test2);
		assertEquals(data.getId(), 7L);
        assertEquals(data.getRight(), test1); 
        assertEquals(data.getLeft(), test2);         
        assertEquals(service.compare("7").getMessage(), "Objects does not have the same size!");
    }
	
	@Test
    public void testCompareSameSize() throws InvalidContentException, InvalidDiffIdException, ComparatorDataNotFoundException, LeftSideNotFilledException, RightSideNotFilledException {
		ComparatorData data = service.addRight("8", test1);
		data = service.addLeft("8", test4);
		assertEquals(data.getId(), 8L);
        assertEquals(data.getRight(), test1); 
        assertEquals(data.getLeft(), test4); 
        assertEquals(service.compare("8").getMessage(), "Objects have the same size, but with different offsets: 7");
    }



}
