import java.io.File;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

public class Sound {
    public void playSound(int index) {
        String[] soundFile = {"src/sounds/Click.wav","src/sounds/WrongBuzz.wav","src/sounds/RightBuzz.wav"};
        try {
//            String soundFile = "src/Click.wav";
            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(new File(soundFile[index]).getAbsoluteFile());
            Clip clip = AudioSystem.getClip();
            clip.open(audioInputStream);
            clip.start();
        }catch(Exception e){
            e.printStackTrace();
        }
    }
}
