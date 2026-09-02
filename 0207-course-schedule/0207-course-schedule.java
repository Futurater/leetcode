class Solution {
    public boolean canFinish(int numCourses, int[][] prerequisites) {
        List<List<Integer>> adj=new ArrayList<>();
        int n=prerequisites.length;
        for(int j=0;j<numCourses;j++){
            adj.add(new ArrayList<>());
        }

        int []indegree=new int[numCourses];

        for(int i=0;i<numCourses;i++){
            for(int[]pair: prerequisites){
                int course=pair[0];
                int prereq=pair[1];

                adj.get(course).add(prereq);
                indegree[prereq]++;
            }

        }

        


        Queue<Integer> q= new LinkedList<>();
        int course=0;
        for(int i=0;i<indegree.length;i++){
            if(indegree[i]==0){
                q.add(i);
            }
        }

        while(!q.isEmpty()){
            int c=q.remove();
            course++;

            for(int p:adj.get(c)){
                indegree[p]--;
                if(indegree[p]==0){
                    q.add(p);
                }
            }
        }
        return course==numCourses;

    }
}