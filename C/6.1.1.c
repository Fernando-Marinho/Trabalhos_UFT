#include <stdio.h>
#include <stdlib.h>
#include <time.h>

int main(){
    int m[4][6], n[6][4], i, j, soma=0;
    srand(time(NULL));
    for(i=0;i<4;i++){
        for(j=0;j<6;j++){
            m[i][j]=rand() % 100;
            n[j][i]=rand() % 100;
            soma=soma+m[i][j]+n[j][i];
        }
    }
    printf("%d\n", soma);
    return 0;
}
