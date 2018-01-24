package introsde.assignment3.soap.ws;

import introsde.assignment3.soap.model.*;
import introsde.assignment3.soap.util.DatabaseInitResource;

import java.util.List;

import javax.jws.WebService;

//Service Implementation

@WebService(endpointInterface = "introsde.assignment3.soap.ws.People",
    serviceName="PeopleService")
public class PeopleImpl implements People {
	
	/* 
	 *  DATABASE_INIT
	 *  Called only once at first request to this resource
	 */
	static {
		DatabaseInitResource.getInitialPersonsList();
	}
	
    @Override
    public Person readPerson(int id) {
        System.out.println("---> Reading Person by id = "+id);
        Person p = Person.getPersonById(id);
        if (p!=null) {
            System.out.println("---> Found Person by id = "+id+" => "+p.getFirstname());
        } else {
            System.out.println("---> Didn't find any Person with  id = "+id);
        }
        return p;
    }
    
	@Override
	public List<Person> getPeople() {
		return Person.getAll();
	}

	@Override
	public Person updatePerson(Person person) {
        Person.updatePerson(person);
        return person;
	}

	@Override
	public Person addPerson(Person person) {
		Person.savePerson(person);
        return person;
	}

	@Override
	public int deletePerson(int id) {
		 Person p = Person.getPersonById(id);
	        if (p!=null) {
	            Person.removePerson(p);
	            return 0;
	        } else {
	            return -1;
	        }
	}

	@Override
	public List<Activity> getPersonActivityPreferencesByType(
										long idPerson, String activityType) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Activity> getAllActivityPreferences() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Activity> getPersonActivityPreferencesById(int idPerson, int idActivity) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void savePersonActivity(int idActivity, Activity activity) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<Activity> updatePersonActivityPreferences(
											int idPerson, Activity activity) {
		// TODO Auto-generated method stub
		return null;
	}


}