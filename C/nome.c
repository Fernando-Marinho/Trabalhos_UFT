#include <stdio.h>
int main(){
    int i;
    char nome[50];
    for(i=0;i<50;i+=10){
        scanf("%s", &nome[i]);
    }
    printf("\n");
    for(i=0;i<50;i+=10){
        printf("%s\n", &nome[i]);
    }
    printf("\n");
    return 0;
}