package org.example;

public class GameAudioEngine extends GameEngine{
    @Override
    public void setupWindow() {

    }

    @Override
    public void update(double dt) {

    }

    @Override
    public void paintComponent() {

    }
    // 实现GameEngine的抽象方法
    @Override
    public AudioClip loadAudio(String filename) {
        // 加载音频文件的实现
        return null;
    }

    // 实现播放音频的方法
    public void playMusic(String filename, float volume) {
        AudioClip audioClip = loadAudio(filename);
        if (audioClip != null) {
            playAudio(audioClip, volume);
        } else {
            System.out.println("Failed to play music: audioClip is null");
        }
    }


}
