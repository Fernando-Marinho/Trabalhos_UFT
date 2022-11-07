#include <stdio.h>
#include <stdlib.h>
#include <time.h>

int main(){
    int a, b, c, d;
    printf("Digite o numero de linhas e colunas para a matriz m:\n");
    scanf("%d %d", &a, &b);
    printf("Digite o numero de linhas e colunas para a matriz n:\n");
    scanf("%d %d", &c, &d);
    int m[a][b], n[c][d], soma[a][d], i, j, k;
    printf("Matriz m:\n");
    for(i=0;i<a;i++, putchar('\n')){
        for(j=0;j<b;j++){
            m[i][j]=rand()%10;
            printf("%d\t", m[i][j]);
        }
    }
    printf("Matriz n:\n");
    for(i=0;i<c;i++, putchar('\n')){
        for(j=0;j<d;j++){
            n[i][j]=rand()%10;
            printf("%d\t", n[i][j]);
        }
    }
     for(i=0;i<a;i++){
        for(j=0;j<d;j++){
            soma[i][j]=0;
        }
     }
    if(b==c){
        printf("Matriz m*n:\n");
        for(i=0;i<a;i++,putchar('\n'))
            for(j=0;j<d;j++){
                soma[i][j]=0;
                    for(k=0;k<c;k++)
                        soma[i][j]+=m[i][k]*n[k][j];
                        printf("%d\t", soma[i][j]);

            }
    }else{
        printf("Nao foi possivel calcular m*n\n");
    }
    return 0;
}