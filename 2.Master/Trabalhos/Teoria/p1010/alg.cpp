#include <iostream>
#include <stdio.h>

using namespace std;

int main(){
    int code1 , qty1, code2, qty2;
    float price1, price2;
            
    scanf("%i %i %f", &code1, &qty1, &price1);
    scanf("%i %i %f", &code2, &qty2, &price2);

    float valueToPay = (float) (qty1 * price1 + qty2 * price2);

    printf("VALOR A PAGAR: R$ %.2f\n", valueToPay);
    return 0;
}