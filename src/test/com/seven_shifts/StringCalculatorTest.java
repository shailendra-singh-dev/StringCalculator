package test.com.seven_shifts;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import org.junit.jupiter.api.Test;

import main.com.seven_shifts.StringCalculator;

public class StringCalculatorTest {

	@Test
	public void emptyStringShouldReturnZero() {
		StringCalculator sut = new StringCalculator();
		assertEquals(0, sut.Add(""));
	}

	@Test
	public void twoNumbersShouldBeAdded() {
		StringCalculator sut = new StringCalculator();
		assertEquals(8, sut.Add("1,2,5"));
	}

	@Test
	public void supportNewLineAsSeparator() {
		StringCalculator sut = new StringCalculator();
		assertEquals(6, sut.Add("1\n,2,3"));
	}

	// Check
	@Test
	public void supportNewLineAsSeparatorInBetween() {
		StringCalculator sut = new StringCalculator();
		assertEquals(7, sut.Add("1,\n2,4"));
	}

	@Test
	public void supportForCustomDelimiterSemiColon() {
		StringCalculator sut = new StringCalculator();
		assertEquals(8, sut.Add("//;\n1;3;4"));
	}
	
	@Test
	public void supportForCustomDelimiterDollar() {
		StringCalculator sut = new StringCalculator();
		assertEquals(6, sut.Add("//$\n1$2$3"));
	}
	
	@Test
	public void supportForCustomDelimiterAtTheRate() {
		StringCalculator sut = new StringCalculator();
		assertEquals(13, sut.Add("//@\n2@3@8"));
	}

	@Test
	public void supportForNumbersLargerThan1000() {
		StringCalculator sut = new StringCalculator();
		assertEquals(2, sut.Add("2,1001"));
	}
	
	
	@Test
	public void negativeNotSupported() {
		StringCalculator sut = new StringCalculator();
		try {
			sut.Add("-1,4");
			fail("exception should have been thrown");
		} catch (IllegalArgumentException e) {
			assertEquals("Negatives not allowed -1", e.getMessage());
		}
	}

}
