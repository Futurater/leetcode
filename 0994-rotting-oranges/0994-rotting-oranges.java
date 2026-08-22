class Solution {
    public int orangesRotting(int[][] grid) {
        int count = 0;
        Queue<int[]> q = new LinkedList<>();
        int fresh = 0;
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                if (grid[i][j] == 2) {
                    q.add(new int[] { i, j });
                }
                if (grid[i][j] == 1) {
                    fresh++;
                }
            }
        }
            int[][] direcs = {
                    { 0, 1 }, { 1, 0 }, { 0, -1 }, { -1, 0 }
            };

            while (!q.isEmpty() && fresh > 0) {
                    int s=q.size();
                for (int k = 0; k < s; k++) {
                    int[] curr = q.remove();

                    int r = curr[0];
                    int c = curr[1];

                    for (int[] d : direcs) {
                        int nr = r + d[0];
                        int nc = c + d[1];

                        if (nr >= 0 && nr < grid.length && nc >= 0 && nc < grid[0].length && grid[nr][nc] == 1) {
                            grid[nr][nc] = 2;
                            fresh--;
                            q.add(new int[] { nr, nc });
                        }
                    }

                }
                count++;
            }
              if (fresh > 0) {
            return -1;
        }
        return count;

        }
      
    }


