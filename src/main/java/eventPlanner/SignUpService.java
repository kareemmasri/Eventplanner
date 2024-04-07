package eventPlanner;

import java.util.*;

public class SignUpService {

    private Map<String, String[]> users = new HashMap<>(); // Maps username to [password, userType]
    private Map<String, List<String>> events = new HashMap<>(); // Maps eventName to [eventDate, location, capacity, eventPlannerUsername]
    private Map<String, List<String>> eventRegistrations = new HashMap<>(); // Maps eventName to list of registered usernames
    private Map<String, List<Double>> eventRatings = new HashMap<>(); // Maps eventName to list of ratings
    private String[] currentUser; // [0]: Username, [1]: UserType

    public static void main(String[] args) {
        SignUpService service = new SignUpService();
        service.run();
    }

    public void run() {
        Scanner scanner = new Scanner(System.in);
        boolean running = true;

        while (running) {
            System.out.println("\nWelcome to the Event Planner CLI!");
            if (currentUser == null) {
                System.out.println("1. Sign Up");
                System.out.println("2. Sign In");
            } else {
                System.out.println("3. View Events");
                if ("Event Planner".equals(currentUser[1]) || "Admin".equals(currentUser[1])) {
                    System.out.println("4. Create Event");
                    System.out.println("5. Update Event");
                    System.out.println("6. Delete Event");
                  
                }
                if ("Guest".equals(currentUser[1]) || "Admin".equals(currentUser[1])) {
                    System.out.println("7. Join Event");
                    System.out.println("8. Rate an Event");
                    System.out.println("9. View Event Ratings");
                }
                System.out.println("10. View Calendar");
                System.out.println("11. Sign Out");
            }
            System.out.println("12. Exit");
            System.out.print("Choose an option: ");

            String input = scanner.nextLine();

            switch (input) {
                case "1":
                    if (currentUser == null) signUp(scanner);
                    break;
                case "2":
                    if (currentUser == null) signIn(scanner);
                    break;
                case "3":
                    viewEvents(scanner); // Updated to include search functionality
                    break;
                case "4":
                    if (currentUser != null && ("Event Planner".equals(currentUser[1]) || "Admin".equals(currentUser[1])))
                        createEvent(scanner);
                    break;
                case "5":
                    if (currentUser != null && ("Event Planner".equals(currentUser[1]) || "Admin".equals(currentUser[1])))
                        updateEvent(scanner);
                    break;
                case "6":
                    if (currentUser != null && ("Event Planner".equals(currentUser[1]) || "Admin".equals(currentUser[1])))
                        deleteEvent(scanner);
                    break;
  
                case "7":
                    if (currentUser != null && "Guest".equals(currentUser[1])) joinEvent(scanner);
                    break;
                case "8":
                    if (currentUser != null && "Guest".equals(currentUser[1])) rateEvent(scanner);
                    break;
                case "9":
                    if (currentUser != null && "Guest".equals(currentUser[1])) viewEventRatings(scanner);
                    break;
                case "10":
                    viewCalendar();
                    break;
                case "11":
                    if (currentUser != null) {
                        currentUser = null;
                        System.out.println("You have been signed out.");
                    }
                    break;
                case "12":
                    running = false;
                    System.out.println("Exiting the system...");
                    break;
                default:
                    System.out.println("Invalid option, please try again.");
            }
        }
        scanner.close();
    }

    private void signUp(Scanner scanner) {
        System.out.print("Enter Username: ");
        String username = scanner.nextLine();
        System.out.print("Enter Password: ");
        String password = scanner.nextLine();
        System.out.println("Select User Type:");
        System.out.println("1. Guest");
        System.out.println("2. Event Planner");
        System.out.println("3. Admin");
        System.out.print("Enter your choice (1-3): ");
        String userTypeChoice = scanner.nextLine();

        String userType;
        switch (userTypeChoice) {
            case "1":
                userType = "Guest";
                break;
            case "2":
                userType = "Event Planner";
                break;
            case "3":
                userType = "Admin";
                break;
            default:
                System.out.println("Invalid user type selected. Please try again.");
                return;
        }

        if (!users.containsKey(username)) {
            users.put(username, new String[]{password, userType});
            System.out.println("Registration successful as " + userType + ".");
        } else {
            System.out.println("Registration failed - username already in use.");
        }
    }

    private void signIn(Scanner scanner) {
        System.out.print("Enter Username: ");
        String username = scanner.nextLine();
        System.out.print("Enter Password: ");
        String password = scanner.nextLine();

        if (users.containsKey(username) && users.get(username)[0].equals(password)) {
            currentUser = new String[]{username, users.get(username)[1]};
            System.out.println("Login successful. Welcome, " + username + "!");
        } else {
            System.out.println("Login failed - incorrect username or password.");
        }
    }

    private void createEvent(Scanner scanner) {
        if (currentUser == null || (!"Event Planner".equals(currentUser[1]) && !"Admin".equals(currentUser[1]))) {
            System.out.println("You must be logged in as an Event Planner or Admin to create events.");
            return;
        }

        System.out.print("Enter Event Name: ");
        String eventName = scanner.nextLine();
        if (eventName.isEmpty()) {
            System.out.println("Event name cannot be empty.");
            return;
        }

        System.out.print("Enter Event Date (e.g., 2023-12-25): ");
        String eventDate = scanner.nextLine();
        if (eventDate.isEmpty() || !eventDate.matches("\\d{4}-\\d{2}-\\d{2}")) {
            System.out.println("Invalid date format. Please use YYYY-MM-DD.");
            return;
        }

        System.out.print("Enter Event Location: ");
        String location = scanner.nextLine();
        if (location.isEmpty()) {
            System.out.println("Event location cannot be empty.");
            return;
        }

        System.out.print("Enter Event Capacity: ");
        String capacity = scanner.nextLine();
        if (!capacity.matches("\\d+")) {
            System.out.println("Invalid capacity. Please enter a number.");
            return;
        }

       
        System.out.print("Enter Decoration Theme (leave blank if none): ");
        String decorationTheme = scanner.nextLine();

        
        System.out.print("Include Sweet Treats Service? (yes/no): ");
        String sweetTreatsResponse = scanner.nextLine();
        String sweetTreatsService = "no".equalsIgnoreCase(sweetTreatsResponse.trim()) ? "No Sweet Treats" : "Sweet Treats Included";

        boolean conflictExists = events.values().stream()
                .anyMatch(details -> details.get(0).equals(eventDate) && details.get(1).equalsIgnoreCase(location));

        if (conflictExists) {
            System.out.println("Event creation failed - Another event is already scheduled at this date and location.");
            return;
        }

        if (events.containsKey(eventName)) {
            System.out.println("Event creation failed - Event name already exists.");
        } else {
            // Include decoration theme and sweet treats service in the event details
            List<String> eventDetails = Arrays.asList(eventDate, location, capacity, currentUser[0], decorationTheme, sweetTreatsService);
            events.put(eventName, eventDetails);
            System.out.println("Event created successfully.");
        }
    }


    private void updateEvent(Scanner scanner) {
        if (currentUser == null || (!"Event Planner".equals(currentUser[1]) && !"Admin".equals(currentUser[1]))) {
            System.out.println("You must be logged in as an Event Planner or Admin to update events.");
            return;
        }

        System.out.print("Enter the name of the event you wish to update: ");
        String eventName = scanner.nextLine();

        if (!events.containsKey(eventName)) {
            System.out.println("Event update failed - Event does not exist.");
            return;
        }

        List<String> eventDetails = events.get(eventName);
        if (!"Admin".equals(currentUser[1]) && !eventDetails.get(3).equals(currentUser[0])) {
            System.out.println("Event update failed - You can only update events you have created.");
            return;
        }

        System.out.print("Enter the updated Event Date (e.g., 2023-12-25): ");
        String updatedDate = scanner.nextLine();
        System.out.print("Enter the updated Event Location: ");
        String updatedLocation = scanner.nextLine();
        System.out.print("Enter the updated Event Capacity: ");
        String updatedCapacity = scanner.nextLine();

        // Check if the event planner wants to update decoration
        System.out.print("Would you like to update the decoration theme for this event? (yes/no): ");
        String updateDecoration = scanner.nextLine().trim();
        if ("yes".equalsIgnoreCase(updateDecoration)) {
            addDecoration(scanner);  // This method will handle the decoration update process
        }

        // Check if the event planner wants to update sweet treats service
        System.out.print("Would you like to update the sweet treats options for this event? (yes/no): ");
        String updateSweetTreats = scanner.nextLine().trim();
        if ("yes".equalsIgnoreCase(updateSweetTreats)) {
            addSweetTreats(scanner);  // This method will handle the sweet treats update process
        }

        // Update the basic details of the event
        List<String> updatedEventDetails = Arrays.asList(updatedDate, updatedLocation, updatedCapacity, eventDetails.get(3));
        events.put(eventName, updatedEventDetails);
        System.out.println("Event updated successfully.");
    }



    private void deleteEvent(Scanner scanner) {
        if (currentUser == null || (!"Event Planner".equals(currentUser[1]) && !"Admin".equals(currentUser[1]))) {
            System.out.println("You must be logged in as an Event Planner or Admin to delete events.");
            return;
        }

        System.out.print("Enter the name of the event you wish to delete: ");
        String eventName = scanner.nextLine();

        if (!events.containsKey(eventName)) {
            System.out.println("Event deletion failed - Event does not exist.");
            return;
        }

        List<String> eventDetails = events.get(eventName);
        if ("Admin".equals(currentUser[1]) || eventDetails.get(3).equals(currentUser[0])) {
            events.remove(eventName);
            System.out.println("Event deleted successfully.");
        } else {
            System.out.println("Event deletion failed - You can only delete events you have created.");
        }
    }

    private void viewEvents(Scanner scanner) {
        // Example filter by location
        System.out.print("Enter location to filter by (leave blank for no filter): ");
        String locationFilter = scanner.nextLine();

        boolean found = false;
        for (Map.Entry<String, List<String>> entry : events.entrySet()) {
            String eventName = entry.getKey();
            List<String> details = entry.getValue();
            String eventLocation = details.get(1); // Assuming location is at index 1

            if (locationFilter.isEmpty() || eventLocation.equalsIgnoreCase(locationFilter)) {
                System.out.println("Event: " + eventName + " Location: " + eventLocation);
                found = true;
            }
        }

        if (!found) {
            System.out.println("No events found for the specified filter.");
        }
    }


    private void joinEvent(Scanner scanner) {
        if (currentUser == null || !"Guest".equals(currentUser[1])) {
            System.out.println("You must be logged in as a Guest to join events.");
            return;
        }
        String username = currentUser[0]; // Use the logged-in username

        System.out.print("Enter Event Name to Join: ");
        String eventName = scanner.nextLine();

        if (!events.containsKey(eventName)) {
            System.out.println("Event does not exist.");
            return;
        }

        List<String> eventDetails = events.get(eventName);
        int capacity = Integer.parseInt(eventDetails.get(2));
        List<String> participants = eventRegistrations.computeIfAbsent(eventName, k -> new ArrayList<>());

        if (participants.contains(username)) {
            System.out.println("You are already registered for this event.");
        } else if (participants.size() < capacity) {
            participants.add(username);
            System.out.println("You have successfully joined '" + eventName + "'.");
        } else {
            System.out.println("Sorry, '" + eventName + "' is full. You cannot join this event.");
        }
    }

    private void rateEvent(Scanner scanner) {
        System.out.print("Enter Event Name to Rate: ");
        String eventName = scanner.nextLine();

        if (!events.containsKey(eventName)) {
            System.out.println("Event does not exist.");
            return;
        }

        System.out.print("Enter Your Rating (0.0 to 5.0): ");
        double rating;
        try {
            rating = Double.parseDouble(scanner.nextLine());
            if (rating < 0.0 || rating > 5.0) throw new NumberFormatException();
        } catch (NumberFormatException e) {
            System.out.println("Invalid rating. Please enter a number from 0.0 to 5.0.");
            return;
        }

        List<Double> ratings = eventRatings.computeIfAbsent(eventName, k -> new ArrayList<>());
        ratings.add(rating);
        System.out.println("Thank you for rating the event!");
    }

    private void viewEventRatings(Scanner scanner) {
        System.out.print("Enter Event Name to View Ratings: ");
        String eventName = scanner.nextLine();

        if (!eventRatings.containsKey(eventName)) {
            System.out.println("No ratings found for this event.");
            return;
        }

        List<Double> ratings = eventRatings.get(eventName);
        double average = ratings.stream().mapToDouble(Double::doubleValue).average().orElse(0.0);
        System.out.println("Average Rating for '" + eventName + "': " + String.format("%.2f", average));
        System.out.println("All Ratings:");
        ratings.forEach(rating -> System.out.println(rating));
    }
    private void viewCalendar() {
        if (events.isEmpty()) {
            System.out.println("No upcoming events.");
            return;
        }

        System.out.println("Upcoming Events:");
        for (Map.Entry<String, List<String>> entry : events.entrySet()) {
            System.out.println("Event: " + entry.getKey() + ", Date: " + entry.getValue().get(0));
        }
    }
    private void addDecoration(Scanner scanner) {
        System.out.print("Would you like to add decoration services to this event? (yes/no): ");
        String response = scanner.nextLine().trim();

        if ("yes".equalsIgnoreCase(response)) {
            System.out.println("Available Decoration Themes:");
            System.out.println("1. Classic Elegance");
            System.out.println("2. Modern Chic");
            System.out.println("3. Rustic Charm");
            System.out.println("4. Vintage");
            System.out.print("Select your preferred theme (1-4): ");
            String theme = scanner.nextLine().trim();
            switch (theme) {
                case "1":
                    theme = "Classic Elegance";
                    break;
                case "2":
                    theme = "Modern Chic";
                    break;
                case "3":
                    theme = "Rustic Charm";
                    break;
                case "4":
                    theme = "Vintage";
                    break;
                default:
                    System.out.println("Invalid selection. No decoration theme added.");
                    theme = "None";
                    break;
            }
            if (!"None".equals(theme)) {
                System.out.println("Decoration theme '" + theme + "' has been added to the event.");
            }
        } else {
            System.out.println("No decoration services will be added to this event.");
        }
    }
    private void addSweetTreats(Scanner scanner) {
        System.out.print("Would you like to add sweet treats to this event? (yes/no): ");
        String response = scanner.nextLine().trim();

        if ("yes".equalsIgnoreCase(response)) {
            System.out.println("Available Sweet Treats Options:");
            System.out.println("1. Cakes");
            System.out.println("2. Cupcakes");
            System.out.println("3. Candies");
            System.out.println("4. Chocolates");
            System.out.print("Select the options you'd like to include (e.g., 1,2): ");
            String options = scanner.nextLine().trim();
            System.out.println("Sweet treats options (" + options + ") have been added to the event.");
        } else {
            System.out.println("No sweet treats will be added to this event.");
        }
    }

    
}
