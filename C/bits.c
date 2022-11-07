#include <stdio.h>

void inicializa(){
    int combinacoes=1;
    //combinacoes =1;
}
int pnc(){
    int combinacoes;
    combinacoes *=2;
    return combinacoes;
}

int main(){
    int bit;
    inicializa();
    for(bit=1;bit<=8;bit++){
        //comb*=2;
        printf("%d bits = %d combinacoes\n", bit, pnc());
    }
    return 0;
}