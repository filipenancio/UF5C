#include <iostream>

extern FILE* yyin;
extern int yyparse();
extern int yydebug;

int main(int argc, char **argv){
	
	//definição de modo debug
	yydebug = 0;

	yyparse();
	return 0;
}

void yyerror(const char* s) {
	std::cout << stderr << ">>: "<< s << std::endl;
	exit(1);
}