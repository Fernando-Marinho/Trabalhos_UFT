#include <stdio.h>

int print(int n, int m){
	printf("%d ", m);
	return((n!=m)?print(n, m+1):n);

}

int main (){
	int n, m=0;
	scanf("%d", &n);
	print(n, m);
	return 0;
}
