#pragma once

#include <iostream>
#include <vector>

#include "node.h"

namespace BL {

	typedef std::vector<NO::Node *> NodeList;

	class Block : public NO::Node {
	public:
		NodeList lines;
		Block() : NO::Node(NO::_VOID) { }
		void computeTree(bool silence){
			for(NO::Node * line: lines) {
				if(line != NULL){
					line->computeTree(silence);
				}
			}
		}
	};

}