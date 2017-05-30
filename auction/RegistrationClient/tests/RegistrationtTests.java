import org.junit.Test;
import webAuction.RegistrationWebService;
import webAuction.RegistrationWebServiceService;
import webAuction.User;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

public class RegistrationtTests {

    private RegistrationWebService registrationService = new RegistrationWebServiceService().getPort(RegistrationWebService.class);

    @Test
    public void GetUser_NonExistentEmail_UserIsNull() {
        User user = registrationService.getUser("");
        assertNull(user);
    }

    @Test
    public void GetUser_ValidEmail_ReturnsCorrectUser() {
        String email = "test@test.nl";

        registrationService.register(email);

        User user = registrationService.getUser(email);

        assertEquals(email, user.getEmail());
    }
}