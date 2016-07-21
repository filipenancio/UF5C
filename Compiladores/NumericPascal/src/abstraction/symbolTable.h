#pragma once

#include <map>
#include <iostream>
#include "variable.h"

extern void yyerror(const char* s, ...);

namespace ST{

	enum Kind {
  		VARIABLE,
  		UNKNOW
	};

	class Symbol : public NO::Node{
    public:
        Kind kind;              
        bool initialized;
        Node * node;
        Symbol(Kind kind, bool initialized, NO::Node *no) : 
        	kind(kind), initialized(initialized), node(no), NO::Node(NO::_VOID) { }
        Symbol() {kind = UNKNOW; initialized = false; node = NULL;}
	};

	typedef std::map<std::string, Symbol*> SymbolList;

	class SymbolTable {
	public:
		SymbolTable() { }
		SymbolTable(SymbolList list) {
			for(std::map<std::string, Symbol*>::iterator it = list.begin(); it!=list.end(); ++it){
				std::string id = it->first;
				Symbol * ant = it->second;

				NO::Node * temp = newVariable(id, NULL);
				temp->setType(ant->node->type());
				temp->setValues(ant->node);

				entryList[id]->initialized = ant->initialized;
			}
		}

		SymbolList getEntry() { return entryList; }

		void merge(SymbolList list){
			for(std::map<std::string, Symbol*>::iterator it = list.begin(); it!=list.end(); ++it){
				std::string id = it->first;
				Symbol * ant = it->second;

				if(checkId(id)){
					entryList[id]->node->setValues(ant->node);
					entryList[id]->initialized = ant->initialized;
				}
			}
		}

		bool checkId(std::string id) { return entryList.find(id) != entryList.end(); }
        void addSymbol(std::string id, Symbol *symbol) { entryList[id] = symbol; }

        NO::Node* newVariable(std::string id, NO::Node* nextNode){
        	if ( checkId(id) ){
        		std::cout << "Redefinição de variável!" << std::endl;
        		return NULL;
        	} 

	       	Symbol * entry = new Symbol(VARIABLE, false, new VAR::Variable(id, nextNode));
	       	addSymbol(id, entry);

		    return entry->node;
        }

        NO::Node* updateTypeVariable(NO::Type type, NO::Node* root){
        	VAR::Variable* varRoot = dynamic_cast<VAR::Variable*>(root);
			varRoot->setType(type);
			  
			while(varRoot->_next){
			    varRoot = dynamic_cast<VAR::Variable*>(varRoot->_next);
			    varRoot->setType(type);
			}

			return NULL;
        }

        NO::Node* assignVariable(std::string id){
		    if ( ! checkId(id) ) {
		    	std::cout << "Variável não definida!" << std::endl;
		    	return NULL;
		    }
		    Symbol * s = entryList[id];
		    s->initialized = true;
		    
		    return s->node; //Creates variable node anyway
		}

        NO::Node* useVariable(std::string id){
		    if ( ! checkId(id) ){ 
		    	std::cout << "Variável não definida!" << std::endl;
		    	return NULL;
		    }
		    if ( ! entryList[id]->initialized ){
		    	std::cout << "Variável não inicializada!" << std::endl;
		    	return NULL;
		    } 
		    return entryList[id]->node;
		}

    private:
    	SymbolList entryList;
	};
}