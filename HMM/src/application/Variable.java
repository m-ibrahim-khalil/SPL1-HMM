package application;

import java.util.ArrayList;

public class Variable {
	static int nState;
	static int nEmission;
	static ArrayList<String> states = new ArrayList <String>();	
    static ArrayList<String> emotions = new ArrayList <String>();
    static ArrayList<String> observations = new ArrayList <String>();
    static int [] intStates = new int [100];
    static int [] intEmissions = new int [1000];
    static double [] startProbability = new double [100];
    static double [][] transitionProbability =  new double [100] [100];
    static double [][] emissionProbability = new double [100][100];
    static int nObservations = 0;

	public static int [] getIntEmission() 
	{
		int nObservations = 0;
		for (String i : observations)
	    {
			 System.out.println(i);
	        for (int j = 0; j < nEmission; j++)
	        {
	            if (i.equals(emotions.get(j)) )
	            {
	                intEmissions [nObservations] = j;
	                nObservations++;
	                System.out.println(emotions.get(j) + " " + intEmissions[nObservations-1]);
	                break;
	            }
	        }
	    }
		return intEmissions;
	}
	
	public static int [] getIntstates ()
	{
		for (int i = 0; i < nState; i++)
		{
			intStates[i] = i;
			System.out.println(states.get(i)+ " " + intStates[i]);
		}
		return intStates;
	}
	/*
	public static double[] getStartingProbability ()
	{
		for (double i : startProbability)
		{
			System.out.println(i);
		}
		return startProbability;
	}
	*/
	public static double[][] getTransitionProbability ()
	{
		for (int i = 0; i < nState; i++)
		{
			for (int j = 0; j < nState; j++)
			{
				System.out.println(transitionProbability[i][j]);
			}
		}
		
		return transitionProbability;
	}
}
