package controller.database;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;

import model.CulerModel;
import model.LeaderboardModel;
import model.LoginModel;
import model.PasswordEncryptionWithAes;
import model.PlayerModel;

import utils.StringUtils;

public class DBController {

	public Connection getConnection() throws SQLException, ClassNotFoundException {
		Class.forName(StringUtils.DRIVER_NAME);
		return DriverManager.getConnection(StringUtils.LOCALHOST_URL, StringUtils.LOCALHOST_USERNAME,
				StringUtils.LOCALHOST_PASSWORD);
	}

	public int registerCuler(CulerModel culer) {
		try {
			PreparedStatement stmt = getConnection().prepareStatement(StringUtils.QUERY_REGISTER_CULER);
			//full_name, username, email, phone_number , birthday, gender,password, user_id, is_admin
			stmt.setString(1, culer.getFull_Name());
			stmt.setString(2, culer.getUsername());
			stmt.setString(3, culer.getEmail());
			stmt.setString(4, culer.getPhoneNumber());
			stmt.setDate(5, Date.valueOf(culer.getBirthday()));
			stmt.setString(6, culer.getGender());
			stmt.setString(7,  PasswordEncryptionWithAes.encrypt(culer.getUsername(),culer.getPassword()));
			stmt.setString(8, culer.getImageUrlFromPart());
			
			
			
			

			// Statement Run
			int result = stmt.executeUpdate();

			if (result > 0) {
				return 1;
			} else {
				return 0;
			}

		} catch (ClassNotFoundException | SQLException ex) {
			ex.printStackTrace();
			return -1;
		}
	}
	
	public int getCulerLoginInfo(LoginModel loginModel) {
		try {
			PreparedStatement st = getConnection().prepareStatement(StringUtils.QUERY_LOGIN_USER_CHECK);
			st.setString(1, loginModel.getUsername());
			ResultSet result = st.executeQuery();
			
			
			
			if (result.next()) {
				// User name and password match in the database
				String userDb = result.getString(StringUtils.USER_NAME);
				String encryptedPwd = result.getString(StringUtils.PASSWORD);
				String decryptedPwd = PasswordEncryptionWithAes.decrypt(encryptedPwd, userDb);
				System.out.print(userDb + decryptedPwd);
				
				// Check if the username and password match the credentials from the database
				if (userDb.equals(loginModel.getUsername()) && decryptedPwd.equals(loginModel.getPassword())) {
					// Login successful, return 1
					return 1;
				} else {
					// Username or password mismatch, return 0
					return 0;
				}
			} else {
				// Username not found in the database, return -1
				return -1;
			}
		} catch (SQLException | ClassNotFoundException ex) {
			ex.printStackTrace(); // Log the exception for debugging
			return -2;
		}
	}
	public ArrayList<CulerModel> getAllCulersInfo() {
		try {
			PreparedStatement stmt = getConnection().prepareStatement(StringUtils.QUERY_GET_ALL_CULER);
			ResultSet result = stmt.executeQuery();

			ArrayList<CulerModel> culers = new ArrayList<CulerModel>();

			while (result.next()) {
				CulerModel culer = new CulerModel();
				culer.setFull_Name(result.getString("full_name"));
				
				culer.setBirthday(result.getDate("birthday").toLocalDate());
				culer.setEmail(result.getString("email"));
				culer.setGender(result.getString("gender"));
				culer.setPhoneNumber(result.getString("phone_number"));
				
				culer.setImageUrlFromDB(result.getString("imageUrl"));
				culer.setUsername(result.getString("username"));				
				culers.add(culer);
			}
			return culers;
		} catch (SQLException | ClassNotFoundException ex) {
			ex.printStackTrace();
			return null;
		}
	}
	public ArrayList<PlayerModel> getAllPlayersInfo() {
		try {
			PreparedStatement stmt = getConnection().prepareStatement(StringUtils.QUERY_GET_ALL_PLAYERS);
			ResultSet result = stmt.executeQuery();

			ArrayList<PlayerModel> players = new ArrayList<PlayerModel>();

			while (result.next()) {
				PlayerModel player = new PlayerModel();
				player.setPlayer_number(result.getString("player_number"));
				
				player.setAge(result.getString("age"));
				player.setPlayer_name(result.getString("player_name"));
				player.setNationality(result.getString("nationality"));
				player.setPosition(result.getString("position"));
				
				player.setImageUrlFromDB(result.getString("playerImageUrl"));
				player.setAppearance(result.getString("appearances"));		
				player.setGoals(result.getString("goals"));	
				player.setAssist(result.getString("assists"));	
				
				players.add(player);
				
			}
			return players;
		} catch (SQLException | ClassNotFoundException ex) {
			ex.printStackTrace();
			return null;
		}
	}
	// Method to fetch session details by ID from the database
		 public PlayerModel getPlayerByNumber(String playerNumber) throws ClassNotFoundException {

		        ResultSet result = null;
				PlayerModel player =new PlayerModel();
				try {
		            // Prepare SQL query
		            String query = "SELECT * FROM player WHERE player_number = ?";
		         // Prepare a statement using the predefined query for login check
			        PreparedStatement st = getConnection().prepareStatement(query);

			        // Set the sesssionId in the first parameter of the prepared statement
			        st.setString(1,playerNumber);
		           
		            // Execute query
		            result = st.executeQuery();

		            // Process result set
		            if (result.next()) {
		                // Extract session details from result set
						/*
						 * String player_number= result.getString("player_number"); String player_name =
						 * result.getString("player_name"); String age = result.getString("age"); String
						 * nationality = result.getString("nationality"); String position =
						 * result.getString("position"); String appearances =
						 * result.getString("appearances"); String goals = result.getString("goals");
						 * String assists = result.getString("assists"); String image =
						 * result.getString("playerImageUrl");
						 */
		                // Extract other session details as needed
		                
		                // Create a new SessionModel object
		                
		                player.setPlayer_number(result.getString("player_number"));						
						player.setAge(result.getString("age"));
						player.setPlayer_name(result.getString("player_name"));
						player.setNationality(result.getString("nationality"));
						player.setPosition(result.getString("position"));						
						player.setImageUrlFromDB(result.getString("playerImageUrl"));
						player.setAppearance(result.getString("appearances"));		
						player.setGoals(result.getString("goals"));	
						player.setAssist(result.getString("assists"));	
		            }
		        } catch (SQLException e) {
		            // Handle SQL exception
		            e.printStackTrace();
		        } finally {
		            // Close resources (ResultSet, Statement)
		            if (result!= null) {
		                try {
		                    result.close();
		                } catch (SQLException e) {
		                    e.printStackTrace();
		                }
		            }
		            
		        }

		        return player;
		    }
		 public int updatePlayerDetails(PlayerModel player) {
				try {
					String imageUrl = player.getImageUrlFromPart();
			        String query;
			        if(!(imageUrl.isEmpty())) {
			            query = "UPDATE PLAYER SET player_name=?, age=?, nationality=?, position=?, appearances=?, goals=?, assists=?, playerImageUrl=? WHERE player_number=?";
			        } else {
			            query = "UPDATE PLAYER SET player_name=?, age=?, nationality=?, position=?, appearances=?, goals=?, assists=? WHERE player_number=?";
			        }

			        PreparedStatement stmt = getConnection().prepareStatement(query);
			        stmt.setString(1, player.getPlayer_name());
			        stmt.setString(2, player.getAge());
			        stmt.setString(3, player.getNationality());
			        stmt.setString(4, player.getPosition());
			        stmt.setString(5, player.getAppearance());
			        stmt.setString(6, player.getGoals());
			        stmt.setString(7, player.getAssist());

			        if (!imageUrl.isEmpty()) {
			            stmt.setString(8, player.getImageUrlFromPart());
			            stmt.setString(9, player.getPlayer_number());
			        } else {
			            stmt.setString(8, player.getPlayer_number());
			        }

			        int result = stmt.executeUpdate();

			        if (result > 0) {
			            return 1;
			        } else {
			            return 0;
			        }
			 
				}catch (SQLException | ClassNotFoundException ex) {
					ex.printStackTrace();
					return -2;
				}
		 }
		 public int registerPlayer(PlayerModel player) {
				try {
					
					String query="INSERT INTO player (player_name, player_number, age, nationality,position ,appearances, goals,assists,playerImageUrl) VALUES (?, ?, ?, ?, ?, ?, ?, ?,?)";
					PreparedStatement stmt = getConnection().prepareStatement(query);
					//full_name, username, email, phone_number , birthday, gender,password, user_id, is_admin
					stmt.setString(1, player.getPlayer_name());
					stmt.setString(2, player.getPlayer_number());
					stmt.setString(3, player.getAge());
					stmt.setString(4, player.getNationality());
					stmt.setString(5, player.getPosition());
					stmt.setString(6, player.getAppearance());
					stmt.setString(7, player.getGoals());
					stmt.setString(8, player.getAssist());
					stmt.setString(9, player.getImageUrlFromPart());
					
					
					
					

					// Statement Run
					int result = stmt.executeUpdate();

					if (result > 0) {
						return 1;
					} else {
						return 0;
					}

				} catch (ClassNotFoundException | SQLException ex) {
					ex.printStackTrace();
					return -1;
				}
			}
		 public int getUserCount() {
			    int userCount = 0;
			    Connection connection = null;
			    PreparedStatement stmt = null;
			    ResultSet resultSet = null;
			    
			    try {
			        // Retrieve database connection
			        connection = getConnection(); // Assuming getConnection() method returns a valid connection
			        
			        // Prepare SQL query to count users
			        String query = "SELECT COUNT(*) AS user_count FROM culer_user";
			        stmt = connection.prepareStatement(query);
			        
			        // Execute query
			        resultSet = stmt.executeQuery();
			        
			        // Retrieve user count from result set
			        if (resultSet.next()) {
			            userCount = resultSet.getInt("user_count");
			        }
			        
			    } catch (ClassNotFoundException | SQLException ex) {
			        ex.printStackTrace();
			        // Handle exception
			        // Returning -1 as error code might not be appropriate here
			        // Consider throwing an exception or returning a meaningful error code
			        return -1;
			    } finally {
			        // Close result set, statement, and connection
			        try {
			            if (resultSet != null) resultSet.close();
			            if (stmt != null) stmt.close();
			            if (connection != null) connection.close();
			        } catch (SQLException e) {
			            e.printStackTrace();
			            // Handle exception
			        }
			    }
			    
			    return userCount;
			}

		    // Add method to retrieve player count
		 public int getPlayerCount() {
			    int playerCount = 0;
			    Connection connection = null;
			    PreparedStatement stmt = null;
			    ResultSet resultSet = null;

			    try {
			        // Retrieve database connection
			        connection = getConnection(); // Assuming getConnection() method returns a valid connection

			        // Prepare SQL query to count players
			        String query = "SELECT COUNT(*) AS player_count FROM player";
			        stmt = connection.prepareStatement(query);

			        // Execute query
			        resultSet = stmt.executeQuery();

			        // Retrieve player count from result set
			        if (resultSet.next()) {
			            playerCount = resultSet.getInt("player_count");
			        }

			    } catch (ClassNotFoundException | SQLException ex) {
			        ex.printStackTrace();
			        // Handle exception
			        // Returning -1 as error code might not be appropriate here
			        // Consider throwing an exception or returning a meaningful error code
			        return -1;
			    
			    }

			    return playerCount;
			}
		 public int deletePlayerInfo(String player_number) {
			 PreparedStatement checkRatingsStmt = null;
			    PreparedStatement deleteRatingsStmt = null;
			    PreparedStatement deletePlayerStmt = null;
			    ResultSet rs = null;
			 try {
				 String checkRatingsQuery = "SELECT 1 FROM RATINGS WHERE player_number=?";
			        checkRatingsStmt = getConnection().prepareStatement(checkRatingsQuery);
			        checkRatingsStmt.setString(1, player_number);
			        rs = checkRatingsStmt.executeQuery();
			        
			            String query = "DELETE FROM PLAYER WHERE player_number=?";
			            if (rs.next()) {
			                String deleteRatingsQuery = "DELETE FROM RATINGS WHERE player_number=?";
			                deleteRatingsStmt = getConnection().prepareStatement(deleteRatingsQuery);
			                deleteRatingsStmt.setString(1, player_number);
			                deleteRatingsStmt.executeUpdate();
			            }
			            	
			            String deletePlayerQuery = "DELETE FROM PLAYER WHERE player_number=?";
			            deletePlayerStmt = getConnection().prepareStatement(deletePlayerQuery);
			            deletePlayerStmt.setString(1, player_number);
			            int result = deletePlayerStmt.executeUpdate();
			       		       

			        

			        if (result > 0) {
			            return 1;
			        } else {
			            return 0;
			        }
			 
				}catch (SQLException | ClassNotFoundException ex) {
					ex.printStackTrace();
					return -2;
				}
		 }
		 public int savePlayerRating(String username, String player_number, String rating) {
			    try {
			        // Check if the user has already voted for the player
			        if (checkUserPlayerCombinationExistence(username, player_number)) {
			        	System.out.print("inside update");
			            // If the combination exists, get the old vote
			            String selectQuery = "SELECT rating FROM RATINGS WHERE username = ? AND player_number = ?";
			            PreparedStatement selectStmt = getConnection().prepareStatement(selectQuery);
			            selectStmt.setString(1, username);
			            selectStmt.setString(2, player_number);
			            ResultSet rs = selectStmt.executeQuery();

			            if (rs.next()) {
			                float oldRating = (float)rs.getInt("rating");
			                float newRating = Float.parseFloat(rating);
			                float averageRating = (oldRating + newRating) / 2;

			                // Update the rating with the average vote
			                String updateQuery = "UPDATE RATINGS SET rating = ? WHERE username = ? AND player_number = ?";
			                PreparedStatement updateStmt = getConnection().prepareStatement(updateQuery);
			                updateStmt.setString(1, String.valueOf(averageRating));
			                updateStmt.setString(2, username);
			                updateStmt.setString(3, player_number);

			                int result = updateStmt.executeUpdate();

			                if (result > 0) {
			                    return 1; // Successful update
			                } else {
			                    return 0; // Update failed
			                }
			            }
			        } else {
			            // If the combination does not exist, insert a new row
			            String insertQuery = "INSERT INTO RATINGS (username, player_number, rating) VALUES (?, ?, ?)";
			            PreparedStatement insertStmt = getConnection().prepareStatement(insertQuery);
			            insertStmt.setString(1, username);
			            insertStmt.setString(2, player_number);
			            insertStmt.setString(3, rating);

			            int result = insertStmt.executeUpdate();

			            if (result > 0) {
			                return 1; // Successful insertion
			            } else {
			                return 0; // Insertion failed
			            }
			        }
			    } catch (SQLException | ClassNotFoundException ex) {
			        ex.printStackTrace();
			        return -2; // Error
			    }
			    return 0; // Error
			}
		 public boolean checkUserPlayerCombinationExistence(String username, String player_number) {
			    try {
			        String query = "SELECT COUNT(*) FROM RATINGS WHERE username = ? AND player_number = ?";
			        PreparedStatement stmt = getConnection().prepareStatement(query);
			        stmt.setString(1, username);
			        stmt.setString(2, player_number);
			        ResultSet rs = stmt.executeQuery();

			        if (rs.next()) {
			            int count = rs.getInt(1);
			            return count > 0; // Return true if the combination exists, false otherwise
			        } else {
			            return false; // Error occurred
			        }
			    } catch (SQLException | ClassNotFoundException ex) {
			        ex.printStackTrace();
			        return false; // Error
			    }
			}
		 public ArrayList<LeaderboardModel> getLeaderboard() {
			    ArrayList<LeaderboardModel> leaderboard = new ArrayList<>();

			    try {
			        Connection conn = getConnection(); // Establish database connection
			        String query = "SELECT p.player_number, p.player_name, p.position, p.age, AVG(r.rating) AS average_rating " +
			                       "FROM Player p " +
			                       "JOIN Ratings r ON p.player_number = r.player_number " +
			                       "GROUP BY p.player_number " +
			                       "ORDER BY average_rating DESC";
			        PreparedStatement stmt = conn.prepareStatement(query);
			        ResultSet rs = stmt.executeQuery();

			        while (rs.next()) {
			            String playerNumber = rs.getString("player_number");
			            String playerName = rs.getString("player_name");
			            String position = rs.getString("position");
			            int age= rs.getInt("age");
			            double averageRating = rs.getDouble("average_rating");

			            // Create Player object and add it to the leaderboard list
			            LeaderboardModel player = new LeaderboardModel(playerNumber, playerName,position, averageRating, age);
			            leaderboard.add(player);
			        }

			        // Close resources
			        rs.close();
			        stmt.close();
			        conn.close();
			    } catch (SQLException | ClassNotFoundException ex) {
			        ex.printStackTrace();
			        // Handle exceptions
			    }

			    return leaderboard;
			}
		 public int getTotalInteractions() {
			    int count = 0;
			    Connection connection = null;
			    PreparedStatement stmt = null;
			    ResultSet resultSet = null;

			    try {
			        // Retrieve database connection
			        connection = getConnection(); // Assuming getConnection() method returns a valid connection

			        // Prepare SQL query to count players
			        String query = "SELECT COUNT(*) AS interactions FROM ratings";
			        stmt = connection.prepareStatement(query);

			        // Execute query
			        resultSet = stmt.executeQuery();

			        // Retrieve player count from result set
			        if (resultSet.next()) {
			            count = resultSet.getInt("interactions");
			        }

			    } catch (ClassNotFoundException | SQLException ex) {
			        ex.printStackTrace();
			        // Handle exception
			        // Returning -1 as error code might not be appropriate here
			        // Consider throwing an exception or returning a meaningful error code
			        return -1;
			    
			    }

			    return count;
			}
		 public ArrayList<LeaderboardModel> getTopFiveRated() {
			    ArrayList<LeaderboardModel> leaderboard = new ArrayList<>();

			    try {
			        Connection conn = getConnection(); // Establish database connection
			        String query = "SELECT p.player_number, p.player_name, p.position, p.age, AVG(r.rating) AS average_rating " +
			                       "FROM Player p " +
			                       "JOIN Ratings r ON p.player_number = r.player_number " +
			                       "GROUP BY p.player_number " +
			                       "ORDER BY average_rating DESC LIMIT 5";
			        PreparedStatement stmt = conn.prepareStatement(query);
			        ResultSet rs = stmt.executeQuery();

			        while (rs.next()) {
			            String playerNumber = rs.getString("player_number");
			            String playerName = rs.getString("player_name");
			            String position = rs.getString("position");
			            int age= rs.getInt("age");
			            double averageRating = rs.getDouble("average_rating");

			            // Create Player object and add it to the leaderboard list
			            LeaderboardModel player = new LeaderboardModel(playerNumber, playerName,position, averageRating, age);
			            leaderboard.add(player);
			        }

			        // Close resources
			        rs.close();
			        stmt.close();
			        conn.close();
			    } catch (SQLException | ClassNotFoundException ex) {
			        ex.printStackTrace();
			        // Handle exceptions
			    }

			    return leaderboard;
			}
		 public CulerModel getCulerDetails(String username) {
				try (Connection con = getConnection()) {
					PreparedStatement st = con.prepareStatement(StringUtils.QUERY_LOGIN_USER_CHECK);
					st.setString(1, username);
					ResultSet result = st.executeQuery();
					CulerModel culerInfo = new CulerModel();
					while (result.next()) {

						culerInfo.setUsername(result.getString(StringUtils.USERNAME));
						culerInfo.setPhoneNumber(result.getString("phone_number"));
						culerInfo.setFull_Name(result.getString("full_name"));
						culerInfo.setGender(result.getString(StringUtils.GENDER));
						culerInfo.setBirthday(result.getDate(StringUtils.BIRTHDAY).toLocalDate());
						culerInfo.setImageUrlFromDB(result.getString("imageUrl"));
						culerInfo.setEmail(result.getString("email"));
					}
					return culerInfo;
				} catch (SQLException | ClassNotFoundException ex) {
					ex.printStackTrace();
					return null;

				}
			}
		 public ArrayList<PlayerModel> getSearchPlayerInfo(String searchValue) {
			 try {
				 PreparedStatement stmt = getConnection().prepareStatement("SELECT * FROM PLAYER WHERE LOWER(player_name) LIKE ?");
				 stmt.setString(1, "%" + searchValue.toLowerCase() + "%");
					ResultSet result = stmt.executeQuery();

					ArrayList<PlayerModel> players = new ArrayList<PlayerModel>();

					while (result.next()) {
						PlayerModel player = new PlayerModel();
						player.setPlayer_number(result.getString("player_number"));
						
						player.setAge(result.getString("age"));
						player.setPlayer_name(result.getString("player_name"));
						player.setNationality(result.getString("nationality"));
						player.setPosition(result.getString("position"));
						
						player.setImageUrlFromDB(result.getString("playerImageUrl"));
						player.setAppearance(result.getString("appearances"));		
						player.setGoals(result.getString("goals"));	
						player.setAssist(result.getString("assists"));	
						
						players.add(player);
						
					}
					return players;
				} catch (SQLException | ClassNotFoundException ex) {
					ex.printStackTrace();
					return null;
				}
		 }
		 public boolean isUsernameTaken(String username) {
		        boolean usernameTaken = false;
		        String query = "SELECT COUNT(*) FROM culer_user WHERE username = ?";
		        
		        try (Connection conn = getConnection();
		             PreparedStatement stmt = conn.prepareStatement(query)) {
		            stmt.setString(1, username);
		            try (ResultSet rs = stmt.executeQuery()) {
		                if (rs.next()) {
		                    int count = rs.getInt(1);
		                    // If count is greater than 0, it means username is already taken
		                    usernameTaken = count > 0;
		                }
		            }
		        } catch (SQLException | ClassNotFoundException e) {
		            e.printStackTrace(); // Handle the exception appropriately
		        }
		        
		        return usernameTaken;
		    }
		 public boolean isJerseyNumberTaken(String player_number) {
		        boolean usernameTaken = false;
		        String query = "SELECT COUNT(*) FROM player WHERE player_number = ?";
		        
		        try (Connection conn = getConnection();
		             PreparedStatement stmt = conn.prepareStatement(query)) {
		            stmt.setString(1, player_number);
		            try (ResultSet rs = stmt.executeQuery()) {
		                if (rs.next()) {
		                    int count = rs.getInt(1);
		                    // If count is greater than 0, it means username is already taken
		                    usernameTaken = count > 0;
		                }
		            }
		        } catch (SQLException | ClassNotFoundException e) {
		            e.printStackTrace(); // Handle the exception appropriately
		        }
		        
		        return usernameTaken;
		    }
		 public int updateCulerDetails(String fullName, String username, String phoneNumber, String imageUrl, String email,LocalDate birthday) {
				try {
					
			        String query;
			        if(!(imageUrl.isEmpty())) {
			            query = "UPDATE culer_user SET full_name=?, birthday=?, email=?, phone_number=?, imageUrl=? WHERE username=?";
			        } else {
			            query = "UPDATE culer_user SET full_name=?, birthday=?,email=?, phone_number=? WHERE username=?";
			        }

			        PreparedStatement stmt = getConnection().prepareStatement(query);
			        stmt.setString(1,fullName);
			        
			        stmt.setDate(2, Date.valueOf(birthday));
			        stmt.setString(3, email);
			        stmt.setString(4, phoneNumber);
			       

			        if (!imageUrl.isEmpty()) {
			            stmt.setString(5, imageUrl);
			            stmt.setString(6, username);
			        } else {
			            stmt.setString(5, username);
			        }

			        int result = stmt.executeUpdate();

			        if (result > 0) {
			            return 1;
			        } else {
			            return 0;
			        }
			 
				}catch (SQLException | ClassNotFoundException ex) {
					ex.printStackTrace();
					return -2;
				}
		 }
}
