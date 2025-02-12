def count(num, one_sum):  
    cnt = 0  
    bin_num = bin(num)[2:]  
    length = len(bin_num)  
    for i in range(length):  
        if bin_num[i] == '1':  
            # num이하의 수 중에 가장 큰 2의 거듭제곱 개수
            val = length-i-1  
            cnt += one_sum[val]  
            # 가장 앞자리 1 추가 더하기 
            cnt += (num - 2**val + 1)  
            num = num - 2 ** val  
    return cnt  

def main():

    x, y = map(int, input().split())  
    one_sum = [0 for _ in range(60)]  

    for i in range(1, 60):  
        one_sum[i] = 2**(i-1) + 2 * one_sum[i-1]  

    print(count(y, one_sum) - count(x-1, one_sum))

if __name__=="__main__":
    main()