package engine;

import java.io.IOException;

import javax.management.JMRuntimeException;

import model.effects.Effect;
import model.effects.EffectType;
import model.effects.Stun;

public class Try extends Ay7aga implements A1Interface,A2{
	
	public int c = A1Interface.a ;
	public static void main(String[]args)  {
		
		A1Interface s = null ;
		
		try {
			aa() ;
		
		} 
			catch (Exception e) {
			// TODO Auto-generated catch block
			
		}
		finally{
			System.out.print("finally statement");
		}
//		Effect [] a = new Stun[7] ;
//		a[0] = new Stun(7) ;
		
		Ay7aga v = new Ay7aga();
		Try q = new Try();
		
//	System.out.println("ay7aga c ="+ v.c);
//	System.out.println("try c ="+ q.c);
	}

	@Override
	public void w() {
		// TODO Auto-generated method stub
		
	}
	
	public static Object aa() throws IOException  {
		throw new IOException() ;
	}
	
	
}



