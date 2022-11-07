#include <stdio.h>
int main () {
	char letra;
	scanf("%c", &letra);
	int mask = 1;
	mask = mask<<5;
	mask = ~mask;
	letra = letra&mask;
	printf("%c \n", letra);
	return 0;
}