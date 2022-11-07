#include <stdio.h>
int mdc (int x, int y){
    return(x%y==0)?y:mdc(y, x%y);
}
int main() {
    int x, y, z, res, res2;
    scanf("%d %d %d", &x, &y, &z);
    res=mdc(x,y);
    res2=mdc(res, z);
    printf("%d\n", res2);
    return 0;
}