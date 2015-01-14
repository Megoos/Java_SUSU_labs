import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

/**
 * Unit test for simple App.
 */
public class TestIS 
    extends TestCase
{
 
    public TestIS( String testName )
    {
        super( testName );
    }

        public static Test suite()
    {
        return new TestSuite( TestIS.class );
    }

        public void testApp()
    {
        assertTrue( true );
    }
}
