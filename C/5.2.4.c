#include <stdio.h>

int print(int n, int m){
	printf("%d ", m);
	return((m>=1)?print(n, m-1):0);
}

int main (){
	int n, m;
	scanf("%d", &n);
	m=n;
	print(n, m);
	return 0;
}
