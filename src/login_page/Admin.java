package login_page;

public class Admin extends User {
	private static final String adminUsername = "Admin";
	private static final String adminPassword = "theadmin";
	
	public Admin(String username, String password) {
		
	}
	
	public static boolean checkAdminLogin(String username, String password) {
		if (username.equals(adminUsername)) {
			if (password.equals(adminPassword)) {
				return true;
			}
		}
		
		return false;
	}
}
