public class Validation {
	
	/*
	 * Dummy constructor; forcibly restricts the user to use this class as static methods.
	 */
	private Validation() {}
	
	public boolean isValidInteger(String input) {
		
		boolean isValid = false;
		
			try {
				Integer.parseInt(input);
				isValid = true;
			}
			catch (Exception e) {}
			
		return isValid;
		
	}
	
	public boolean isValidDouble(String input) {
		
		boolean isValid = false;
		
			try {
				Double.parseDouble(input);
				isValid = true;
			}
			catch (Exception e) {}
			
		return isValid;
		
	}
	
	public boolean isValidInteger(String input, int minimum, int maximum) {
		
		boolean isValid = false;
		
		if (isValidInteger(input) && valueIsInRange(new Double(input), minimum, maximum)) {
			isValid = true;
		}
			
		return isValid;
		
	}
	
	public boolean isValidDouble(String input, double minimum, double maximum) {
		
		boolean isValid = false;
		
		if (isValidDouble(input) && valueIsInRange(new Double(input), minimum, maximum)) {
			isValid = true;
		}
			
		return isValid;
		
	}
	
	public boolean valueIsInRange(double input, double maximum, double minimum) {
		
		boolean isInRange = false;
		
		if (input >= minimum && input <= maximum) {
			isInRange = true;
		}
		
		return isInRange;
		
	}
	
}
