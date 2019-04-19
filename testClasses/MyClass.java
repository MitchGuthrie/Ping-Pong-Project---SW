package testClasses;

public class MyClass {

	SpecialClass sc1 = null;
	SpecialClass sc2 = new SpecialClass();
	SpecialClass sc3 = sc2;

	public int multiply(int x, int y) {
		// the following is just an example
		if (x > 999) {
			throw new IllegalArgumentException("X should be less than 1000");
		}
		return x / y;
	}

	public double multiply(double x, double y) {

		return x * y;
	}

	public int divide(int x, int y) {
		// the following is just an example

		if (y != 0) {
			return x / y;
		} else {
			throw new IllegalArgumentException("y should not be equal to 0");
		}
	}

	class SpecialClass {
		private int field1 = 0;
		private double field2 = 0;

		public int getField1() {
			return field1;
		}

		public void setField1(int value) {
			this.field1 = value;
		}

		public double getField2() {
			return field2;
		}

		public void setField2(int value) {
			this.field2 = value;
		}
	}

	public SpecialClass processClass1() {
		return sc1;
	}

	public SpecialClass processClass2() {
		return sc2;
	}

	public SpecialClass processClass3() {
		return sc3;
	}
}
