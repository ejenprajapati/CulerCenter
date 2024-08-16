package model;

import java.io.File;


import javax.servlet.http.Part;

import utils.StringUtils;

public class PlayerModel {
private String player_number;	
	
	private String player_name;
	private String age;
	private String nationality;
	private String position;
	private String appearance;
	private String goals;
	private String assist;
	private String imageUrlFromPart;
	
	public PlayerModel() {
		
	}
	/**
	 * @param player_number
	 * @param birthday
	 * @param player_name
	 * @param age
	 * @param nationality
	 * @param position
	 * @param appearance
	 * @param goals
	 * @param assist
	 * @param imageUrlFromPart
	 */
	public PlayerModel(String player_number, String player_name, String age, String nationality,
			String position, String appearance, String goals, String assist, Part imagePart) {
		super();
		this.player_number = player_number;		
		this.player_name = player_name;
		this.age = age;
		this.nationality = nationality;
		this.position = position;
		this.appearance = appearance;
		this.goals = goals;
		this.assist = assist;
		this.imageUrlFromPart = getImageUrl(imagePart);
	}
	/**
	 * @return the player_number
	 */
	public String getPlayer_number() {
		return player_number;
	}
	/**
	 * @param player_number the player_number to set
	 */
	public void setPlayer_number(String player_number) {
		this.player_number = player_number;
	}
	/**
	 * @return the birthday
	 */
	
	/**
	 * @param birthday the birthday to set
	 */
	
	/**
	 * @return the player_name
	 */
	public String getPlayer_name() {
		return player_name;
	}
	/**
	 * @param player_name the player_name to set
	 */
	public void setPlayer_name(String player_name) {
		this.player_name = player_name;
	}
	/**
	 * @return the age
	 */
	public String getAge() {
		return age;
	}
	/**
	 * @param age the age to set
	 */
	public void setAge(String age) {
		this.age = age;
	}
	/**
	 * @return the nationality
	 */
	public String getNationality() {
		return nationality;
	}
	/**
	 * @param nationality the nationality to set
	 */
	public void setNationality(String nationality) {
		this.nationality = nationality;
	}
	/**
	 * @return the position
	 */
	public String getPosition() {
		return position;
	}
	/**
	 * @param position the position to set
	 */
	public void setPosition(String position) {
		this.position = position;
	}
	/**
	 * @return the appearance
	 */
	public String getAppearance() {
		return appearance;
	}
	/**
	 * @param appearance the appearance to set
	 */
	public void setAppearance(String appearance) {
		this.appearance = appearance;
	}
	/**
	 * @return the goals
	 */
	public String getGoals() {
		return goals;
	}
	/**
	 * @param goals the goals to set
	 */
	public void setGoals(String goals) {
		this.goals = goals;
	}
	/**
	 * @return the assist
	 */
	public String getAssist() {
		return assist;
	}
	/**
	 * @param assist the assist to set
	 */
	public void setAssist(String assist) {
		this.assist = assist;
	}
	/**
	 * @return the imageUrlFromPart
	 */
	public String getImageUrlFromPart() {
		return imageUrlFromPart;
	}
	/**
	 * @param imageUrlFromPart the imageUrlFromPart to set
	 */
	public void setImageUrlFromPart(Part part) {
		this.imageUrlFromPart = getImageUrl(part);
	}

	public void setImageUrlFromDB(String imageUrl) {
		this.imageUrlFromPart = imageUrl;
	}
	/**
	 * @return the full_Name
	 */

	private String getImageUrl(Part part) {
		// Define the directory path to store uploaded user images. This path should be configured elsewhere in the application.
	    String savePath = StringUtils.IMAGE_DIR_PLAYERS;

	    // Create a File object representing the directory to store uploaded images.
	    File fileSaveDir = new File(savePath);

	    // Initialize the variable to store the extracted image file name.
	    String imageUrlFromPart = null;

	    // Check if the directory to store uploaded images already exists.
	    if (!fileSaveDir.exists()) {
	        // If the directory doesn't exist, create it.
	    	// user mkdirs() method to create multiple or nested folder
	        fileSaveDir.mkdir();
	    }

	    // Get the Content-Disposition header from the request part. This header contains information about the uploaded file, including its filename.
	    String contentDisp = part.getHeader("content-disposition");

	    // Split the Content-Disposition header into individual parts based on semicolons.
	    String[] items = contentDisp.split(";");

	    // Iterate through each part of the Content-Disposition header.
	    for (String s : items) {
	        // Check if the current part starts with "filename" (case-insensitive trim is used).
	        if (s.trim().startsWith("filename")) {
	            // Extract the filename from the current part.
	            // The filename is assumed to be enclosed in double quotes (").
	            // This part removes everything before the equal sign (=) and the double quotes surrounding the filename.
	            imageUrlFromPart = s.substring(s.indexOf("=") + 2, s.length() - 1);
	            break; // Exit the loop after finding the filename
	        }
	    }

	    // If no filename was extracted from the Content-Disposition header, set a default filename.
	    if (imageUrlFromPart == null || imageUrlFromPart.isEmpty()) {
	        imageUrlFromPart = "default-player.png";
	    }

	    // Return the extracted or default image file name.
	    return imageUrlFromPart;

}
}
