class Solution {
    public boolean validPath(int n, int[][] edges, int source, int destination) {
        ArrayList<Integer>[]grapgh=new ArrayList[n];

        for(int i=0;i<n;i++){
            grapgh[i]=new ArrayList<>();
        }

        for(int[] edge : edges){
            int u=edge[0];
            int v=edge[1];

            grapgh[u].add(v);
            grapgh[v].add(u);
            
        }
        boolean []vis=new boolean[n];
       return dfs(grapgh,vis,source,destination);
    }
public boolean dfs(ArrayList<Integer>[]grapgh,boolean []vis,int src,int dest){
    if(src==dest){
        return true;
    }

    vis[src]=true;
    for(int neigh:grapgh[src]){
        if(!vis[neigh] && dfs(grapgh,vis,neigh,dest)){
            return true;
        }
    }
    return false;

    }
}