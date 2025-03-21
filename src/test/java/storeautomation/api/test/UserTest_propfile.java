package storeautomation.api.test;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import com.github.javafaker.Faker;
import io.restassured.response.Response;
import storeautomation.api.endpoints.UserEndPoints;
import storeautomation.api.endpoints.UserEndPoints_propfile;
import storeautomation.api.payload.User;

public class UserTest_propfile {
	
	Faker faker;
	User userPayload;
	public static Logger logger = LogManager.getLogger();
	
	@BeforeClass
	public void setupDate() {
		
		faker = new Faker();
		userPayload = new User();
		
		  userPayload.setId(faker.idNumber().hashCode());
		  userPayload.setUsername(faker.name().username());
		  userPayload.setFirstName(faker.name().firstName());
		  userPayload.setLastName(faker.name().lastName());
		  userPayload.setEmail(faker.internet().safeEmailAddress());
		  userPayload.setPassword(faker.internet().password(5,10));
		  userPayload.setPhone(faker.phoneNumber().cellPhone());
		  
		 // logger = LogManager.getLogger(this.getClass());
		 
		
	}
	
	@Test(priority = 1)
	public void testPostUser() throws InterruptedException {
	  logger.info("************* Creating User ******************");	
	  Response response = UserEndPoints_propfile.creatUser(userPayload);
	  response.then().log().all();
	  Assert.assertEquals(response.statusCode(), 200);
	
	}
	
	@Test(priority = 2)
	public void testGetUserByName() throws InterruptedException {
		
		logger.info("************* Getting the User ******************");
		Response response = UserEndPoints_propfile.readUser(this.userPayload.getUsername());
		response.then().log().all();
		Assert.assertEquals(response.getStatusCode(), 200);

		
	}

	@Test(priority = 3)
	public void testUpdateUser() {

		logger.info("************* Updating User ******************");
		userPayload.setFirstName(faker.name().firstName());
		userPayload.setLastName(faker.name().lastName());
		userPayload.setEmail(faker.internet().safeEmailAddress());

		Response response = UserEndPoints_propfile.updateUser(this.userPayload.getUsername(), userPayload);
		response.then().log().all();
		Assert.assertEquals(response.getStatusCode(), 200);
		
		// Verify after the update
		
		Response responseAfterUpdate = UserEndPoints.readUser(this.userPayload.getUsername());
		responseAfterUpdate.then().log().all();
		Assert.assertEquals(responseAfterUpdate.getStatusCode(), 200);

	}
	
	@Test(priority = 4)
	public void testDeleteUser() throws InterruptedException {
		logger.info("************* Deleting User ******************");
		Response response = UserEndPoints_propfile.deleteUser(this.userPayload.getUsername());
		response.then().log().all();
		Assert.assertEquals(response.getStatusCode(), 200);
		
	}
	
}
