import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class Test_sq {
	Complex cn1 = new Complex(); 
	Complex cn2 = new Complex();
	
	@Test 
	public void test_D_zero() 
	{
		Equation eq = new Equation (1, 6, 9);
		Complex check = new Complex(-3.0, 0);
		eq.Calculate(cn1, cn2);
		assertEquals(check, cn1); 
		assertEquals(check, cn2); 
	}
	
	@Test
	public void test_D_is_pos()
	{
		Equation eq = new Equation(2, 5, 3);
		Complex check1 = new Complex(-1.0, 0);
		Complex check2 = new Complex(-1.5, 0);
		eq.Calculate(cn1, cn2);
		assertEquals(check1, cn1); 
		assertEquals(check2, cn2); 
	}
	
	@Test
	public void test_D_is_neg()
	{
		Equation eq = new Equation(1, 2, 4);
		Complex check1 = new Complex(-1.0, 1.7320508);
		Complex check2 = new Complex(-1.0, -1.7320508);
		eq.Calculate(cn1, cn2);
		assertEquals(check1, cn1); 
		assertEquals(check2, cn2); 
	}
	
	@Test (expected = CoefficientIsZeroException.class)
	public void test_coeff_is_zero()
	{
		Equation eq = new Equation(0, 2, 4);
	}
}
