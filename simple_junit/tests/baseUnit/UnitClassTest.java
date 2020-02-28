package baseUnit;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import org.junit.FixMethodOrder;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class UnitClassTest {

	
	static int testInstValue;
	UnitClass myObjectUnderTest;
	
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		System.out.println("Set UP Before Class ");
		
		testInstValue = 10;
	
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
		System.out.println("Tear Down after Class ");		
		
	}

	@Before
	public void setUp() throws Exception {
		System.out.println("Set UP before test ");

		myObjectUnderTest = new UnitClass( testInstValue );
		
		System.out.println( myObjectUnderTest );

	}

	@After
	public void tearDown() throws Exception {
		System.out.println("Tear Down after test ");
		
	}

	@Test
	public void testSetValue() {
		System.out.println( " Test setValue ...." + myObjectUnderTest );
		
		assertTrue( myObjectUnderTest.getValue() > 0 );

		myObjectUnderTest.setValue( 1 );

		System.out.println( " after testSetValue  " + myObjectUnderTest );
		
		
	}

	@Test
	public void testOther() {
		System.out.println( " Test Other ...." + myObjectUnderTest );
		
		assertTrue( myObjectUnderTest.getValue() > 0 );

		myObjectUnderTest.setValue( 2 );
		
		assertTrue( myObjectUnderTest.getValue() - 5 > 0 );
		

		System.out.println( " after testOther  " + myObjectUnderTest );
		
		
	}
	
	
}
