/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.irest.playSound;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import javazoom.jl.player.Player;
/**
 *
 * @author palashs
 */
public class Sound_mp3 {

    private String filename;
    private Player player;

    // constructor that takes the name of an MP3 file
    public Sound_mp3(String filename) {
        this.filename = filename;
    }

    public void close() {
        if (player != null) {
            player.close();
        }
    }

    // play the MP3 file to the sound card
    public void play() {
        try {
            FileInputStream fis = new FileInputStream(filename);
            BufferedInputStream bis = new BufferedInputStream(fis);
            player = new Player(bis);
        } catch (Exception e) {
            System.out.println("Problem playing file " + filename);
            System.out.println(e);
        }

        // run in new thread to play in background
        new Thread() {
            public void run() {
                try {
                    player.play();
                } catch (Exception e) {
                    System.out.println(e);
                }
            }
        }.start();

    }

    // test client
    public static void main(String[] args) {
        String filename = "C:\\Users\\Public\\Music\\Sample Music\\Sleep Away.mp3";
        Sound_mp3 mp3 = new Sound_mp3(filename);
        mp3.play();

        // do whatever computation you like, while music plays
        int N = 4000;
        double sum = 0.0;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                sum += Math.sin(i + j);
            }
        }
        System.out.println("sum   " + sum);

        // when the computation is done, stop playing it
        mp3.close();

        // play from the beginning
        mp3 = new Sound_mp3(filename);
        mp3.play();

    }
}
