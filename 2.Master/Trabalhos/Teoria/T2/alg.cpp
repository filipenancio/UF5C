#include <iostream>
#include <vector>
#include <stdio.h>
#include <stdlib.h>
#include <math.h>
#include <string>
#include <string.h>
#include <map>
#include <typeinfo>

using namespace std;

typedef struct 
{
    int nIf;
    bool elseCase;
    int nL;
    vector<int> variables;
} State;

int main(){

    // REGRAS
        //comandos
        // comando operando1,operando2
        //   MOV, ADD, SUB, MUL, DIV, MOD   --- Só operando 2 pode ser int
        //   IFEQ, IFG, IFL, IFGE, IFLE     --- Qqer operando pode ser int
        // comando operando
        //   CALL, RET
        
        // Obs.: ENDIF - marca final bloco if

        //variáveis 
        // R0, ..., R9
        // R0 - entrada
        // R9 - retorno

        //constantes - apenas inteiros
        // 0, ..., 999
        // sucessore de 999 é 0
        // antecessor de 0 é 999

    // Map para auxiliar na identificação de comandos
    map<string, int> mapCommands;
    mapCommands["MOV"] = 1; 
    mapCommands["ADD"] = 2; 
    mapCommands["SUB"] = 3; 
    mapCommands["MUL"] = 4; 
    mapCommands["DIV"] = 5; 
    mapCommands["MOD"] = 6;
    mapCommands["IFEQ"] = 7; 
    mapCommands["IFG"] = 8; 
    mapCommands["IFL"] = 9;
    mapCommands["IFGE"] = 10; 
    mapCommands["IFLE"] = 11;
    mapCommands["CALL"] = 12;
    mapCommands["RET"] = 13;
    mapCommands["ENDIF"] = 14;

    // Map para auxiliar na obtenção de posição das variáveis no vetor de variáveis
    map<string, int> mapOperators;
    mapOperators["R0"] = 0; 
    mapOperators["R1"] = 1; 
    mapOperators["R2"] = 2; 
    mapOperators["R3"] = 3; 
    mapOperators["R4"] = 4; 
    mapOperators["R5"] = 5; 
    mapOperators["R6"] = 6;
    mapOperators["R7"] = 7; 
    mapOperators["R8"] = 8; 
    mapOperators["R9"] = 9;

    // Vetor de comandos de um algoritmo;
    vector<string> algoritm;

    // Vetor que contrala o contexto das variáveis
    vector<State> contexts;

    // Vetor de resultados dos casos de teste
    vector<int> results;
    
    // Variáveis de definição para execução de cada caso de teste
    int lines = -1, param = -1;

    while(lines != 0 && param != 0){ //caso linhas = param = 0 - fim casos de testes 
        cin.clear();
        cin >> lines >> param; // ler numero de linhas e parametro
        cin.ignore();

        for(int i = 1; i <= lines; i++){
            string line = "";
            getline(cin, line);
            algoritm.push_back(line);
        }
        
        vector<int> variables(10,0); // inicio vetor de variáveis

        State context; // iniciando contexto
        context.elseCase = false;
        context.nIf = 0;
        context.nL = -1;
        context.variables = variables;
        context.variables[0]=param; // atribui a R0 o param de entrada
        contexts.push_back(context); // adiciona primeiro contexto
        
        for(int l = 0; l<lines; l++){
            context.nL=l;
            string line = algoritm[l]; 
            string comand, operators;
          
            if(strcmp(line.c_str(), "ENDIF") != 0){
                size_t separator = line.find(" ");
                comand = line.substr(0,separator);
                operators = line.substr(separator+1);
            } else {
                comand = line;
            }

            if(context.elseCase && context.nIf>0){
                switch(mapCommands[comand]){
                    case 7: context.nIf++; break;
                    case 8: context.nIf++; break;
                    case 9: context.nIf++; break;
                    case 10: context.nIf++; break;
                    case 11: context.nIf++; break;
                    case 14: {
                        context.nIf--;
                        if(context.nIf == 0){
                            context.elseCase = false;
                        }
                        break;
                    }
                    default: break;
                }
            } else {
                switch(mapCommands[comand]){
                    case 1: { //MOV
                        size_t v = operators.find(",");
                        int v1 = mapOperators[operators.substr(0,v)];
                        int aux = (operators.substr(v+1).at(0) != 'R') 
                                    ? atoi(operators.substr(v+1).c_str())
                                    : context.variables[mapOperators[operators.substr(v+1)]];
                        context.variables[v1] = aux;
                        break;
                    }
                    case 2: { //ADD
                        size_t v = operators.find(",");
                        int v1 = mapOperators[operators.substr(0,v)];
                        int aux = (operators.substr(v+1).at(0) != 'R') 
                                    ? atoi(operators.substr(v+1).c_str())
                                    : context.variables[mapOperators[operators.substr(v+1)]];
                        context.variables[v1] = (context.variables[v1] + aux) % 1000;
                        break;
                    }
                    case 3: { //SUB
                        size_t v = operators.find(",");
                        int v1 = mapOperators[operators.substr(0,v)];
                        int aux = (operators.substr(v+1).at(0) != 'R') 
                                    ? atoi(operators.substr(v+1).c_str())
                                    : context.variables[mapOperators[operators.substr(v+1)]]; 
                        aux = context.variables[v1] - aux;
                        context.variables[v1] = (aux < 0)
                                        ? 1000+aux
                                        : aux;
                        break;
                    }
                    case 4: { //MUL
                        size_t v = operators.find(",");
                        int v1 = mapOperators[operators.substr(0,v)];
                        int aux = (operators.substr(v+1).at(0) != 'R') 
                                    ? atoi(operators.substr(v+1).c_str())
                                    : context.variables[mapOperators[operators.substr(v+1)]]; 
                        context.variables[v1] = (context.variables[v1] * aux) % 1000;
                        break;
                    }
                    case 5: { //DIV
                        size_t v = operators.find(",");
                        int v1 = mapOperators[operators.substr(0,v)];
                        int aux = (operators.substr(v+1).at(0) != 'R') 
                                    ? atoi(operators.substr(v+1).c_str())
                                    : context.variables[mapOperators[operators.substr(v+1)]];  
                        context.variables[v1] = context.variables[v1] / aux;
                        break;
                    }
                    case 6: { //MOD
                        size_t v = operators.find(",");
                        int v1 = mapOperators[operators.substr(0,v)];
                        int aux = (operators.substr(v+1).at(0) != 'R') 
                                    ? atoi(operators.substr(v+1).c_str())
                                    : context.variables[mapOperators[operators.substr(v+1)]]; 
                        context.variables[v1] = context.variables[v1] % aux; 
                        break;
                    }
                    case 7: { //IFEQ
                        context.nIf++;
                        size_t v = operators.find(",");
                        int v1=-1, v2=-1, a1=-1, a2=-1;
                        if(operators.substr(0,v).at(0) != 'R'){
                            a1 = atoi(operators.substr(0,v).c_str());
                        } else {
                            v1 = mapOperators[operators.substr(0,v)];
                        }

                        if(operators.substr(v+1).at(0) != 'R'){
                            a2 = atoi(operators.substr(v+1).c_str());
                        } else {
                            v2 = mapOperators[operators.substr(v+1)];
                        }

                        if(v1 != -1 && v2 != -1){
                            if(context.variables[v1] != context.variables[v2]){
                                context.elseCase = true;
                            }
                        } else {
                            if(a1 != -1 && v2 != -1){
                                if(a1 != context.variables[v2]){
                                    context.elseCase = true;
                                }
                            } else {
                                if(v1 != -1 && a2 != -1){
                                    if(context.variables[v1] != a2){
                                        context.elseCase = true;
                                    }
                                } else {
                                    if(a1 != a2){
                                        context.elseCase = true;
                                    }
                                }
                            }
                        }
                        break;
                    }
                    case 8: { //IFG
                        context.nIf++;
                        size_t v = operators.find(",");
                        int v1=-1, v2=-1, a1=-1, a2=-1;
                        if(operators.substr(0,v).at(0) != 'R'){
                            a1 = atoi(operators.substr(0,v).c_str());
                        } else {
                            v1 = mapOperators[operators.substr(0,v)];
                        }

                        if(operators.substr(v+1).at(0) != 'R'){
                            a2 = atoi(operators.substr(v+1).c_str());
                        } else {
                            v2 = mapOperators[operators.substr(v+1)];
                        }

                        if(v1 != -1 && v2 != -1){
                            if(context.variables[v1] <= context.variables[v2]){
                                context.elseCase = true;
                            }
                        } else {
                            if(a1 != -1 && v2 != -1){
                                if(a1 <= context.variables[v2]){
                                    context.elseCase = true;
                                }
                            } else {
                                if(v1 != -1 && a2 != -1){
                                    if(context.variables[v1] <= a2){
                                        context.elseCase = true;
                                    }
                                } else {
                                    if(a1 <= a2){
                                        context.elseCase = true;
                                    }
                                }
                            }
                        }
                        break;
                    }
                    case 9: { //IFL
                        context.nIf++;
                        size_t v = operators.find(",");
                        int v1=-1, v2=-1, a1=-1, a2=-1;
                        if(operators.substr(0,v).at(0) != 'R'){
                            a1 = atoi(operators.substr(0,v).c_str());
                        } else {
                            v1 = mapOperators[operators.substr(0,v)];
                        }

                        if(operators.substr(v+1).at(0) != 'R'){
                            a2 = atoi(operators.substr(v+1).c_str());
                        } else {
                            v2 = mapOperators[operators.substr(v+1)];
                        }

                        if(v1 != -1 && v2 != -1){
                            if(context.variables[v1] >= context.variables[v2]){
                                context.elseCase = true;
                            }
                        } else {
                            if(a1 != -1 && v2 != -1){
                                if(a1 >= context.variables[v2]){
                                    context.elseCase = true;
                                }
                            } else {
                                if(v1 != -1 && a2 != -1){
                                    if(context.variables[v1] >= a2){
                                        context.elseCase = true;
                                    }
                                } else {
                                    if(a1 >= a2){
                                        context.elseCase = true;
                                    }
                                }
                            }
                        }
                        break;
                    }
                    case 10: { //IFGE
                        context.nIf++;
                        size_t v = operators.find(",");
                        int v1=-1, v2=-1, a1=-1, a2=-1;
                        if(operators.substr(0,v).at(0) != 'R'){
                            a1 = atoi(operators.substr(0,v).c_str());
                        } else {
                            v1 = mapOperators[operators.substr(0,v)];
                        }

                        if(operators.substr(v+1).at(0) != 'R'){
                            a2 = atoi(operators.substr(v+1).c_str());
                        } else {
                            v2 = mapOperators[operators.substr(v+1)];
                        }

                        if(v1 != -1 && v2 != -1){
                            if(context.variables[v1] < context.variables[v2]){
                                context.elseCase = true;
                            }
                        } else {
                            if(a1 != -1 && v2 != -1){
                                if(a1 < context.variables[v2]){
                                    context.elseCase = true;
                                }
                            } else {
                                if(v1 != -1 && a2 != -1){
                                    if(context.variables[v1] < a2){
                                        context.elseCase = true;
                                    }
                                } else {
                                    if(a1 < a2){
                                        context.elseCase = true;
                                    }
                                }
                            }
                        }
                        break;
                    }
                    case 11: { //IFEL
                        context.nIf++;
                        size_t v = operators.find(",");
                        int v1=-1, v2=-1, a1=-1, a2=-1;
                        if(operators.substr(0,v).at(0) != 'R'){
                            a1 = atoi(operators.substr(0,v).c_str());
                        } else {
                            v1 = mapOperators[operators.substr(0,v)];
                        }

                        if(operators.substr(v+1).at(0) != 'R'){
                            a2 = atoi(operators.substr(v+1).c_str());
                        } else {
                            v2 = mapOperators[operators.substr(v+1)];
                        }

                        if(v1 != -1 && v2 != -1){
                            if(context.variables[v1] > context.variables[v2]){
                                context.elseCase = true;
                            }
                        } else {
                            if(a1 != -1 && v2 != -1){
                                if(a1 > context.variables[v2]){
                                    context.elseCase = true;
                                }
                            } else {
                                if(v1 != -1 && a2 != -1){
                                    if(variables[v1] > a2){
                                        context.elseCase = true;
                                    }
                                } else {
                                    if(a1 > a2){
                                        context.elseCase = true;
                                    }
                                }
                            }
                        }
                        break;
                    }
                    case 12: { //CALL
                        int newParam;
                        if(operators.at(0) != 'R'){
                            newParam = atoi(operators.c_str()); // recebeu inteiro
                        } else {
                            newParam = context.variables[mapOperators[operators]]; // pega valor no vetor de variáveis
                        }

                        vector<int> newVariables(10,0); // inicio vetor de variáveis

                        State newContext;
                        newContext.elseCase = false;
                        newContext.nIf = 0;
                        newContext.nL = -1;
                        newContext.variables = newVariables;
                        newContext.variables[0] = newParam;
                        contexts.push_back(newContext);

                        context = newContext;
                        l=-1;
                        break;
                    }
                    case 13: { //RET
                        int ret;
                        if(operators.at(0) != 'R'){
                            ret = atoi(operators.c_str()); // recebeu inteiro
                        } else {
                            ret = context.variables[mapOperators[operators]]; // pega valor no vetor de variáveis
                        }
                        if(contexts.size() == 1){ // verifica se tem outro contexto em execução
                            contexts.pop_back();
                            results.push_back(ret); // se não houver guarda resultado
                        } else {
                            contexts.pop_back(); // elimina vetor de variais atuais da lista de contexto
                            context = contexts.back(); // brestaura contexto anterior
                            context.variables[9] = ret; // atribui o retorno a variável R9
                        }
                        break;
                    }
                    case 14: { //ENDIF
                        context.nIf--; //registra endif encontrado
                        break;
                    }
                    default: break;
                }
            }
        }
        // limpeza de vetores para proximo caso de teste
        algoritm.clear();
        contexts.clear();
        variables.clear();
    }

    //impressão dos resultados de cada algoritmo
    for(int i=0; i<results.size(); i++){
        if(results[i]==-1){
            printf("*\n");
        }else{
            printf("%i\n", results[i]);
        }
    }

    return 0;
}