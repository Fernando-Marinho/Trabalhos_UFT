#include <stdio.h>
int main () {
int d, n;
float h=0.0, termo;
printf("Declare o valor de n:\n");
scanf("%d", &n);
for(d=1; d<=n; d++){
termo=1.0/d;
h=termo+h;
}
printf("%f\n", h);
return 0;
}



