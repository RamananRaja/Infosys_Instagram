package com.instagram.view;

import java.io.BufferedReader;
import java.io.InputStreamReader;

import com.instagram.controller.InstagramControllerInterface;
import com.instagram.utility.ControllerFactory;

public class InstagramView {

	public static void main(String[] args) {
		String s = "y";
		
		while (s.equalsIgnoreCase("y")) {
			System.out.println("*******************MAIN MENU*******************");
			System.out.println("1.SignUp");
			System.out.println("2.SignIn");
			System.out.println("3.View my profile");
			System.out.println("4.Edit my profile");
			System.out.println("5.View all profile");
			System.out.println("6.Delete my profile");
			System.out.println("7.SignOut");
			System.out.println("8.Search profile");
			System.out.println("*******************MAIN MENU*******************");
			System.out.println("Enter your choice: ");

			try {
				BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
				int choice = Integer.parseInt(br.readLine());

				InstagramControllerInterface ic = ControllerFactory.createObject();

				switch (choice) {
				case 1:
					ic.createProfile();
					break;
				case 2:
					ic.loginProfile();
					break;
				case 3:
					ic.viewMyProfile();
					break;
				case 4:
					ic.editMyProfile();
					break;				
				case 5:
					ic.viewAllProfile();
					break;
				case 6:
					ic.deleteProfile();
					break;
				case 7:
					ic.signOut();
					break;
				case 8:
					ic.searchProfile();
					break;
				default:
					System.out.println("Invalid Input");
					break;
				}
					
				System.out.println("Do you want to continue (y/n): ");
				s = br.readLine();
				
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		System.out.println("APPLICATION CLOSED");
		return;
	}

}
