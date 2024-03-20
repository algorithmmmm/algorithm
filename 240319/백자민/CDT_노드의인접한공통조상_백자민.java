package day240319;

import java.io.*;
import java.util.*;

public class CDT_노드의인접한공통조상_백자민 {

    static int N,root;

    static int[] depth, parent;

    static HashSet<Integer> childSet;

    static ArrayList<Integer>[] tree;

    public static void dfs(int tmp) {
        for(int next: tree[tmp]) {
            depth[next] = depth[tmp]+1;
            dfs(next);
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());

        depth = new int[N+1];
        parent = new int[N+1];

        tree = new ArrayList[N+1];
        for(int i=1;i<=N;i++) {
            tree[i] = new ArrayList<>();
        }

        childSet = new HashSet<>();
        StringTokenizer st;
        for(int i=1;i<N;i++) {
            st = new StringTokenizer(br.readLine());
            int p = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());

            tree[p].add(c);
            parent[c] = p;
            childSet.add(c);
        }

        //루트노드 찾기
        for(int n=1;n<=N;n++) {
            if(!childSet.contains(n)) {
                root = n;
                break;
            }
        }

        //루트노드부터 각 노드의 깊이 구하기
        depth[root] = 1;
        dfs(root);

        st = new StringTokenizer(br.readLine());
        int a = Integer.parseInt(st.nextToken());
        int b = Integer.parseInt(st.nextToken());

        while(depth[a]!=depth[b]) {
            if(depth[a]>depth[b])
                a = parent[a];
            else
                b = parent[b];
        }

        while(a!=b) {
            a = parent[a];
            b = parent[b];
        }

        System.out.println(a);
    }
}