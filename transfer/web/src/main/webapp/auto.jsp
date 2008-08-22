<html>
   <%@ taglib uri="http://java.sun.com/jsf/core" prefix="f" %>
   <%@ taglib uri="http://java.sun.com/jsf/html" prefix="h" %>   
   <f:view>
      <head> 
         <link href="styles.css" rel="stylesheet" type="text/css"/> 
         <title>
            <h:outputText value="#{msgs.indexWindowTitle}"/>
         </title>
      </head>

      <body>
         <h:outputText value="#{msgs.indexPageTitle}" styleClass="emphasis"/>
         <h:form>
            <table>
               <tr>
                  <td>
                     <h:outputText value="#{msgs.namePrompt}"/>
                  </td>
                  <td>
                     <h:inputText value="#{form.name}"/>
                  </td>
               </tr>
               <tr>
                  <td>
                     <h:outputText value="#{msgs.contactMePrompt}"/>
                  </td>
                  <td>
                     <h:selectBooleanCheckbox value="#{form.contactMe}"/>
                  </td>
               </tr>
               <tr>
                  <td>
                     <h:outputText value="#{msgs.bestDayPrompt}"/>
                  </td>
                  <td>
                     <h:selectManyMenu value="#{form.bestDaysToContact}" >
                        <f:selectItems value="#{form.daysOfTheWeekItems}"/>
                     </h:selectManyMenu>
                  </td>
               </tr>
               <tr>
                  <td>
                     <h:outputText value="#{msgs.yearOfBirthPrompt}"/>
                  </td>
                  <td>
                     <h:selectOneListbox size="5" value="#{form.yearOfBirth}">
                        <f:selectItems value="#{form.yearItems}" />
                     </h:selectOneListbox>
                  </td>
               </tr>
               <tr>
                  <td>
                     <h:outputText value="#{msgs.colorPrompt}"/>
                  </td>
                  <td>
                     <h:selectManyCheckbox value="#{form.colors}" >
               			<f:selectItem itemValue="1 " itemLabel="1" itemDisabled="false"/>
               			<f:selectItem itemValue="2 " itemLabel="2" itemDisabled="true"/>
               			<f:selectItem itemValue="3 " itemLabel="3" itemDisabled="false"/>
               			<f:selectItem itemValue="4 " itemLabel="4" itemDisabled="true"/>
                     </h:selectManyCheckbox>
                  </td>
               </tr>
               <tr>
                  <td>
                     <h:outputText value="#{msgs.languagePrompt}"/>
                  </td>
                  <td>
                     <h:selectManyListbox value="#{form.languages}">
                        <f:selectItems value="#{form.languageItems}"/>
                     </h:selectManyListbox>
                  </td>
               </tr>
               <tr>
                  <td>
                     <h:outputText value="#{msgs.educationPrompt}"/>
                  </td>
                  <td>
                     <h:selectOneRadio value="#{form.education}" layout="pageDirection">
                        <f:selectItems value="#{form.educationItems}"/>
                     </h:selectOneRadio>
                     <h:selectBooleanCheckbox label="" >
                     
                     </h:selectBooleanCheckbox>
                     <h:graphicImage value="" rendered=""></h:graphicImage>
                     <h:inputText binding="" disabled="" converter=""></h:inputText>
                     <f:convertNumber pattern="###,###.00"/>
                  </td>
               </tr>
            </table>
            <h:commandButton value="#{msgs.buttonPrompt}" 
               action="showInformation"/>
         </h:form>
         <h:messages/>
      </body>
   </f:view>
</html>  
