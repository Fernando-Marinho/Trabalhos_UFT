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
void print(int *n2, int n){
    int i;
    printf("\n");
    for(i=0;i<n;i++){
        printf("%d ", n2[i]);
    }
}
void bubble_sort(int *n2, int n){
    int k, j, aux;
    for(k=1;k<n;k++){
        for(j=0;j<n-k;j++){
            if(n2[j]>n2[j+1]){
                aux=n2[j];
                n2[j]=n2[j+1];
                n2[j+1]=aux;
            }
        }
    }
}
int main(){
    int n, *n2, i;
    srand(time(NULL));
    scanf("%d", &n);
    n2=malloc(n*sizeof(int));
    random(n2,n);
    bubble_sort(n2,n);
    print(n2,n);
    return 0;
}