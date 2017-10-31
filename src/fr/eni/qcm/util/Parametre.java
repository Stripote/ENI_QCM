package fr.eni.qcm.util;

import java.util.ResourceBundle;

public class Parametre {

	public static String lire(String cle){
		ResourceBundle bundle = ResourceBundle.getBundle("fr.eni.qcm.util.param");
		
		return (null!=bundle) ? bundle.getString(cle) : null;
	}

}
