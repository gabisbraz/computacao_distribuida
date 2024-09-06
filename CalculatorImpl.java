
// CalculatorImpl.java
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class CalculatorImpl extends UnicastRemoteObject implements Calculator {

    protected CalculatorImpl() throws RemoteException {
        super();
    }

    @Override
    public double add(double a, double b) throws RemoteException {
        return a + b;
    }

    @Override
    public double subtract(double a, double b) throws RemoteException {
        return a - b;
    }

    @Override
    public double multiply(double a, double b) throws RemoteException {
        return a * b;
    }

    @Override
    public double divide(double a, double b) throws RemoteException {
        if (b == 0) {
            throw new RemoteException("Division by zero");
        }
        return a / b;
    }

    @Override
    public String addFractions(int num1, int denom1, int num2, int denom2) throws RemoteException {
        int commonDenominator = denom1 * denom2;
        int newNumerator = (num1 * denom2) + (num2 * denom1);
        return simplifyFraction(newNumerator, commonDenominator);
    }

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
}
