/* *Class created on [ Aug 24, 2008 | 11:45:06 PM ] */ 
package com.asta.app2.tutorial.hellotrinidad;


import javax.faces.context.FacesContext;

//import org.apache.myfaces.trinidad.render.ExtendedRenderKitService;
//import org.apache.myfaces.trinidad.util.Service;


/**
 *
 * @author <a href="mailto:saeid3@gmail.com">Saeid Moradi</a>
 */

/**
 * A typical simple backing bean, that is backed to <code>sayHello.xhtml</code>
 * 
 */
/*public class HelloTrinidadBacking
{

  //properties
  private String _name;

  *//**
   * default empty constructor
   *//*
  public HelloTrinidadBacking()
  {   
  }

  //-------------------getter & setter
  public String getName()
  {
    return _name;
  }

  public void setName(String name)
  {
    this._name = name;
  }

  *//**
   * Method that is backed to a submit button of a form.
   *//*
  public String send()
  {
    FacesContext facesContext = FacesContext.getCurrentInstance();
    ExtendedRenderKitService service = (ExtendedRenderKitService)
      Service.getRenderKitService(facesContext, ExtendedRenderKitService.class);
    service.addScript(facesContext, "alert('Script added by ExtendedRenderKitService')");

    //do real logic
    return ("success");
  }
}*/