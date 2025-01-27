class Solution {
    public void bfs(int src,boolean[] visited,ArrayList<Integer>[] adj)
    {
        Queue<Integer> q=new LinkedList<>();
        q.add(src);
        visited[src]=true;
        while(!q.isEmpty())
        {
            int x=q.poll();
            for(int a:adj[x])
            {
                if(!visited[a])
                {
                    q.add(a);
                    visited[a]=true;
                }
            }
        }
    }
    public List<Boolean> checkIfPrerequisite(int numCourses, int[][] prerequisites, int[][] queries) {
        int i,j,n=numCourses;
        ArrayList<Integer>[] adj=new ArrayList[n];
        for(i=0;i<n;i++)
            adj[i]=new ArrayList<>();
        int[] indegree=new int[n];
        for(int[] x:prerequisites)
            adj[x[0]].add(x[1]);
        boolean[][] reach=new boolean[n][n];
        for(i=0;i<n;i++)
        {
            boolean[] visited=new boolean[n];
            bfs(i,visited,adj);
            for(j=0;j<n;j++)
                reach[i][j]=visited[j];
        }
        List<Boolean> res=new ArrayList<>();
        for(int[] x:queries)
            res.add(reach[x[0]][x[1]]);
        return res;
    }
}