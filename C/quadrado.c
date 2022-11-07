#include <stdio.h>
#include <math.h>
int main () {
	int n1, n2, q1, q2, soma1=0, soma2=0;
	scanf("%d %d", &n1, &n2);
	q1=pow(n1,2.0);
	q2=pow(n2,2.0);
	while(q1>0){
		soma1+=(q1%10);
        q1/=10;
				}
	if(soma1==n2){
		 while(q2>0){
			soma2+=(q2%10);
			q2/=10;
				}
		}else{
			printf("Nao sao quadrados perfeitos\n");}
	if(soma2==n1){
		printf("%d e %d sao quadrados perfeitos\n", n1, n2);	
		 }else{
			printf("Nao sao quadrados perfeitos\n");}
return 0;
}