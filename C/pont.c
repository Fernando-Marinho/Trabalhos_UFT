#include <stdio.h>
int main(){
    int x, y, *p;
    y=0;
    p=&y;
    x=*p;
    printf("%d\n", x);
    return 0;
}