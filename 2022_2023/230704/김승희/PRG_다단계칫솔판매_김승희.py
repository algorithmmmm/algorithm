def solution(enroll, referral, seller, amount):
    answer = [0 for _ in range(len(enroll))]   
    enidx = dict()  # key : name, value : idx
    for i in range(len(enroll)):
        enidx[enroll[i]] = i
    
    for i in range(len(seller)):
        answer = payment(enidx, referral, seller[i], amount[i]*100, answer)
    return answer
# name : 이름, price : 가격
def payment(enidx, referral, name, price, answer):
    if name == "-" or price == 0: return answer
    idx = enidx[name]      # name에 해당하는 index
    charge = int(price * 0.1)   # 수수료
    recommend = referral[idx]   # name의 추천인
    answer[idx] += price - charge   # 이익 : 수수료를 뺀 가격
    return payment(enidx, referral, recommend, charge, answer)