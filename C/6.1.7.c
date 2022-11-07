#include <stdio.h>
int main(){
    int n;
    printf("Digite um numero impar:\n");
    scanf("%d", &n);
    int i, j, k=1, space;
    printf("\n");
    while(k<=n){
        for(j=0;j<=n;j++, putchar('\t')){
            if(j<k){
                printf("  ");
            }else{
                printf("%d", j);
            }
        }

        printf("\n");
        n--;
        k++;
    }
    fflush(stdin);
    getchar();
    return 0;
}