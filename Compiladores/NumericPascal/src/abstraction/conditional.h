#pragma once

#include "scope.h"
#include "node.h"

extern Scope scope;

namespace COND{

	class IF : public NO::Node {
	public:

		IF(NO::Node * node, bool hasElse) : _condition(node), _hasElse(hasElse) { }

		void computeTree(bool silence){
			if(_condition->type() != NO::_BOOLEAN){
				std::cout << "Erro:: Esperado condição booleana para ramo condicional!" << std::endl;
				return;
			}

			if(_condition->boolValue()){
				scope.changeScopeTemp1Compartilhado();
				std::cout << "Ramo IF executado!" << std::endl;
			} else {
				if(_hasElse){
					scope.changeScopeTemp2Compartilhado();
					std::cout << "Ramo ELSE executado!" << std::endl;
				}else{
					scope.clearScopeTemp();
					std::cout << "Ramo condicional ignorado!" << std::endl;
				}

			}
		}

	private:
		NO::Node * _condition;
		bool _hasElse;		
	};

}