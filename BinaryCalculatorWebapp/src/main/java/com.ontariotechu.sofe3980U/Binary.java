package com.ontariotechu.sofe3980U;

/**
 * Unsigned integer Binary variable
 *
 */
public class Binary
{
	private String number="0";  // string containing the binary value '0' or '1'
	/**
	* A constructor that generates a binary object.
	*
	* @param number a String of the binary values. It should contain only zeros or ones with any length and order. otherwise, the value of "0" will be stored.   Trailing zeros will be excluded and empty string will be considered as zero.
	*/
	public Binary(String number) {
		if (number == null || number.isEmpty()) {
			this.number = "0"; // Default to "0" for null or empty input
			return;
		}
	
		// Validate the binary string (only '0' or '1' allowed)
		for (int i = 0; i < number.length(); i++) {
			char ch = number.charAt(i);
			if (ch != '0' && ch != '1') {
				this.number = "0"; // Default to "0" for invalid input
				return;
			}
		}
	
		// Remove leading zeros
		int beg;
		for (beg = 0; beg < number.length(); beg++) {
			if (number.charAt(beg) != '0') {
				break;
			}
		}
	
		// If all digits are '0', ensure number is "0"
		this.number = (beg == number.length()) ? "0" : number.substring(beg);
	
		// Ensure empty strings are replaced with "0"
		if (this.number.isEmpty()) {
			this.number = "0";
		}
	}
	/**
	* Return the binary value of the variable
	*
	* @return the binary value in a string format.
	*/
	public String getValue()
	{
		return this.number;
	}
	/**
	* Adding two binary variables. For more information, visit <a href="https://www.wikihow.com/Add-Binary-Numbers"> Add-Binary-Numbers </a>.
	*
	* @param num1 The first addend object
	* @param num2 The second addend object
	* @return A binary variable with a value of <i>num1+num2</i>.
	*/
	public static Binary add(Binary num1,Binary num2)
	{
		// the index of the first digit of each number
		int ind1=num1.number.length()-1;
		int ind2=num2.number.length()-1;
		//initial variable
		int carry=0;
		String num3="";  // the binary value of the sum
		while(ind1>=0 ||  ind2>=0 || carry!=0) // loop until all digits are processed
		{
			int sum=carry; // previous carry
			if(ind1>=0){ // if num1 has a digit to add
				sum += (num1.number.charAt(ind1)=='1')? 1:0; // convert the digit to int and add it to sum
				ind1--; // update ind1
			}
			if(ind2>=0){ // if num2 has a digit to add
				sum += (num2.number.charAt(ind2)=='1')? 1:0; // convert the digit to int and add it to sum
				ind2--; //update ind2
			}
			carry=sum/2; // the new carry
			sum=sum%2;  // the resultant digit
			num3 =( (sum==0)? "0":"1")+num3; //convert sum to string and append it to num3
		}
		Binary result=new Binary(num3);  // create a binary object with the calculated value.
		return result;
		
	}
	//This is the class for the bitwise OR (logical OR)
	public static Binary or(Binary num1, Binary num2)
	{
		// the index of the last digit of each number (LSB)
		int ind1 = num1.number.length() - 1;
		int ind2 = num2.number.length() - 1;
		StringBuilder sb = new StringBuilder();
		// Process from LSB to MSB, treating missing digits as '0'
		while (ind1 >= 0 || ind2 >= 0) {
			char c1 = (ind1 >= 0) ? num1.number.charAt(ind1) : '0';
			char c2 = (ind2 >= 0) ? num2.number.charAt(ind2) : '0';
			// result bit is '1' if either bit is '1'
			sb.append((c1 == '1' || c2 == '1') ? '1' : '0');
			ind1--;
			ind2--;
		}
		// reverse since we built from LSB to MSB
		String num4 = sb.reverse().toString();
		return new Binary(num4);
	}

	//Bitwise logical AND of two binaries.

	public static Binary and(Binary num1, Binary num2)
	{
		// the index of the last digit of each number (LSB)
		int ind1 = num1.number.length() - 1;
		int ind2 = num2.number.length() - 1;
		StringBuilder sb = new StringBuilder();
		// Process from LSB to MSB, treating missing digits as '0'
		while (ind1 >= 0 || ind2 >= 0) {
			char c1 = (ind1 >= 0) ? num1.number.charAt(ind1) : '0';
			char c2 = (ind2 >= 0) ? num2.number.charAt(ind2) : '0';
			// result bit is '1' only if both bits are '1'
			sb.append((c1 == '1' && c2 == '1') ? '1' : '0');
			ind1--;
			ind2--;
		}
		// reverse since we built from LSB to MSB
		String num5 = sb.reverse().toString();
		return new Binary(num5);
	}

	 // Multiply two binary numbers using repeated addition.

	public static Binary multiply(Binary num1, Binary num2)
	{
		// quick zero checks
		if (num1.number.equals("0") || num2.number.equals("0")) {
			return new Binary("0");
		}
		Binary result = new Binary("0");
		int shift = 0; // number of zeros to append (LSB side)
		for (int i = num2.number.length() - 1; i >= 0; i--) {
			if (num2.number.charAt(i) == '1') {
				// shift num1 by appending `shift` zeros
				StringBuilder sb = new StringBuilder(num1.number);
				for (int z = 0; z < shift; z++) sb.append('0');
				Binary term = new Binary(sb.toString());
				result = Binary.add(result, term);
			}
			shift++;
		}
		return result;
	}

	/**
	 * Backwards-compatible alias using capitalized name used earlier in the project.
	 */
	public static Binary Multiply(Binary num1, Binary num2) {
		return multiply(num1, num2);
	}
}	
