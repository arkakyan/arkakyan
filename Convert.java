/*
 * Convert.java
 * 
 * TCSS 371 assignment 1
 */

package code;

import java.util.Arrays;

/**
 * A class to provide static methods for converting numbers between bases.
 * 
 * @author Alan Fowler
 * @author Khin Win
 * @version 1.1
 */
public final class Convert {

	/**
	 * A private constructor to inhibit instantiation of this class.
	 */
	private Convert() {
		// Objects should not be instantiated from this class.
		// This class is just a home for static methods (functions).
		// This design is similar to the Math class in the Java language.
	}

	/**
	 * Accepts an array of characters representing the bits in a 2's complement number
	 * and returns the decimal equivalent.
	 * 
	 * precondition:
	 * This method requires that the maximum length of the parameter array is 16.
	 * 
	 * @param theBits an array representing the bits in a 2's complement number
	 * @throws IllegalArgumentException if the length of the parameter array > 16
	 * @return the decimal equivalent of the 2's complement parameter
	 */
	public static int convert2sCompToDecimal(final char[] theBits) {
		// convert char array into long. 
		long number = Long.parseLong(String.valueOf(theBits));
		// initialize the exponent, decimalNumber, and remainder.
		int exponent = 0 ;
		int decimalNumber = 0;
		int remainder;
		// if theBits length is greater than 16 then throw Exception.
		if (theBits.length > 16) {
			throw new IllegalArgumentException("The array length is greater than 16.");
		}
		// check the length is less than or equal to 16 then find the decimal Number.
		if (theBits.length <=16 ) {
			// if the number is less than or zero quit the loop.
			while (number > 0) {
				/*
				 * Convert Binary to Decimal. 
				 */
				remainder = (int) (number % 10);
				number = number / 10;
				decimalNumber += remainder * Math.pow(2, exponent);
				exponent = exponent + 1;
			}
			/*
			 * Check index zero is one or not, if it's return the update value.
			 * 
			 * If INDEX ZERO is ONE then first, multiply theBits length-1 to 2.
			 * And, multiply that number into (-2) and add the Decimal Number.
			 * return the updated value.
			 */
			if (theBits[0] == '1') {
				int value = (int) Math.pow(2, theBits.length-1);
				int updateDecimal = decimalNumber + (value * -2);
				return updateDecimal;
			}
		}

		return decimalNumber;
	}

	/**
	 * Accepts a decimal parameter and returns an array of characters
	 * representing the bits in the 16 bit two's complement equivalent.
	 * 
	 * precondition:
	 * This method requires that the two's complement equivalent
	 * won't require more than 16 bits
	 * 
	 * @param theDecimal the decimal number to convert to 2's complement
	 * @throws IllegalArgumentException if the parameter cannot be represented in 16 bits
	 * @return a 16 bit two's complement equivalent of the decimal parameter
	 */
	public static char[] convertDecimalTo2sComp(final int theDecimal) {
		// create string builder.
		StringBuilder s = new StringBuilder();
		// if the decimal is greater than 16 bits throw exception.
		if (theDecimal > 32767 || theDecimal < -32768) {
			throw new IllegalArgumentException("The integer value can't be greater than 16 bits.");
		}

		else {
			// if the Decimal number is less than zero , multiply with (-1).
			if (theDecimal < 0) {
				int number = theDecimal * -1;
				while (number > 0) {
					// adding the remainder value into the String Builder.
					s.append(number % 2);
					number = number / 2;
				}
			}
			// check the Decimal Number is greater than zero or not.
			if (theDecimal > 0) {
				int number = theDecimal;
				while (number > 0) {
					s.append(number % 2);
					number = number / 2;
				}
			}

			// create the first Array and reverse the current String Builder values.
			char[] firstArray = s.reverse().toString().toCharArray();

			// create the second Array that has 16 bits.
			char[] secondArray = new char[16];
			// Fill the Array with zero first.
			Arrays.fill(secondArray, '0');

			/*
			 * 
			 * First Array and starts at zero.
			 * Second Array and starts at the result.
			 * copy the first array.
			 */

			System.arraycopy(firstArray, 0, secondArray, secondArray.length - firstArray.length  , 
					firstArray.length);

			// if the decimal number is negative then flip the binary.
			if (theDecimal < 0) {
				return arrayCheck(secondArray);

			}
			return secondArray;
		}
	}

	/**
	 * First, this method starts finding the '1'.
	 * After it found the one, check whether the current one is zero or not.
	 * If it's change '1' to '0'.
	 * return the updated binaryNumber.
	 * 
	 * @param theBinaryNumber theBinaryNumber that has 2's complement
	 * @return theBinaryNumber return the updated BinaryNumber
	 */
	public static char[] arrayCheck (final char[] theBinaryNumber) {
		// initialized the checkMark.
		boolean checkMark = false;
		for (int i = theBinaryNumber.length-1; i>=0; i--) {
			// when i found the 1 change the boolean value.
			if (theBinaryNumber[i] == '1') {
				checkMark = true;
			}
			// if both are true then change current binary number to '1'. 
			if (checkMark && theBinaryNumber[i]=='0') {
				theBinaryNumber[i]='1';
			}
		}
		// return binaryNumber.
		return theBinaryNumber;

	}
}



