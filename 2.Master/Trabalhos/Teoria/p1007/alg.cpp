#include <iostream>
#include <stdio.h>

using namespace std;

int main(){
    int a, b, c, d;
        
    int dif;

    cin >> a;
    cin >> b;
    cin >> c;
    cin >> d;

    dif = a*b - c*d; 

    printf("DIFERENCA = %i\n", dif);
    return 0;
}