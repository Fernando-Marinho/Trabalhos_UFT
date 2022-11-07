#include <stdio.h>
int main () {
	char letra;
	scanf("%c", &letra);
	int aux=1;
	aux=aux<<5;
	letra=letra|aux;
	printf("%c\n", letra);
	return 0;
}