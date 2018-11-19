#include <iostream>
#include <stdio.h>
#include <math.h>

using namespace std;

int main(){

    double r, v;
    double pi = 3.14159;
            
    scanf("%lf", &r);
    
    v = pow(r, 3) * pi * 4/3.0;

    printf("VOLUME = %0.3lf\n", v);
    return 0;
}