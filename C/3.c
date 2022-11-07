#include <stdio.h>
#include <stdlib.h>
void vetor(int *vet, int n){
    int i;
    for(i=0;i<n;i++){
        scanf("%d", &vet[i]);
    }
    putchar('\n');
}
int busca(int *vet, int ini, int fim, int search){
    int qtd=1;
    int meio=(ini+fim)/2;
    if(ini>fim){
        return 1;
    }
    if(search==vet[meio]){
        return 1;
    }
    else{
        if(search>vet[meio]){
            qtd+=busca(vet,meio+1,fim,search);
        }else{
            qtd+=busca(vet,ini,meio-1,search);
        }
        if(qtd>0){
            return qtd;
        }
    }
}

int main(){
    int n, search, p;
    printf("Digite o tamanho do vetor e o numero para buscar:\n");
    scanf("%d %d", &n, &search);
    int *vet;
    vet=malloc(n*sizeof(int));
    vetor(vet,n);
    p=busca(vet,0,n,search);
    printf("\n%d\n",p);
    return 0;
}