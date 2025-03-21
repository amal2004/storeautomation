package storeautomation.api.test;

import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.response.Response;
import storeautomation.api.endpoints.UserEndPoints;
import storeautomation.api.payload.User;
import storeautomation.api.utilities.DataProviders;

public class DDTests {
	
	@Test(priority = 1, dataProvider = "Data", dataProviderClass =DataProviders.class )
	public void testPostUser(String userID, String userName, String firstName, String lastName,
			String emailAddress, String password, String phoneNumber) throws InterruptedException {
		
		User userPayload = new User();
		
		  userPayload.setId(Integer.parseInt(userID));
		  userPayload.setUsername(userName);
		  userPayload.setFirstName(firstName);
		  userPayload.setLastName(lastName);
		  userPayload.setEmail(emailAddress);
		  userPayload.setPassword(password);
		  userPayload.setPhone(phoneNumber);
		  
		  Response response = UserEndPoints.creatUser(userPayload);
		  response.then().log().all();
		  Assert.assertEquals(response.statusCode(), 200);
		  Thread.sleep(3000);
		
	}
	
	@Test(priority = 2, dataProvider = "UserNames", dataProviderClass = DataProviders.class )
	public void testDeleteUser(String userName) throws InterruptedException {
		Thread.sleep(3000);
		Response response = UserEndPoints.deleteUser(userName);
		response.then().log().all();
		Assert.assertEquals(response.getStatusCode(), 200);
		
	}

}
