package com.edubridge.contact_app;

import java.util.List;
import java.util.Scanner;

import com.edubridge.contact_app.model.Contact;
import com.edubridge.contact_app.model.dao.ContactDao;
import com.edubridge.contact_app.model.dao.ContactDaoImpl;

public class Application {

	public static void main(String[] args) {
		
		ContactDao dao = new ContactDaoImpl();
		
		Scanner in = new Scanner(System.in);
		
		while(true) {
			System.out.println("My Contact Application");
			System.out.println("------------------------");
			System.out.println("1. Add Contact");
			System.out.println("2. View All Contacts");
			System.out.println("3. Search Contact");
			System.out.println("4. Update Contact");
			System.out.println("5. Delete Contact");
			System.out.println("6. Delete All Contacts");
			System.out.println("0. Exit");
			System.out.println("------------------------");
			System.out.println("Select Options");
			
			int option = in.nextInt();
			
			switch(option) {
			case 1:
				System.out.println("Add Contact");
				System.out.println("Enter Name");
				String name =in.next();
				System.out.println("Enter E-Mail");
				String email = in.next();
				System.out.println("Enter Mobile");
				long mobile = in.nextLong();
				
				Contact nwcon = new Contact();
//				nwcon.setId(id);
				nwcon.setName(name);
				nwcon.setEmail(email);
				nwcon.setMobile(mobile);
				
				int status = dao.addContact(nwcon);
				if(status>=1) {
					System.out.println("Contact Added");
				}else {
					System.out.println("something went wrong");
				}
				break;
			case 2:
				System.out.println("View All Contacts");
				List<Contact> contacts = dao.getAllContacts();
				
				if(contacts.size() != 0) {
					for (Contact c : contacts) {
						System.out.println(c.getId()+"\t"+c.getName()+"\t"+c.getEmail()+"\t"+c.getMobile());
					}
				}
				else {System.out.println("No Contact Found");}
				break;
			case 3:
				System.out.println("Search Contact");
				System.out.println("------------------------------------------");
				System.out.println("Enter the Contact Name to Search");
				String searchName =in.next();
				Contact searchedContact = dao.getContact(searchName);
			    if (searchedContact != null) {
			        System.out.println(searchedContact.getId() + "\t" + searchedContact.getName() + "\t" + searchedContact.getEmail() + "\t" + searchedContact.getMobile());
			    } else {
			        System.out.println("Contact Not Found");
			    }

				break;
			case 4:
				System.out.println("Update Contact");
				System.out.println("------------------------------------------");
				System.out.println("Please Enter Contact Name to Update:");
			    String updateName = in.next();
			    Contact updateContact = dao.getContact(updateName);
			    if (updateContact != null) {
			        System.out.println("Please Enter New Contact Name:");
			        String newName = in.next();
			        System.out.println("Please Enter New Contact E-Mail:");
			        String newEmail = in.next();
			        System.out.println("Please Enter New Contact Mobile:");
			        long newMobile = in.nextLong();
			        updateContact.setName(newName);
			        updateContact.setEmail(newEmail);
			        updateContact.setMobile(newMobile);
			        int status1 = dao.updateContact(updateContact);
			        if (status1 >= 1) {
			            System.out.println("Contact Updated Successfully");
			        } else {
			            System.out.println("Update Failed");
			        }
			    } else {
			        System.out.println("Contact Not Found");
			    }
			    break;
			case 5:
				System.out.println("Delete Contact");
		        System.out.println("------------------------------------------");
		        System.out.println("Please Enter Contact Name to Delete:");
		        String deleteName = in.next();
		        Contact deleteContact = dao.deleteContact(deleteName);
		        if (deleteContact != null) {
		            System.out.println("Contact Deleted Successfully");
		        } else {
		            System.out.println("Delete Failed");
		        }
		        break;
			case 6:
				dao.deleteAllContacts();
			    System.out.println("All Contacts Deleted");
			    break; 
			case 0:
				System.out.println("Thank You \n Have a Nice Day");
				System.exit(0);
				break;
			default:
				System.out.println("Invalid Option");
			
			
			}
		}

	}

}
