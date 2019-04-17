package application.Hmm;

public class ForwardProccesing {
	
	protected double[][] forwardProc(int[] o, int numStates,double [] pi ,double[][] trans_p, double[][] emit_p ) 
	{ 
		
		  double[][] f = new double[numStates][o.length]; 
		  //double [][] kola = new double [11][12];
		  
		  //System.out.println(kola.length);
		  
		  for (int l = 0; l < f.length; l++)
		  { 
			  f[l][0] = pi[l] * emit_p[l][o[0]]; 
		  } 
		  for (int i = 1; i < o.length; i++) 
		  { 
			  for (int k = 0; k < f.length; k++) 
			  { 
				  double sum = 0; 
				  
				  for (int l = 0; l < numStates; l++) 
				  { 
					  sum += f[l][i-1] * trans_p[l][k]; 
				  } 
				  
				  f[k][i] = sum * emit_p[k][o[i]]; 
			  } 
		 }
		  
		 return f; 
	} 
}
