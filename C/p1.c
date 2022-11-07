#include<stdio.h>
#include<stdlib.h>
#include<time.h>
int main(){
    unsigned int l, c, i, j, k, lt, ct;
    srand(time(NULL));
    printf("Digite as dimensoes da matriz:\n");
    scanf("%d %d", &l, &c);
    lt=c;
    ct=l;
    float a[l][c], res[l][ct], t[lt][ct];
    printf("Matriz a:\n"); 
    for(i=0;i<l;i++, putchar('\n')){
        for(j=0;j<c;j++){
            a[i][j]=rand()%10;
            printf("%0.2f\t", a[i][j]);
        }
    }
    printf("Matriz Transporta:\n");
    for(j=0;j<c;j++, putchar('\n')){
        for(i=0;i<l;i++){
            t[j][i]=a[i][j];
            printf("%0.2f\t", t[j][i]);
        }
    }
    
    printf("Resultado:\n");
    for(i=0;i<l;i++, putchar('\n')){
        for(j=0;j<ct;j++){
            res[i][j]=0;
            for(k=0;k<c;k++)
                res[i][j]+=(a[i][k]*t[k][j]);
                printf("%0.2f\t", res[i][j]);
        }
    }
    return 0;
}