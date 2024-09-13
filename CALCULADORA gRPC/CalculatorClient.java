import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class CalculatorClient {

    public static void main(String[] args) {
        try {
            // Get the registry
            Registry registry = LocateRegistry.getRegistry("localhost");

            // Lookup the Calculator object
            Calculator stub = (Calculator) registry.lookup("Calculator");

            // Invoke remote methods for basic operations
            double a = 10.0;
            double b = 5.0;

            System.out.println("Addition: " + stub.add(a, b));
            System.out.println("Subtraction: " + stub.subtract(a, b));
            System.out.println("Multiplication: " + stub.multiply(a, b));
            System.out.println("Division: " + stub.divide(a, b));

            // Invoke remote methods for fraction operations
            int num1 = 1;
            int denom1 = 4;
            int num2 = 1;
            int denom2 = 6;

            System.out.println("Addition of " + num1 + "/" + denom1 + " and " + num2 + "/" + denom2 + ": " 
                + stub.addFractions(num1, denom1, num2, denom2));
            
            int numerator = 4;
            int denominator = 8;
            System.out.println("Simplification of " + numerator + "/" + denominator + ": " 
                + stub.simplifyFraction(numerator, denominator));

        } catch (Exception e) {
            System.err.println("Client exception: " + e.toString());
            e.printStackTrace();
        }
    }
}
