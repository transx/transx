/* *Class created on [ Jun 3, 2008 | 11:35:44 AM ] */ 
package com.asta.app2.model.enums;

import com.asta.app2.util.BundleUtil;
	
/**
 *
 * @author <a href="mailto:saeid3@gmail.com">Saeid Moradi</a>
 */
public enum Gender {
	MALE,FEMALE;
	
	public String getLabel(){
		switch(this){
			case MALE:
				return BundleUtil.getMessageBundle("gender.male");
			case FEMALE:
				return BundleUtil.getMessageBundle("gender.female");
		}
		return "Unknown";
	}
	public String getLabelShort(){
		switch(this){
		case MALE:
			return BundleUtil.getMessageBundle("gender.male.short");
		case FEMALE:
			return BundleUtil.getMessageBundle("gender.female.short");
		}
		return "Unknown";
	}
}


