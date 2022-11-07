#include <stdio.h>
int ordem (int n){
	printf("%d", n%10);	
	return((n>=10)?ordem(n/10):1);
}
int main(){
	int n;
	scanf("%d", &n);
	ordem(n);
	printf("\n");
	return 0;
}