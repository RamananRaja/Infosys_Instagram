package com.instagram.dao;

import java.util.ArrayList;
import java.util.List;

import com.instagram.entity.InstagramUser;

public interface InstagramDAOInterface {
	
	int createProfileDAO(InstagramUser iu);

	boolean loginProfileDAO(InstagramUser iu);

	List<String> viewMyProfileDAO(InstagramUser iu);

	ArrayList<ArrayList<String>> viewAllProfile();

	int deleteProfile(InstagramUser iu);

	int editMyProfile(InstagramUser iu);

	ArrayList<ArrayList<String>> searchProfile(InstagramUser iu);

}
