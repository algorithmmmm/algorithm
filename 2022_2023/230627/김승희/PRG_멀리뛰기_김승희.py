def solution(n):
    answer = 0
    PIVOT = 1234567
    fibonacci = [0 for _ in range(max(n+1, 3))]
    fibonacci[1] = 1
    fibonacci[2] = 2
    for i in range(3, len(fibonacci)):
        fibonacci[i] = fibonacci[i-1] + fibonacci[i-2]
        fibonacci[i] %= PIVOT
    return fibonacci[n]