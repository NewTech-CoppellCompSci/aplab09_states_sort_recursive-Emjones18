package sortState;

import java.util.*;
import java.io.*; 

public class UnitedStates
{
	// instance variables
	private static ArrayList <State> states;
	
	public UnitedStates()
	{
	   states = new ArrayList <State> ();
	   
	   readFile();
	   printStates();
	   
	   System.out.println();
	   System.out.println("=========================");
	   System.out.println("  Sorted by State");
	   System.out.println("=========================");
	   printStates();	
	   
	   menu();
	}
	
	
	
	/*
	 * Merge Sort
	 * 
	 * Use a merge sort to order the ArrayList
	 * by the state's name
	 */
	public void sortStates(int front, int back) {
		
		if (front < back) {
			int mid = (front + back) / 2;
			
			sortStates(front,mid);
			sortStates(mid+1, back);
			
			mergeTwoSortedArrays(front, mid, back);
//			for (int i = 0; i < states.size(); i++) {
//				
//				for (int i2 = 0; i2 < frontEnd.size(); i2++) {
//					
//				states.set(i,  backEnd.get(i2));
//				}
//				for (int i2 = 0; i2 < backEnd.size(); i2++) {
//					states.set(i,  backEnd.get(i2));
//			}
//				
//		}
	}
}

	public void mergeTwoSortedArrays(int front,int mid, int back) {
		
		State[] temp = new State[states.size()];
		
//		temp = new ArrayList <State> ();
//		
//		for (int i = front; i != back; i++) {
//			temp.add(states.get(i));
//			
//		}
		int i = front;
		int j = mid + 1;
		int k = front;
		
		while (i <= mid && j <= back) {
			
		
			if (states.get(i).getName().compareTo(states.get(j).getName()) < 0) {
				
				State holder = states.get(i);
				temp[k] = holder;
				
				i++;
			}
			else {
				State holder = states.get(j);
				temp[k] = holder;
				j++;
			}
			k++;
		}
		
		while (i <= mid) {
			State holder = states.get(i);
			temp[k] = holder;
			i++;
			k++;
		}
		while(j <= back) {
			State holder = states.get(j);
			temp[k] = holder;
			j++;
			k++;
		}
		
		for (int i2 = front; i2 <= back; i2++) {
		
			states.set(i2,temp[i2]);
			
		}
		
	}


	/*
	 * Quick Sort
	 * 
	 * Use a selection sort to order the ArrayList
	 * by the state's capital
	 */
	public void sortCapitals(int low, int high) {
		
	while (low < high) {
		int split = partition(low,high);
		
		sortCapitals(low,split);
		sortCapitals(split + 1, high);
	}
	
	}
	public int partition(int low, int high) {
		int pivot = states.get(0).getCapital().compareTo("a");
		int bottom = low - 1;
		int top = high + 1;
		
		while (bottom + 1 < pivot) {
			
			while (bottom + 1 < pivot) {
				
				while (top - 1 < pivot) {
					
					if (bottom == top) {
						
						return top;
					}
				}
				State holder = states.get(bottom);
				states.set(bottom, states.get(top));
				states.set(top,  holder);
			}
		}
		return 0;
	}
	
	
	
	

    public void menu()
	{
		
		boolean quitSelected = false;
		
		while (!quitSelected) {
			Scanner inKey = new Scanner(System.in);
			
			System.out.println("\n");
			System.out.println("=========================");
			System.out.println("          Menu");
			System.out.println("=========================");
			System.out.println("  1 - Sort by State");
			System.out.println("  2 - Sort by Capital");
			System.out.println("  3 - Exit");
			System.out.print("\n   Enter Selection: ");
			
			
			int choice = inKey.nextInt();
			
			
			switch (choice) {
			case (1):
	
				System.out.println("\n");
				System.out.println("=========================");
				System.out.println("  Sorted by State");
				System.out.println("=========================");
				sortStates(0, states.size() - 1);
				printStates();
				
				break;
			case (2):
	
				System.out.println("\n");
				System.out.println("=========================");
				System.out.println("  Sorted by Capital");
				System.out.println("=========================");
				sortCapitals(0, states.size() - 1);
				printStates();	
				
				break;
			case (3):
				System.out.println("\n\nGoodbye!");
				quitSelected = true;
				break;
			default:
				System.out.println("\nNot a valid Choice:");
				
	
			}
		
		}
		

	}
	
	
	
	
	
	public void printStates()
	{
		for(State s : states)
		{
			System.out.printf("%-15s", s.getName());
			System.out.printf("%-15s", s.getCapital());
			System.out.printf("%-25s", s.getNickname());
			System.out.printf("%10s\n", s.getPopulation());	
		}
	}
	
	public void readFile()
	{
		Scanner scan = null;
		try
		{
			scan = new Scanner(new File("states.txt"));
		}
		catch(FileNotFoundException ex)
		{
			System.out.println("File not Found!");
		}
		
		String name;
		String capital;
		String nickname;
		int population;
		while(scan.hasNextLine())
		{
			name = scan.nextLine();
			capital = scan.nextLine();
			nickname = scan.nextLine();
			population = scan.nextInt();
			if(scan.hasNextLine())
			{
			   String dummy = scan.nextLine();	
			}
			  
			
			State state = new State(name, capital, nickname, population);
			states.add(state);
		}
		
		
		
	}
}