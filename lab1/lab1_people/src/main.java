
public class main {

	public static void main(String[] args) {
			try{
				Person m1 = new Person("Георгий", "Викторович", "Мусатов", -22);
			}catch (WrongAgeValueException me1){
				System.err.print(me1);
			}
	}
}
