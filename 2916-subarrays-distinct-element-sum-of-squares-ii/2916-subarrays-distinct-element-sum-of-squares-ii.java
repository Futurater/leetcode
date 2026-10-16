import java.util.Arrays;

class Solution {
    private static final long MOD = 1_000_000_007L;
    private long[] tree;
    private long[] lazy;

    public int sumCounts(int[] nums) {
        int n = nums.length;
        tree = new long[4 * n];
        lazy = new long[4 * n];

        // Track the last seen index of each number (values are up to 10^5)
        int[] lastSeen = new int[100_001];
        Arrays.fill(lastSeen, -1);

        long totalAns = 0;
        long curSqSum = 0; // Sum of c[l]^2 for the current right endpoint r

        for (int r = 0; r < n; r++) {
            int val = nums[r];
            int prev = lastSeen[val];

            // 1. Query the current sum of c[l] in range [prev + 1, r]
            long rangeSum = query(1, 0, n - 1, prev + 1, r);

            // 2. Update curSqSum using: (c + 1)^2 - c^2 = 2*c + 1
            long countInRange = r - prev;
            curSqSum = (curSqSum + 2 * rangeSum + countInRange) % MOD;

            // 3. Add +1 to all c[l] in range [prev + 1, r]
            update(1, 0, n - 1, prev + 1, r, 1);

            // 4. Accumulate into global answer and record lastSeen
            totalAns = (totalAns + curSqSum) % MOD;
            lastSeen[val] = r;
        }

        return (int) totalAns;
    }

    // Push lazy updates down to children
    private void pushDown(int node, int start, int end) {
        if (lazy[node] != 0) {
            int mid = (start + end) / 2;
            int left = 2 * node;
            int right = 2 * node + 1;

            tree[left] += lazy[node] * (mid - start + 1);
            lazy[left] += lazy[node];

            tree[right] += lazy[node] * (end - mid);
            lazy[right] += lazy[node];

            lazy[node] = 0;
        }
    }

    // Range Add: add 'val' to all elements in [ql, qr]
    private void update(int node, int start, int end, int ql, int qr, int val) {
        if (ql <= start && end <= qr) {
            tree[node] += (long) val * (end - start + 1);
            lazy[node] += val;
            return;
        }
        pushDown(node, start, end);
        int mid = (start + end) / 2;
        if (ql <= mid) {
            update(2 * node, start, mid, ql, qr, val);
        }
        if (qr > mid) {
            update(2 * node + 1, mid + 1, end, ql, qr, val);
        }
        tree[node] = tree[2 * node] + tree[2 * node + 1];
    }

    // Range Sum Query: return sum of elements in [ql, qr]
    private long query(int node, int start, int end, int ql, int qr) {
        if (ql <= start && end <= qr) {
            return tree[node];
        }
        pushDown(node, start, end);
        int mid = (start + end) / 2;
        long sum = 0;
        if (ql <= mid) {
            sum += query(2 * node, start, mid, ql, qr);
        }
        if (qr > mid) {
            sum += query(2 * node + 1, mid + 1, end, ql, qr);
        }
        return sum;
    }
}