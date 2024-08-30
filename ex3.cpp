#include <cstdio>
#include <pthread.h>

double saldo = 1000.00;
pthread_mutex_t mutex;

void* depositos(void* arg) {
    double valor_depositado = *(double*)arg;
    for (int i = 0; i < 100; i++) {
        pthread_mutex_lock(&mutex);
        saldo += valor_depositado;
        pthread_mutex_unlock(&mutex);
    }
    return nullptr;
}

void* saques(void* arg) {
    double valor_sacado = *(double*)arg;
    for (int i = 0; i < 100; i++) {
        pthread_mutex_lock(&mutex);
        saldo -= valor_sacado;
        pthread_mutex_unlock(&mutex);
    }
    return nullptr;
}

int main() {
    pthread_t thread1, thread2;
    
    double valor_depositado = 5.0;
    double valor_sacado = 2.0;

    pthread_mutex_init(&mutex, nullptr);

    pthread_create(&thread1, nullptr, depositos, &valor_depositado);
    pthread_create(&thread2, nullptr, saques, &valor_sacado);

    pthread_join(thread1, nullptr);  
    pthread_join(thread2, nullptr);

    printf("Saldo final: %.2lf\n", saldo);

    pthread_mutex_destroy(&mutex); 

    return 0;
}
