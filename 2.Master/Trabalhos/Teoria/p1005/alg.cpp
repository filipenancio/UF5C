#include <iostream>
#include <stdio.h>

using namespace std;

int main(){
    double a, b;
    double peso_a = 3.5, peso_b = 7.5;
    
    double media;

    cin >> a;
    cin >> b;

    media = (a * peso_a + b * peso_b) / (peso_a + peso_b);

    printf("MEDIA = %.5lf\n", media);
    return 0;
}