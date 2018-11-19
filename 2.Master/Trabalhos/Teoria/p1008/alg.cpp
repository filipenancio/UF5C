#include <iostream>
#include <stdio.h>

using namespace std;

int main(){
    int number, hours;
    float valueHour, salary;
        
    cin >> number;
    cin >> hours;
    cin >> valueHour;
    
    salary = hours*valueHour; 

    printf("NUMBER = %i\nSALARY = U$ %.2f\n", number, salary);
    return 0;
}