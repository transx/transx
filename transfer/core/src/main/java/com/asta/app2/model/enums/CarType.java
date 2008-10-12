/* *Class created on [ Jun 1, 2008 | 6:17:59 PM ] */
package com.asta.app2.model.enums;

import com.asta.app2.util.BundleUtil;


/**
 * 
 * @author <a href="mailto:saeid3@gmail.com">Saeid Moradi</a>
 */
public enum CarType {
	TYPE1, TYPE2;
	
	public String getLabel(){
		switch(this){
			case TYPE1:
				return BundleUtil.getMessageBundle("carType.type1");
			case TYPE2:
				return BundleUtil.getMessageBundle("carType.type2");
		}
		return "Unknown";
	}
}
