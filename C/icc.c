#include<stdio.h>
#include<stdlib.h>
#include<string.h>
int main(){
    int p[100], i, j, contapart=0, contquant=0, temp=0;
    unsigned int idade[100][10], quant[100];
    float v[100], vf[100];
    char op[100][10], nome[100][1000], op2;
    for(i=0;i<2;i++){
        printf("Quantas pessoas fazem parte do plano?\n");
        scanf("%u", &quant[i]);
        if(quant[i]>10){
            printf("Quantidade Invalida\n");
            return 0;
        }
        if(quant[i]>3){
            contquant++;
        }
        p[i]=i;
        v[i]=0;
        vf[i]=0;
        temp=0;
        for(j=0;j<quant[i];j++){
            printf("Qual seu nome?\n");
            scanf("%s", &nome[i][temp]);
            temp+=10;
            printf("Qual opcao de plano vc deseja? Digite 'E' para Enfermaria ou 'A' Apartamento?\n");
            scanf("%s", &op[i][j]);
            if(op[i][j]=='E' || op[i][j]=='e'){
                v[i]=150.00;
            }else{
                if(op[i][j]=='A' || op[i][j]=='a'){
                    v[i]=250.00;
                    contapart++;
                }else{
                    printf("Opcao Invalida\n");
                    return 0;
                }
            }
            printf("Qual sua idade?\n");
            scanf("%d", &idade[i][j]);
            if(idade[i][j]>=22 && idade[i][j]<=50){
                v[i]+=v[i]*0.5;
            }else{
                if(idade[i][j]>50){
                    v[i]=v[i]*2;
                }
            }
            vf[i]+=v[i];
        }
        printf("Valor do plano %d: %0.2f\n", p[i], vf[i]);
    }
    printf("\n");
    printf("Quantidade de planos com apartamento: %d\n", contapart);
    printf("Quantidade de planos com mais de 3 pessoas: %d\n", contquant);
    printf("Deseja conferir os dados de um plano especifico? Digite 'S' para sim e 'N' para nao\n");
    scanf("%c", &op);
    while(op2=='S' || op2=='s'){
        printf("Digite o numero do plano\n");
        scanf("%d", &i);
        printf("Plano %d:\n", i);
        temp=0;
        for(j=0;j<quant[i];j++){
            printf("Nome: %s\n", &nome[i][temp]);
            printf("Opcao escolhida: %c\n", op[i][j]);
            printf("Idade: %d\n", idade[i][j]);
            temp+=10;
        }
        printf("Valor total do plano: %0.2f\n", v[i]);
        printf("Deseja conferir os dados de outro plano?\n");
        scanf("%c", &op2);
    }
    return 0;
}