package application.Hmm;

public class Viterbi {

    public static int[] compute(int[] obs, int[] states, double[] start_p, double[][] trans_p, double[][] emit_p, int nStates,int nObs)
    {
        double[][] V = new double[nObs][nStates];
        int[][] path = new int[nStates][nObs];

        for (int y = 0; y < nStates; y++)
        {
            V[0][y] = start_p[y] * emit_p[y][obs[0]];
            path[y][0] = y;
        }

        for (int t = 1; t < nObs; ++t)
        {
            int[][] newpath = new int[nStates][nObs];

            for (int y = 0; y < nStates; y++)
            {
                double prob = -1;
                int state;
                for (int y0 = 0; y0 < nStates; y0++)
                {
                    double nprob = V[t - 1][y0] * trans_p[y0][y] * emit_p[y][obs[t]];
                    if (nprob > prob)
                    {
                        prob = nprob;
                        state = y0;
                        //
                        V[t][y] = prob;
                        //
                        System.arraycopy(path[state], 0, newpath[y], 0, t);
                        newpath[y][t] = y;
                    }
                }
            }

            path = newpath;
        }

        double prob = -1;
        int state = 0;
        for (int y = 0; y < nStates; y++)
        {
            if (V[nObs - 1][y] > prob)
            {
                prob = V[nObs - 1][y];
                state = y;
            }
        }
        System.out.println(path[state].length);
        return path[state];
    }
}