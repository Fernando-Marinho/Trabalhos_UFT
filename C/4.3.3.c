#include <stdio.h>
int main () {
int n=1, i, maior=0, menor=0;
while(n!=0)
	{
scanf("%d", &n);
	if(n>maior)
	{
	maior=n;
	}
	else
	    {
		if(n<menor)
		{
		menor=n;
		}else
		{
		}
	    }
	}
	printf("Maior:%d\nmenor:%d\n", maior, menor);
return 0;
}
