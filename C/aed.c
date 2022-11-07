#include <stdio.h>
#include <stdlib.h>
#define max 100

char lista[max];

void inserir(){
    char item[max];
    int i;
    static int cont=1;
    char control;
    for(i=cont-1;i<max;i++){
        printf("Que item deseja adicionar?\n");
        fflush(stdin);
        scanf("%c", &item[i]);
        lista[i]=item[i];
        cont++;
        printf("Deseja adicionar mais um item?\n");
        fflush(stdin);
        scanf("%c", &control);
        if(control=='n'){
            break;
        }
    }
    printf("Foi adicionado um total de %d iten(s)\n", cont);

}

void remover(){
    char op;
    int i;
    printf("Que item vc deseja remover?\n");
    fflush(stdin);
    scanf("%c", &op);
    for(i=0;i<max;i++){
        if(lista[i]==op){
            lista[i]=0;
            //printf("Removido\n");      
        }
    }
}

void mostrar(){
    //printf("aqui\n");
    for(int i=0;i<max;i++){
        if(lista[i]!=0){
            printf("%c\n", lista[i]);
        }
    }
}

int main (){
    int op, i;
    char op2='s';
    for(i=0;i<max;i++){
    lista[i]=0;
    }
    while(op2=='s'){
        printf("Deseja inserir(1)?\nDeseja remover(2)?\nDeseja mostrar(3)?\n");
        scanf("%d", &op);
        if(op==1){
            inserir();
        }else{
            if(op==2){
                remover();
            }else{
                if(op==3){
                    mostrar();
                }else{
                    printf("opcao invalida");
                }
            }
        }
        printf("Deseja conferir o menu novamente?\n");
        fflush(stdin);
        scanf("%c", &op2);
    }
    return 0;
}