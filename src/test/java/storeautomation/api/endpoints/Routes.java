package storeautomation.api.endpoints;

// create - https://petstore.swagger.io/v2/user
// get - https://petstore.swagger.io/v2/user/{username}
// update - https://petstore.swagger.io/v2/user/{username}
// delete - https://petstore.swagger.io/v2/user/{username}


public class Routes {
	
	public static String  base_url = "https://petstore.swagger.io/v2";
	
	// User model
	
	public static String post_url = base_url+"/user";
	public static String get_url = base_url+"/user/{username}";
	public static String update_url = base_url+"/user/{username}";
	public static String delete_url = base_url + "/user/{username}";
	
	// Store Module
	
	
	// Pet Module

}
