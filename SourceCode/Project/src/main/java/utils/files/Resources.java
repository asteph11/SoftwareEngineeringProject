package utils.files;

import javax.imageio.ImageIO;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.HashMap;
import java.util.Map;


/**
 * Will contain references to all resources stored. Id's will be granted to all resources, grouped based on directory
 * type and named based on the file name
 */
public class Resources {

    private final Map<String, BufferedImage> imagesFiles = new HashMap<>();
    private final Map<String, Clip> audioFiles = new HashMap<>();
    private final Map<String, File> textFiles = new HashMap<>();

    public Resources() {
    }

    public void init() {

        loadImageFiles();
        loadAudioFiles();
        loadTextFiles();

        System.out.println(this);

    }


    public void loadImageFiles() {
        //TODO : Create list of files with image resource names instead of this hardcoding
        String[] fileNames = {
                "testbutton.png",
                "avatar.png",
                "backgroundImage.png"};

        for(String fileName : fileNames) {
            imagesFiles.put(fileName.split("\\.")[0], loadImageFile(fileName));
        }

        for(String fileName: fileNames) {
            if(imagesFiles.get(fileName) != null) {
                System.out.println(imagesFiles.get(fileName.split("\\.")[0]).getWidth());
            }
        }
    }

    public BufferedImage loadImageFile(String fileName) {
        InputStream resourceBuff = Resources.class.getResourceAsStream("/images/" + fileName);
        try {
            if(resourceBuff != null) {
                return ImageIO.read(resourceBuff);

            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }



    public void loadAudioFiles() {
        //TODO : Create list of files with image resource names instead of this hardcoding
        String[] fileNames = {
                "buttonclick.wav"
        };

        for(String fileName: fileNames) {
            String rawName = fileName.split("\\.")[0];
            audioFiles.put(rawName, loadAudioFile(fileName));
        }

        for(String fileName: fileNames) {
            if(audioFiles.get(fileName) != null) {
                System.out.println(audioFiles.keySet());
            }
        }

    }

    public Clip loadAudioFile(String fileName) {

        Clip clip = null;

        InputStream resourceBuff = Resources.class.getResourceAsStream("/audio/" + fileName);

        if (resourceBuff != null) {
            try {
                clip = AudioSystem.getClip();
            } catch (LineUnavailableException e) {
                e.printStackTrace();
            }
        }

        return clip;
    }


    public void loadTextFiles() {
        //TODO : Create list of files with image resource names instead of this hardcoding
        String[] fileNames = {
                "SaveData.xml"
        };

        for(String fileName: fileNames) {
            textFiles.put(fileName, loadTextFile(fileName));
        }

    }

    public File loadTextFile(String fileName) {

        if (fileName == null) {
            throw new NullPointerException("File name cannot be null");
        }

        return new File(fileName);
    }

    public String toString() {
        String s = "Image Files:\n";
        for(String key: imagesFiles.keySet()) {
            s += "\t" + key + " " + (imagesFiles.get(key) != null) + "\n";
        }

        s += "Audio Files:\n";
        for(String key: audioFiles.keySet()) {
            s += "\t" + key + " " + (audioFiles.get(key) != null) + "\n";
        }
        s += "Text Files:\n";
        for(String key: textFiles.keySet()) {
            s += "\t" + key + " " + (textFiles.get(key) != null) + "\n";
        }

        return s;
    }

}
