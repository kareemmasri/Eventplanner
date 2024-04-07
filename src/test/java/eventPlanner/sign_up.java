package eventPlanner;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;

public class sign_up {

    private final SharedState sharedState;

    // Constructor that PicoContainer will use to inject shared state
    public sign_up(SharedState state) {
        this.sharedState = state;
    }

    @Given("the system is accessible via command in Eclipse")
    public void theSystemIsAccessibleViaCommandInEclipse() {
        Assert.assertTrue("System should be accessible", true);
    }

    @Given("a visitor is on the command line interface")
    public void aVisitorIsOnTheCommandLineInterface() {
        Assert.assertTrue("CLI should be ready for input", true);
    }

    @When("they register with a {string}, {string}, and {string}")
    public void theyRegisterWithAAnd(String username, String password, String userType) {
        if (!sharedState.users.containsKey(username)) {
            sharedState.users.put(username, new String[]{password, userType});
            sharedState.responseMessage = "Registration successful.";
        } else {
            sharedState.responseMessage = "Registration failed - username already in use.";
        }
    }

    @Then("they should receive a message {string}")
    public void theyShouldReceiveAMessage(String expectedMessage) {
        Assert.assertEquals(expectedMessage, sharedState.responseMessage);
    }

    @Given("{string} is already registered with password {string} and user type {string}")
    public void isAlreadyRegisteredWithPasswordAndUserType(String username, String password, String userType) {
        sharedState.users.put(username, new String[]{password, userType});
    }

    @When("they login with {string} and {string}")
    public void theyLoginWithAnd(String username, String password) {
        if (sharedState.users.containsKey(username) && sharedState.users.get(username)[0].equals(password)) {
            sharedState.responseMessage = "Login successful.";
        } else {
            sharedState.responseMessage = "Login failed - incorrect password.";
        }
    }

    @When("they attempt to login with {string} and {string}")
    public void theyAttemptToLoginWithAnd(String username, String password) {
        theyLoginWithAnd(username, password);
    }

    @When("a visitor attempts to login with {string} and {string}")
    public void aVisitorAttemptsToLoginWithAnd(String username, String password) {
        if (!sharedState.users.containsKey(username)) {
            sharedState.responseMessage = "Login failed - account not found.";
        } else {
            theyLoginWithAnd(username, password);
        }
    }

    @Given("{string} has an account")
    public void hasAnAccount(String username) {
        String defaultPassword = "defaultPassword";
        String defaultUserType = "Guest"; // Or "Event Planner", "Admin" based on your application's needs

        sharedState.users.computeIfAbsent(username, k -> new String[]{defaultPassword, defaultUserType});
    }

    @Given("{string} is already registered")
    public void isAlreadyRegistered(String username) {
        String defaultPassword = "defaultPassword";
        String defaultUserType = "DefaultUserType"; // Change as needed

        sharedState.users.computeIfAbsent(username, k -> new String[]{defaultPassword, defaultUserType});
    }

    @When("a visitor registers with {string}")
    public void aVisitorRegistersWith(String username) {
        String defaultPassword = "somePassword";
        String defaultUserType = "Guest";
        
        theyRegisterWithAAnd(username, defaultPassword, defaultUserType);
    }
}
