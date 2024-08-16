package utils;

public class StringUtils {

	// Start: DB Connection
	public static final String DRIVER_NAME = "com.mysql.cj.jdbc.Driver";
	public static final String LOCALHOST_URL = "jdbc:mysql://localhost:3306/culer_center";
	public static final String LOCALHOST_USERNAME = "root";
	public static final String LOCALHOST_PASSWORD = "";
	
	public static final String IMAGE_ROOT_PATH = "\\Eclipse Projects\\CulerCenter\\src\\main\\webapp\\resource\\images\\";
	public static final String IMAGE_DIR_PLAYERS = "E:/" + IMAGE_ROOT_PATH + "players\\";
	public static final String IMAGE_DIR_USER = "E:/" + IMAGE_ROOT_PATH + "user\\";
	// End: DB Connection

	// Start: Queries
	public static final String QUERY_REGISTER_CULER = "INSERT INTO culer_user ("
			+ "full_name, username, email, phone_number , birthday, gender,password,imageUrl) "
			+ "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

	public static final String QUERY_LOGIN_USER_CHECK = "SELECT * FROM culer_user WHERE username = ?";
	public static final String QUERY_GET_ALL_CULER = "SELECT * FROM culer_user";
	public static final String QUERY_GET_ALL_PLAYERS = "SELECT * FROM player";
	// End: Queries

	// Start: Parameter names
	public static final String USERNAME = "username";
	public static final String USER_NAME = "username";
	public static final String FULL_NAME= "fullName";
	public static final String LAST_NAME = "lastName";
	public static final String BIRTHDAY = "birthday";
	public static final String GENDER = "gender";
	public static final String EMAIL = "email";
	public static final String PHONE_NUMBER = "phoneNumber";
	
	public static final String PASSWORD = "password";
	public static final String RETYPE_PASSWORD = "retypePassword";
	public static final String IMAGE = "image";
	// End: Parameter names

	// Start: Validation Messages
	// Register Page Messages
	public static final String MESSAGE_SUCCESS_REGISTER = "Successfully Registered!";
	public static final String MESSAGE_ERROR_REGISTER = "Please correct the form data.";
	public static final String MESSAGE_ERROR_SERVER = "An unexpected server error occurred.";
	public static final String MESSAGE_ERROR_USERNAME = "Username is already registered.";
	public static final String MESSAGE_ERROR_EMAIL = "Email is already registered.";
	public static final String MESSAGE_ERROR_PHONE_NUMBER = "Phone number is already registered.";
	public static final String MESSAGE_ERROR_PASSWORD_UNMATCHED = "Password is not matched.";

	// Login Page Messages
	public static final String MESSAGE_SUCCESS_LOGIN = "Successfully LoggedIn!";
	public static final String MESSAGE_ERROR_LOGIN = "Either username or password is not correct!";
	public static final String MESSAGE_ERROR_CREATE_ACCOUNT = "Account for this username is not registered! Please create a new account.";

	// Other Messages
	public static final String MESSAGE_SUCCESS_DELETE = "Successfully Deleted!";
	public static final String MESSAGE_ERROR_DELETE = "Cannot delete the user!";
	public static final String MESSAGE_SUCCESS = "successMessage";
	public static final String MESSAGE_ERROR = "errorMessage";
	// End: Validation Messages

	// Start: JSP Route
	public static final String PAGE_URL_LOGIN = "/pages/login.jsp";
	public static final String PAGE_URL_REGISTER = "/pages/register.jsp";
	public static final String PAGE_URL_WELCOME = "/pages/welcome.jsp";
	public static final String PAGE_URL_HOME = "/pages/home.jsp";
	public static final String PAGE_URL_RATE_PLAYER = "/pages/rateplayer.jsp";
	public static final String PAGE_URL_ADD_PLAYER = "/pages/addPlayer.jsp";
	public static final String PAGE_URL_RATE_PLAYER_USER = "/pages/ratePlayerUser.jsp";
	public static final String PAGE_URL_UPDATE = "/pages/updatePlayer.jsp";
	public static final String PAGE_URL_UPDATE_USER = "/pages/userUpdate.jsp";
	public static final String PAGE_ADD_PLAYER = "/pages/addPlayer.jsp";
	public static final String URL_INDEX = "/index.jsp";
	public static final String URL_USER_PROFILE = "/pages/profile.jsp";
	// End: JSP Route

	// Start: Servlet Route
	public static final String SERVLET_URL_LOGIN = "/login";
	public static final String SERVLET_URL_REGISTER = "/registerculer";
	
	
	public static final String SERVLET_URL_LOGOUT = "/logout";
	public static final String SERVLET_URL_HOME = "/home";
	public static final String SERVLET_URL_MODIFY_USER = "/modifyUser";
	public static final String SERVLET_URL_MODIFY_PLAYER = "/modify";
	public static final String SERVLET_URL_UPDATE_PLAYER = "/updatePlayer";
	public static final String SERVLET_URL_ADD_PLAYER ="/addPlayer";
	public static final String SERVLET_URL_RATE_PLAYER ="/rate_player";
	public static final String SERVLET_URL_RATE ="/rating";
	public static final String SERVLET_URL_LEADERBOARD ="/leaderboard";
	public static final String SERVLET_URL_PROFILE="/profile";
	public static final String SERVLET_URL_UPDATE_PROFILE="/updateProfile";
	// End: Servlet Route

	// Start: Normal Text
	public static final String USER = "user";
	public static final String SUCCESS = "success";
	public static final String TRUE = "true";
	public static final String JSESSIONID = "JSESSIONID";
	public static final String LOGIN = "Login";
	public static final String LOGOUT = "Logout";
	public static final String STUDENT_MODEL = "studentModel";
	public static final String PLAYERS_LISTS = "playerLists";
	public static final String CULER_LISTS = "culerLists";
	public static final String SLASH= "/";
	public static final String DELETE_ID= "deleteId";
	public static final String UPDATE_ID= "updateId";
}