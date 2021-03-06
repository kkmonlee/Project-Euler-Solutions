/**
 * Created by aa on 29/10/16.
 */
public class p213 {

    public static void main(String[] args) {
        long start = System.nanoTime();

        double[][][][] grid = new double[900][2][30][30];
        int[] dx = { -1, 1, 0, 0 };
        int[] dy = { 0, 0, 1, -1 };

        for (int f = 0; f < 900; f++) {
            grid[f][0][f % 30][f / 30] = 1;

            for (int r = 1; r <= 50; r++) {
                int cur = r % 2;
                int prev = 1 - cur;
                grid[f][cur] = new double[30][30];

                for (int x = 0; x < 30; x++) {
                    for (int y = 0; y < 30; y++) {
                        int tot = 4;

                        if (x == 0 || x == 29) {
                            tot--;
                        }

                        if (y == 0 || y == 29) {
                            tot--;
                        }

                        for (int d = 0; d < 4; d++) {
                            int nx = x + dx[d];
                            int ny = y + dy[d];

                            if (nx < 0 || ny < 0 || nx >= 30 || ny >= 30) {
                                continue;
                            }

                            grid[f][cur][nx][ny] += grid[f][prev][x][y] / tot;
                        }
                    }
                }
            }
        }

        double result = 0;

        for (int x = 0; x < 30; x++) {
            for (int y = 0; y < 30; y++) {
                double p = 1;

                for (int f = 0; f < 900; f++) {
                    p *= 1 - grid[f][0][x][y];
                }

                result += p;
            }
        }

        long end = System.nanoTime();
        long runtime = end - start;
        System.out.printf("%.6f%n", result);
        System.out.println("Runtime: " + runtime / 1000000 + "ms (" + runtime
                + "ns)");
    }

}
