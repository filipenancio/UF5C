#include "variables.h"
#include "../symbolTable/symbolTable.h"

using namespace VAR;

extern ST::SymbolTable symtab;

void Variable::printTypeVariable(Node* node, Genre genre){
  Variable* varNode = dynamic_cast<Variable*>(node);
  std::string stringToPrint;

  switch (symtab.entryList[varNode->name].type){
    case ST::D_INTEGER:
      stringToPrint = genre == Genre::MASC ? "intero" : "inteira"; break;
    case ST::D_REAL:
      stringToPrint = "real"; break;
    case ST::D_BOOLEAN:
      stringToPrint = genre == Genre::MASC ? "booleano" : "booleana"; break;
    default:
      stringToPrint = genre == Genre::MASC ? "desconhecido" : "desconhecida"; break;
  }

  std::cout << stringToPrint;
}

void Variable::printType(Genre genre){
  Variable::printTypeVariable(this, genre);
}

void Variable::printTree(){
  std::cout << "variavel ";
  printTypeVariable(this, Genre::FEM);
  std::cout << " "+name;
  return;
}
