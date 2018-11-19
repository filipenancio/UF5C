#include <iostream>
#include <stdio.h>

using namespace std;

int main(){
    char name[256];
    float salary, sales;
    float percBonus = (float)15/100;
        
    cin.getline(name,256);
    cin >> salary;
    cin >> sales;
    
    float total = salary + sales * percBonus; 

    printf("TOTAL = R$ %.2f\n", total);
    return 0;
}