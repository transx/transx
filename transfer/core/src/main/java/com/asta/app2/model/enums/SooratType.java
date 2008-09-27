/* *Class created on [ Sep 10, 2008 | 2:56:38 PM ] */ 
package com.asta.app2.model.enums;

import com.asta.app2.util.BundleUtil;
	
/**
 *
 * @author <a href="mailto:saeid3@gmail.com">Saeid Moradi</a>
 */
public enum SooratType {
	INNER,OUTER,PRIVATE;
	
	public String getLabel(){
		switch(this){
			case INNER:
				return BundleUtil.getMessageBundle("soorat.inner");
			case OUTER:
				return BundleUtil.getMessageBundle("soorat.outer");
			case PRIVATE:
				return BundleUtil.getMessageBundle("soorat.private");
		}
		return "Unknown";
	}
}


