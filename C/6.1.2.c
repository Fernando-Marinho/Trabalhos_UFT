#include<stdio.h>
#include<stdlib.h>
#include<time.h>

int main(){
    int m[3][8], n[3][8], soma[3][8]={0}, dif[3][8]={0}, i, j;
    srand(time(NULL));
    for(i=0;i<3;i++){
        for(j=0;j<8;j++){
            m[i][j]=rand()%(100 + 1 - 1) + 1;
            n[i][j]=rand()%(100 + 1 - 1) + 1;
            soma[i][j]=m[i][j]+n[i][j];
            dif[i][j]=m[i][j]-n[i][j];
        }
    }
    printf("Soma da Matriz:\n");
    for(i=0;i<3;i++, putchar('\n')){
        for(j=0;j<8;j++){
             printf("%d\t", soma[i][j]);
        }
    }
    printf("Diferenca da Matriz:\n");
    for(i=0;i<3;i++, putchar('\n')){
        for(j=0;j<8;j++){
             printf("%d\t", dif[i][j]);
        }
    }
    return 0;
}