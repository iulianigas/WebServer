package validators;

public class PortNumberValidator {

	public static boolean validate(int portNumber) {
		
		if (String.valueOf(portNumber).isEmpty())
            return false;

        if (!String.valueOf(portNumber).matches("^[0-9]+$"))
            return false;
        
        return true;
	}
}
