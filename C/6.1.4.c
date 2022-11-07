#include<stdio.h>
#include<time.h>
#include<stdlib.h>

int main(){
    unsigned int n;
    printf("Digite o tamanho da matriz NxN:\n");
    scanf("%d", &n);
    if(n<0 || n==0){
        printf("Matriz Invalida\n");
        return 0;
    }
    int m[n][n], soma=0, i, j, sl, sc;
    srand(time(NULL));
    printf("Matriz digitada:\n");
    for(i=0;i<n;i++,putchar('\n')){
        for(j=0;j<n;j++,putchar('\t')){
            m[i][j]=rand() % 10;
            printf("%d",m[i][j]);
        }
    }
    printf("Digite a linha a qual deseja somar os termos:\n");
    scanf("%d", &sl);
    if(sl>n-1){
        printf("Linha Invalida\n");
        return 0;
    }
    for(j=0;j<n;j++){
        soma+=m[sl][j];
    }
    printf("Soma da linha %d: %d\n", sl, soma);
    printf("Digite a coluna a qual deseja somar os termos:\n");
    scanf("%d", &sc);
    if(sc>n-1){
        printf("Coluna Invalida\n");
        return 0;
    }
    soma=0;
    for(i=0;i<n;i++){
        soma+=m[i][sc];
    }
    printf("Soma da coluna %d: %d\n", sc, soma);
    soma=0;
    for(i=0;i<n;i++){
        for(j=0;j<n;j++){
            if(i==j){
                soma+=m[i][j];
            }
        }
    }
    printf("Soma da diagonal principal: %d\n", soma);
    soma=0;
    for(i=0;i<n;i++){
        for(j=0;j<n;j++){
            if(i+j==n-1){
                soma+=m[i][j];
            }
        }
    }
    printf("Soma da diagonal secundaria: %d\n", soma);
    soma=0;
    for(i=0;i<n;i++){
        for(j=0;j<n;j++){
            soma+=m[i][j];
        }
    }
    printf("Soma de todos os elementos da matriz: %d\n", soma);
    return 0;
}