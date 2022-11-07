#include <stdio.h>
#include <stdlib.h>
#include <time.h>

int main(){
    int m[6][4],i,j, maior[6]={0}, r[6][4]={{0},{0}};
    srand(time(NULL));
    for(i=0;i<6;i++, putchar('\n')){
        for(j=0;j<4;j++, putchar('\t')){
            m[i][j]=rand()%10;
            printf("%d", m[i][j]);
            if(m[i][j]>maior[i]){
                maior[i]=m[i][j];
            }
        }
    }
    for(i=0;i<6;i++){
        printf("Maior elemento da linha %d: %d\n", i, maior[i]);
    }
    printf("Matriz resultante:\n");
    for(i=0;i<6;i++,putchar('\n')){
        for(j=0;j<4;j++,putchar('\t')){
            r[i][j]=m[i][j]*maior[i];
            printf("%d", r[i][j]);
        }
    }
    return 0;
}