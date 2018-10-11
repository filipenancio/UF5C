#pragma once

namespace NO{

	enum Type {
		_INTEGER  = 0,
		_REAL     = 1, 
		_BOOLEAN  = 2,
		_STRING   = 3,
		_VOID     = 4,
		_UNKNOW   = 5
	};


	class Node {
    protected:
      int _valueOfInt;
      float _valueOfFloat;
      bool _valueOfBoolean;

      Type _type;

      bool _isOperate = false;
      bool _isBooleanExpression = false;
      bool _isVariable = false;

    public:
      Node() { }
      Node(Type type) : _type(type){ }
      Type type() { return _type; }
      void setType(Type type) { _type = type;}
      bool isOperate() { return _isOperate; }
      bool isBooleanExpression() { return _isBooleanExpression; }
      bool isVariable() { return _isVariable; }
      virtual void computeTree(bool silence) { }

      int intValue() { return _valueOfInt; }
      float floatValue() { return _valueOfFloat; }
      bool boolValue() { return _valueOfBoolean; }

      void setValues(Node * temp){
        _valueOfInt = temp->intValue();
        _valueOfFloat = temp->floatValue();
        _valueOfBoolean = temp->boolValue();

        _isVariable = temp->isVariable();
      }
  	};

}
