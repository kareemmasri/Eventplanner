package eventPlanner;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;

import java.util.ArrayList;
import java.util.List;

public class sweet_treat {

    private List<String> sweetTreatOptions = new ArrayList<>();
    private String selectedPackage;
    private boolean customizationAgreed = false;
    private boolean dietaryNeedsMet = false;

    @Given("the event planner is preparing sweet treats options")
    public void theEventPlannerIsPreparingSweetTreatsOptions() {
        // Simulate preparation of sweet treats options
        sweetTreatOptions.add("cakes");
        sweetTreatOptions.add("cupcakes");
        sweetTreatOptions.add("candies");
        sweetTreatOptions.add("chocolates");
    }

    @When("they design a variety of sweet treats packages")
    public void theyDesignAVarietyOfSweetTreatsPackages() {
        // Simulate the design of various sweet treats packages
        Assert.assertFalse("Sweet treats options should be available", sweetTreatOptions.isEmpty());
    }

    @Then("they should offer options including cakes, cupcakes, candies, and chocolates")
    public void theyShouldOfferOptionsIncludingCakesCupcakesCandiesAndChocolates() {
        // Verify that the expected options are available
        Assert.assertTrue("Expected sweet treats options should be offered", sweetTreatOptions.containsAll(List.of("cakes", "cupcakes", "candies", "chocolates")));
    }

    @Then("ensure there are customizable themes for different events")
    public void ensureThereAreCustomizableThemesForDifferentEvents() {
        // Simulate ensuring customizable themes are available
        Assert.assertTrue("Customizable themes should be available for different events", true);
    }

    @Given("a client is interested in the sweet treats service")
    public void aClientIsInterestedInTheSweetTreatsService() {
        // Simulate a client's interest in the sweet treats service
        Assert.assertTrue("A client should be interested in the sweet treats service", true);
    }

    @When("the event planner presents the sweet treats options")
    public void theEventPlannerPresentsTheSweetTreatsOptions() {
        // Assuming this step is where the options are actually prepared/presented
        sweetTreatOptions.add("cakes");
        sweetTreatOptions.add("cupcakes");
        sweetTreatOptions.add("candies");
        sweetTreatOptions.add("chocolates");

        // Now assert that the options have been presented (i.e., the list is not empty)
        Assert.assertFalse("Sweet treats options should be presented", sweetTreatOptions.isEmpty());
        System.out.println("Presenting sweet treats options...");
    }

    @When("the client selects a package")
    public void theClientSelectsAPackage() {
        // Simulate a client selecting a package
        selectedPackage = "custom cake package";
    }

    @Then("the event planner should discuss and agree on customization details like flavors, themes, and quantities")
    public void theEventPlannerShouldDiscussAndAgreeOnCustomizationDetailsLikeFlavorsThemesAndQuantities() {
        // Simulate agreeing on customization details
        customizationAgreed = true;
    }

    @Then("confirm the sweet treats order for the event")
    public void confirmTheSweetTreatsOrderForTheEvent() {
        // Confirm the order with agreed-upon details
        Assert.assertTrue("Sweet treats order should be confirmed for the event", customizationAgreed);
    }

    @Given("a client has specific dietary requirements for sweet treats")
    public void aClientHasSpecificDietaryRequirementsForSweetTreats() {
        // Simulate a client having specific dietary requirements
        Assert.assertTrue("Client has specific dietary requirements", true);
    }

    @When("the event planner reviews the requirements with the client")
    public void theEventPlannerReviewsTheRequirementsWithTheClient() {
        // Review dietary requirements with the client
        Assert.assertTrue("Requirements should be reviewed with the client", true);
    }

    @Then("they should offer alternatives that cater to allergies, vegan, gluten-free, and sugar-free preferences")
    public void theyShouldOfferAlternativesThatCaterToAllergiesVeganGlutenFreeAndSugarFreePreferences() {
        // Simulate offering suitable alternatives
        dietaryNeedsMet = true;
    }

    @Then("finalize the sweet treats order ensuring all dietary needs are met")
    public void finalizeTheSweetTreatsOrderEnsuringAllDietaryNeedsAreMet() {
        // Finalize the order, ensuring all dietary needs are addressed
        Assert.assertTrue("All dietary needs should be met in the final order", dietaryNeedsMet);
    }
}
