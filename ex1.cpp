#include <iostream>
#include <cstdio>

void depositos(double& saldo, double valor_depositado) {
    saldo += valor_depositado;
}

void saques(double& saldo, double valor_sacado) {
    saldo -= valor_sacado;
}

int main() {
    double saldo = 1000.00;

    for (int i = 0; i < 100; i++) {
        depositos(saldo, 5.0);  
        saques(saldo, 2.0);
    }

    printf("Saldo final: %.2lf\n", saldo);
    return 0;
}
