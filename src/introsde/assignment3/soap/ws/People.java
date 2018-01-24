package introsde.assignment3.soap.ws;

import introsde.assignment3.soap.model.*;
import java.util.List;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;
import javax.jws.WebResult;
import javax.jws.soap.SOAPBinding;
import javax.jws.soap.SOAPBinding.Style;
import javax.jws.soap.SOAPBinding.Use;

@WebService
@SOAPBinding(style = Style.DOCUMENT, use=Use.LITERAL) //optional
public interface People {
    @WebMethod(operationName="readPersonList") // Method 1
    @WebResult(name="people") 
    public List<Person> getPeople();

    @WebMethod(operationName="readPerson") // Method 2
    @WebResult(name="person") 
    public Person readPerson(@WebParam(name="idPerson") int id); 

    @WebMethod(operationName="updatePerson") // Method 3
    @WebResult(name="updatedPerson") 
    public Person updatePerson(@WebParam(name="person") Person person);

    @WebMethod(operationName="createPerson") // Method 4
    @WebResult(name="createdPerson") 
    public Person addPerson(@WebParam(name="person") Person person);
    
    @WebMethod(operationName="deletePersonById") //Method 5
    @WebResult(name="idPerson") 
    public int deletePerson(@WebParam(name="id") int id);
    
    
    @WebMethod(operationName="readPersonPreferencesByType") //Method 6
    @WebResult(name="personPreferences")
    public List<Activity> getPersonActivityPreferencesByType(
    						@WebParam(name="idPerson") int idPerson,
    						@WebParam(name="type") String activityType); 
    
    @WebMethod(operationName="readPreferences") //Method 7
    @WebResult(name="allPreferences")
    public List<Activity> getAllActivityPreferences();
    
    
    @WebMethod(operationName="readPersonPreferencesById") //Method 8
    @WebResult(name="personPreferences")
    public Activity getPersonActivityPreferencesById(
    						@WebParam(name="idPerson") int idPerson,
    						@WebParam(name="idActivity") int idActivity); 
    
    @WebMethod(operationName="savePersonPreferences") //Method 9
    @WebResult(name="personPreferences")
    public void savePersonActivity(@WebParam(name="idPerson") int idPerson,
    								@WebParam(name="activity") Activity activity);
    
    @WebMethod(operationName="updatePersonPreferences") //Method 10
    @WebResult(name="personPreferences")
    public Activity updatePersonActivityPreferences(
    						@WebParam(name="idPerson") int idPerson,
    						@WebParam(name="activity") Activity activity); 
    
    
    
}