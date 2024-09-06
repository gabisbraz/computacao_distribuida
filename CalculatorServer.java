import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

public class CalculatorServer implements Calculator {

    // Implement the add method
    public double add(double a, double b) throws RemoteException {
        return a + b;
    }

    // Implement the subtract method
    public double subtract(double a, double b) throws RemoteException {
        return a - b;
    }

    // Implement the multiply method
    public double multiply(double a, double b) throws RemoteException {
        return a * b;
    }

    // Implement the divide method
    public double divide(double a, double b) throws RemoteException {
        if (b == 0) throw new ArithmeticException("Division by zero");
        return a / b;
    }

    // Implement the addFractions method
    @Override
    public String addFractions(int num1, int denom1, int num2, int denom2) throws RemoteException {
        int commonDenominator = denom1 * denom2;
        int newNumerator = (num1 * denom2) + (num2 * denom1);
        return simplifyFraction(newNumerator, commonDenominator);
    }

    // Implement the simplifyFraction method
    @Override
    public String simplifyFraction(int numerator, int denominator) throws RemoteException {
        int gcd = gcd(numerator, denominator);
        numerator /= gcd;
        denominator /= gcd;

        // Handle cases where the denominator is negative
        if (denominator < 0) {
            numerator = -numerator;
            denominator = -denominator;
        }

        return numerator + "/" + denominator;
    }

    // Helper method to compute the greatest common divisor
    private int gcd(int a, int b) {
        if (b == 0) return a;
        return gcd(b, a % b);
    }

    public static void main(String[] args) {
        try {
            // Create and export the CalculatorServer object
            CalculatorServer server = new CalculatorServer();
            Calculator stub = (Calculator) UnicastRemoteObject.exportObject(server, 0);

            // Bind the remote object's stub in the registry
            Registry registry = LocateRegistry.createRegistry(1099);
            registry.bind("Calculator", stub);

            System.out.println("CalculatorServer is running...");
        } catch (Exception e) {
            System.err.println("Server exception: " + e.toString());
            e.printStackTrace();
        }
    }
}
