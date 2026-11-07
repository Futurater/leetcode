import java.util.Arrays;

class Solution {
    public int[] countServers(int n, int[][] logs, int x, int[] queries) {
        Arrays.sort(logs, (a, b) -> Integer.compare(a[1], b[1]));

        int m = queries.length;
        int[][] sortedQueries = new int[m][2]; // {query time, original index}

        for (int i = 0; i < m; i++) {
            sortedQueries[i][0] = queries[i];
            sortedQueries[i][1] = i;
        }
        Arrays.sort(sortedQueries, (a, b) -> Integer.compare(a[0], b[0]));

        int[] logCount = new int[n + 1];
        int[] answer = new int[m];

        // Logs in the current window are tracked by their sorted-array indices.
        int left = 0, right = 0, activeServers = 0;

        for (int[] query : sortedQueries) {
            int time = query[0];
            int start = time - x;

            // Add logs at times <= time.
            while (right < logs.length && logs[right][1] <= time) {
                int server = logs[right][0];
                if (logCount[server]++ == 0) {
                    activeServers++;
                }
                right++;
            }

            // Remove logs strictly before the inclusive window start.
            while (left < right && logs[left][1] < start) {
                int server = logs[left][0];
                if (--logCount[server] == 0) {
                    activeServers--;
                }
                left++;
            }

            answer[query[1]] = n - activeServers;
        }

        return answer;
    }
}
