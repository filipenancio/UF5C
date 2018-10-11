#pragma 

#include "node.h"

namespace TYPE {

	class Integer : public NO::Node {
	public: 
		Integer(int val) : NO::Node(NO::_INTEGER) {
			_valueOfInt = val;
		}

		void computeTree(bool silence) { 
			std::cout << _valueOfInt << std::endl; 
		}

		int value() {
			return _valueOfInt;
		}

	};

	class Real : public NO::Node {
	public:
		Real(float val) : NO::Node(NO::_REAL) {
			_valueOfFloat = val;
		}

		void computeTree(bool silence) { 
			std::cout << std::fixed << _valueOfFloat << std::endl; 
		}

		float value() {
			return _valueOfFloat;
		}
	};

	class Boolean : public NO::Node {
	public:
		Boolean(bool val) : NO::Node(NO::_BOOLEAN) {
			_valueOfBoolean = val;
		}

		void computeTree(bool silence) {
			std::cout << _valueOfBoolean << std::endl;
		}

		bool value() {
			return _valueOfBoolean;
		}
	};

}