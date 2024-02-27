import java.io.*;
import java.util.Scanner;
// To Serialize a class (write to binary file, must make the class serializable)
public class Main {

	public static void main(String[] args) throws InterruptedException {
		
		Scanner keyboard = new Scanner(System.in);
		Contact[] myContacts = new Contact[100];
		int count = 0, choice;
		String firstName, lastName, mobile, birthday;
		boolean isFavorite;
		File BINARY_FILE = new File("Contacts.dat");
		
		System.out.println("Loading Contact Information from Database...");
		if(BINARY_FILE.exists()) {
			try {
				ObjectInputStream fileReader = new ObjectInputStream(new FileInputStream(BINARY_FILE));
				myContacts = (Contact[]) fileReader.readObject();
				fileReader.close();
				for (int i = 0; i < myContacts.length; i++) {
					if (myContacts[i] == null)
						break;
					count++;
				}
			} catch (IOException | ClassNotFoundException e) {
				System.err.println(e.getMessage());
			}
		}
        // TODO: Load contacts from binary file
		System.out.println("Done! " + count + " contacts loaded");
		
		do {
		System.out.print(
				  "\n********************************************************************\n"
				+ "**                                                                **\n"
				+ "**                       MIKE'S PHONE CONTACTS                    **\n"
				+ "**                                                                **\n"
				+ "********************************************************************\n"
				+ "1) Add New Contact...\n"
				+ "2) View Contact Names\n"
				+ "3) View Contact Details\n"
				+ "4) Exit\n"
				+ "********************************************************************\n"
				+ ">> ");
			choice = keyboard.nextInt();
			
			switch (choice)
			{
			case 1:  // Add New Contact...
				// Clear out \n from keyboard
				keyboard.nextLine();
				System.out.print("First Name: ");
				firstName = keyboard.nextLine();
				System.out.print("Last  Name: ");
				lastName = keyboard.nextLine();
				System.out.print("Mobile   #: ");
				mobile = keyboard.nextLine();
				System.out.print("Birthday  : ");				
				birthday = keyboard.nextLine();
				System.out.print("Favorite (Y/N): ");
				isFavorite = keyboard.nextLine().equalsIgnoreCase("Y");
				
				// TODO: Instantiate new Contact, add it to the array;
				myContacts[count++] = new Contact(firstName, lastName, mobile, birthday, isFavorite);

				break;
				
			case 2:  // View Contact Names
				System.out.println("\n********************************************************************");
				System.out.println("                        Contact Names");
				System.out.println("********************************************************************");
				// TODO: Print contact names (only)
				for (int i = 0; i < count; i++) {
					System.out.println(myContacts[i].getFullName());

				}
				break;
				
			case 3:  // View Contact Details
				System.out.println("\n********************************************************************");
				System.out.println("                        Contact Details");
				System.out.println("********************************************************************");
				// TODO: Print contact details
				for (int i = 0; i < count; i++) {
					System.out.println(myContacts[i]);

				}
				break;
				
			case 4:  // Exit
				System.out.println("Saving Contact Information to Database...");
                try {
                    ObjectOutputStream fileWriter = new ObjectOutputStream(new FileOutputStream(BINARY_FILE));
					fileWriter.writeObject(myContacts);
					fileWriter.close();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
                break;
			default:  // Error - Invalid input
				System.err.println("Invalid choice. Please select (1-4)");
				Thread.sleep(500); // To pause a bit of time (e.g. 0.5 second) before restarting loop
				
			}
		
		}
		while (choice != 5);
		
		// TODO: Save contacts to binary file
		System.out.println("Done! " + count + " contacts saved");
		
		keyboard.close();
	}

}
