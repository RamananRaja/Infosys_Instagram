package com.instagram.controller;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import com.instagram.dao.InstagramDAOInterface;
import com.instagram.entity.InstagramUser;
import com.instagram.utility.DAOFactory;

public class InstagramController implements InstagramControllerInterface {

	private BufferedReader br;
	InstagramDAOInterface id;
	static boolean signedIn = false;
	static String name, password, email, address, newEmail;
	public InstagramController(){
		br = new BufferedReader(new InputStreamReader(System.in));
		id = DAOFactory.myMethod();
	}
	//CREATE PROFILE
	public void createProfile() {
		if(signedIn == true) {
			System.out.println("To create new profile, signout from this account");
		} else {
			try {
				System.out.println("Enter name:");
				name = br.readLine();
				System.out.println("Enter password:");
				password = br.readLine();
				System.out.println("Enter email:");
				email = br.readLine();
				System.out.println("Enter address:");
				address = br.readLine();
				
				InstagramUser iu = new InstagramUser();
				iu.setName(name);
				iu.setPassword(password);
				iu.setEmail(email);
				iu.setAddress(address);
				
				int i = id.createProfileDAO(iu);
				
				if(i>0) {
					System.out.println("Profile created..!");
				} else {
					System.out.println("Profile not created..!");
				}
				
				
			} catch(Exception e) {
				e.printStackTrace();
		}
		}
	}
	//LOGIN PROFILE
	public void loginProfile() {
		if(signedIn == false) {
			try {
				System.out.println("Enter email: ");
				email = br.readLine();
				System.out.println("Enter password: ");
				password = br.readLine();
				InstagramUser iu = new InstagramUser();
				iu.setEmail(email);
				iu.setPassword(password);
				
				boolean b = id.loginProfileDAO(iu);
				if(b) {
					System.out.println("Login Success");
					signedIn = true;
				} else {
					System.out.println("Login Failed - Invalid email/password");
				}
			} catch(Exception e) {
				e.printStackTrace();
			}
		} else {
			System.out.println("Already signed In..!");
		}
		
	}
	//VIEW MY PROFILE
	public void viewMyProfile() {
		InstagramUser iu = new InstagramUser();
		
		if(signedIn == true) {			
			iu.setEmail(email);
			
			List<String> myProfile = id.viewMyProfileDAO(iu);
			//System.out.println("from controller"+myProfile);
			System.out.println("Name: "+myProfile.get(0));
			System.out.println("Password: "+myProfile.get(1));
			System.out.println("Email: "+myProfile.get(2));
			System.out.println("Address: "+myProfile.get(3));
			 
			  
			 
		} else {
			System.out.println("Login to view your profile");
		}
	}
	//EDIT MY PROFILE
	public void editMyProfile() {
		//System.out.println("im in edit my profile");
		//EDIT MY PROFILE - view my profile
		InstagramUser iu = new InstagramUser();
		
		if(signedIn == true) {			
			iu.setEmail(email);
			
			List<String> myProfile = id.viewMyProfileDAO(iu);
			//System.out.println("from controller"+myProfile);
			System.out.println("Name: "+myProfile.get(0));
			System.out.println("Password: "+myProfile.get(1));
			System.out.println("Email: "+myProfile.get(2));
			System.out.println("Address: "+myProfile.get(3));
		} else {
			System.out.println("Login to edit your profile"); 
			return;
		}
		
		//EDIT MY PROFILE - edit my profile
		int i=0;
		try {
			System.out.println("Enter new name: ");
			name = br.readLine();
			System.out.println("Enter new password: ");
			password = br.readLine();
			System.out.println("Enter new email: ");
			newEmail = br.readLine();
			System.out.println("Enter new address: ");
			address = br.readLine();
			iu.setName(name);
			iu.setPassword(password);
			iu.setNewEmail(newEmail);
			iu.setAddress(address);
			
			i = id.editMyProfile(iu);
			System.out.println(i);
			if(i>0) {
				System.out.println("Profile edited successfully");
				iu.setEmail(newEmail);
			} else {
				System.out.println("Profile edit failed");
			}
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	//VIEW ALL PROFILE
	public void viewAllProfile() {
		List<ArrayList<String>> allProfile = id.viewAllProfile();
		System.out.println("NAME\t\t\tEMAIL\t\t\t\t\tADDRESS");
		for (int i = 0; i < allProfile.size(); i++) {
			for (int j = 0; j < allProfile.get(i).size(); j++) {
				System.out.print(allProfile.get(i).get(j) + "\t\t\t");
			}
			System.out.println();
		}
	}
	//DELETE PROFILE
	public void deleteProfile() {
		if(signedIn == true) {
			try {				
				System.out.println("Are you sure, you want to delete your account permanently?");
				String ch;
				ch  = br.readLine();
				if(ch.equalsIgnoreCase("y")) {
					InstagramUser iu = new InstagramUser();
					iu.setEmail(email);
					int i = id.deleteProfile(iu);
					if(i>0) {
						System.out.println("Your profile "+iu.getEmail()+" deleted successfully..!");
						
						  iu.setName(null);
						  iu.setPassword(null);
						  iu.setEmail(null);
						  iu.setPassword(null);
						
						signedIn = false;
					}
				}
			} catch(Exception e) {
				e.printStackTrace();
			}
		} else {
			System.out.println("SignIn to delete your account..!");
		}
	}
	//SIGNOUT
	public void signOut() {
		InstagramUser iu = new InstagramUser();
		if(signedIn == true) {
			  iu.setName(null);
			  iu.setPassword(null);
			  iu.setEmail(null);
			  iu.setPassword(null);
			  
			  signedIn = false;
			  System.out.println("Successfully signed out from your account");
		} else {
			System.out.println("Currently not signedIn to any account..!");
		}
	}
	//SEARCH PROFILE
	public void searchProfile() {
		InstagramUser iu = new InstagramUser();
		try {
			System.out.println("Enter profile name to search: ");
			String searchName = br.readLine();
			iu.setSearchName(searchName);
			List<ArrayList<String>> searchProfile = id.searchProfile(iu);
			//System.out.println(searchProfile);
			System.out.println("NAME\t\t\tEMAIL\t\t\t\t\tADDRESS");
			for (int i = 0; i < searchProfile.size(); i++) {
				for (int j = 0; j < searchProfile.get(i).size(); j++) {
					System.out.print(searchProfile.get(i).get(j) + "\t\t\t");
				}
				System.out.println();
			}
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	
	
}
