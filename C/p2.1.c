#include <stdio.h>
int main(){
    int n, i, ant=0, atu=1, l=0;
    scanf("%d", &n);
    int f[n];
    for(i=0;i<n;i++){
        f[i]=0;
    }
    f[0]=atu;
    for(i=1;i<n;i++){
        atu=atu+ant;
        ant=atu-ant;
        f[i]=atu;
    }
    for(i=n-1;i>=0;i--){
        printf("%d\t", f[i]);
    }
    return 0;

}