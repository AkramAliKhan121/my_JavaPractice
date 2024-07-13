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
			System.out.println("1. Add contact");
			System.out.println("2. View all contacts");
			System.out.println("3. Search Contact");
			System.out.println("4. Update Contact");
			System.out.println("5. Delete Contact");
			System.out.println("6. Delete All Contacts");
			System.out.println("0. exit");
			System.out.println("Select options");
			
			int option = in.nextInt();
			
			switch(option) {
			case 1:
				System.out.println("add contact");
				System.out.println("enter name");
				String name =in.next();
				System.out.println("Enter e-mail");
				String email = in.next();
				System.out.println("Enter mobile");
				long mobile = in.nextLong();
				
				Contact nwcon = new Contact();
//				nwcon.setId(id);
				nwcon.setName(name);
				nwcon.setEmail(email);
				nwcon.setMobile(mobile);
				
				int status = dao.addContact(nwcon);
				if(status>=1) {
					System.out.println("contact added");
				}else {
					System.out.println("something went wrong");
				}
				break;
			case 2:
				System.out.println("view all contacts");
				List<Contact> contacts = dao.getAllContacts();
				
				if(contacts.size() != 0) {
					for (Contact c : contacts) {
						System.out.println(c.getId()+"\t"+c.getName()+"\t"+c.getEmail()+"\t"+c.getMobile());
					}
				}
				else {System.out.println("no contact found");}
				break;
			case 3:
				System.out.println("search contact");
				System.out.println("------------------------------------------");
				System.out.println("Enter the contact name to search");
				String searchName =in.next();
				Contact searchedContact = dao.getContact(searchName);
			    if (searchedContact != null) {
			        System.out.println(searchedContact.getId() + "\t" + searchedContact.getName() + "\t" + searchedContact.getEmail() + "\t" + searchedContact.getMobile());
			    } else {
			        System.out.println("Contact not found");
			    }

				break;
			case 4:
				System.out.println("Update contact");
				System.out.println("------------------------------------------");
				System.out.println("Please enter contact name to update:");
			    String updateName = in.next();
			    Contact updateContact = dao.getContact(updateName);
			    if (updateContact != null) {
			        System.out.println("Please enter new contact name:");
			        String newName = in.next();
			        System.out.println("Please enter new contact email:");
			        String newEmail = in.next();
			        System.out.println("Please enter new contact mobile:");
			        long newMobile = in.nextLong();
			        updateContact.setName(newName);
			        updateContact.setEmail(newEmail);
			        updateContact.setMobile(newMobile);
			        int status1 = dao.updateContact(updateContact);
			        if (status1 >= 1) {
			            System.out.println("Contact updated successfully");
			        } else {
			            System.out.println("Update failed");
			        }
			    } else {
			        System.out.println("Contact not found");
			    }
			    break;
			case 5:
				System.out.println("delete contact");
		        System.out.println("------------------------------------------");
		        System.out.println("Please enter contact name to delete:");
		        String deleteName = in.next();
		        Contact deleteContact = dao.deleteContact(deleteName);
		        if (deleteContact != null) {
		            System.out.println("Contact deleted successfully");
		        } else {
		            System.out.println("Delete failed");
		        }
		        break;
			case 6:
				dao.deleteAllContacts();
			    System.out.println("All contacts deleted");
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
