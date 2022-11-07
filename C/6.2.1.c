#include <stdio.h>
#include <stdlib.h>
#include <time.h>

int main(){
    int a, b, c, d;
    printf("dimensoes da matriz m:\n");
    scanf("%d %d", &a, &b); 
    if(a>100 || b>100){
       printf("Tamanho Invalido\n");
       return 0; 
    }
    printf("dimensoes da matriz n:\n");
    scanf("%d %d", &c, &d);
    if(c>100 || d>100){
        printf("Tamanho Invalido\n");
        return 0; 
    }
    int m[a][b], n[c][d], t[100][100]={{0},{0}},i,j,k;
    srand(time(NULL));
    for(i=0;i<a;i++){
        for(j=0;j<b;j++){
            m[i][j]=rand()%10;
        }
    }
    for(i=0;i<c;i++){
        for(j=0;j<d;j++){
            n[i][j]=rand()%10;
        }
    }
    if(b==c){
        printf("Matriz m*n:\n");
        for(i=0;i<a;i++,putchar('\n')){
            for(j=0;j<d;j++){
                t[i][j]=0;
                for(k=0;k<c;k++)
                    t[i][j]+=m[i][k]*n[k][j];
                    printf("%d\t", t[i][j]);

            }
        }
    }else{
        printf("Nao foi possivel calcular m*n\n");
        return 0;
    }

    return 0;
}