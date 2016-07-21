# Proposta

## Especificações da linguagem:

## Tipos de dados: 

integer, real, boolean. 
 
­## Operadores:

- Expressões matemáticas: +, ­, *, /, :=
- Funções matemáticas: DIV, MOD, INT, FRAC, ABS, SQR, SQRT 
- Relacionais: =, <>, >, < 
- Lógicos: NOT, AND, OR, XOR 

­## Condicionais: 

IF THEN e IF THEN ELSE  

­## Repetição: 

FOR, WHILE, REPEAT 

­## Funções: 

As   funções   são   definidas,   contendo   um   escopo   próprio,   tendo   como   valor   de   retorno  
uma variável pré­declarada internamente, com o mesmo nome da função. 

­## Escopos: 

Escopo local: 
­   É   o   escopo   definido   entre   a   declaração   de   uma   função   e   o   código  
propriamente dito. 
Escopo Global: 
­   O   escopo   global,   compreende   as   variáveis   globais   do   programa,   sendo  
necessário realizar a definição em conjunto com a declaração de início do programa. 

­## Entrada e Saída de dados: 

Writeln: ­ Saida de dados 
Readln: ­ Entrada de dados

## Exemplo Estrutura

----------------------------------------------------------------//-------------------------------------------------
var  quant,cont,cont_m,cont_f:integer;
var  sexo:string;
var  dif:real;

function diferenca(n1:integer, n2:integer, quant:integer):real
begin
   diferenca:=(n1/quant)*100-(n2/quant)*100;
end;
 begin  {inicializa as variáveis}
  cont_m := 0; cont_f := 0;
  writeln('Qual a quantidade de pessoas?');
  readln(quant);  
  while quant<1 do {obriga a quantidade de pessoas ser positiva}
    begin
       writeln('Quantidade inválida! Digite uma quantidade maior que 1!');
       readln(quant);
    end;
  for cont:=1 to quant do
    begin
      writeln('Qual o sexo da ',cont,'o  pessoa?<m> masculino ou <f> feminino'); 
      readln(sexo);  
      while ((sexo<>'m') and (sexo<>'f')) do
    begin
      writeln('Sexo inválido! Digite <m> para masculino ou <f> para feminino!');
      readln(sexo);
    end;
    if sexo='m' then cont_m:=cont_m+1; 
    else cont_f := cont_f+1;
    end;
    writeln('Resultado:');
    writeln('o número de homens é: ',cont_m:3);
    writeln('A diferença percentual entre homens e mulheres é: ');
    dif = diferenca(cont_m, cont_f, quant);
    if dif>0 then write(dif,'% a mais de homens');
    else write(abs(dif),'% a mais de mulheres');
