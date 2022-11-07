#include<stdio.h>
#include<stdlib.h>
#include<time.h>

int main(){
unsigned int n;
scanf("%d", &n);
int m[n][n], i, j;
double a, b[n][n];
srand(time(NULL));
for(i=0;i<n;i++, printf("\n")){
    for(j=0;j<n;j++, printf("\t")){
        m[i][j]=rand()%100;
    }
}
printf("Matriz digitada:\n");
for(i=0;i<n;i++, printf("\n")){
    for(j=0;j<n;j++, printf("\t")){
        printf("%d",m[i][j]);
    }
}
scanf("%lf", &a);
printf("Matriz multiplicada por %0.2lf:\n", a);
for(i=0;i<n;i++, printf("\n")){
    for(j=0;j<n;j++, printf("\t")){
        b[i][j]=m[i][j]*a;
        printf("%0.2lf", b[i][j]);
    }
}
    return 0;
}