#include <stdio.h>

int main(){
    FILE *fp;
    int a;
    fp=fopen("digitos.txt", "r");
    a=fp;
    printf("%d", a);
    fclose(fp);
    return 0;
}