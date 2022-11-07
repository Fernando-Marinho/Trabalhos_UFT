#include<stdio.h>
#include<stdlib.h>
#include<time.h>
void pr(int **m, int l, int c){
	int i, j;
	for(i=0;i<l;i++, putchar('\n')){
		for(j=0;j<c;j++){
			if(i<j){
				m[i][j]=(2*i)+(7*j);
			}
			if(i==j){
				m[i][j]=3*(i*i);
			}
			if(i>j){
				m[i][j]=4*(i*i*i)+5*(j*j)+1;
			}
			printf("%d\t", m[i][j]);
		}
	}
}

int main(){
	srand(time(NULL));
	int l, c;
	scanf("%d %d", &l, &c);
	int **m,i,j;
	m=malloc(l*sizeof(int*));
	for(i=0;i<l;i++){
		m[i]=malloc(c*sizeof(int));
	}
	pr(m,l,c);
	return 0;
}