def solution(cacheSize, cities):
    answer = 0
    cache = dict()
    idx = 0
    # cacheSize가 0인 경우
    if cacheSize == 0:
        return len(cities) * 5
    for city in cities:
        city = city.upper()     # 모두 대문자로
        idx += 1
        # cache가 꽉 차 있는 경우
        if len(cache) == cacheSize:
            # cache에 존재하지 않는 경우
            if city not in cache:
                chk_value = 1000001
                chk_key = None
                # value가 가장 작은 key를 찾는다.
                for key in cache:
                    if cache[key] < chk_value:
                        chk_value = cache[key]
                        chk_key = key
                del cache[chk_key]  # 삭제
                answer += 5
            else:   # cache에 존재하는 경우
                answer += 1
            cache[city] = idx
        else:   # cache에 자리가 있는 경우
            if city in cache:   # cache에 존재하는 경우
                answer+=1
            else:   # chache에 존재하지 않는 경우
                answer += 5     
            cache[city] = idx
    return answer