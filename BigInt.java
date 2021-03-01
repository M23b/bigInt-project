package mainPack;
import java.lang.Math;
import java.util.ArrayList;
import java.util.List;
import java.util.Arrays;
import java.text.*;
import java.math.BigInteger;

//::::: Info ::::://
/*
 * 	Organization
 * 		//::::: Constructors ::::://
 * 		//::::: Getters & Setters ::::://
 * 		//::::: Public Methods ::::://
 * 		//::::: Computation Background ::::://
 */

public class BigInt{
	private String number;
	private boolean sign; 
	private ArrayList<Integer> digits = new ArrayList<Integer>();
	private String num;
	private boolean hadSign;
	//private char sign;
	
	//::::: Constructors ::::://
	public BigInt(String num) {
		this.num = num;
		//System.out.println("this.num " + this.num);
		/**
		 *	@param String number
		 *	Takes the string input from the user and initializes it if the input is valid
		 */
		//check for valid input
		if(validInput(this.num)==true) {
			setSign(this.num);
			this.number = setNumber(num);	//
			setDigits();
			
			//createArrayList();
			
		}
		else {
			System.out.println("Error: Input is invalid");
		}
	}//public BigInt(String number)
	
	 private BigInt()
	    {
//	        Private constructor doing noting - for inner use.
	    }
	
	//::::: Getters & Setters ::::://
	
	private String setNumber(String num) {
		//return int that must be assigned to final var
		String tempNum="";
		try {
		for(int i = 0; i<num.length(); i++) {
			tempNum = tempNum + String.valueOf(num.charAt(i));
			
		}
			//return Integer.valueOf(num);	//Initializes the new int
		}catch(java.lang.NumberFormatException ValueOfErrorInSetNum) {
			//return int that must be assigned to final var
			System.out.println("ValueOfErrorInSetNum");
			/*int tempNum;
			for(int i = 0; i<num.length(); i++) {
				tempNum = tempNum + Integer.valueOf(String.valueOf(num.charAt(i)));
				
			}*/
			System.out.println("tempNum " + tempNum);
			return tempNum;	//Initializes the new int
		}
		return tempNum;
	}
	
	private String getNumber() {
		return number;
	}
	
	private void setSign(String num) {
		if(num.charAt(0) == '-') {
			this.sign = false;
			this.num = num.substring(1);
			this.hadSign = true;
			//System.out.println("this.num string in setSign " + this.num);
			return;
		}
		else if(num.charAt(0) == '+') {
			this.sign  = true;
			this.num = num.substring(1);
			this.hadSign = true;
			return;
		}
		else if(Character.isDigit(num.charAt(0))) {
			this.sign  = true;
			return;
		}
		//this.sign = true;
	}
	
	private boolean getSign() {
		return this.sign;
	}
	
	private void setDigits() {
		for(int i = 0; i<this.num.length(); i++) {
			//nt temp = (Integer.valueOf(number.charAt(i)));
			this.digits.add(Integer.valueOf(this.num.charAt(i)));
			//System.out.println("digit at i in setdigits " + String.valueOf(this.digits.get(i)-48));
		}
	}

	private ArrayList<Integer> getDigits(){
		return this.digits;
	}
	
	//::::: Public Methods ::::://
	
	public String toString() {
		if(this.sign == false) {
			if(this.digits.size() == 1 && this.digits.get(0) == 0){
				return this.num;
			}
			else {
				return "-" + this.num;
			}
			
		}
		else if(this.sign == true) {
			if(this.hadSign == true) {
				return "+" + this.num;
			}
		}
		return this.num;
	}
	
	public BigInt add(BigInt bigInt) {
		boolean valueFound = false;
		String sumSign = "";
		String sum = "";
		
		//System.out.println("this. sign " + this.sign);
		//System.out.println("bigint. sign " + bigInt.sign);
		/**
		 * add : read in bigInt
		 * 
		 */
		while (valueFound == false){ 
			/**		
			 *			if this.positive and bigInt.positive == true -> add regularly -> final number is +
			 *
			 *			if this.positive and bigInt.positive == false -> add regularly -> final number is -
			 */
			if(this.sign == true && bigInt.sign == true) {
				sum = addition(this.digits, bigInt.digits, bigInt);
				sumSign = "+";
				valueFound = true;
			}
			else if(this.sign == false && bigInt.sign == false) {
				sum = addition(this.digits, bigInt.digits, bigInt);
				sumSign = "-";
				valueFound = true;
			}
			/**
			 *			if this.positive == false and bigInt.positive == true:: -a & +b
			 *				if this.list.value > bigInt.list.value -> subtract a-b -> final number is -
			 *				if this.list.value < bigInt.list.value -> subtract b-a -> final number is +
			 *				if this.list.value == bigInt.list.value -> break sum == 0
			 */
			else if(this.sign == false && bigInt.sign == true) {
				if(findBiggerInt(this, bigInt) == "A") {
					sum = subtraction(this.digits, bigInt.digits, bigInt);
					sumSign = "-";
					valueFound = true;
				}
				else if(findBiggerInt(this, bigInt) == "B") {
					sum = subtraction(bigInt.digits, this.digits, bigInt);
					sumSign = "+";
					valueFound = true;
				}
				else if(findBiggerInt(this, bigInt) == "E") {
					sum = "0";
					valueFound = true;
				}
			}
			/**		
			 *			if this.positive == true and bigInt.positive == false :: +a & -b
			 *				if this.list.value > bigInt.list.value -> subtract a-b -> final number is +
			 *				if this.list.value < bigInt.list.value -> subtract b-a -> final number is -
			 *				if this.list.value == bigInt.list.value -> break sum == 0
			 */
			else if (this.sign == true && bigInt.sign == false) {
				if(findBiggerInt(this, bigInt) == "A") {
					sum = subtraction(this.digits, bigInt.digits, bigInt);
					sumSign = "+";
					valueFound = true;
				}
				else if(findBiggerInt(this, bigInt) == "B") {
					sum = subtraction(bigInt.digits, this.digits, bigInt);
					sumSign = "+";
					valueFound = true;
				}
				else if(findBiggerInt(this, bigInt) == "E") {
					sum = "0";
					valueFound = true;
				}
			}
		}
		//String sum = addControl(this, bigInt);
		//try {
			sum = sumSign + sum;
			//System.out.println("Sum after calculations: " + sum);
		//}catch(noSumSign) {
		//	String sum = sum;
		//}
		return new BigInt(sum);
	}
	
	public BigInt subtract(BigInt bigInt) {
		boolean valueFound = false;
		String sumSign = "";
		String sum = "";
		 /*
		 *	subtract : read in bigInt
		 */
		while(valueFound == false){ 
			/*
			 *	if this.positive and bigInt.positive == true (a) - (b)
			 *		if a > b -> a - b -> final number is +
			 *		if a < b -> b - a -> final number is -
			 *		else answer should be 0 -> a - b -> 0
			 */
			if((this.sign == true) && (bigInt.sign == true)) {
				if(findBiggerInt(this, bigInt) == "A") {
					sum = subtraction(this.digits, bigInt.digits, bigInt);
					sumSign = "+";
					valueFound = true;
				}
				else if(findBiggerInt(this, bigInt) == "B") {
					sum = subtraction(bigInt.digits, this.digits, bigInt);
					sumSign = "-";
					valueFound = true;
				}
				else if(findBiggerInt(this, bigInt) == "E") {
					sum = "0";
					valueFound = true;
				}
			}
			/*
			 *	if this.positive and bigInt.positive == false (-a) - (-b) 
			 *		if a > b -> a - b -> final number is - 
			 *		if b < a -> b - a -> final number is +
			 *		else else answer should be 0 -> a - b -> 0
			 */
			else if((this.sign == false) && (bigInt.sign == false)) {
				if(findBiggerInt(this, bigInt) == "A") {
					sum = subtraction(this.digits, bigInt.digits, bigInt);
					sumSign = "-";
					valueFound = true;
				}
				else if(findBiggerInt(this, bigInt) == "B") {
					sum = subtraction(bigInt.digits, this.digits, bigInt);
					sumSign = "+";
					valueFound = true;
				}
				else if(findBiggerInt(this, bigInt) == "E") {
					sum = "0";
					valueFound = true;
				}
			}
			/*
			 *	if this.positive == false and bigInt.positive == true:: (-a) - (+b)
			 *		a + b -> final number is -
			 *
			 *	if this.positive == true and bigInt.positive == false:: (a) - (-b)
			 *		a + B -> final number is +
			 *
			 */
			else if(this.sign == false && bigInt.sign == true) {
				sum = addition(this.digits, bigInt.digits, bigInt);
				sumSign = "-";
				valueFound = true;
			}
			else if(this.sign == true && bigInt.sign == false) {
				sum = addition(this.digits, bigInt.digits, bigInt);
				sumSign = "+";
				valueFound = true;
			}
		}
		System.out.println("this. sign " + this.sign);
		System.out.println("bigint. sign " + bigInt.sign);
		
			sum = sumSign + sum;
			//System.out.println("Sum after calculations: " + sum);
		return new BigInt(sum);
	}
	
	public BigInt multiply(BigInt bigInt) {
		/*
		 * multiply : read in bigInt
		 */
		boolean valueFound = false;
		String sumSign = "";
		String sum = "";

		while(valueFound == false){ 
			/*
			 *	if this.positive and bigInt.positive == true (a) * (b) or this.positive and bigInt.positive == false (-a) * (-b)
			 * 		a * b -> final answer is +
			 * 
			 */
			if(((this.sign == true) && (bigInt.sign == true)) || ((this.sign == false) && (bigInt.sign == false))) {
					sum = multiplication(this.digits, bigInt.digits, bigInt);
					valueFound = true;
			}
			/*
			 *	if this.positive == false and bigInt.positive == true	(-a) * (+b)
			 *		a * b -> final answer is -	
			 *			
			 */
			else if(((this.sign == false) && (bigInt.sign == true))) {
				sum = multiplication(this.digits, bigInt.digits, bigInt);
				sumSign = "-";
				valueFound = true;
			}
			/*
			 *	if this.positive == true and bigInt.positive == false	(+a) * (-b)
			 *		a * b -> final answer is -			
			 */	
			else if(((this.sign == true) && (bigInt.sign == false))) {
				sum = multiplication(this.digits, bigInt.digits, bigInt);
				sumSign = "-";
				valueFound = true;
			}
		
		}
		sum = sumSign + sum;
		//System.out.println("Sum after calculations: " + sum);
		return new BigInt(sum);	
		
	}
	
	public BigInt multiplyWithBIGINT(BigInt bigInt) {
		//used just to check answers
		BigInteger localThis = new BigInteger(this.number);
		BigInteger input = new BigInteger(bigInt.number);
		
		BigInteger newNumber = localThis.multiply(input);
		String numString = newNumber.toString();
		System.out.println("numString " + numString);
		
		//byte[] numString = newNumber.toByteArray();
		//String(numString);
		return new BigInt(numString);
	}
	
	public BigInt divideByWithBIGINT(BigInt bigInt) {
		//used just to check answers
		BigInteger localThis = new BigInteger(this.number);
		BigInteger input = new BigInteger(bigInt.number);
		
		BigInteger newNumber = localThis.divide(input);
		String numString = newNumber.toString();
		System.out.println("numString " + numString);
		
		//byte[] numString = newNumber.toByteArray();
		//String(numString);
		return new BigInt(numString);
	}

	public BigInt modulusWithBIGINT(BigInt bigInt) {
		//used just to check answers
		BigInteger localThis = new BigInteger(this.number);
		BigInteger input = new BigInteger(bigInt.number);
		
		BigInteger newNumber = localThis.mod(input);
		String numString = newNumber.toString();
		System.out.println("numString " + numString);
		
		//byte[] numString = newNumber.toByteArray();
		//String(numString);
		return new BigInt(numString);
	}
	
	//::::: Utility Methods ::::://
	
	private static boolean validInput(String number){
		/**
		 *	@param String number
		 *	@return
		 *	Checks the user input to see if it is valid
		 *	Invalid Inputs: contains a letter, has only a sign + or -, contains leading whitespace
		 */
		if(number.charAt(0) == ' ') {
			return false;
		}
		if(((number.charAt(0) == '-') || (number.charAt(0) == '+')) && (number.length() == 1)) {
			return false;
		}
		for(int i = 0; i<number.length(); i++) {
			if(Character.isLetter(number.charAt(i))) {
				return false;
			}
		}
		return true;	//if no errors are found
		
	}

	private String findBiggerInt(BigInt A, BigInt B) {
    	if(A.digits.size() > B.digits.size()){
    		return "A";
    	}
    	else if(A.digits.size() < B.digits.size()){
    		return "B";
    	}
    	else if(A.digits.size() == B.digits.size()){
    		if(A.digits.size() == 1){
	    		if(A.digits.get(0) == 0 && B.digits.get(0) == 0){
	    			return "E";
	    		}
	    		if(A.digits.get(0) == 0 && B.digits.get(0) > 0){
	    			return "B";
	    		}
	    		if(A.digits.get(0) > 0 && B.digits.get(0) == 0){
	    			return "A";
	    		}
    		}
    		for(int i = 0; i<A.digits.size(); i++) {
    			if(A.digits.get(i) > B.digits.get(i)) {
    				return "A";
    			}
    			if(A.digits.get(i) < B.digits.get(i)) {
    				return "B";
    			}
    		}
    	
    	}

    	return "E";
    	
	}
	
	private String determineSign(String largerNum, BigInt bigInt) {
		String sign;
		if(largerNum == "A") {
			sign = String.valueOf(this.sign);
			return sign;
		}
		if(largerNum == "B") {
			sign = String.valueOf(bigInt.sign);
			return sign;
		}
		else {
			return "+";
		}
	}
	
	private ArrayList<ArrayList<Integer>> equalLengths(BigInt x, BigInt y) {
		/**
		 * Method that adds leading zeros to a numberList if it is shorter than the other numberList
		 * The method does not mofify the lists from the objects but creates two new lists and returns a list
		 * that can be accessed in the method that calls equalLengths()
		 * 
		 * the function will take the objects in and compare the size() of each which is the number of elements in each
		 * add() will add zeros to the front of the shorter array. since the array and number are in the same format 
		 * i.e. 10 -> [1,0]. when the zeros are added to the fron t of the shorter number, the magnitude of the number will not
		 * be changed. i.e. [1,0] and [0,1,0] both have the integer value of 10
		 */
		ArrayList<Integer> xList = new ArrayList<Integer>(x.digits);
		ArrayList<Integer> yList = new ArrayList<Integer>(y.digits);
		//System.out.println("xList " + xList);
		//System.out.println("yList " + yList);
		if(xList.size() > yList.size()) {
			while(xList.size() > yList.size()) {
				yList.add(0, 48);
			}
		}
		if(xList.size() < yList.size()) {
			while(xList.size() < yList.size()) {
				xList.add(0, 48);
			}
		}
		ArrayList<ArrayList<Integer>> xy = new ArrayList<ArrayList<Integer>>();
		xy.add(xList); xy.add(yList);
		return xy;
		//return new ArrayList<Integer>[] {xList, yList};
	} 
	
	private ArrayList<ArrayList<Integer>> equalLengths(ArrayList<Integer> x, ArrayList<Integer> y) {
		/**
		 * Method that adds leading zeros to a numberList if it is shorter than the other numberList
		 * The method does not mofify the lists from the objects but creates two new lists and returns a list
		 * that can be accessed in the method that calls equalLengths()
		 */
		
		//System.out.println("x in eqlen() " + x);
		//System.out.println("y in eqlen() " + y);
		if(x.size() > y.size()) {
			while(x.size() > y.size()) {
				y.add(0, 48);	//prior the 0 was 48. It may cause problems later
			}
		}
		if(x.size() < y.size()) {
			while(x.size() < y.size()) {
				x.add(0, 48);	//prior the 0 was 48. It may cause problems later
			}
		}
		ArrayList<ArrayList<Integer>> xy = new ArrayList<ArrayList<Integer>>();
		xy.add(x); xy.add(y);
		return xy;
		//return new ArrayList<Integer>[] {xList, yList};
	} 
	
	private ArrayList<ArrayList<Integer>> equalLengthsForMultiplication(ArrayList<Integer> x, ArrayList<Integer> y) {
		/**
		 * Method that adds leading zeros to a numberList if it is shorter than the other numberList
		 * The method does not mofify the lists from the objects but creates two new lists and returns a list
		 * that can be accessed in the method that calls equalLengths()
		 */
		
		//System.out.println("x in eqlen() " + x);
		//System.out.println("y in eqlen() " + y);
		if(x.size() > y.size()) {
			while(x.size() > y.size()) {
				y.add(0, 0);	//prior the 0 was 48. It may cause problems later
			}
		}
		if(x.size() < y.size()) {
			while(x.size() < y.size()) {
				x.add(0, 0);	//prior the 0 was 48. It may cause problems later
			}
		}
		ArrayList<ArrayList<Integer>> xy = new ArrayList<ArrayList<Integer>>();
		xy.add(x); xy.add(y);
		return xy;
		//return new ArrayList<Integer>[] {xList, yList};
	}
	
	private ArrayList<Integer> extendArray(ArrayList<Integer> x) {
		/**
		 * Method that adds leading zeros to a numberList if it is shorter than the other numberList
		 * The method does not mofify the lists from the objects but creates two new lists and returns a list
		 * that can be accessed in the method that calls equalLengths()
		 */
		
		//System.out.println("x in eqlen() " + x);
		//System.out.println("y in eqlen() " + y);
		
		x.add(0, Integer.valueOf(0));
		return x;
		//return new ArrayList<Integer>[] {xList, yList};
	} 
	
	private ArrayList<Integer> reverseArrayList(ArrayList<Integer> arrayList) { 
    	/**
    	 *  Takes an arraylist as a parameter and returns  
    	 *  a reversed arraylist
    	 *  Arraylist for storing reversed elements
    	 */
        ArrayList<Integer> revArrayList = new ArrayList<Integer>(); 
        for (int i = arrayList.size() - 1; i >= 0; i--) { 
        	// Append the elements in reverse order 
            revArrayList.add(arrayList.get(i)); 
            }
            // Return the reversed arraylist 
            return revArrayList; 
        } 
	
	private String convertArrayListToString(String sign, ArrayList<Integer> A) {
    	String number = sign;
    	for(int i = 0; i<A.size(); i++) {
    		number = number + String.valueOf(A.get(i));
    		
    	}
    	return number;
    }
	
	
	private ArrayList<Integer> normalize(ArrayList<Integer> A){
		ArrayList<Integer> B = new ArrayList<Integer>();
		String str = "";
		int carry = 0;
		A = reverseArrayList(A);
		System.out.println("A in normalize " + A);
		for(int i = 0; i < A.size(); i++) {
			//	 loop to normalize arrayLisut integer
        	int a = A.get(i), b;
        	b = a + carry;
        	carry = 0;
        	
        	if(b>9) {
        		carry = b / 10;
        		int d = b % 10;
        		str = str + Integer.toString(d) + ",";
        		//C.add(d);
        		//System.out.println("C in add carry" + C);
        	}
        	else {
        		str = str + Integer.toString(b) + ",";
        		//C.add(c);
        		//System.out.println("C in add " + C);
        	}  	
    	}
		//System.out.println("Str " + str);
    	ArrayList<String> arrayList = new ArrayList<String>    (Arrays.asList(str.split(",")));
    	ArrayList<Integer> newNum = new ArrayList<Integer>();
    	for(String fav:arrayList){
    	    newNum.add(Integer.parseInt(fav.trim()));
    	}
    	//System.out.println("newNum " + newNum);
    	String D = convertArrayListToString(B);
		
		return newNum; 
	}
	
	//::::: Computation Background ::::://
	
 	private String convertArrayListToString(ArrayList<Integer> A) {
    	String str = "";
    	for(int i = 0; i<A.size(); i++) {
    		str = str + String.valueOf(A.get(i));
    		
    	}
    	return str;
    }
	
	
	//::::: Computation Background ::::://
	
	
 	
 	private String addControl(BigInt A, BigInt B){
    /** not used just iuncommented method to hide code	
    	 * 	Return string contain with proper sign at the front
    	 
		//determine sign	
		String largerNum = findBiggerInt(this, B);
		String sign = determineSign(largerNum, B);
		//if larger number is negative the sum will be a negative number
		ArrayList<ArrayList<Integer>> xy = equalLengths(this, B);
		ArrayList<Integer> x = xy.get(0);
		ArrayList<Integer> y = xy.get(1);
		//ArrayList<Integer> XY
    		
    	  	* Add
    	  	*
       		* (+) + (+) = add a + b
       		* (+) + (-) = subtract a - b
       		* (-) + (-) = add a + b
       		* ' ' + ' ' = add (inferred positive) a + b
       		* 
       		
    		
    		//	(+) + (+) = add a + b //	' ' + ' ' = add (inferred positive) a + b
    		if((A.sign == "+") && (B.sign == "+")) {
    			//compute addition
    			String sumXY = convertArrayListToString("+", addition(A.digits, B.digits));
    			//newObject.sign = '+';
    			System.out.println("add 1");
    			return sumXY;	
    		}
    	    //	(+) + (-) = subtract a - b	//NEED TO FIX SO THT IF FIRST NUMBER IS LARGER IT STAYS NEGATIVE OR IF FIRST IS SMALLER IT SWITCHES TO POSITIVE
    		if((A.sign == "+") && (B.sign == "-")) {
    			//if(largerNum == "Equal") {
    			String sumXY = convertArrayListToString(sign, subtraction(A.digits, B.digits));
    			//newObject.sign = ' ';
    			System.out.println("add 2");
    			return sumXY;
    			//}
    			/**
    			if(largerNum == "A") {
    				newNumber = convertArrayListToString("+", subtraction(A, B));
    				//newObject.sign = '+';
    				System.out.println("add 3");
    				return newNumber;
    			}
    			if(largerNum == "B") {
    				newNumber = convertArrayListToString("-", subtraction(B, A));
    				//newObject.sign = '-';
    				System.out.println("add 4");
    				return newNumber;
    			}*/
    			
    		/**}
    		//(-) + (+) = subtract a - b	//NEED TO FIX SO THT IF FIRST NUMBER IS LARGER IT STAYS NEGATIVE OR IF FIRST IS SMALLER IT SWITCHES TO POSITIVE
    		if(((A.sign) == '-') && (((signOfB == '+')) || (signOfB == ' '))) { //THIS WHOLE FUNCTION IS BROKEN. FIX IT
        			//if(largerNum == "Equal") {
        			newNumber = convertArrayListToString("", subtraction(A, B));
        			//newObject.sign = ' ';
        			System.out.println("add 5");
        			return newNumber;
        			/
        			if(largerNum == "A") {
        				newNumber = convertArrayListToString("-", subtraction(A, B));
        				//newObject.sign = '-';
        				System.out.println("add 6");
        				//System.out.println("Proper code block executed");
        				return newNumber;
        			}
        			if(largerNum == "B") {
        				newNumber = convertArrayListToString("+", subtraction(B, A));
        				//newObject.sign = '+';
        				System.out.println("add 7");
        				return newNumber;
        			}
    		}
    	    //	(-) + (-) = add a + b
    		if(signOfA == '-' && signOfB == '-') {
    			//compute addition
    			newNumber = convertArrayListToString("-", addition(A, B));
    			//newObject.sign = '-';
    			System.out.println("add 8");
    			return newNumber;
    		}

    	//}*/
    	return "Error";
    }
	
	
	
 	private String addition(ArrayList<Integer> A, ArrayList<Integer> B, BigInt bigInt) {
    	/**
    	 * @param ArrayList<Integer> A, ArrayList<Integer> B
    	 * @returns ArrayList<Integer> C
    	 * computes A + B
    	 */
		//determine sign	
		//greater value does this -- String largerNum = findBiggerInt(this, );
		// sign findss this in add main -- String sign = determineSign(largerNum, B);
		//if larger number is negative the sum will be a negative number
		ArrayList<ArrayList<Integer>> xy = equalLengths(this, bigInt);
		ArrayList<Integer> x = xy.get(0);
		ArrayList<Integer> y = xy.get(1);
		//System.out.println(x);
		//System.out.println(y);
    	ArrayList<Integer> C = new ArrayList<Integer>();	//will hold the sum of the two arrays
    	int arraySize; 
    	if(y.size() > x.size()) {
    		arraySize = y.size();}	//compare the array sizes
    	else {
    		arraySize = x.size();}
    	//reverse the arrays for computation
    	x = reverseArrayList(x);	//reverse array 1
    	y = reverseArrayList(y);	//reverse array 2
    	int carry = 0;
    	for(int i = 0; i < arraySize; i++) {
        	int a = x.get(i)-48, b = y.get(i)-48, c;
        	//System.out.println("a in add " + a);
        	//System.out.println("b in add " + b);
        	//System.out.println("carry " + carry);
        	c = a + b + carry;
        	carry = 0;
        	if(c>9) {
        		carry = 1;
        		c = c-10;
        		C.add(c);
        	}
        	else {
        		C.add(c);
        	}  	
    	}
    	//C = normalizeArray(C);
    	C = reverseArrayList(C);
    	//System.out.println("C in addition " + C);
    	String D = convertArrayListToString(C);
    	//I want this function to return a string
    	return D;
    }
	
	
 	private ArrayList<Integer> addition(ArrayList<Integer> E, ArrayList<Integer> F) {
		/**
    	 * @param ArrayList<Integer> A, ArrayList<Integer> B
    	 * @returns ArrayList<Integer> C
    	 * computes A + B
    	 */
		//determine sign	
		//greater value does this -- String largerNum = findBiggerInt(this, );
		//sign findss this in add main -- String sign = determineSign(largerNum, B);
		//if larger number is negative the sum will be a negative number
		ArrayList<ArrayList<Integer>> xy = equalLengths(E, F);
		ArrayList<Integer> x = xy.get(0);
		ArrayList<Integer> y = xy.get(1);
		System.out.println(x);
		System.out.println(y);
		int length = x.size() + y.size() + 1;
    	ArrayList<Integer> C = new ArrayList<Integer>(length);	//will hold the sum of the two arrays
    	int arraySize; 
    	if(y.size() > x.size()) {
    		arraySize = y.size();}	//compare the array sizes
    	else {
    		arraySize = x.size();}
    	String str = "";
    	//reverse the arrays for computation
    	x = reverseArrayList(x);	//reverse array 1
    	y = reverseArrayList(y);	//reverse array 2
    	int carry = 0;
    	for(int i = 0; i < arraySize; i++) {
        	int a = x.get(i), b = y.get(i), c;
        	//System.out.println("a in add " + a);
        	//System.out.println("b in add " + b);
        	//System.out.println("carry " + carry);
        	c = a + b + carry;
        	carry = 0;
        	//System.out.println("c " + c);
        	if(c>9) {
        		carry = c % 10;
        		int d = c / 10;
        		str = str + Integer.toString(d) + ",";
        		//C.add(d);
        		//System.out.println("C in add carry" + C);
        	}
        	else {
        		str = str + Integer.toString(c) + ",";
        		//C.add(c);
        		//System.out.println("C in add " + C);
        	}  	
    	}
    	//C = normalizeArray(C);
    	//System.out.println("Str " + str);
    	
    	ArrayList<String> arrayList = new ArrayList<String>    (Arrays.asList(str.split(",")));
    	ArrayList<Integer> newNum = new ArrayList<Integer>();
    	for(String fav:arrayList){
    	    newNum.add(Integer.parseInt(fav.trim()));
    	}
    	C = reverseArrayList(C);
    	//System.out.println("newNum in addition " + newNum);
    	String D = convertArrayListToString(C);
    	//I want this function to return a string
    	return newNum;
    }
	
	
 	
 	private ArrayList<Integer> additionForMultiplication(ArrayList<Integer> E, ArrayList<Integer> F) {
		/**
    	 * @param ArrayList<Integer> A, ArrayList<Integer> B
    	 * @returns ArrayList<Integer> C
    	 * computes A + B
    	 */
		//determine sign	
		//greater value does this -- String largerNum = findBiggerInt(this, );
		//sign findss this in add main -- String sign = determineSign(largerNum, B);
		//if larger number is negative the sum will be a negative number
 		ArrayList<Integer> sum = new ArrayList<Integer>();
 		
 		ArrayList<ArrayList<Integer>> xy = equalLengthsForMultiplication(E, F);
		ArrayList<Integer> x = xy.get(0);
		ArrayList<Integer> y = xy.get(1);
		System.out.println("x " + x);
		System.out.println("y " + y);
		boolean zero = true;
		
		while(zero==true) {
			if(x.size() == 1) {
				zero = false;
				break;
			}
			if (x.get(0) == 0 && y.get(0) == 0) {
				
				x.remove(0);
				y.remove(0);
			}else {
				zero = false;
				break;
			}
		}
    	String str = "";
    	//reverse the arrays for computation
    	//x = reverseArrayList(x);	//reverse array 1
    	//y = reverseArrayList(y);	//reverse array 2
    	//System.out.println("rev x " + x);
		//System.out.println("rev y " + y);
    	int carry = 0;
    	
    	for(int i = x.size()-1; i>=0; i--) {
        	int a = x.get(i), b = y.get(i), c;
        	//System.out.println("a in add " + a);
        	//System.out.println("b in add " + b);
        	//System.out.println("carry " + carry);
        	c = a + b;
        	//System.out.println("c " + c);
        	if(c>9) {
        		carry = c / 10;
        		int d = c % 10;
        		sum.add(0, d);
        		try{
        			int temp = (x.get(i-1));
        			int carryTemp = temp + carry;
        			x.set(i-1, carryTemp);
        		}catch(java.lang.IndexOutOfBoundsException IndexOutOfBounds) {
        			
        			System.out.println("Error " + carry);
        			System.out.println("Error sum " + sum);
        			//	error will occur if x has been iterated through. The carry will be added to the end of the array 
        			sum.add(0, carry);
        			System.out.println("Post error sum " + sum);
        		}
        		//String temp = Integer.toString(d) + ",";
        		//str += temp;
        		//System.out.println("C in add carry" + C);
        	}
        	else {
        		sum.add(0, c);
        		//str = str + Integer.toString(c) + ",";
        		//System.out.println("C in add " + C);
        	}
        	//System.out.println("i " + i);
    	}
    	//	trim leading zeros?
    	if(sum.size() > 1 && sum.get(0) == 0) {
    		sum.remove(0);
    	}
    	// add additional carry if it exists
    	//if(carry>0) {
    	//	sum.add
    	//}
    	//System.out.println("icarry " + carry);
    	//System.out.println("Str " + str);
    	//C = normalizeArray(C);
    	//System.out.println("Str " + str);
    	//ArrayList<String> arrayList = new ArrayList<String>    (Arrays.asList(str.split(",")));
    	//ArrayList<Integer> newNum = new ArrayList<Integer>();
    	//for(String fav:arrayList){
    	//    newNum.add(Integer.parseInt(fav.trim()));
    	//}
    	//sum = reverseArrayList(sum);
    	System.out.println("sum " + sum);
    	//I want this function to return a string
    	return sum;
    }
 	
 	private String subtraction(ArrayList<Integer> A, ArrayList<Integer> B, BigInt bigInt) {
    	/**
    	 * @param ArrayList<Integer> A, ArrayList<Integer> B
    	 * @returns ArrayList<Integer> C
    	 * computes A - B
    	 */
		System.out.println("A at start of sub. a should equal x " + A);
		System.out.println("B at start of sub. a should equal y " + B);
		ArrayList<ArrayList<Integer>> xy = equalLengths(A, B);
		ArrayList<Integer> x = xy.get(0);	//System.out.println("x in subtraction " + x);
		ArrayList<Integer> y = xy.get(1);	//System.out.println("y in subtraction " + y);
		
    	ArrayList<Integer> C = new ArrayList<Integer>();	//will hold the difference of the two arrays
    	int arraySize; 
    	if(y.size() > x.size()) {
    		arraySize = y.size();}	//compare the array sizes
    	else {
    		arraySize = A.size();}
    	//reverse the arrays for computation
    	x = reverseArrayList(x);	//System.out.println("x in subtraction after reverse " + x + " x.size " + x.size()); //reverse array 1
    	y = reverseArrayList(y);	//System.out.println("y in subtraction after reverse " + y + " y.size " + y.size()); //reverse array 2
    	for(int i = 0; i < arraySize; i++) {
    	int a = x.get(i)-48, b = y.get(i)-48, c;
    		//System.out.println("a (xAt(i)) in subtraction loop " + a + " rep " + (i+1));
    		//System.out.println("b (yAt(i)) in subtraction loop " + b + " rep " + (i+1));
    		if(b>a) {
    			a = a + 10;
    			try {
    				A.set(i+1, (x.get(i+1) - 1));	//regrouping for subtraction
    			}
    			catch(java.lang.IndexOutOfBoundsException OutOfBounds) {
    				System.out.println("Big subtraction error");
    			}
    		}
    	c = (a-b);
    	C.add(c);	//add int c to C arrayList
    	//System.out.println("C at end of subtraction loop " + C);
    	
    	}
    	C = reverseArrayList(C);
    	//C = reverseArrayList(C);
    	//System.out.println("C in subtraction " + C);
    	String D = convertArrayListToString(C);
    	//System.out.println("D " + D);
    	return D;
	}
	
	private String multiplication(ArrayList<Integer> arrayOne, ArrayList<Integer> arrayTwo, BigInt bigInt) {
    	//System.out.println("Ran Multiplication");
		/**
    	 * @param ArrayList<Integer> A, ArrayList<Integer> B
    	 * @returns ArrayList<Integer> C
    	 * computes A + B
    	 */
		//NEW IDEA START
		//System.out.println("A at start of multi. a should equal x " + A);
		//System.out.println("B at start of multi. a should equal y " + B);
		
		//	define the accumulator and the answer array lists
		//	reverse both array lists for easier for loop manipulation 
		//	iterate through the first number
		//	if the value is equal to 0
		// 		skip the second for loop and multiply the magnitude by 10
		//	else 
		//		iterate through the second number
		//		multiply each index by the outer loop value
		//		add each of the multiples to the accumulator
		//		
		//		add the accumulator to to the answer
		
		ArrayList<Integer> A = new ArrayList<Integer>();
		ArrayList<Integer> B = new ArrayList<Integer>();
		
		BigInt answerObject = new BigInt();
		
		
		if(arrayOne.size()>arrayTwo.size() || arrayOne.size()==arrayTwo.size()) {
			A = arrayOne;
			B = arrayTwo;
		}
		else {
			B = arrayOne;
			A = arrayTwo;
		}
		ArrayList<Integer> accumulator = new ArrayList<Integer>();
		ArrayList<Integer> answer = new ArrayList<Integer>();
		int factorOfNum = 1;
		int numOfFactors = 0;
		System.out.println("A " + A);
		System.out.println("B " + B);
		
		A = reverseArrayList(A);
		B = reverseArrayList(B);
		
		System.out.println("revA " + A);
		System.out.println("revB " + B);
		
		for(int i = 0; i < A.size(); i++) {	//1111 * 22 ->
			//	 loop to iterate through first number
			int a = A.get(i)-48;
				for(int j = 0; j< B.size(); j++) {				
					//	loop to iterate through second number
					
					int b = B.get(j)-48;
					int c;
					
					
					c = a * b;
					accumulator.add(0, c);
					System.out.println("a " + a + "\t" + "b " + b + "\t" +"c " + c + "\t");
					
				}
				
				System.out.println("accumulator before normalize " + accumulator);
				accumulator = normalizeArrayList(accumulator);
				System.out.println("accumulator after normalize " + accumulator);
				
				if(numOfFactors > 0) {
					//accumulator = reverseArrayList(accumulator);
					for(int k = 0; k<numOfFactors; k++) {
						/*	loop to add zeros to the back of the array if the factor of the multiplication digit
						 *	digit is greater that 0		*/
						//System.out.println("accumulator in adding zeros b4" + accumulator);
						accumulator.add(0);
						
						
					}
					//accumulator = reverseArrayList(accumulator);
				}
				
				
				String accumulatorString = convertArrayListToString(accumulator);
				BigInt accumulatorObject = new BigInt(accumulatorString);
				
				try {
					answerObject = answerObject.add(accumulatorObject);
				}catch(java.lang.Error ObjectNotInitialized) {
					// first iteration through loop, answer object not created
					answerObject = new BigInt(accumulatorString);
				}
					
				
				
				//answer = additionForMultiplication(answer, accumulator);
				//System.out.println("after answer " + answer);
				accumulator.clear();
			
			//System.out.println("after for loop accumulator " + accumulator);
			//accumulator = reverseArrayList(accumulator);	//reverse back to proper format
			
			//System.out.println("accumulator after zeros " + accumulator);
			factorOfNum = factorOfNum * 10;
			numOfFactors += 1;
			//accumulator = normalize(accumulator);
			//System.out.println("accumulator " + accumulator);
			//normalizse the numbers in the arrayList aka compute the carry
			//System.out.println("before answer " + answer);
			//accumulator = reverseArrayList(accumulator);
			//System.out.println("before accumulator " + accumulator);
			
		}
			
		//System.out.println("D after multi " + D);
		//System.out.println("D at end of multi " + D);
		//System.out.println(C);
		//System.out.println("answer " + answer);
		//answer = reverseArrayList(answer);
		String answerStr = answerObject.toString();
		System.out.println(answerStr);
    	return answerStr;
    	
    }
	
	private ArrayList<Integer> normalizeArrayList(ArrayList<Integer> A){
		// 	arrayList will come in default format of [1,2,3] -> 123
		A = reverseArrayList(A);
		//	A is not [3,2,1]
		ArrayList<Integer> newArray = new ArrayList<Integer>();
		for(int i = 0; i<A.size();i++){
			int num = A.get(i);
			int carry = 0;
			if(num>9) {
				carry = num / 10;
				int d = num%10;
				newArray.add(d);
				try{
	    			int temp = (A.get(i+1));
	    			int carryTemp = temp + carry;
	    			A.set(i+1, carryTemp);
	    		}catch(java.lang.IndexOutOfBoundsException IndexOutOfBounds) {
	    			
	    			System.out.println("Error " + carry + "\t" + "A at Error " + A + "\t");
	    			//	error will occur if x has been iterated through. The carry will be added to the end of the array 
	    			A.add(carry);
	    			System.out.println("Post error sum " + A);
	    		}
	    		//String temp = Integer.toString(d) + ",";
	    		//str += temp;
	    		//System.out.println("C in add carry" + C);
	    	}
	    	else {
				newArray.add(num);	//	adds to the front of the arrayList	
	    		//str = str + Integer.toString(c) + ",";
	    		//System.out.println("C in add " + C);
			}
			
		}
		newArray = reverseArrayList(newArray);
	return newArray;
	}
	
	public BigInt multiply2(BigInt multi)
    {
		//this method is not used
        BigInt int_to_return = new BigInt();;
        ArrayList<Integer> digits = new ArrayList<Integer>();
        boolean sign = true;
        String signStr = "";
        int first;
        int second;
        int sum;
        int carry = 0;
        int count = 0;
        this.digits = reverseArrayList(this.digits);
        multi.digits = reverseArrayList(multi.digits);
        for (int i=0; i<this.digits.size(); i++)
        {
            first = this.digits.get(i);
            for (int j=0; j<multi.digits.size(); j++)
            {
                second = multi.digits.get(j);
                sum = (first * second) + carry;
                if (sum > 9)
                {
                    carry = sum/10;
                    sum = sum%10;
                }
                else {
                	carry = 0;
                }
               
                digits.add(0, sum);   
                
                System.out.println("sum " + sum);
                System.out.println("digits " + digits);
                /*
                if (j+i >= digits.size())
                {
                    digits.add(j+i, sum);
                    if (j == multi.digits.size()-1 && carry != 0)
                        digits.add(carry);
                }
                else
                {
                    sum += digits.get(j+i);
                    if (sum>9)
                    {
                        carry += sum/10;
                        sum =sum%10;
                    }
                    digits.set(j+i, sum);
                }
                */
            }
            
            String digitsStr = convertArrayListToString(digits);
            if(count == 0) {
            	int_to_return = new BigInt(digitsStr);
            }
            else {
            	BigInt tempBigInt = new BigInt(digitsStr);
            	int_to_return = int_to_return.add(tempBigInt);
            }
            
            System.out.println("int_to_return inside loop " + int_to_return);
            
            count++;
            digits.clear();
            for(int k=0; k<count; k++) {
            	digits.add(0);
            }
        }
        
        this.digits = reverseArrayList(this.digits);
        multi.digits = reverseArrayList(multi.digits); 
        
    	/*
		 *	if this.positive and bigInt.positive == true (a) * (b) or this.positive and bigInt.positive == false (-a) * (-b)
		 * 		a * b -> final answer is +
		 * 
		 */
		if(((this.sign == true) && (multi.sign == true)) || ((this.sign == false) && (multi.sign == false))) {
			sign = true;
		}
		/*
		 *	if this.positive == false and bigInt.positive == true	(-a) * (+b)
		 *		a * b -> final answer is -	
		 *			
		 */
		else if(((this.sign == false) && (multi.sign == true))) {
			sign = false;
		}
		/*
		 *	if this.positive == true and bigInt.positive == false	(+a) * (-b)
		 *		a * b -> final answer is -			
		 */	
		else if(((this.sign == true) && (multi.sign == false))) {
			sign = false;
		}
		
		String digitString = convertArrayListToString(digits);
		
		if(sign == false) {
			signStr = "-";
		}
		
		digitString = signStr + digitString;
		
        return new BigInt(digitString);
    }

	
	
	
	
}//for class