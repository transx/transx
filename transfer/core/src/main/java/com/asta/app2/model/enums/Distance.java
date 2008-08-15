/* *Class created on [ Jul 26, 2008 | 11:03:42 AM ] */ 
package com.asta.app2.model.enums;

import com.asta.app2.util.BundleUtil;
	
/**
 *
 * @author <a href="mailto:saeid3@gmail.com">Saeid Moradi</a>
 */
public enum Distance {
	DOWN300, UP300;
	
	public String getLabel(){
		switch(this){
			case DOWN300:
				return BundleUtil.getMessageBundle("distance.down300");
			case UP300:
				return BundleUtil.getMessageBundle("distance.up300");
		}
		return "Unknown";
	}
}

