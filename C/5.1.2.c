#include <stdio.h>
void num(int n1, int n2){
    if(n1<n2){
        for(n1; n1<=n2;n1++){
            printf("%d ", n1);
        }
    }else{
        for(n2; n2<=n1;n2++){
            printf("%d ", n2);
        }
    }
}
int main() {
    int n1, n2;
    scanf("%d %d", &n1, &n2);
    num(n1, n2);
    return 0;
}
