package day240507;
import java.io.*;
import java.util.*;

public class CDT_검색어와접두사_백자민 {

    static int N,M;

    static TrieNode root;

    static String search(String s) {
        StringBuilder sb = new StringBuilder();
        TrieNode tmp = root;
        
        for(int i=0,size=s.length();i<size;i++) {
            if(tmp!=null) {
                int idx = s.charAt(i)-'a';
                tmp = tmp.child[idx];
            }

            
            if(tmp==null) {
                sb.append("0 ");
            } else {
                sb.append(tmp.cnt+" ");
            }
        }

        return sb.toString();
    }

    static void insert(String s) {
        TrieNode tmp = root;

        for(int i=0,size=s.length();i<size;i++) {
            int idx = s.charAt(i)-'a';

            if(tmp.child[idx]==null) {
                tmp.child[idx] = new TrieNode();
            }

            tmp = tmp.child[idx];
            tmp.cnt++; //단어를 만들고 있으므로 1추가
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        root = new TrieNode();

        st = new StringTokenizer(br.readLine());
        for(int i=0;i<N;i++) {
            insert(st.nextToken());
        }

        System.out.println(search(br.readLine()));
    }

    static class TrieNode {
        TrieNode[] child = new TrieNode[26];
        int cnt; //현재 노드를 거쳐가는 단어 개수 저장

        public TrieNode() {
            this.cnt = 0;

            for(int i=0;i<26;i++) {
                child[i] = null;
            }
        }
    }
}
