from mpi4py import MPI
import datetime
from matplotlib import pyplot as plt

def f(x): #funcao para calcular f(x)
    func_val = (5*pow(x, 3)) + (3*pow(x, 2)) + 4*x + 20
    return func_val

def calcular(n, x0, xn): #funcao para calcular a integral
    if n == 0:
        print("Divisao por zero")
    elif n < 0:
        print("Intervalo Invalido")
    else:
        h = (xn - x0) / n
        x = x0 + h
        soma = 0

        for i in range(1,n-1):
            soma = soma + f(x)
            x = x + h

        R = h * (( f(x0) + f(xn) ) / 2 + soma)
        return (R)

n = 1000000
x0 = 0
xn = 100000

comm = MPI.COMM_WORLD 
size = comm.Get_size() 
rank = comm.Get_rank() 

wt = MPI.Wtime()
start_time = datetime.datetime.now()

if(rank == 0):

    resultado = calcular(n, x0, xn)
    resultado=comm.bcast(resultado,root=0)

    for i in range(1,size):
        comm.send(resultado, dest=i,tag=1)

else : 
    resultado = comm.recv(source=0,tag=1) 
    wt = MPI.Wtime() - wt

    end_time = datetime.datetime.now()
    time_diff = (end_time - start_time)
    execution_time = time_diff.total_seconds() 
    
    print ("ID ", rank, ": O resultado da integral da funcao f e ", resultado)
    print("Tempo de execucao 1 em segundos: ", wt)
    print("Tempo de execucao 2 em segundos: ", execution_time)
    




