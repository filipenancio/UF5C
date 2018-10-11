#pragma once

#include "scope.h"
#include "node.h"
#include "type.h"
#include "variable.h"
#include <limits>

extern Scope scope;

namespace OP{

    enum Operation {
        PLUS, SUB, MULT, DIV,

        ASSIGN_TYPE, ASSIGN,

        EQUALS, NEQUALS,
        BIGGEST, BIGOREQUALS,
        SMALLEST, SMALLOREQUALS,

        AND, OR, XOR, NOT,

        _DIV, _MOD,
        _INT, _FRAC,
        _ABS,
        _SQR, _SQRT,

        IN, OUT
    };

    class Operate : public NO::Node {
    public:
        Operate(Node * left, Operation op, Node * right) : 
            _left(left), _operation(op), _right(right) { 

            _isOperate = true;
            if(isCompareOperation()){
                _isBooleanExpression = true;
            }
            error = false;
            calculate();
        }

        void calculate(){
            if(isSameType()) {
                switch(type()) {
                case NO::_INTEGER: {
                        operateInteger();
                    }break;
                case NO::_REAL: {
                        operateReal();
                    }break;
                case NO::_BOOLEAN: {
                        operateBoolean();
                    }break;
                default: {
                        std::cout << "Tipo de dado não reconhecido!" << std::endl;
                    }
                }
            }else{
                std::cout << "Operação não permitida entre tipos de dados diferentes!" << std::endl;
                error = true;
            }
        }

        void recalculate(bool silence){
            if(_left->isOperate()){
                _left->computeTree(silence);
            }
            if(_left->isVariable()){
                _left = scope.useVariable(dynamic_cast<VAR::Variable*>(_left)->_name);
            }
            if(_right->isOperate()){
                _right->computeTree(silence);
            }
            if(_right->isVariable()){
                _right = scope.useVariable(dynamic_cast<VAR::Variable*>(_right)->_name);
            }
            calculate();
        }

        void computeTree(bool silence){
            if(silence){
                recalculate(silence);
            }else{
            switch(type()) {
            case NO::_INTEGER: {
                    if(!error){
                    if(isCompareOperation()){
                        std::cout << _valueOfBoolean << std::endl;
                    }else{
                        if(_isAssign){
                            std::cout << dynamic_cast<VAR::Variable*>(_left)->_name << " := " << _valueOfInt << std::endl;  
                        }else{
                        std::cout << _valueOfInt << std::endl;                
                    }}}
                }break;
            case NO::_REAL: {
                    if(!error){
                    if(isCompareOperation()){
                        std::cout << _valueOfBoolean << std::endl;
                    }else{
                        if(_isAssign){
                            std::cout << dynamic_cast<VAR::Variable*>(_left)->_name << " := " << _valueOfFloat << std::endl;  
                         }else{
                        std::cout << _valueOfFloat << std::endl;
                    }}}
                }break;
            case NO::_BOOLEAN: {
                    if(!error){
                    if(_isAssign){
                        std::cout << dynamic_cast<VAR::Variable*>(_left)->_name << " := " << _valueOfBoolean << std::endl;  
                    }else{
                        std::cout << _valueOfBoolean << std::endl;
                    }}
                }break;
            default: {
                    std::cout << "Tipo de dado não reconhecido!" << std::endl;
                }
            }}         
        }

    private:
        Node * _left;
        Node * _right;

        Operation _operation;
        bool error;
        bool _isAssign = false;

        bool isCompareOperation(){
            return  _operation == EQUALS ||
                    _operation == NEQUALS ||
                    _operation == BIGGEST ||  
                    _operation == BIGOREQUALS ||
                    _operation == SMALLEST || 
                    _operation == SMALLOREQUALS;
        }

        bool isSameType(){
            if(_left){
                if(_left->type() == _right->type()){
                    setType(_left->type());
                    return true;
                } 
            }else{
                setType(_right->type());
                return true;
            }
            
            return false;
        }

        void operateInteger(){         
            switch(_operation) {
            case PLUS: {
                   _valueOfInt = _left->intValue() + _right->intValue();
                }break;
            case SUB: {
                    if(_left){
                        _valueOfInt = _left->intValue() - _right->intValue();
                    } else {
                        _valueOfInt = - _right->intValue();
                    }
                }break;
            case MULT: {
                    _valueOfInt = _left->intValue() * _right->intValue();
                }break;
            case DIV: {
                    _valueOfInt = _left->intValue() / _right->intValue();
                }break;
            case EQUALS: {
                    _valueOfBoolean = _left->intValue() == _right->intValue();
                }break; 
            case NEQUALS: {
                    _valueOfBoolean = _left->intValue() != _right->intValue();
                }break;
            case BIGGEST: {
                    _valueOfBoolean = _left->intValue() > _right->intValue();
                }break;
            case BIGOREQUALS: {
                    _valueOfBoolean = _left->intValue() >= _right->intValue();
                }break;
            case SMALLEST: {
                    _valueOfBoolean = _left->intValue() < _right->intValue();
                }break;
            case SMALLOREQUALS: {
                    _valueOfBoolean = _left->intValue() <= _right->intValue();
                }break;
            case ASSIGN: {
                    _valueOfInt = _right->intValue();
                    dynamic_cast<VAR::Variable*>(_left)->setInt(_right->intValue());
                    _isAssign = true;
                }break;
            default: {
                    std::cout << "Operação desconhecida para valor Inteiro!" << std::endl;
                    error = true;
                }
            }
        }

        void operateReal(){
            switch(_operation) {
            case PLUS: {
                    _valueOfFloat = _left->floatValue() + _right->floatValue();
                }break;
            case SUB: {
                    if(_left){
                        _valueOfFloat = _left->floatValue() - _right->floatValue();
                    } else {
                        _valueOfFloat = - _right->floatValue();
                    }
                }break;
            case MULT: {
                    _valueOfFloat = _left->floatValue() * _right->floatValue();
                }break;
            case DIV: {
                    _valueOfFloat = _left->floatValue() / _right->floatValue();
                }break;
            case EQUALS: {
                    _valueOfBoolean = _left->floatValue() == _right->floatValue();
                }break; 
            case NEQUALS: {
                    _valueOfBoolean = _left->floatValue() != _right->floatValue();
                }break;
            case BIGGEST: {
                    _valueOfBoolean = _left->floatValue() > _right->floatValue();
                }break;
            case BIGOREQUALS: {
                    _valueOfBoolean = _left->floatValue() >= _right->floatValue();
                }break;
            case SMALLEST: {
                    _valueOfBoolean = _left->floatValue() < _right->floatValue();
                }break;
            case SMALLOREQUALS: {
                    _valueOfBoolean = _left->floatValue() <= _right->floatValue();
                }break;
            case ASSIGN: {
                    _valueOfFloat = _right->floatValue();
                    dynamic_cast<VAR::Variable*>(_left)->setFloat(_right->floatValue());
                    _isAssign = true;
                }break;
            default: {
                    std::cout << "Operação desconhecida para valor Real!" << std::endl;
                    error=true;
                }
            }
        }

        void operateBoolean(){
            switch(_operation) {
            case AND: {
                    _valueOfBoolean = _left->boolValue() && _right->boolValue();
                }break;
            case OR: {
                    _valueOfBoolean = _left->boolValue() || _right->boolValue();
                }break;
            case XOR: {
                    _valueOfBoolean = _left->boolValue() ^ _right->boolValue();
                }break;
            case NOT: {
                    _valueOfBoolean = ! _right->boolValue();
                }break;
            case ASSIGN: {
                    _valueOfBoolean = _right->boolValue();
                    dynamic_cast<VAR::Variable*>(_left)->setBool(_right->boolValue());
                    _isAssign = true;
                }break;
            default: {
                    std::cout << "Operação desconhecida para valor Boolean!" << std::endl;
                    error=true;
                }
            }
        }
    };
}