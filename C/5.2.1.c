#include <stdio.h>
#include <math.h>
int cubo(int n){
	if(n==1){ 
		return 1;
	}else{
		return(pow(n,3.0)+cubo(n-1));
	}
}

int main () {
	int n, soma=0;
	scanf("%d", &n);
	soma=cubo(n);
	printf("%d\n", soma);	
	return 0;
}
