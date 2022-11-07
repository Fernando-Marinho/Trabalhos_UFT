#include <stdio.h>
int main () {
int n, i=1, fatorial=1;
scanf("%d", &n);
for(n; n>=i; n--){
fatorial*=n;
}
printf("%d\n", fatorial);
return 0;
}
