%{
#include <string>
#include "variableType/variables.h"
#include "parser.h"
#include "string.h"
extern int yylineno;
void yyerror(const char* s, ...);
%}

%option noyywrap nodefault yylineno

_digs [0-9]+
_var  [a-z]+

_int {_digs}
_real "."{_digs}|{_digs}"."|{_digs}"."{_digs}

%%

[ \t \n]               ; //ignores spaces
 /*definições*/
"integer"           {return D_INT;}
"real"              {return D_REAL;}
"boolean"           {return D_BOOL;} 

 /*valores booleanos*/
"TRUE"              {yylval.boolean = true; return T_TRUE;}
"FALSE"             {yylval.boolean = false; return T_FALSE;}

 /*operadores aritmétricos*/
"+"                 {return T_PLUS;}
"-"                 {return T_SUB;}
"*"                 {return T_MULT;}
"/"                 {return T_DIV;}

 /*Separador e atribuiçã*/
","                 {return T_COMMA;}
":"                 {return T_ASSIGN_TYPE;}
":="                {return T_ASSIGN;}
";"                 {return T_EOFL;}

 /*parenteses*/
"("                 {return T_OPENP;}
")"                 {return T_CLOSEP;}

 /*funções matemáticas*/
"DIV"				{return F_DIV;}
"MOD"				{return F_MOD;}
"INT"				{return F_INT;}
"FRAC"				{return F_FRAC;}
"ABS"				{return F_ABS;}
"SQR"				{return F_SQR;}
"SQRT"				{return F_SQRT;}

 /*operadores relacionais*/
"="                 {return T_EQUALS;}
"<>"                {return T_NEQUALS;}
">"                 {return T_BIGGEST;}
"<"                 {return T_SMALLEST;}
">="                {return T_BIGOREQUALS;}
"<="                {return T_SMALLOREQUALS;}

 /*operadores binários*/
"AND"               {return T_AND;}
"OR"                {return T_OR;}
"NOT"               {return T_NOT;}
"XOR"				{return T_XOR;}

 /*condições e laços*/
"IF"    			{return IF;}
"THEN"  			{return THEN;}
"ELSE"  			{return ELSE;}
"WHILE" 			{return WHILE;}
"FOR"    			{return DO;}
"REPEAT"    		{return ENDIF;}

 /*Entradas e saídas*/
"readln"			{return OUT;}
"writeln"			{return IN;}

 /*valores*/
{_var}              {yylval.string = strdup(yytext); return T_VAR;}
{_int}              {yylval.integer = std::atoi(yytext); return T_INT;}
{_real}             {yylval.real = std::atof(yytext); return T_REAL;}

.                   {yyerror("Unknown token! %c\n", *yytext);} /*complains about every unknown character*/

%%

void yyerror(const char *s, ...){
    va_list ap;
    va_start(ap, s);
    std::fprintf(stderr, "Line %d: error: ", yylineno);
    std::vfprintf(stderr, s, ap);
}
