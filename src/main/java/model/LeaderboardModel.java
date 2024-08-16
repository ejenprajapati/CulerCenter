package model;


	public class LeaderboardModel {
	    private String playerNumber;
	    private String playerName;
	    private String position;
	    private double averageRating;
	    private int age;

	    // Constructor
	    public LeaderboardModel(String playerNumber, String playerName, String position, double averageRating, int age) {
	        this.playerNumber = playerNumber;
	        this.playerName = playerName;
	        this.position = position;
	        this.averageRating = averageRating;
	        this.age= age;
	    }

	    // Getters and setters
	    public String getPlayerNumber() {
	        return playerNumber;
	    }

	    public void setPlayerNumber(String playerNumber) {
	        this.playerNumber = playerNumber;
	    }

	    public String getPlayerName() {
	        return playerName;
	    }

	    public void setPlayerName(String playerName) {
	        this.playerName = playerName;
	    }

	    public String getPosition() {
	        return position;
	    }

	    public void setPosition(String position) {
	        this.position = position;
	    }

	    public double getAverageRating() {
	        return averageRating;
	    }

	    public void setAverageRating(double averageRating) {
	        this.averageRating = averageRating;
	    }
	    public int getAge() {
	        return age;
	    }

	    public void setAge(int age) {
	        this.age = age;
	    }
	    
}
