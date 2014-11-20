
public class Person {
	//Обьявление полей класса
	private String firstName;
	private String middleName;
	private String lastName;
	private int age;
	
	Person(){};
	
	//метод записывает данные в поля	
	Person (String firstName, String middleName, 
			String lastName, int age) throws WrongAgeValueException{
		this.firstName = firstName;
		this.middleName = middleName;
		this.lastName = lastName;
		setAge(age);
	}
	
	//установка и проверка возраста
	public void setAge(int age) throws WrongAgeValueException{
		if((age>=0) && (age<150)){
			this.age = age;			
		}
		else{
			throw new WrongAgeValueException();			
		}
	}
	
	
	public String toString()
	{
		return (this.firstName + " " + this.middleName + " " + this.lastName+ ", " + this.age);
	}
	
}
