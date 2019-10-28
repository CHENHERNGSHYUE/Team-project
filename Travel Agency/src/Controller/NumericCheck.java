package Controller;

/**
 * Function of restrict the type should be numeric
 * @author Group 25
 *
 */
public class NumericCheck {
	
	/**
	 * 
	 * @param str
	 * @return whether the input is all numeric number
	 */
	public static boolean isNumeric(String str) {
		return str.matches("-?\\d+");  
	}

}
