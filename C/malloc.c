#include<stdio.h>
#include<stdlib.h>
int main(){
    int *p, i;
    p=malloc(4*sizeof(int));
    for(i=0;i<4;i++){
        p[i]=1;
        printf("%d\t", p[i]);
    }
    printf("\n");
    p=(int *) realloc (p, 6 * sizeof(int));    
    for(i=0;i<6;i++){
        if(p[i]!=1){
            p[i]=2;
        }
        printf("%d\t", p[i]);
    }
    printf("\n");
    free(p);
    for(i=0;i<6;i++){
        printf("%d\t", p[i]);
    }
    printf("\n");
    return 0;
}