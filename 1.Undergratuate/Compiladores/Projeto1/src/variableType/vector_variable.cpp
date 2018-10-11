#include "variables.h"
#include "../symbolTable/symbolTable.h"

using namespace VAR;

extern ST::SymbolTable symtab;

void VectorVariable::printTree(){
  std::cout << "arranjo ";
  printTypeVariable(this, Genre::MASC);
  std::cout << " "+name;
  return;
}

void VectorVariable::printType(Genre genre){
  Variable::printTypeVectorVariable(this, genre);
}

void VectorVariable::printTypeVectorVariable(VectorVariable* vectorVarNode, Genre genre){
  for(Node* node : arranjo){

  }
  Variable* node =

}
