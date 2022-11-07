#include <stdio.h>
#include <stdlib.h>
#include <time.h>

int main(){
    int m[5][5], a[5]={0}, b[5]={0}, i, j;
    srand(time(NULL));
    printf("Matriz preenchida:\n");
    for(i=0;i<5;i++,putchar('\n')){
        for(j=0;j<5;j++,putchar('\t')){
            m[i][j]=rand()%10;
            printf("%d", m[i][j]);
        }
    }
    for(i=0;i<5;i++){
       for(j=0;j<5;j++){
            a[i]+=m[i][j];
       }
       printf("Soma dos elementos da linha %d: %d\n", i, a[i]);
    }
    printf("\n");
    for(i=0;i<5;i++){
       for(j=0;j<5;j++){
            b[i]+=m[j][i];
       }
       printf("Soma dos elementos da coluna %d: %d\n", i, b[i]);
    }
    return 0;
}