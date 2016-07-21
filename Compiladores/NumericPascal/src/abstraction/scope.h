#pragma once

#include "symbolTable.h"

	class Scope {
	public:
		ST::SymbolTable * currentSymTab;
		

		Scope(){ 
			global = new ST::SymbolTable();
			currentSymTab = global; 
			temp1 = NULL;
			temp2 = NULL;
		}

		NO::Node* newVariable(std::string id, NO::Node* nextNode){
			return currentSymTab->newVariable(id, nextNode);
		}

		NO::Node* updateTypeVariable(NO::Type type, NO::Node* root){
			return currentSymTab->updateTypeVariable(type, root);
		}

		NO::Node* assignVariable(std::string id){
			return currentSymTab->assignVariable(id);
		}

		NO::Node* useVariable(std::string id){
			return currentSymTab->useVariable(id);
		}

		void changeScopeTemp1Compartilhado(){
			if(temp1 != NULL){
				global->merge(temp1->getEntry());
				currentSymTab = global;
				temp1 = NULL;
				temp2 = NULL;
			} else {
				temp1 = new ST::SymbolTable(currentSymTab->getEntry());
				currentSymTab = temp1;
			}
		}

		void changeScopeTemp2Compartilhado(){
			if(temp2 != NULL){
				global->merge(temp2->getEntry());
				currentSymTab = global;
				temp1 = NULL;
				temp2 = NULL;
			} else {
				temp2 = new ST::SymbolTable(currentSymTab->getEntry());
				currentSymTab = temp2;
			}
		}

		void clearScopeTemp(){
			temp1 = NULL;
			temp2 = NULL;
			currentSymTab = global;
		}

	private:
		ST::SymbolTable * global;
		ST::SymbolTable * temp1;
		ST::SymbolTable * temp2;
	};

