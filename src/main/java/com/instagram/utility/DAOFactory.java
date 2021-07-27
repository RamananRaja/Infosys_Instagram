package com.instagram.utility;

import com.instagram.dao.InstagramDAO;
import com.instagram.dao.InstagramDAOInterface;

public class DAOFactory {

	public static InstagramDAOInterface myMethod(){
		return new InstagramDAO();
	}

}
