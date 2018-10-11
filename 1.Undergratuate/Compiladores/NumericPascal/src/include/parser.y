%{
    #include "../abstraction/scope.h"
    #include "../abstraction/node.h"
    #include "../abstraction/block.h"
    #include "../abstraction/operation.h"
    #include "../abstraction/conditional.h"
    #include "../abstraction/loop.h"

    BL::Block *programRoot; /* raiz do programa */
    Scope scope;        /* tratamento de escopos */
    
    extern int yylex();
    extern void yyerror(const char* s, ...);
%}

%define parse.trace

/* yylval == %union
 * union informs the different ways we can store data
 */
%union {
    const char * string;
    int integer;
    float real;
    bool boolean;

    NO::Node * node;
    BL::Block * block;
}

/* token defines our terminal symbols (tokens).
 */
%token <string> D_INT D_REAL D_BOOL D_VAR

/*definições*/
%token <string> T_VAR
%token <integer> T_INT
%token <real> T_REAL

/*valores booleanos*/
%token <boolean> T_TRUE T_FALSE

/*operadores aritmétricos*/
%token T_PLUS T_SUB T_MULT T_DIV

/*Separador e atribuição*/
%token T_COMMA T_ASSIGN_TYPE T_ASSIGN T_EOFL

/*parenteses*/
%token T_OPENP T_CLOSEP

/*comentário*/
%token T_COMMENT

/*operadores relacionais*/ 
%token T_BIGGEST T_SMALLEST T_BIGOREQUALS T_SMALLOREQUALS T_EQUALS T_NEQUALS

 /*funções matemáticas*/
//%token F_DIV F_MOD F_INT F_FRAC F_ABS F_SQR F_SQRT

 /*operadores binários*/
%token T_AND T_OR T_NOT T_XOR

/*condições*/
%token IF THEN ELSE 

/*laços*/
%token WHILE DO

/*Entradas e saídas*/
//%token OUT

 /*Definição de Escopos*/
%token T_BEGIN T_END

%type <node> expr line variables
%type <block> lines possibleLines program 

/* Operator precedence for mathematical operators
 * The latest it is listed, the highest the precedence
 */
%left T_PLUS T_SUB
%left T_MULT T_DIV
%left T_OPENP
%nonassoc error

/* Starting rule */
%start program

%%
program : lines { programRoot = $1; }
        ;


lines   : line { $$ = new BL::Block(); if($1 != NULL) { $$->lines.push_back($1);  $1->computeTree(false); } }
        | lines line  { if($2 != NULL) { $1->lines.push_back($2); $2->computeTree(false);} }
       // | lines error { yyerrok; }
        ;

line    : T_EOFL { $$ = NULL; }/*nothing here to be used */
        | T_COMMENT { $$ = NULL; }
        | expr T_EOFL { $$ = $1; }        
        | D_VAR variables T_ASSIGN_TYPE D_INT T_EOFL { $$ = scope.updateTypeVariable(NO::_INTEGER, $2); }
        | D_VAR variables T_ASSIGN_TYPE D_REAL T_EOFL { $$ = scope.updateTypeVariable(NO::_REAL, $2); }
        | D_VAR variables T_ASSIGN_TYPE D_BOOL T_EOFL { $$ = scope.updateTypeVariable(NO::_BOOLEAN, $2); }        
        | T_VAR T_ASSIGN expr { NO::Node* node = scope.assignVariable($1); if(node) { $$ = new OP::Operate(node, OP::ASSIGN, $3); } else { $$ = NULL; } }
        | IF expr THEN T_BEGIN possibleLines T_END T_EOFL { $$ = new COND::IF($2, false); }
        | IF expr THEN T_BEGIN possibleLines T_END ELSE T_BEGIN possibleLines T_END T_EOFL { $$ = new COND::IF($2, true); }
        | IF expr THEN T_COMMENT T_BEGIN possibleLines T_END T_EOFL { $$ = new COND::IF($2, false); }
        | IF expr THEN T_COMMENT T_BEGIN possibleLines T_END ELSE T_BEGIN possibleLines T_END T_EOFL { $$ = new COND::IF($2, true); }
		| WHILE expr DO T_BEGIN possibleLines T_END T_EOFL { $$ = new LOOP::While($2, $5); }
		;

possibleLines   : line { $$ = new BL::Block(); if($1 != NULL) $$->lines.push_back($1);}
                | possibleLines line  { if($2 != NULL) $1->lines.push_back($2); }
                ;

variables : T_VAR { $$ = scope.newVariable($1, NULL); }
          | variables T_COMMA T_VAR { $$ = scope.newVariable($3, $1); }
          ;

expr    : T_INT { $$ = new TYPE::Integer($1); }
        | T_REAL { $$ = new TYPE::Real($1); }
        | T_TRUE { $$ = new TYPE::Boolean($1); }
        | T_FALSE { $$ = new TYPE::Boolean($1); }
        | T_VAR { $$ = scope.useVariable($1); }       
        | T_OPENP expr T_CLOSEP { $$ = $2; }
        | expr T_PLUS expr { $$ = new OP::Operate($1, OP::PLUS, $3); }
        | expr T_SUB expr { $$ = new OP::Operate($1, OP::SUB, $3); }
        | expr T_MULT expr { $$ = new OP::Operate($1, OP::MULT, $3); }
        | expr T_DIV expr { $$ = new OP::Operate($1, OP::DIV, $3); }
        | expr T_AND expr { $$ = new OP::Operate($1, OP::AND, $3); }
        | expr T_OR expr { $$ = new OP::Operate($1, OP::OR, $3); }
        | expr T_XOR expr { $$ = new OP::Operate($1, OP::XOR, $3); }
        | expr T_BIGGEST expr { $$ = new OP::Operate($1, OP::BIGGEST, $3); }
        | expr T_SMALLEST expr { $$ = new OP::Operate($1, OP::SMALLEST, $3); }
        | expr T_EQUALS expr { $$ = new OP::Operate($1, OP::EQUALS, $3); }
        | expr T_NEQUALS expr { $$ = new OP::Operate($1, OP::NEQUALS, $3); }
        | expr T_BIGOREQUALS expr { $$ = new OP::Operate($1, OP::BIGOREQUALS, $3); }
        | expr T_SMALLOREQUALS expr { $$ = new OP::Operate($1, OP::SMALLOREQUALS, $3); }
        | T_SUB expr { $$ = new OP::Operate(NULL, OP::SUB, $2); }
        | T_NOT expr { $$ = new OP::Operate(NULL, OP::NOT, $2); }
        ;

%%
