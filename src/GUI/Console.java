package GUI;

import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.sound.sampled.AudioFileFormat;
import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.DataLine;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.SourceDataLine;

import engine.Game;

public class Console {

	public static void main(String []args) throws UnsupportedAudioFileException, IOException, LineUnavailableException {
		
//		File audioFile = new File("Al pic/The-Avengers-Theme-Song.mp3");
//		AudioInputStream sound = AudioSystem.getAudioInputStream(audioFile);
//		Clip clip = AudioSystem.getClip();
//		clip.open(sound);
//		clip.start();
//		clip.stop();
		
		//clip.open(sound2);
		
      
		
//		URL file = null;
//		try {
//			file = new URL("C:\\Users\\nadae\\eclipse-workspace\\Marvel-M2\\Al pic\\The-Avengers-Theme-Song.mp3");
//		} catch (MalformedURLException e1) {
//			// TODO Auto-generated catch block
//			System.out.print(e1.getMessage()) ;
//		}
//
//        AudioInputStream ais = null;
//		try {
//			ais = AudioSystem.getAudioInputStream(file);
//		} catch (UnsupportedAudioFileException | IOException e) {
//			// TODO Auto-generated catch block
//			System.out.print(e.getMessage()) ;
//		}
//        Clip clip = null;
//		try {
//			clip = AudioSystem.getClip();
//		} catch (LineUnavailableException e) {
//			// TODO Auto-generated catch block
//			System.out.print(e.getMessage()) ;
//		}
//        try {
//			clip.open(ais);
//		} catch (LineUnavailableException | IOException e) {
//			// TODO Auto-generated catch block
//			System.out.print(e.getMessage()) ;
//		}
//        clip.setFramePosition(0);
//        clip.start();
		
		
		
		try {
			GameGUI ourlistener = new GameGUI();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
		
//		public void paint( Graphics g )    
//		 {  
//		 for ( int x = 30; x <= 300; x += 30 )
//		 for ( int y = 30; y <= 300; y += 30 ) 
//		 g.drawRect( x, y, 30, 30 );
//
//		 } 
		
		
	
	}
	
	
	
}





//final AudioFileFormat.Type [] types = AudioSystem.getAudioFileTypes();
//for (final AudioFileFormat.Type t : types) {
//    System.out.println("Returning Type : " + t);
//} // End of the for //                
//final String PATH = "C:\\Users\\bbrown\\Downloads\\swing-hacks-examples-20060109\\Ch10-Audio\\75\\soundcloud2.mp3";             
//final File file = new File(PATH);
//final AudioInputStream in = AudioSystem.getAudioInputStream(new BufferedInputStream(new FileInputStream(file)));
//
//AudioInputStream din = null;
//final AudioFormat baseFormat = in.getFormat();
//final AudioFormat decodedFormat = new AudioFormat(AudioFormat.Encoding.PCM_SIGNED,
//        baseFormat.getSampleRate(),
//        16,
//        baseFormat.getChannels(),
//        baseFormat.getChannels() * 2,
//        baseFormat.getSampleRate(),
//        false);
//
//System.out.println("Channels : " + baseFormat.getChannels());                
//din = AudioSystem.getAudioInputStream(decodedFormat, in);        
//rawplay(decodedFormat, din);
//in.close(); 
