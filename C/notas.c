#include <stdio.h>
int main () {
float nota1, nota2, media;
int opcao;
scanf("%f", &nota1);
if(nota1<0 || nota1>10){
printf("nota invalida\n");
return 0;
}
scanf("%f", &nota2);
if(nota2<0 || nota2>10){
printf("nota invalida\n");
return 0;
}
media=(nota1+nota2)/2;
printf("media=%f\n", media);
printf("novo calculo (1-sim 2-nao)\n");
scanf("%d", &opcao);
while(opcao==1){
scanf("%f", &nota1);
if(nota1<0 || nota1>10){
printf("nota invalida\n");
return 0;
}
scanf("%f", &nota2);
if(nota2<0 || nota2>10){
printf("nota invalida\n");
return 0;
}
media=(nota1+nota2)/2;
printf("media=%f\n", media);
printf("novo calculo (1-sim 2-nao)\n");
scanf("%d", &opcao);
}
return 0;
}
