#include <stdio.h>
int main(){
    char nome[], nome2[], i;
    for(i=0;i<5;i++){
        scanf("%s", &nome[i]);
        nome2[i]=nome[i];
        printf("Seu nome: %s\n", nome[i]);
    }
    for(i=0;i<5;i++){
        printf("%s\t", nome2)[i];
    }
    printf("\n");
    return 0;
}