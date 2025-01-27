# january27_2025
The problems that I solved today

1.There are a total of numCourses courses you have to take, labeled from 0 to numCourses - 1. You are given an array prerequisites where prerequisites[i] = [ai, bi] indicates that you must take course ai first if you want to take course bi. For example, the pair [0, 1] indicates that you have to take course 0 before you can take course 1. Prerequisites can also be indirect. If course a is a prerequisite of course b, and course b is a prerequisite of course c, then course a is a prerequisite of course c. You are also given an array queries where queries[j] = [uj, vj]. For the jth query, you should answer whether course uj is a prerequisite of course vj or not. Return a boolean array answer, where answer[j] is the answer to the jth query.

Code:
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

2.Given a boolean 2D matrix grid of size n * m. You have to find the number of distinct islands where a group of connected 1s (horizontally or vertically) forms an island. Two islands are considered to be distinct if and only if one island is not equal to another (not rotated or reflected).

Code:
class Solution {
    void dfs(int i,int j,boolean[][] visited,int[][] grid,int r1,int c1,List<String> l)
    {
        visited[i][j]=true;
        int[][] dir={{-1,0},{0,1},{1,0},{0,-1}};
        int n=grid.length,m=grid[0].length;
        l.add((i-r1)+","+(j-c1));
        for(int[] d:dir)
        {
            int r=i+d[0];
            int c=j+d[1];
            if(r>=0 && r<n && c>=0 && c<m && !visited[r][c] && grid[r][c]==1)
            {
                visited[r][c]=true;
                dfs(r,c,visited,grid,r1,c1,l);
            }
        }
    }
    int countDistinctIslands(int[][] grid) {
        Set<List<String>> s=new HashSet<>();
        int i,j,n=grid.length,m=grid[0].length;
        boolean[][] visited=new boolean[n][m];
        for(i=0;i<n;i++)
        {
            for(j=0;j<m;j++)
            {
                if(!visited[i][j] && grid[i][j]==1)
                {
                    List<String> l=new ArrayList<>();
                    dfs(i,j,visited,grid,i,j,l);
                    s.add(l);
                }
            }
        }
        return s.size();
    }
}
