package testClasses;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.jupiter.api.Test;

import entities.Ball;
import level.Level;

class MyClassTest {

	private Ball ball;
	private double[] testDataX = new double[] { 0.0, 1.35, 3.56, 9.278, -1.3591, -3.79123 };
	private double[] testDataY = new double[] { 3.3, 5.5, 2.5, 4.5, 9.5, -8.5 };
	private double[] answers = new double[] { 0.0, 7.425, 8.900, 41.751, -12.91145, 32.225455 };
	private double[] tolerances = new double[] { 1. / 10, 1. / 1000, 1. / 1000, 1. / 1000, 1. / 10000, 1e-6 };
	Level level = new Level("/res/Levels/small_test_level.png");

	@BeforeClass
	public static void setUp() {

		System.out.println("once before all tests");
	}

	@Before
	public void setUpEachTest() {
		ball = new Ball(level, "ball", 100, 100, 2, true);

		System.out.println("once before each test");
	}

	@Test
	public void testMove(int xa, int ya) {
		int value1 = 0;
		int value2 = 0;

		int actual = 1;// ball.move(xa, ya);
		// Compute using another approach:
		int expected = 1;

		// String error_message = "mulitiply - operand1: " + value1 + " operand2: " +
		// value2;

		assertEquals(expected, actual);

		// return expected;
	}

	// @Test // Non-Null Object
	// public void testNonNull() {
	// Object obj = ball.processClass2();
	//
	// assertNotNull("processClass1 returns null", obj);
	// }

	@Test
	void test() {
		fail("Not yet implemented");
	}

}
