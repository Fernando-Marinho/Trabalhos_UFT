#include <stdio.h>
int horas(int h1, int m1, int h2, int m2){
    int result = (h2*60+m2) - (h1*60 + m1);
    return (result<0) ? result+24*60: result;
}
int main () {
    int h1, m1, h2, m2, diff=0;
    scanf("%d %d %d %d", &h1, &m1, &h2, &m2);
    diff = horas(h1, m1, h2, m2);
    printf("A diff eh %d : %d\n", diff/60, diff%60); 
    return 0;
}
