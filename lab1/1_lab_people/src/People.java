import java.util.ArrayList;

public class People 
{
	private ArrayList<Person> people = new ArrayList<Person>(); 
	
	People(){}
	
	public boolean add(Person p) {
		return people.add(p);
	}
	
	public void add(int index, Person p) {
		people.add(index, p);
	}
	
	public Person remove(int index)	{
		return people.remove(index);
	}
	
	public void clear()	{
		people.clear();
	}
	
	public String toString()
	{
		StringBuffer strbuf = new StringBuffer();
		for (Person p : people)
		{
			strbuf.append(p.toString() + "\n");
		}
		return strbuf.toString();
	}
	
}