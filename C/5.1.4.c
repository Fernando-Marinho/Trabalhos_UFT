#include <stdio.h>
void convert(int n, int sec, int min, int hr){
if(n<=59){
		sec=n;
	}else{
		sec=n%60;
	}
	if(n>=60){
	n/=60;
	min=n%60;
	}
	n*=60;
	while(n>=3600){
		n/=3600;
		hr=n;
	}
	printf("%d:%d:%d\n", hr, min, sec);
}
int main () {
	int n, sec=0, min=0, hr=0;
	scanf("%d", &n);
    convert(n, sec, min, hr);
	return 0;
}
