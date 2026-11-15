class Solution {
    public List<String> findRepeatedDnaSequences(String s) {
        /*
        * 1. 슬라이딩윈도로 10개 단위로 O(N) 탐색
        * 2. HashSet에 10개 단위 저장
        * 3. 있으면 output에 저장
        */

        HashSet<String> store = new HashSet<>();
        HashSet<String> result = new HashSet<>();

        for(int i = 0; i <= s.length() - 10; i++) {
            String str = s.substring(i, i + 10);

            if(store.contains(str)) {
                result.add(str);
            } else {
                store.add(str);
            }
        }

        List<String> list = new ArrayList<>(result);
        return list;
    }
}