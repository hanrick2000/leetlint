package Easy;

/**
 * Created by 1:00 AM on 10/13/2015.
 */
public class NumOfIslands {

    public static void main(String[] args) {
        NumOfIslands nis = new NumOfIslands();
        nis.test();
    }

    public void test() {
        int[][] graph = new int[][]{
                {1, 1, 0, 0, 0},
                {0, 1, 0, 0, 1},
                {0, 0, 0, 1, 1},
                {0, 0, 0, 0, 0},
                {0, 0, 0, 0, 1}
        };

        int islands = count(graph);
        System.out.println("result: " + islands);
    }

    int[] dx = new int[]{1, 0, -1, 0};
    int[] dy = new int[]{0, 1, 0, -1};

    public int count(int[][] map) {
        if (map == null) {
            return -1;
        }

        int R = map.length;
        int C = map[0].length;

        boolean[][] visited = new boolean[R][C];


        int cnt = 0;
        for (int r = 0; r < R; ++r) {
            for (int c = 0; c < C; ++c) {
                if (map[r][c] == 1 && !visited[r][c]) {
                    floodClean(map, r, c, visited);
                    cnt++;
                }
            }
        }
        return cnt;
    }

    private void floodClean(int[][] map, int r, int c, boolean[][] visited) {
        if (r < 0 || r >= map.length || c < 0 || c >= map[0].length || visited[r][c] == true || map[r][c] == 0) {
            return;
        } else {
            visited[r][c] = true;
        }
        floodClean(map, r + dx[0], c + dy[0], visited);
        floodClean(map, r + dx[1], c + dy[1], visited);
        floodClean(map, r + dx[2], c + dy[2], visited);
        floodClean(map, r + dx[3], c + dy[3], visited);
    }

    private void flood(int[][] map, int r, int c) {
        if (r < 0 || r >= map.length || c < 0 || c >= map[0].length || map[r][c] == 0) {
            return;
        } else {
            map[r][c] = 0;
        }
        flood(map, r + dx[0], c + dy[0]);
        flood(map, r + dx[1], c + dy[1]);
        flood(map, r + dx[2], c + dy[2]);
        flood(map, r + dx[3], c + dy[3]);
    }

    /**
     * I got confused, how do I goto multi direction at the same time. Threads? No, simply DFS!
     */
//    private void flood(int[][] map, int r, int c, int cnt) {
//        while (true) {
//            if (map[r + dx[0]][c + dy[0]] == 1)  {
//                map[r+dx[0]][c+dy[0]] = 0;
//            }
//        }
//    }
}
