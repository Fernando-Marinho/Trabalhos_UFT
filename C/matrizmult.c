#include <stdio.h>
#include <time.h>
#include <stdlib.h>

int main (){
    int a, b, c, d;
    printf("Quantas linhas e colunas vc deseja na matriz m?\n");
    scanf("%d %d", &a, &b);
    printf("Quantas linhas e colunas vc deseja na matriz n?\n");
    scanf("%d %d", &c, &d);
    int m[a][b], n[c][d], i, j, k, soma,r[a][d];
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
    printf("Matriz m:\n");
    for(i=0;i<a;i++,putchar('\n')){
        for(j=0;j<b;j++,putchar('\t')){
            printf("%d", m[i][j]);
        }
    }
    printf("Matriz n:\n");
    for(i=0;i<c;i++,putchar('\n')){
        for(j=0;j<d;j++,putchar('\t')){
            printf("%d", n[i][j]);
        }
    }
    k=0;
    if(b==c){
        printf("Matriz m*n:\n");
        for(i=0;i<a;i++, putchar('\n')){
            for(j=0;j<d;j++){
                soma=0;
                for(k=0;k<c;k++)
                    soma+=m[i][k]*n[k][j]; 
                    r[i][j]=soma;
                    printf("%d\t",r[i][j]);
            }
        }
    }else{
        printf("Nao foi possivel calcular m*n\n");
    }
    return 0;
}
