import play.test.*;
import play.mvc.*;
import play.mvc.Http.*;
import org.junit.*;

public class ApplicationTest extends FunctionalTest {
    
	 @Test
	 public void testTheHomePage() {
	 	Response response = GET("/");
	    assertStatus(200, response);
	 }	 
}