#include <stdio.h>

int soma(int n){
	if(n==1)
		return 1;
	else
		return(n+soma(n-1));
} 

int main() {
	int n, m=0;
	scanf("%d", &n);
	m=soma(n);
	printf("%d\n", m); 	
	return 0;
}
