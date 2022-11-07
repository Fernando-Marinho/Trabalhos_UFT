#include<stdio.h>
#include <stdlib.h>
#include <time.h>

int main(){
    srand(time(NULL));
    int **m, i, j, k, r[3][3];
    m=malloc(3*sizeof(int*));
    for(i=0;i<3;i++){
        m[i]=malloc(3*sizeof(int));
    }
    printf("Matriz:\n");
    for(i=0;i<3;i++, putchar('\n')){
        for(j=0;j<3;j++){
            m[i][j]=rand()%10;
            printf("%d\t", m[i][j]);
        }
    }
    printf("Matriz transporta:\n");
    for(i=0;i<3;i++, putchar('\n')){
        for(j=0;j<3;j++){
            printf("%d\t", m[j][i]);
        }
    }
    return 0;
} 