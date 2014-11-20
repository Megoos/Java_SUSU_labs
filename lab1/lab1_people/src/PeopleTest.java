import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

public class PeopleTest {
	
	People testpeople = new People();
	ArrayList<Person> newcol = new ArrayList<Person>();
	
	@Before
	public void setUp() throws Exception {
		testpeople.add(new Person("Георгий", "Викторович", "Мусатов", 22));
		testpeople.add(new Person("Михаил", "Евгеньевич", "Гуськов", 25));
		testpeople.add(new Person("Ксения", "Игоревна", "Иванова", 30));
		
		newcol.add(new Person("Анна", "Андреевна", "Викторова", 30));
		newcol.add(new Person("Сергей", "Сергеевич", "Кравченко", 27));
	}

	@Test
	public void test_add() throws WrongAgeValueException {
		testpeople.add(new Person("Федор", "Андреевич", "Демин", 28));
		assertEquals("Георгий Викторович Мусатов, 22\n"
				+ "Михаил Евгеньевич Гуськов, 25\n"
				+ "Ксения Игоревна Иванова, 30\n"
				+ "Федор Андреевич Демин, 28\n", 
						testpeople.toString());
	}
	
	@Test
	public void test_add_index() throws WrongAgeValueException	{
		testpeople.add(2,new Person("Федор", "Андреевич", "Демин", 28));
		assertEquals("Георгий Викторович Мусатов, 22\n"
				+ "Михаил Евгеньевич Гуськов, 25\n"
				+ "Федор Андреевич Демин, 28\n"
				+ "Ксения Игоревна Иванова, 30\n", 
						testpeople.toString());
	}
	
	@Test (expected = IndexOutOfBoundsException.class)
	public void test_add_index_out_of_bounds()
	{
		testpeople.add(6, new Person());
	}
	
	@Test
	public void test_addAll() throws WrongAgeValueException
	{
		testpeople.addAll(newcol);
		assertEquals("Георгий Викторович Мусатов, 22\n"
				+ "Михаил Евгеньевич Гуськов, 25\n"
				+ "Ксения Игоревна Иванова, 30\n"
				+ "Анна Андреевна Викторова, 30\n"
				+ "Сергей Сергеевич Кравченко, 27\n",
						testpeople.toString());
	}
	
	@Test
	public void test_addAll_index()
	{
		testpeople.addAll(2, newcol);
		assertEquals("Георгий Викторович Мусатов, 22\n"
				+ "Михаил Евгеньевич Гуськов, 25\n"
				+ "Анна Андреевна Викторова, 30\n"
				+ "Сергей Сергеевич Кравченко, 27\n"
				+ "Ксения Игоревна Иванова, 30\n",				
						testpeople.toString());
	}
	
	@Test (expected = IndexOutOfBoundsException.class)
	public void test_addAll_index_out_of_bounds()
	{
		testpeople.addAll(7, newcol);
	}
	
	@Test
	public void test_remove()
	{
		testpeople.remove(0);
		assertEquals("Михаил Евгеньевич Гуськов, 25\n"
				+ "Ксения Игоревна Иванова, 30\n", 
						testpeople.toString());
	}
	
	@Test (expected = IndexOutOfBoundsException.class)
	public void test_remove_index_out_of_bounds()
	{
		testpeople.remove(7);
	}
	
	@Test
	public void test_clear()
	{
		testpeople.clear();
		assertEquals("", testpeople.toString());
	}
}