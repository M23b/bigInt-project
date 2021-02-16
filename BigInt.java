package mainPack;
import java.lang.Math;
import java.util.ArrayList;
import java.util.List;
import java.util.Arrays;
import java.text.*;

public class BigInt {
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



	//::::: Getters & Setters ::::://

	
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
			System.out.println("digit at i in setdigits " + String.valueOf(this.digits.get(i)-48));
		}
	}

	private ArrayList<Integer> getDigits(){
		return this.digits;
	}
	
	//::::: Methods ::::://
	
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
	
	//::::: Utility ::::://
	
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

	
	//::::: Used Utilities ::::://

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
	
	public String determineSign(String largerNum, BigInt bigInt) {
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
				yList.add(0, Integer.valueOf(48));
			}
		}
		if(xList.size() < yList.size()) {
			while(xList.size() < yList.size()) {
				xList.add(0, Integer.valueOf(48));
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
				y.add(0, 0);
			}
		}
		if(x.size() < y.size()) {
			while(x.size() < y.size()) {
				x.add(0, 0);
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
	
	//UNUSED
	/**
	private void createArrayList() {
		
		 // Creates arrayList from string for constructor use only
		
	if(this.number.charAt(0) == '+' || this.number.charAt(0) == '-') {
		this.sign = this.number.charAt(0);
		this.setNumber(this.number.substring(1));
	}
	for(int i = 0; i < this.number.length(); i++) {
		//char charNum = this.numString.charAt(i);    
        int tempNum = (Character.getNumericValue(this.number.charAt(i)) - 48);		// changing value of the number
        this.numberList.add(tempNum);
		}
	}
	*/
	/**
	private static String compareTo(BigInt A, BigInt B) {
		String largerNum = findBiggerInt(A, B);	//determines larger num
	}*/
	
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
        	System.out.println("a in add " + a);
        	System.out.println("b in add " + b);
        	System.out.println("carry " + carry);
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
    	 * 
    	 * THIS ADDITION METHOD IS FOR MULTIPLICATION CORRECTION ONLY
    	 */
		//determine sign	
		//greater value does this -- String largerNum = findBiggerInt(this, );
		// sign findss this in add main -- String sign = determineSign(largerNum, B);
		//if larger number is negative the sum will be a negative number
		//ArrayList<ArrayList<Integer>> xy = equalLengths(A, B);
		//ArrayList<Integer> x = xy.get(0);
		//ArrayList<Integer> y = xy.get(1);
		
		//System.out.println("xy A x " + x);
		//System.out.println("xy B y " + y);
    		
    	///if(x.get(x.size()-1) >= 5 && y.get(y.size()-1) >= 5) {
    	//	C = extendArray(C);
    	//}
    	//will hold the sum of the two arrays
    	
		/*
		int arraySize;	//find arrayList with more elements
    	if(C.size() > D.size()) {
    		arraySize = C.size();}	//compare the array sizes
    	else {
    		arraySize = D.size();}
    	
    	ArrayList<ArrayList<Integer>> xy = equalLengths(D, C);	//set the number of elements in each arrayList equal to each other
    	ArrayList<Integer> X = xy.get(0);	System.out.println("X" + X);
    	ArrayList<Integer> Y = xy.get(1);	System.out.println("Y" + Y);
    	
    	ArrayList<Integer> Z = new ArrayList<Integer>();	//initialize arrayList to hold to normalized sum of original arrayLists C and D

    	//reverse the arrays for computation
    	X = reverseArrayList(X);	System.out.println("rev X" + X);//reverse array 1
    	Y = reverseArrayList(Y);	System.out.println("rev Y" + Y);//reverse array 2
    	
    	int carry = 0;
    	int needMore = 0;
    	//if(D.get(D.size()-1) >= 5 && C.get(C.size()-1) >= 5) {
    	//	arraySize = arraySize + 1;
    	//}
    	System.out.println("y.Size() " + Y.size());
    	for(int i = 0; i < Y.size(); i++) {
    		System.out.println("Executed");
    		int x = X.get(i), y = Y.get(i), z;
            
        	z = x + y + carry;
        	System.out.println("z " + z);
            carry = 0;	//reset carry
            
            //E.ensureCapacity(E.size() + 1);
            //System.out.println("C before if statement in addition : " + C);
            if(z > 9) {
            System.out.println("Executed");
            	carry = z/10;
            	z = z%10;
            	Z.add(z);
            }
            else {
            	Z.add(Z.size(), z+Z.get(i+1));
            	System.out.println("Z in addition " + Z);
            }
    	}
    	//C = normalizeArray(C);
    	Z.add(carry);
    	Z = reverseArrayList(Z);
    	System.out.println("Z in addition " + Z);
    	//String D = convertArrayListToString(C);
    	//I want this function to return a string
    	return Z;*/
		
		/**
    	 * @param ArrayList<Integer> A, ArrayList<Integer> B
    	 * @returns ArrayList<Integer> C
    	 * computes A + B
    	 */
		//determine sign	
		//greater value does this -- String largerNum = findBiggerInt(this, );
		// sign findss this in add main -- String sign = determineSign(largerNum, B);
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
	
	private String subtraction(ArrayList<Integer> A, ArrayList<Integer> B, BigInt bigInt) {
    	/**
    	 * @param ArrayList<Integer> A, ArrayList<Integer> B
    	 * @returns ArrayList<Integer> C
    	 * computes A - B
    	 */
		System.out.println("A at start of sub. a should equal x " + A);
		System.out.println("B at start of sub. a should equal y " + B);
		ArrayList<ArrayList<Integer>> xy = equalLengths(A, B);
		ArrayList<Integer> x = xy.get(0);	System.out.println("x in subtraction " + x);
		ArrayList<Integer> y = xy.get(1);	System.out.println("y in subtraction " + y);
		
    	ArrayList<Integer> C = new ArrayList<Integer>();	//will hold the difference of the two arrays
    	int arraySize; 
    	if(y.size() > x.size()) {
    		arraySize = y.size();}	//compare the array sizes
    	else {
    		arraySize = A.size();}
    	//reverse the arrays for computation
    	x = reverseArrayList(x);	System.out.println("x in subtraction after reverse " + x + " x.size " + x.size()); //reverse array 1
    	y = reverseArrayList(y);	System.out.println("y in subtraction after reverse " + y + " y.size " + y.size()); //reverse array 2
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
    	C = reverseArrayList(C);
    	System.out.println("C in subtraction " + C);
    	String D = convertArrayListToString(C);
    	return D;
	}
	
	private String multiplication(ArrayList<Integer> X, ArrayList<Integer> Y, BigInt bigInt) {
    	//System.out.println("Ran Multiplication");
		/**
    	 * @param ArrayList<Integer> A, ArrayList<Integer> B
    	 * @returns ArrayList<Integer> C
    	 * computes A + B
    	 */
		//determine sign	
		//greater value does this -- String largerNum = findBiggerInt(this, );
		// sign findss this in add main -- String sign = determineSign(largerNum, B);
		//if larger number is negative the sum will be a negative number
		/*ArrayList<ArrayList<Integer>> xy = equalLengths(this, bigInt);
		ArrayList<Integer> x = xy.get(0);
		ArrayList<Integer> y = xy.get(1);
		System.out.println(x);
		System.out.println(y); */
    	/*
		int arraySize; 
    	if(B.size() > A.size()) {
    		arraySize = B.size();}	//compare the array sizes
    	else {
    		arraySize = A.size();}
    	ArrayList<Integer> C = new ArrayList<Integer>(A.size() + B.size() + 1);
    	ArrayList<Integer> D = new ArrayList<Integer>(A.size() + B.size() + 1);//will hold the sum of the two arrays
    	System.out.println(C.size());
    	System.out.println(D.size());
    	//reverse the arrays for computation
    	A = reverseArrayList(A);	//reverse array 1
    	B = reverseArrayList(B);	//reverse array 2
    	int carry = 0;
    	int reps = 0;
    	for(int i = 0; i < A.size(); i++) {
    		int a = A.get(i)-48;
    		for(int j = 0; j < B.size(); j++) {
    			int b = B.get(j)-48, c;
    			c = (a * b) + carry;
    			System.out.println("a * b = C -> " + a + " * " + b + " = " + c);
    			//System.out.println("a in multiply " + a);
            	//System.out.println("b in multiply " + b);
            	//System.out.println("carry " + carry);  	
    			carry = 0;
    			if(c>9) {
            		carry = c/10;
            		System.out.println("carry = c/10 : " + carry);
            		c = c%10;
            		System.out.println("c = c%10 : " + c);
            		C.add(i, c);
            	}
    			else {
            		C.add(i, c);
            	}  
    			System.out.println("C after round " + C);
        	}
    	}
    		//C = reverseArrayList(C);
    		//for(int g=0; g < C.size(); g++) {
    		//	D.add(Integer.valueOf(C.get(g)));
    		//}
    		System.out.println("D after addition: " + D);
    		System.out.println("C in multiplication after reverseal before addition: " + C);
    		D = addition(D, C);
    		System.out.println("D after addition: " + D);
    		C.clear();
    		reps++;
    		for(int k = 0; k<reps; k++) {
    			C.add(0, Integer.valueOf(0));
    		}
    	*/
    	//C = normalizeArray(C);
    	//C = reverseArrayList(C);
    	//System.out.println("D in multiplication " + D);
    	//String E = convertArrayListToString(D);
    	//I want this function to return a string
		
		ArrayList<ArrayList<Integer>> AB = equalLengths(this, bigInt);		//sets array lengths equal to each other
		ArrayList<Integer> A = AB.get(0);	//Initializes array
		ArrayList<Integer> B = AB.get(1);	//Initializes array
		int carry = 0;	//sed in loop to normalize C
		A = reverseArrayList(A);	//System.out.println("A in Multiplicatiopn " + A);//reverse arrayLists A and B
		B = reverseArrayList(B);	//System.out.println("B in Multiplicatiopn " + B);
		
		ArrayList<Integer> C = new ArrayList<Integer>();	//System.out.println("C in Multiplicatiopn " + C);//Initialize arrayList C
		ArrayList<Integer> D = new ArrayList<Integer>();	//System.out.println("D in Multiplicatiopn " + D);
		/* Try Catch block to hold code that iterates through both arrays and multiplies the two numbers together then normalizes the number*/
		try {
			for(int i=0; i<A.size(); i++) {
				for(int j=0;j<B.size();j++) {
					int a = A.get(i)-48;int b = B.get(j)-48;
					int c = a * b;
					C.add(C.size(), c);
				}
				
				for(int k = 0; k<i; k++) {
					C.add(0, 0);
				}
				D = reverseArrayList(D);
				for(int l = C.size() - D.size()+1; l < C.size(); l++) {
					D.add(0,0);
				}
				D = reverseArrayList(D);
				//System.out.println("D" + D);
				//C = reverseArrayList(C);
				//System.out.println("Reversed C " + C);
				//C = reverseArrayList(C);
				//System.out.println("C" + C);
				//D = reverseArrayList(D);
				for(int q = 0; q<D.size(); q++) {
					/* Should add the two array s together if it works properly*/
					D.set(q, D.get(q) + C.get(q));
				}
				//System.out.println("Sum D " + D);
				
				
				for(int g = i; g<C.size(); g++) {	//Iterate through the bottom number of multiplication
					if(C.size()>D.size() && D.size() == 0) {	
						// should only iterate on the first time through -> creates D which is the sum of the two numbers
						for(int m=0;m<C.size();m++) {
							D.add(m, C.get(g));
							//System.out.println("First if block executed");
							g++;
						}
					}
					else if(C.size()>0 && D.size()>0) {
						int size;
						int dif;
						/*if(C.size() > D.size()) {
							size = C.size();
							dif = size - D.size();
						}
						if(C.size() < D.size()) {
							size = D.size();
							dif = size - C.size();
						}
						else {
							size = C.size();
							dif = size - D.size();
						}*/
						for(int n = g ; n < C.size(); n++) {
							try {
								if ((D.size()) - n==0) {
									D.add(g, C.get(n-1));
									//System.out.println("Executed");
									
								}else {
									/* D.set(g, C.get(n-1) + D.get(n)); // -> commented out because correct number was already reached
									 * with above methods
									 */
								}
								//System.out.println("D at end of try " + D);	
							}catch(java.lang.IndexOutOfBoundsException IndexOutOfRange) {
								D.add(g, C.get(g-1) + D.get(g));
								//System.out.println("D in inner catch " + D);
							}
							//System.out.println("D at end of loop " + D);
						}
					System.out.println("Multiplication for loop executed " + i + " times");
					}
					
				}
				System.out.println(D);
				//D = addition(D, C);
				C.clear();
				for(int k = 0; k<i; k++) {
					C.add(0, 0);
				}
			for(int p = 0; p < C.size(); p++) {
				D.set(p, D.get(p) + C.get(p));
			}
			//System.out.println("D at end of try " + D);
			}
		}catch(java.lang.IndexOutOfBoundsException IndexOutOfRange) {
			System.out.println("Catch Error In Multiplication Method");
			//System.out.println("D in outer catch " + D);
		}
		
		//need to normalize array
		for(int w = 0; w<D.size(); w++) {
			/*
			 * Method will normalize arrayList D and carry numbers greater than 9 to the next index
			 */
			//D = reverseArrayList(D);
			if(D.get(w)>9) {
				try {
					int carryVal = D.get(w)/10;
					int newVal = D.get(w)%10;
					D.set(w, newVal);
					try {
						D.set(w+1, D.get(w+1) + carryVal);
					}catch(java.lang.IndexOutOfBoundsException IndexOutOfRange) {
						D.add(0,0);
						D.set(w+1, D.get(w+1) + carryVal);
					}
				}catch(java.lang.IndexOutOfBoundsException IndexOutOfRange) {
					D.add(0,0);
				}
			}
		}
		D = reverseArrayList(D);
		//System.out.println("D at end of multi " + D);
		//System.out.println(C);
		String E = convertArrayListToString(D);
    	return E;
    }
	
	
	
	
	
	
	
	
	
	
	
	
	
}//for class