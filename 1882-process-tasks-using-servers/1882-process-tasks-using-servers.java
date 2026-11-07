import java.util.*;

class Solution {
    public int[] assignTasks(int[] servers, int[] tasks) {
        int n = servers.length;
        int m = tasks.length;

        // Free servers: smallest weight, then smallest index
        PriorityQueue<Integer> free = new PriorityQueue<>((a, b) -> {
            if (servers[a] != servers[b]) {
                return Integer.compare(servers[a], servers[b]);
            }
            return Integer.compare(a, b);
        });

        /*
         * Busy servers:
         * [0] = time when the server becomes free
         * [1] = server index
         *
         * Ordered by finish time first.
         */
        PriorityQueue<long[]> busy = new PriorityQueue<>((a, b) -> {
            if (a[0] != b[0]) {
                return Long.compare(a[0], b[0]);
            }

            int serverA = (int) a[1];
            int serverB = (int) b[1];

            if (servers[serverA] != servers[serverB]) {
                return Integer.compare(servers[serverA], servers[serverB]);
            }

            return Integer.compare(serverA, serverB);
        });

        for (int i = 0; i < n; i++) {
            free.offer(i);
        }

        int[] answer = new int[m];
        long currentTime = 0;

        for (int task = 0; task < m; task++) {
            // The task arrives no earlier than second task.
            currentTime = Math.max(currentTime, task);

            // Release all servers that are free at currentTime.
            while (!busy.isEmpty() && busy.peek()[0] <= currentTime) {
                free.offer((int) busy.poll()[1]);
            }

            /*
             * If no server is free, jump to the earliest time at which
             * at least one server becomes available.
             */
            if (free.isEmpty()) {
                currentTime = busy.peek()[0];

                // Release every server finishing at this time.
                while (!busy.isEmpty() && busy.peek()[0] <= currentTime) {
                    free.offer((int) busy.poll()[1]);
                }
            }

            // Select the best available server.
            int server = free.poll();
            answer[task] = server;

            // This task starts at currentTime.
            long finishTime = currentTime + tasks[task];
            busy.offer(new long[] { finishTime, server });
        }

        return answer;
    }
}
