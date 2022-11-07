#include <stdio.h>
void num(int a, int b, int c, int soma){
    for(b; b<=c; b++){
        if(b%a==0){
            soma+=b;
        }
    }
    printf("%d\n", soma);
}
int main () {
    int a, b, c, soma;
    scanf("%d %d %d", &a, &b, &c);    
    soma=0;
    num(a, b, c, soma);
    return 0;
}
