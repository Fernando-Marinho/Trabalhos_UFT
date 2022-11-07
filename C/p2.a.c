#include<stdio.h>
#include<stdlib.h>
#include<time.h>

void random(int *n2, int n){
    int i;
    for(i=0;i<n;i++){
        n2[i]=rand()%10;
        printf("%d ", n2[i]);
    }
}
int compar(int *a, int *b){
    if(*a<*b){
        return -1;
    }
    if(*a==*b){
        return 0;
    }
    if(*a>*b){
        return 1;
    }
}
void print(int *n2, int n){
    int i;
    printf("\n");
    for(i=0;i<n;i++){
        printf("%d ", n2[i]);
    }
}
int main(){
    int n, *n2, i;
    srand(time(NULL));
    scanf("%d", &n);
    n2=malloc(n*sizeof(int));
    random(n2,n);
    int (*compar1)()=compar;
    qsort(n2,n,sizeof(int),compar1);
    print(n2,n);
    return 0;
}