#include <stdio.h>
int verifica (int n){
    if(n<0){
        return 0;
    }else{
        return 1;
    }
}
int main () {
    int n; 
    scanf("%d", &n);        
    if(verifica(n)==0){
    puts("Negativo\n");
    }else{
    puts("Positivo\n");
    }
    return 0;
}
