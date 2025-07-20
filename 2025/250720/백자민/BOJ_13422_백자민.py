import sys

input = lambda: sys.stdin.readline().rstrip()


def main():
    T = int(input())
    
    for t in range(T):
        #N:집의 수 / M:훔칠 연속된 집의 수 / K:훔칠 돈 최대치
        N,M,K = map(int, input().split())
        lst = list(map(int, input().split()))

        #원형처리를 위해 처음~M-1개를 기존 리스트 뒤에다 붙여줌
        #1차원 리스트 한 번만 돌아도 원형 리스트 처리 가능
        if M!=N:
            for i in range(M-1):
                lst.append(lst[i])
        
        #누적합 만들기  sum_lst[i] = lst[0]~lst[i-1] 까지의 합 나타냄
        sum_lst = [0]
        total_sum = 0
        for i in lst:
            total_sum += i
            sum_lst.append(total_sum)
        
        #M만큼의 구간을 옮겨가며 성공할 가능성 카운팅
        l,r,count = 0,M,0
        while r != len(sum_lst):
            if sum_lst[r] - sum_lst[l] < K:
                count += 1
            
            l+=1
            r+=1
        print(count)

if __name__=="__main__":
    main()
