/*
Author Krishna Chaitanya Neti - 8042884

Number Converter - Gives a String value for any number entered in between 0 and 999999999.

Methodology - 
Assume the entered number to be 369589563
1. Break up the number into three digit blocks (369, 589, 563).
2. Convert the individual blocks into Text.
3. Join all the text strings with denominations - million and thousand.
The output would be - "three hundred sixty nine million five hundred eighty nine thousand five hundred sixty three"

Please compile and run the file in the command prompt. You can provide any number between 0 and 999999999 (included) as input in the command line.
*/
import java.util.ArrayList;

public class NumberConverter{
	//returns the String value for the input number
	private String getNumberEquivalent(int number){
		String convStrVal = "";
		switch(number){
			case 0: convStrVal = "zero";break;
			case 1: convStrVal = "one";break;
			case 2: convStrVal = "two";break;
			case 3: convStrVal = "three";break;
			case 4: convStrVal = "four";break;
			case 5: convStrVal = "five";break;
			case 6: convStrVal = "six";break;
			case 7: convStrVal = "seven";break;
			case 8: convStrVal = "eight";break;
			case 9: convStrVal = "nine";break;
			case 10: convStrVal = "ten";break;
			case 11: convStrVal = "eleven";break;
			case 12: convStrVal = "twelve";break;
			case 13: convStrVal = "thirteen";break;
			case 14: convStrVal = "fourteen";break;
			case 15: convStrVal = "fifteen";break;
			case 16: convStrVal = "sixteen";break;
			case 17: convStrVal = "seventeen";break;
			case 18: convStrVal = "eighteen";break;
			case 19: convStrVal = "ninteen";break;
			case 20: convStrVal = "twenty";break;
			case 30: convStrVal = "thirty";break;
			case 40: convStrVal = "forty";break;
			case 50: convStrVal = "fifty";break;
			case 60: convStrVal = "sixty";break;
			case 70: convStrVal = "seventy";break;
			case 80: convStrVal = "eighty";break;
			case 90: convStrVal = "ninty";break;
			case 100: convStrVal = "hundred";break;
		}
		return convStrVal;
	}
	
	//Returns the denomination for the Three digit blocks -- million, thousand and an empty string
	String getDenomination(int index){
		String deno = "";
		switch(index){
			case 0: deno = "";break;
			case 1: deno = " thousand ";break;
			case 2: deno = " million ";break;
		}
		return deno;
	}
	
	//Returns the text format for the Three digit blocks 
	String getTextFormat(int number){
		String tempStr = "";
		int hundredthPlace = number/100;
		int reducedNumber = number%100;
		int tenthPlace = (reducedNumber/10);
		if(number < 20){
			tempStr = getNumberEquivalent(number);
		}else{
			if(hundredthPlace != 0){
				tempStr = getNumberEquivalent(hundredthPlace) +" "+ getNumberEquivalent(100) +" ";
			}
			if(reducedNumber != 0 && reducedNumber%10 == 0){
				tempStr += getNumberEquivalent(reducedNumber);
			}else if(reducedNumber != 0 && reducedNumber%10 != 0){
				if(tenthPlace != 0){
					tempStr += getNumberEquivalent(tenthPlace*10) +" ";
				}
				tempStr += getNumberEquivalent(reducedNumber%10);
			}
		}
		return tempStr;
	}

	//Breaks up the original input number into three digit blocks 
	ArrayList<Integer> getNumberArray(int number){
		ArrayList<Integer> arrTemp = new ArrayList<Integer>();
		int quotient = 0;
		if(number > 1000){
			while(number >= 1000){
				quotient = number%1000;
				number = number/1000;
				arrTemp.add(quotient);
				if(number < 1000){
					arrTemp.add(number);					
				}
			}
		}
		else{
			arrTemp.add(number);
		}
		return arrTemp;
	}	

	//The main function where conversion begins
	String convert(int number){
		ArrayList<Integer> numArr = getNumberArray(number);
		String convertedString = "";
		int enteredNumLength = numArr.size() - 1;

		if(enteredNumLength == 0 && (int)numArr.get(0) == 0){
			convertedString = getTextFormat(0); 
		}
		else{
			while(enteredNumLength >= 0){
				int tempNum = (int)numArr.get(enteredNumLength);
				if(tempNum > 0){
					convertedString += getTextFormat(tempNum) 
						+getDenomination(enteredNumLength);
				}
				enteredNumLength--;
			}
		}
		return convertedString;
	}	

	//test case function
	void performSampleTests(){
		//Sample Trivial tests
			System.out.println("No number given as input in the command line.");
			System.out.println("Performing checks for some sample values...");
			System.out.println("Entering Number : 0");
			System.out.println("The String value for 0 is : "+convert(0));
			System.out.println("Entering Number : 1");
			System.out.println("The String value for 1 is : "+convert(1));
			System.out.println("Entering Number : 10");
			System.out.println("The String value for 10 is : "+convert(10));
			System.out.println("Entering Number : 17");
			System.out.println("The String value for 17 is : "+convert(17));
			System.out.println("Entering Number : 40");
			System.out.println("The String value for 40 is : "+convert(40));
			System.out.println("Entering Number : 100");
			System.out.println("The String value for 100 is : "+convert(100));
			System.out.println("Entering Number : 479");
			System.out.println("The String value for 479 is : "+convert(479));
			System.out.println("Entering Number : 1234");
			System.out.println("The String value for 1234 is : "+convert(1234));
			System.out.println("Entering Number : 20000");
			System.out.println("The String value for 20000 is : "+convert(20000));
			System.out.println("Entering Number : 200010");
			System.out.println("The String value for 200010 is : "+convert(200010));
			System.out.println("Entering Number : 6543698");
			System.out.println("The String value for 6543698 is : "+convert(6543698));
			System.out.println("Entering Number : 999999999");
			System.out.println("The String value for 999999999 is : "+convert(999999999));
	}

	public static void main(String[] args){
		NumberConverter numConv = new NumberConverter();
		String convertedString = "";
		if(args.length > 0){
			//taking the value provided in the command line as input
			try{
				int enteredNumber = Integer.parseInt(args[0]);
				System.out.println("Entered Number is : "+enteredNumber);
				if(enteredNumber >= 0 && enteredNumber <= 999999999){
					convertedString = numConv.convert(enteredNumber);
					System.out.println("Converted String : "+convertedString);
				}
				else{
					System.out.println("Please re-execute and only enter a value as an Integer between 0 and 999999999");
				}
			}catch(NumberFormatException e){
					System.out.println("Please re-execute and only enter a value as an Integer between 0 and 999999999");
			}
		}
		else{
			numConv.performSampleTests();
		}
	}
}