#include<stdio.h>
#define MAX 10
typedef int conteudo;
typedef struct {
    conteudo topo;
    conteudo pilha[MAX];
}PilhaEstatica;

void inicia(PilhaEstatica *p){
    p->topo=0;
}
 void empilha(PilhaEstatica *p){
     p->pilha[p->topo]=1;
     p->topo++;
     printf("Teste\n");
 }
conteudo desempilha(PilhaEstatica *p){
    p->pilha[p->topo]=0;
    p->topo--;
}
int main(){
    PilhaEstatica *p;
    char op='s';
    inicia(p);
    printf("%d\n", p->topo);
    empilha(p);
    printf("%d\n", p->topo);
    return 0;
}
