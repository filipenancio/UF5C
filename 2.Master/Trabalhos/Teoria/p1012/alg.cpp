#include <iostream>
#include <stdio.h>
#include <math.h>

using namespace std;

int main(){

    int i;

    cin.clear();
    cin >> i;
    cout << i << endl;

    string s1, s2;

    cin.ignore();
    getline(cin, s1);
    cout << s1 << endl;

    getline(cin, s2);
    cout << s2 << endl;

    cin.clear();
    cin >> i;
    cout << i << endl;

    
    return 0;
}