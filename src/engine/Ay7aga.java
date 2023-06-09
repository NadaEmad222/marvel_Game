package engine ;
import java.util.Scanner;
import java.util.Vector;

public  class Ay7aga {

	
		
		public static void main(String [] args){

			Scanner sc = new Scanner(System.in);
			int R=sc.nextInt();
			int C=sc.nextInt();
			for(int i=0;i<(2*R);i++){
			for(int j=0;j<(2*C);j++){

			if((i==0 && j==0) ||(i==0 && j==1)||(i==1 && j==0)||(i==1 && j==1))
			System.out.print(".");
			else
			if(i%2==0){ //even row
			if(j%2==0)
			System.out.print("+");
			else
			System.out.print("-");
			}
			else
			{  //odd row
			if(j%2==0)
			System.out.print("|");
			else
			System.out.print(".");

			}

			}
			System.out.println();
			}
			
			
			
			}

			
		
	}
