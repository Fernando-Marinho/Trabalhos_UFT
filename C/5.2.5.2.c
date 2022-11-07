#include <stdio.h>
int ordem (int n){
	if(n<=0){
		printf("\n");
		return 0;
	}else{
		printf("%d", n%10);
		return(ordem(n/10));
	}	
}
int main(){
	int n;
	scanf("%d", &n);
	ordem(n);	
	return 0;
}