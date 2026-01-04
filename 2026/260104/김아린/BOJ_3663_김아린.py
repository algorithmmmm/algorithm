import sys

def solution(name: str) -> int:
    answer = 0
    length = len(name)
    move = length - 1 # 좌우로 갈 수 있는 최대 횟수

    for cur in range(length):
        ch = name[cur]
        front = ord(ch) - ord('A') # 앞으로 움직였을 때 횟수
        back = ord('Z') - ord(ch) + 1 # 뒤로 움직였을 때 횟수
        answer += min(front, back)

        # A가 아닐 때까지 끝으로 이동
        end = cur + 1
        while end < length and name[end] == 'A':
            end += 1

        l1 = cur # 0 ~ 현재 위치 길이
        l2 = length - end # 끝 ~ A가 아닌 곳까지 길이
        move = min(move, l1 + l2 + min(l1, l2))

    return answer + move

def main():
    input = sys.stdin.readline

    N = int(input().strip())
    for _ in range(N):
        name = input().strip()
        print(solution(name))

if __name__ == "__main__":
    main()