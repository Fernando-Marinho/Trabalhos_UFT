#include<stdio.h>
#include<stdlib.h>
#include<time.h>

int main(){
    int maior=0, n[6]={3,4,1,2,8,7}, i;
    srand(time(NULL));
    for(i=0;i<6;i++){
        //n[i]=rand()%10;
        maior=n[i];
        maior=((maior+n[i])+abs(maior-n[i]))/2;
        printf("%d ", n[i]);
    }
    printf("\n");
    return 0;
}