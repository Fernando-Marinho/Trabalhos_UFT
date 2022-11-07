%option noyywrap

%{
#include<stdio.h>
#include<stdlib.h>
%}

NUM [0-9]
LETRA [a-zA-Z_]
FRACAO \.{NUM}+
ALPHANUM ({NUM}|{LETRA})
OPERADOR_RELACIONAL (<|>|=|<=|>=|<>)
OPERADOR_ARITMETICO [+\-*/]
OPERADOR_LOGICO (and|or|not)
PALAVRA_RESERVADA (begin|end|for|do|if|then|else|while|dol)
TIPO_DADO (integer|real|double|char|string|boolean)
INVALIDO [^ |^\n]
%%

; 						{printf("<DELIMITADOR>");}
:=						{printf("<ATRIBUICAO>");}
{OPERADOR_RELACIONAL}	{printf("<OPERADOR RELACIONAL>");}
{OPERADOR_ARITMETICO}	{printf("<OPERADOR ARITMETICO>");}
{OPERADOR_LOGICO}		{printf("<OPERADOR LOGICO>");}
{PALAVRA_RESERVADA}		{printf("<PALAVRA RESERVADA>");}
{TIPO_DADO}+			{printf("<TIPO DADO>");}
(\_{ALPHANUM}+)			{printf("<IDENTIFICADOR>");}
[+-]?{NUM}+				{printf("<NUMERO_INTEIRO>");}
[+-]?{NUM}+{FRACAO}		{printf("<NUMERO_REAL>");}
(\"{ALPHANUM}+\")		{printf("<LITERAL>");}
(\#{ALPHANUM}+\#)		{printf("<COMENTARIO>");}
{INVALIDO}*				{printf("<INVALIDO>");}

%%

int main() {
	printf("Entre o codigo fonte:\n");
	yylex();
	return 0;
}