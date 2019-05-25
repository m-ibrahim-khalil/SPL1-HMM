package application.Hmm;

public class ForwardProccesing {
	
	public static double[][] forwardProc(int[] obs, double [] start_p, double[][] trans_p, double[][] emit_p , int nStates)
	{ 
		
		  double[][] probability_table = new double[nStates][obs.length];
		  //double [][] kola = new double [11][12];
		  
		  //System.out.println(kola.length);
		  int n = probability_table.length;
		  
		  for (int l = 0; l < n ; l++)
		  { 
			  probability_table[l][0] = start_p[l] * emit_p[l][obs[0]];
		  }

		  for (int i = 1; i < obs.length; i++)
		  { 
			  for (int k = 0; k < n; k++)
			  {
				  double sum = 0; 
				  
				  for (int l = 0; l < nStates; l++)
				  { 
					  sum += probability_table[l][i-1] * trans_p[l][k];
				  } 
				  
				  probability_table[k][i] = sum * emit_p[k][obs[i]];
			  } 
		 }
		  
		 return probability_table;
	} 
}
