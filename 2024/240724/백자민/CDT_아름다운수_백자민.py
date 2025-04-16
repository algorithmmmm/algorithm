from collections import defaultdict

n = int(input())
ans=0 #정답 저장할 변수

number = '' #만든 숫자를 저장할 문자열

#만든 숫자가 아름다운 수인지 검사하는 함수
def isBeautiful():
    cnt2 = number.count('2')
    cnt3 = number.count('3')
    cnt4 = number.count('4')

    #2,3,4가 각각의 배수만큼 사용되지 않았다면 아름다운 수X(1은 몇 번이 연속해도 가능하므로 제외)
    if cnt2%2!=0 or cnt3%3!=0 or cnt4%4!=0:
        return False
    
    #순서대로 사용되었는지 확인
    idx=0
    while idx<n:
        tmp = int(number[idx]) #지금 검사할 숫자
        for i in range(idx, idx+tmp): #현재 idx번부터 tmp만큼 반복되는지 확인
            #중간에 다른 수가 껴있는 경우
            if int(number[i])!=tmp:
                return False
        idx+=tmp #검사한 부분 건너뛰기

    #모든 조건을 만족한다면 True 리턴
    return True

def makeNumber(cnt):
    global number,ans

    #n자리수를 모두 만든 경우
    if cnt==n:
        #아름다운 수인 경우 카운트 증가
        if isBeautiful():
            ans+=1
        return
    
    #1~4를 사용해서 
    for i in range(1, 5):
        number+=str(i) #i를 더해 숫자 만들기
        makeNumber(cnt+1)
        number = number[:-1] #최근에 추가한 문자 제거
    
    return 

makeNumber(0)

print(ans)