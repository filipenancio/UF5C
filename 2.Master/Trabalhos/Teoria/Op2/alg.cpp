#include <stdio.h>
#include <iostream>
#include <fstream>
#include <string>
#include <map>

using namespace std;

int main() {
    ifstream inputFile;

    string init;
    string notTerminals;
    string terminals;
    map<string, string> productions;
    string input;

    string aux;
    string delimiter = " -> ";
    string stopGramar = "# -> #";
    string stopTests = "#";
    int instanceCount = 0;

    string matrix[50][50] = {{}};

    for(int i = 0; i<50;i++){
        for(int j = 0; j<50;j++){
            matrix[i][j] = "";
        }
    }

    inputFile.open("ex.in");
    while(!inputFile.eof()) {
        getline(inputFile, init);
        getline(inputFile, notTerminals);
        getline(inputFile, terminals);

        getline(inputFile, aux);
        while(aux.compare(stopGramar)!=0) {
            productions[aux.substr(0, aux.find(delimiter))] = aux.substr(aux.find(delimiter)+delimiter.length(), aux.length());
            getline(inputFile, aux);
        }

        cout << "Instancia " << instanceCount++ << endl;
        getline(inputFile, aux);
        int sizeInput = aux.length();
        while(aux.compare(stopTests)!=0) {         

            for(int step = 1; step<=sizeInput; step++){
                matrix[0][step] = productions[aux.substr(step-1, step)];
            }

            for(int step = 2; step<=sizeInput; step++){
                for(int j = 1; j <= sizeInput - step; j++){
                    for(int p = 1; p<=sizeInput - step + 1; p++){
                        //faltando completar o triangulo
                        matrix[step][p] = productions[aux.substr()];                    
                    }
                }
            }

            string result = "";
            if(matrix[sizeInput][0].compare(init)!=0){
                result = "nao";
            }

            cout << aux << " " << result << " e uma palavra valida" << endl;

            getline(inputFile, aux);
        }
        cout << endl;
    }
    inputFile.close();
    cout << endl;

    return 0;
}