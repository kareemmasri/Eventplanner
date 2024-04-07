package eventPlanner;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class decoration {

    private String selectedPackage; // Example of state within this class

    @When("the event planner presents the decoration options")
    public void theEventPlannerPresentsTheDecorationOptions() {
       // Implementation for presenting options
       System.out.println("Presenting decoration options...");
    }

    @Then("guests should be provided with a variety of packages to choose from")
    public void guestsShouldBeProvidedWithAVarietyOfPackagesToChooseFrom() {
       // Validation that guests are presented with options
       System.out.println("Guests are reviewing decoration packages...");
    }

    @Given("a guest has selected a decoration package")
    public void aGuestHasSelectedADecorationPackage() {
        // Simulate a package selection
        selectedPackage = "Package 1";
        System.out.println("Guest selected: " + selectedPackage);
    }

    @When("the event planner discusses customization options with the guest")
    public void theEventPlannerDiscussesCustomizationOptionsWithTheGuest() {
        // Discuss customization options
        if (selectedPackage != null) {
            System.out.println("Discussing customization options for " + selectedPackage);
        }
    }

    @When("agrees on details such as colors, themes, and floral arrangements")
    public void agreesOnDetailsSuchAsColorsThemesAndFloralArrangements() {
        // Agree on customization details
        System.out.println("Agreed on customization details...");
    }

    @Then("the event planner should be able to customize the package as per the guest's preferences")
    public void theEventPlannerShouldBeAbleToCustomizeThePackageAsPerTheGuestSPreferences() {
        // Implement customization logic
        System.out.println("Customizing package as per preferences...");
    }

    @Then("confirm the final decoration details for the event")
    public void confirmTheFinalDecorationDetailsForTheEvent() {
        // Confirm final details
        System.out.println("Final decoration details confirmed for the event.");
    }

}
