#pragma once

#include "scope.h"
#include "node.h"
#include "operation.h"

extern Scope scope;

namespace LOOP{


	class While : public NO::Node {
	public:
		While(NO::Node * condition, BL::Block * lines) : _condition(condition), _lines(lines){ }

		void computeTree(bool silence){
			if(!_condition->isOperate()){
				std::cout << "Erro:: Esperado uma expressão como condição para o laço!" << std::endl;
				return;

			}

			if(!_condition->isBooleanExpression()){
				std::cout << "Erro:: Esperado uma expressão booleana como condição para o laço!" << std::endl;
				return;
			}
			
			OP::Operate * op = dynamic_cast<OP::Operate*>(_condition);

			if(op->boolValue()){
				_run = true;
				op->computeTree(true);
			}

			while(op->boolValue()){
				_lines->computeTree(true);
				op->computeTree(true);
			}

			if(_run){
				scope.changeScopeTemp1Compartilhado();
			} else {
				scope.clearScopeTemp();
			}
		}

	private:
		NO::Node * _condition;
		BL::Block * _lines;
		bool _run = false;
	};

}