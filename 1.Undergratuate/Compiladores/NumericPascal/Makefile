FLEX=flex
BISON=bison
CC=clang++
PARAM=-std=c++11 -fcxx-exceptions -Wno-deprecated-register
OUTPUT=numericPascal

all: flex yacc clang

flex: src/include/scanner.l
	${FLEX} --outfile=src/lib/scanner.cpp src/include/scanner.l

yacc: src/include/parser.y
	${BISON} --defines=src/abstraction/parser.h --output=src/lib/parser.cpp src/include/parser.y

clang: src/abstraction/*.h src/main.cpp
	${CC} -o ${OUTPUT} src/lib/*.cpp  src/main.cpp ${LLVMPARAM} ${PARAM}

clean:
	rm src/lib/parser.cpp src/abstraction/parser.h src/lib/scanner.cpp ${OUTPUT}

test_all: all
	./${OUTPUT} < src/test/test_expressao  
	./${OUTPUT}	< src/test/test_variaveis
	./${OUTPUT} < src/test/test_condicional
	./${OUTPUT} < src/test/test_repeticao

test_expressao: all
	./${OUTPUT} < src/test/test_expressao

test_variaveis: all
	./${OUTPUT} < src/test/test_variaveis

test_condicional: all
	./${OUTPUT} < src/test/test_condicional

test_repeticao: all
	./${OUTPUT} < src/test/test_repeticao