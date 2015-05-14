package modele;
import java.io.File;
import java.io.IOException;
import java.net.URL;

import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.DataLine;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

public class SoundClip {
	
	private Clip clip;
	
	public SoundClip() {	
		
		try
		{
			URL url = this.getClass().getClassLoader().getResource("pushing_onwards.wav");
			AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(url);
			AudioFormat format = audioInputStream.getFormat();
			DataLine.Info info = new DataLine.Info(Clip.class, format);
			this.clip = (Clip)AudioSystem.getLine(info);
			clip.open(audioInputStream);
		} 	catch (UnsupportedAudioFileException e) {
	         e.printStackTrace();
		} catch (IOException e) {
	         e.printStackTrace();
		} catch (LineUnavailableException e) {
	         e.printStackTrace();
		}
	}

	public Clip getClip() {
		return clip;
	}
	
	public void setClip(String nomMusique)
	{
		try
		{
			URL url = this.getClass().getClassLoader().getResource(nomMusique);
			AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(url);
			AudioFormat format = audioInputStream.getFormat();
			DataLine.Info info = new DataLine.Info(Clip.class, format);
			this.clip = (Clip)AudioSystem.getLine(info);
			clip.open(audioInputStream);
		} 	catch (UnsupportedAudioFileException e) {
	         e.printStackTrace();
		} catch (IOException e) {
	         e.printStackTrace();
		} catch (LineUnavailableException e) {
	         e.printStackTrace();
		}
	}
}
