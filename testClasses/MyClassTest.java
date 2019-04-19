package testClasses;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.jupiter.api.Test;

class MyClassTest {

	private MyClass my_class;
	private double[] testDataX = new double[] { 0.0, 1.35, 3.56, 9.278, -1.3591, -3.79123 };
	private double[] testDataY = new double[] { 3.3, 5.5, 2.5, 4.5, 9.5, -8.5 };
	private double[] answers = new double[] { 0.0, 7.425, 8.900, 41.751, -12.91145, 32.225455 };
	private double[] tolerances = new double[] { 1. / 10, 1. / 1000, 1. / 1000, 1. / 1000, 1. / 10000, 1e-6 };

	@BeforeClass
	public static void setUp() {

		System.out.println("once before all tests");
	}

	@Before
	public void setUpEachTest() {
		my_class = new MyClass();

		System.out.println("once before each test");
	}

	@Test
	public void testMultiply() {
		int value1 = (int) (Math.random() * 1000);
		int value2 = (int) (Math.random() * 1000);

		int actual = my_class.multiply(value1, value2);

		// Compute using another approach:
		int expected = value1 * value2;

		String error_message = "mulitiply - operand1: " + value1 + " operand2: " + value2;

		assertEquals(error_message, expected, actual);
	}

	@Test
	public void testMultiply1() {
		int value1 = (int) (Math.random() * 1000);
		int value2 = (int) (Math.random() * 1000);

		int actual = my_class.multiply(value1, value2);

		// Compute using another approach:
		int expected = value1 * value2;

		String error_message = "mulitiply - operand1: " + value1 + " operand2: " + value2;

		if (expected != actual) {
			fail(error_message);
		} else {
			System.out.println("Test multiply1 passes");
		}

	}

	@Test
	public void testMultiply2() {
		int i = 0;
		double result = 0;
		String message = "";

		for (i = 0; i < testDataX.length; i++) {
			MyClass tester = new MyClass();
			result = tester.multiply(testDataX[i], testDataY[i]);

			message = "Test # " + i + " Failed " + "Expected: " + answers[i] + "Actual: " + result;
			assertEquals(message, answers[i], result, tolerances[i]);
		}

	}

	@Test // Non-Null Object
	public void testNonNull() {
		Object obj = my_class.processClass2();

		assertNotNull("processClass1 returns null", obj);
	}

	@Test
	void test() {
		fail("Not yet implemented");
	}

}
