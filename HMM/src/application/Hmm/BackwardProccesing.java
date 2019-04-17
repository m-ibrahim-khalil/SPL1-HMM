package application.Hmm;

public class BackwardProccesing {
	
	protected double[][] backwardProc(int[] o , int numStates, double [][] trans_p, double [][] emit_p) { 
		
		  int T = o.length; 
		  
		  double[][] bwd = new double[numStates][T]; 
		  
		  for (int i = 0; i < numStates; i++) 
			  bwd[i][T - 1] = 1; 
		  for (int t = T - 2; t >= 0; t--) 
		  { 
			  for (int i = 0; i < numStates; i++) 
			  { 
				  bwd[i][t] = 0; 
				  for (int j = 0; j < numStates; j++) 
					  bwd[i][t] += (bwd[j][t + 1] * trans_p[i][j] * emit_p[j][o[t + 1]]);
				  
			  } 
		  } 
		  return bwd; 
		 } 
	
}
