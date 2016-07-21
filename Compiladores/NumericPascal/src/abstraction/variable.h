#pragma once

#include "node.h"

namespace VAR {

class Variable : public NO::Node {
  public:
   	std::string _name;
   	NO::Node *_next;

   	Variable(std::string name, Node *next) : 
   		_name(name), _next(next), NO::Node(NO::_UNKNOW) { 
        _isVariable = true;
    }

    void tryAddVariable(NO::Node * no);

    void setInt(int value){
      _valueOfInt = value;
    }

    void setFloat(float value){
      _valueOfFloat = value;
    }

    void setBool(bool value){
      _valueOfBoolean = value;
    }
   
  	void computeTree(bool silence){
      if(silence){
      }else{
  		if(type()!=NO::_UNKNOW) {
      	switch(type()){
  			case NO::_INTEGER: {
            std::cout << _name << " := " << intValue() << std::endl;  
          }break;
        case NO::_REAL: {
            std::cout << _name << " := " << floatValue() << std::endl;  
          }break;
        case NO::_BOOLEAN: {
            std::cout << _name << " := " << boolValue() << std::endl;  
          }break;
        case NO::_UNKNOW: {
            //definition of variable;
      	  }break;
        default: {
            std::cout << "Tipo de dado não reconhecido!" << std::endl;
          }
        }
      }}
    }
  };
}