/* *Class created on [ Jul 12, 2008 | 10:52:29 AM ] */ 
package com.asta.app2.webapp.util.sitemesh.mapper;
	
import java.io.File;
import java.util.Locale;
import java.util.Properties;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.opensymphony.module.sitemesh.Config;
import com.opensymphony.module.sitemesh.Decorator;
import com.opensymphony.module.sitemesh.DecoratorMapper;
import com.opensymphony.module.sitemesh.Page;
import com.opensymphony.module.sitemesh.mapper.AbstractDecoratorMapper;
import com.opensymphony.module.sitemesh.mapper.DefaultDecorator;

/**
 * The LocaleDecoratorMapper maps a suitable decorator based on
 * the language and/or country of the incoming request.
 *
 * <p/>When LanguageDecoratorMapper is in the chain, it will request
 * the appropriate Decorator from its parent. It will then add an
 * extension to the filename of the Decorator and if that file exists
 * it shall be used as the Decorator instead.
 * 
 * <p/>Consider the following examples (assuming the default locale
 * of the JVM is en/US):
 * 
 * <pre>
 * Decorator filename  Language  Country  Decorator filename to use
 * mydecorator.jsp     en        GB       mydecorator-GB.jsp
 * mydecorator.jsp     en        US       mydecorator.jsp
 * mydecorator.jsp     fr        FR       mydecorator-fr-FR.jsp
 * mydecorator.jsp     fa        IR       mydecorator-fa-IR.jsp
 * mydecorator.jsp     es        US       mydecorator-es.jsp
 * </pre>
 * 
 * <p/>If a localized decorator does not exist, it will silently fallback
 * to the default locale.
 *
 * @author <a href="mailto:jason_chambers@yahoo.com">Jason Chambers</a>
 * @author <a href="mailto:saeid3@gmail.com">Saeid Moradi</a>
 *
 * @see com.opensymphony.module.sitemesh.DecoratorMapper
 */
public final class LocaleDecoratorMapper extends AbstractDecoratorMapper {
	private static final Log log = LogFactory.getLog(LocaleDecoratorMapper.class);	
    public void init(Config config, Properties properties, DecoratorMapper parent) throws InstantiationException {
        super.init(config, properties, parent);
    }

    public Decorator getDecorator(HttpServletRequest request, Page page) {
    	log.error(request.toString());
        try {
            Decorator result = null;
            final Decorator d = super.getDecorator(request, page);
            String path = modifyPath(d.getPage(), getExt(request));
            log.error(path);
            File decFile = new File(config.getServletContext().getRealPath(path));

            if (decFile.isFile()) {
                result = new DefaultDecorator(d.getName(), path, null) {
                    public String getInitParameter(String paramName) {
                        return d.getInitParameter(paramName);
                    }
                };
            }
            return result == null ? super.getDecorator(request, page) : result;
        }
        catch (NullPointerException e) {
            return super.getDecorator(request, page);
        }
    }

    /**
     * Return the extension to reflect the locale of the request.
     * 
     * <p/>The extension returned is of the form [-ll][-CC] where ll
     * is a lower-case two-letter ISO Language code as defined 
     * by ISO-639 and CC is an upper-case two-letter code as defined
     * by ISO-3166. 
     * 
     * <p/>Consider the following examples (assuming the default locale
     * of the JVM is en/US):
     * 
     * <pre>
     * Language  Country  Return
     * en        GB       GB
     * en        US       ""
     * fr        FR       fr-FR
     * fa        IR       fa-IR
     * es        US       es
     * </pre>
     * 
     * <p/>If the locale of the request matches this locale, then an empty
     * string is returned. 
     */
    private String getExt(HttpServletRequest request) {        
        String language = request.getLocale().getLanguage();
        String country = request.getLocale().getCountry();
        StringBuffer ext = new StringBuffer();
        Locale thisLocale = Locale.getDefault();
               
        if (!language.equals("") && !language.equals(thisLocale.getLanguage())) {
            // Foreign language             
            ext.append("-");
            ext.append(language);
        }
        if (!country.equals("") && !country.equals(thisLocale.getCountry())) {
            // Foreign country            
            ext.append("-");
            ext.append(country);
        }
        log.error(ext.toString());
        return ext.toString();
    }
    
    /** Change /abc/def.jsp into /abc/def-XYZ.jsp */
    private static String modifyPath(String path, String ext) {
        int dot = path.indexOf('.');
        if (dot > -1) {
            return path.substring(0, dot) + ext + path.substring(dot);
        }
        else {
            return path + '-' + ext;
        }
    }

}

