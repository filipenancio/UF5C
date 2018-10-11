#include "variables.h"
#include "../symbolTable/symbolTable.h"

using namespace VAR;

extern ST::SymbolTable symtab;

void VectorDefinition::printTree(){
  std::cout << "Declaracao de arranjo ";

  Variable::printTypeVariable(root, Genre::FEM);
  std::cout << " de tamanho ";
  std::cout << maxQuantity;
  std::cout << ": "
  Variable* node = dynamic_cast<Variable*>(root);
  printListNameOfVariable(node);

  return;
}

void VectorDefinition::printListNameOfVariable(Variable* vectorVarNode){
  if(vectorVarNode->next){
    Variable* node = dynamic_cast<Variable*>(vectorVarNode->next);
    printListNameOfVariable(node);
    std::cout << ", ";
  }
  std::cout << varNode->name;
  return;
}
