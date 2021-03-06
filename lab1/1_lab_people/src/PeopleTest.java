import static org.junit.Assert.*;


import org.junit.Before;
import org.junit.Test;

public class PeopleTest {
	
	People testpeople = new People();
		
	@Before
	public void setUp() throws Exception {
		testpeople.add(new Person("�������", "����������", "�������", 22));
		testpeople.add(new Person("������", "����������", "�������", 25));
		testpeople.add(new Person("������", "��������", "�������", 30));		
	}

	@Test
	public void test_add() throws WrongAgeValueException {
		testpeople.add(new Person("�����", "���������", "�����", 28));
		assertEquals("������� ���������� �������, 22\n"
				+ "������ ���������� �������, 25\n"
				+ "������ �������� �������, 30\n"
				+ "����� ��������� �����, 28\n", 
						testpeople.toString());
	}
	
	@Test
	public void test_add_index() throws WrongAgeValueException	{
		testpeople.add(2,new Person("�����", "���������", "�����", 28));
		assertEquals("������� ���������� �������, 22\n"
				+ "������ ���������� �������, 25\n"
				+ "����� ��������� �����, 28\n"
				+ "������ �������� �������, 30\n", 
						testpeople.toString());
	}
	
	@Test (expected = IndexOutOfBoundsException.class)
	public void test_add_index_out_of_bounds()
	{
		testpeople.add(6, new Person());
	}	
	
	@Test
	public void test_remove()
	{
		testpeople.remove(0);
		assertEquals("������ ���������� �������, 25\n"
				+ "������ �������� �������, 30\n", 
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