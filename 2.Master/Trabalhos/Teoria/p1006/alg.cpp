#include <iostream>
#include <stdio.h>

using namespace std;

int main(){
    double a, b, c;
    double peso_a = 2, peso_b = 3, peso_c = 5;
    
    double media;

    cin >> a;
    cin >> b;
    cin >> c;

    media = (a * peso_a + b * peso_b + c * peso_c) / (peso_a + peso_b + peso_c);

    printf("MEDIA = %.1lf\n", media);
    return 0;
}