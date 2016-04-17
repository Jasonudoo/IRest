/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.irest.tools;

import com.irest.dialogue.ErrorDialoge;
import com.irest.main.Irest_Main;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.net.URL;
import java.text.CharacterIterator;
import java.text.StringCharacterIterator;
import javax.imageio.ImageIO;

/**
 *
 * @author palashs
 */
public class Tools {

    public BufferedImage[] loadImages() {
        try {
            String filePath = "/com/irest/image/";
            String[] fileNames = {
                "1.jpg", "2.jpg", "4.jpg", "5.jpg", "6.jpg", "7.jpg", "8.jpg", "9.jpg", "10.jpg", "11.jpg", "12.jpg", "13.jpg", "14.jpg",
                "15.jpg", "16.jpg", "17.jpg", "18.jpg", "19.jpg", "20.jpg", "21.jpg", "22.jpg", "23.jpg", "24.jpg", "25.jpg"};
            BufferedImage[] images = new BufferedImage[fileNames.length];
            for (int j = 0; j < images.length; j++) {
                try {
                    URL url = getClass().getResource(filePath + fileNames[j]);
                    images[j] = ImageIO.read(url);
                } catch (Exception mue) {
                    mue.printStackTrace();
                }
            }
            return images;
        } catch (Exception e) {

            e.printStackTrace();
            return null;
        }
    }

    public static String convertMillsToString(long millTime) {
        String timers = "";
        long times = millTime;
        times = times / 1000;
        long sec = 0, min = 0, hrs = 0;
        sec = times % 60;
        min = times / 60;
        if (min > 60) {
            hrs = min / 60;
            min = min % 60;
        } else {
            hrs = 00;
        }
        String hrs1 = "";
        String min1 = "";
        String sec1 = "";

        hrs1 = pad(hrs);
        min1 = pad(min);
        sec1 = pad(sec);
        timers = hrs1 + ":" + min1 + ":" + sec1;
        return timers;
    }

    public static String pad(long value) {
        String returnValue = "" + value;
        if ((value) < 10) {
            returnValue = "0" + value;
        }
        return returnValue;
    }

    public static String backlashReplace(String myStr) {
        final StringBuilder result = new StringBuilder();
        final StringCharacterIterator iterator = new StringCharacterIterator(myStr);
        char character = iterator.current();
        while (character != CharacterIterator.DONE) {
            if (character == '\\') {
                result.append("/");
            } else {
                result.append(character);
            }
            character = iterator.next();
        }
        return result.toString();
    }

    public static void creteConfigFile() {
        try {
            File fs = new File(Constants.configFoldwrPath + "/config.txt");
            FileWriter fw = new FileWriter(fs);
            PrintWriter pw = new PrintWriter(fw);
            pw.println(Constants.schedule1);
            pw.println(Constants.schedule2);
            pw.println(Constants.schedule3);
            pw.println(Constants.schedule4);
            pw.println(Constants.schedule5);
            pw.println(Constants.schedule6);
            pw.println(Constants.schedule7);
            pw.flush();
            pw.close();
            fw.close();
        } catch (Exception e) {
        }
    }

    public static void read() {
        try {
            String line = "";
            int temp = 0;
            File fs = new File(Constants.configFoldwrPath + "/config.txt");
            if (fs.exists()) {
                BufferedReader br = new BufferedReader(new FileReader(Constants.configFoldwrPath + "/config.txt"));
                while ((line = br.readLine()) != null) {
                    temp++;
                    switch (temp) {
                        case 1:
                            Constants.timeMicroBreak = Long.parseLong(line);
                            break;
                        case 2:
                            Constants.microDuration = Long.parseLong(line);
                            break;
                        case 3:
                            Constants.numberOfMicros = Long.parseLong(line);
                            break;
                        case 4:
                            Constants.midDuration = Long.parseLong(line);
                            break;
                        case 5:
                            Constants.numberOfMids = Long.parseLong(line);
                            break;
                        case 6:
                            Constants.longDuration = Long.parseLong(line);
                            break;
                        case 7:
                            Constants.schedule7 = (line);
                            break;
                    }
                }
                br.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void createFolder() {
        try {
            boolean temp = true;
            Constants.configFoldwrPath = backlashReplace(((System.getenv("USERPROFILE")) + ("\\My Documents\\")));
            System.out.println(Constants.configFoldwrPath);

            Constants.configFoldwrPath = Constants.configFoldwrPath + "/IREST";
            File dir = new File(Constants.configFoldwrPath);
            if (!dir.exists()) {
                temp = dir.mkdir();
            }
            if (!temp) {
                ErrorDialoge.showError("Unable to create config file folder");
            }
        } catch (Exception e) {

        }
    }
}
