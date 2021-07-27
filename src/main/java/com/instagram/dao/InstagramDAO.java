package com.instagram.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.instagram.entity.InstagramUser;

public class InstagramDAO implements InstagramDAOInterface {
	
	private Connection con;
	//Constructor
	//jdbc connection
	public InstagramDAO() {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/instagram_db","root","admin");
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	//CREATE PROFILE DAO
	public int createProfileDAO(InstagramUser iu) {
		int i=0;
		
		try {
			
			PreparedStatement ps = con.prepareStatement("insert into instagramUser values (?,?,?,?)");
			
			ps.setString(1, iu.getName());
			ps.setString(2, iu.getPassword());
			ps.setString(3, iu.getEmail());
			ps.setString(4, iu.getAddress());
			
			i=ps.executeUpdate();
			
		} catch(Exception e) {
			e.printStackTrace();
		}
		
		return i;
	}

	//LOGIN PROFILE DAO
	public boolean loginProfileDAO(InstagramUser iu) {
		boolean b= false;
		try {
			PreparedStatement ps = con.prepareStatement("select * from instagramUser where email=? and password=?");
			ps.setString(1, iu.getEmail());
			ps.setString(2, iu.getPassword());
			
			ResultSet res = ps.executeQuery();
			if(res.next()) {
				b= true;
			}
			
		} catch(Exception e) {
			e.printStackTrace();
		}
		return b;
	}
	
	//VIEW MY PROFILE DAO
	public List<String> viewMyProfileDAO(InstagramUser iu) {
		List<String> myProfile = new ArrayList<String>();
		try {
			PreparedStatement ps = con.prepareStatement("select * from instagramUser where email=?");
			ps.setString(1, iu.getEmail());
			//System.out.println("from InstagramDAO "+iu.getEmail());
			ResultSet res = ps.executeQuery();
			
			if(res.next()) {
					//System.out.println("im in DAO if");
				    myProfile.add(res.getString(1));
					myProfile.add(res.getString(2));
					myProfile.add(res.getString(3));
					myProfile.add(res.getString(4)); 
			}
		} catch(Exception e) {
			e.printStackTrace(); 
		}
		return myProfile;
	}
	//EDIT MY PROFILE
	public int editMyProfile(InstagramUser iu) {
		int i=0;
		try {
			PreparedStatement ps = con.prepareStatement("update instagramUser set name=?, password=?, email=?, address=? where email=?");
			ps.setString(1, iu.getName());
			ps.setString(2, iu.getPassword());
			ps.setString(3, iu.getNewEmail());
			ps.setString(4, iu.getAddress());
			ps.setString(5, iu.getEmail());
			
			System.out.println(iu.getName());
			System.out.println(iu.getPassword());
			System.out.println(iu.getNewEmail());
			System.out.println(iu.getAddress());
			System.out.println(iu.getEmail());
			
			
			i = ps.executeUpdate();
			
		} catch(Exception e) {
			e.printStackTrace();
		}
		return i;
	}
	//VIEW ALL PROFILE
	public ArrayList<ArrayList<String>> viewAllProfile() {
		ArrayList<ArrayList<String> > allProfile = new ArrayList<ArrayList<String> >();
		try {
			int i=0;
			PreparedStatement ps = con.prepareStatement("select name, email, address from instagramUser");
			ResultSet res = ps.executeQuery();
			while(res.next()) {
				allProfile.add(new ArrayList<String>());
				allProfile.get(i).add(0,res.getString(1));
				allProfile.get(i).add(1,res.getString(2));
				allProfile.get(i).add(2,res.getString(3));
				i++;
			}
		} catch(Exception e) {
			e.printStackTrace();
		}
		return allProfile;
	}
	//DELETE PROFILE
	public int deleteProfile(InstagramUser iu) {
		int i=0;
		try {
			PreparedStatement ps = con.prepareStatement("delete from instagramUser where email=?");
			ps.setString(1, iu.getEmail());
			i = ps.executeUpdate();
			
		} catch(Exception e) {
			e.printStackTrace();
		}
		return i;
	}
	public ArrayList<ArrayList<String>> searchProfile(InstagramUser iu) {
		ResultSet res;
		int i=0;
		ArrayList<ArrayList<String> > searchProfile = new ArrayList<ArrayList<String> >();
		try {
			PreparedStatement ps = con.prepareStatement("select name, email, address from instagramUser where name=?");
			ps.setString(1, iu.getSearchName());
			res = ps.executeQuery();
			while(res.next()) {
				searchProfile.add(new ArrayList<String>());
				searchProfile.get(i).add(0,res.getString(1));
				searchProfile.get(i).add(1,res.getString(2));
				searchProfile.get(i).add(2,res.getString(3));
				i++;
			}
		} catch(Exception e) {
			e.printStackTrace();
		}
		return searchProfile;
	}
	
}
