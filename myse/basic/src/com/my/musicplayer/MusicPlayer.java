package com.my.musicplayer;

class Player {
	String musicTitle;
	String artist;
	String genre;
	String release;
	int like;
	int volume;
	boolean next;
	
	
	Player() {}
	
	public Player(String musicTitle, String artist, String genre, String release, int like) {
		this(musicTitle, artist, genre, release, like, 0, false);
	}
	
	public Player(String musicTitle, String artist, String genre, String release, int like, int volume, boolean next) {
		this.musicTitle = musicTitle;
		this.artist = artist;
		this.genre = genre;
		this.release = release;
		this.like = like;
		this.volume = volume;
		this.next = next;
	}
	
	public void print () {
		System.out.println("노래제목: " + musicTitle +"아티스트: " + artist + "장르: " + genre + "발매일: " + release);
	}
	
	void volumeUp() {
		volume++;
	}
	
	void volumeDown() {
		volume++;
	}
	
	void nextPlay () {
		next = true;
	}
		
}
public class MusicPlayer {
	public static void main(String[] args) {
		Player music = new Player();
		
		music.nextPlay();
		
		
		
		
		

	}
}	


