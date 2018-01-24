package introsde.assignment3.soap.ws;

import introsde.assignment3.soap.util.DatabaseInitResource;
import introsde.assignment3.soap.model.Activity;
import introsde.assignment3.soap.model.Person;

import java.util.ArrayList;
import java.util.List;

import javax.jws.WebService;
import javax.ws.rs.core.Response;

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
	        if (p != null) {
	            Person.removePerson(p);
	            return 0;
	        } else {
	            return -1;
	        }
	}

	@Override
	public List<Activity> getPersonActivityPreferencesByType(
										int idPerson, String activityType) {
		Person p = Person.getPersonById(idPerson);
    	List<Activity> acts = new ArrayList<Activity>();
    	for (Activity a: p.getActivityPreferences()) {
    		if (a.getType().equals(activityType)){
    			acts.add(a);
    		}
    	}
		return acts;
	}

	@Override
	public List<Activity> getAllActivityPreferences() {
		return Activity.getAll();
	}

	@Override
	public Activity getPersonActivityPreferencesById(int idPerson, int idActivity) {
		Person p = Person.getPersonById(idPerson);
    	List<Activity> acts = new ArrayList<Activity>();
    	for (Activity a: p.getActivityPreferences()) {
    		if (a.getIdActivity() == idActivity){
    			return a;
    		}
    	}
		return null;
	}

	@Override
	public void savePersonActivity(int idPerson, Activity activity) {
		Person p = Person.getPersonById(idPerson);
    	List<Activity> acts = p.getActivityPreferences();
    	System.out.println("adding new activity..");
    	Activity.saveActivity(activity);
    	acts.add(activity);
    	p.setActivityPreferences(acts);
    	Person.updatePerson(p);		
	}

	@Override
	public Activity updatePersonActivityPreferences(
											int idPerson, Activity activity) {
		System.out.println("--> Updating Activity for... " + idPerson);
        Response res;
        Person existing = Person.getPersonById(idPerson);
        Activity a = null;
        if (existing == null) {
            return null;
        } else {
        	boolean activity_exist = false;
        	//get activities of existing person
        	List<Activity> acts = existing.getActivityPreferences();
        	for (int i = 0; i < acts.size(); i++) {
        		a = acts.get(i);
        		if (a.getIdActivity() == activity.getIdActivity()) {
        			activity_exist = true;
        			a.setDescription(activity.getDescription());
        			a.setName(activity.getName());
        			a.setPlace(activity.getPlace());
        			a.setStartdate(activity.getStartdate());
        			a.setType(activity.getType()); 	
        			a.setPerson(existing);
        			System.out.println(a.getPlace());
            		a = Activity.updateActivity(a);
            		Person.updatePerson(existing);
            		return a;
        		}
        	}
        	if ( !activity_exist) {
        		return null;
//        		throw new RuntimeException("Update: Activity with id " + activity_id
//                        + " not found");
        	}
        }
        return null;
	}


}