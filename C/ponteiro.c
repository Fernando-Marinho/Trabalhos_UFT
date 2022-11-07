#include <stdio.h>
int main (){
    int a=10, *b, **c, ***d;
    b=&a;
    c=&b;
    d=&c;
    printf("Endereco de a: %d\n", b);
    printf("Endereco de a: %d\n", &a);
    printf("Conteudo de a: %d\n", *b);
    printf("Endereco de b: %d\n", &b);
    printf("Endereco de c: %d\n", &c);
    printf("Conteudo de a: %d\n", **c);
    printf("Endereco de a: %d\n", *c);
    printf("Endereco de b: %d\n", c);
    printf("Conteudo de a: %d\n", ***d);
    printf("Conteudo de b(Endereco de a): %d\n", **d);
    printf("Conteudo de c(Endereco de b): %d\n", *d);
    printf("Conteudo de d(Endereco de c): %d\n", d);
    printf("Endereco de d: %d\n", &d);
    scanf("%d", b);
    printf("Conteudo de a: %d\n", a);
    scanf("%d", *c);
    printf("Conteudo de a: %d\n", a);
    scanf("%d", **d);
    printf("Conteudo de a: %d\n", a);

    return 0;
}