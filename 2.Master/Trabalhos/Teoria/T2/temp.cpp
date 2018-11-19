#include <stdio.h>
#include <iostream>
#include <fstream>
#include <string>
#include <map>

using namespace std;

int main() {
    ifstream inputFile;
    inputFile.open("ex.in");

    string input1;
    string input2;

    while(!inputFile.eof()) {
        getline(inputFile, input1);
        getline(inputFile, input2);
        
        cout << "X = " <<  atoi(input1)+atoi(input2) << endl;
    }
    inputFile.close();
    cout << endl;

    return 0;
}