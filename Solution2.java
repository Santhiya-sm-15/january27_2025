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
