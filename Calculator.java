// Calculator.java
import java.rmi.Remote;
import java.rmi.RemoteException;

public interface Calculator extends Remote {
    // Métodos para operações com números de ponto flutuante
    public double add(double a, double b) throws RemoteException;
    public double subtract(double a, double b) throws RemoteException;
    public double multiply(double a, double b) throws RemoteException;
    public double divide(double a, double b) throws RemoteException;

    // Métodos para operações com frações
    public String addFractions(int num1, int denom1, int num2, int denom2) throws RemoteException;
    public String simplifyFraction(int numerator, int denominator) throws RemoteException;
}