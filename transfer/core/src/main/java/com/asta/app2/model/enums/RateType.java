/* *Class created on [ Jul 21, 2008 | 5:38:46 PM ] */ 
package com.asta.app2.model.enums;

import com.asta.app2.util.BundleUtil;
	
/**
 *
 * @author <a href="mailto:saeid3@gmail.com">Saeid Moradi</a>
 */
public enum RateType {
	ORDINARY,SPRING;
	
	public String getLabel(){
		switch(this){
			case ORDINARY :
				return BundleUtil.getMessageBundle("rateType.ordinary");
			case SPRING :
				return BundleUtil.getMessageBundle("rateType.spring");
		}
		return "nuKnown";
	}
}


