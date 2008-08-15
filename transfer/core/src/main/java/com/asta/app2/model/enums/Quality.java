/* *Class created on [ Jun 1, 2008 | 6:12:02 PM ] */
package com.asta.app2.model.enums;

import com.asta.app2.util.BundleUtil;

/**
 * 
 * @author <a href="mailto:saeid3@gmail.com">Saeid Moradi</a>
 */
public enum Quality {
	UP, NORMAL;
	
	public String getLabel(){
		switch(this){
		case UP:
			return BundleUtil.getMessageBundle("quality.up");
		case NORMAL:
			return BundleUtil.getMessageBundle("quality.normal");
		}
		return "Unknown";
	}
}
