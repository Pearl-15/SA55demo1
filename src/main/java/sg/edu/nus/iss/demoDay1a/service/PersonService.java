package sg.edu.nus.iss.demoDay1a.service;

import java.util.ArrayList;
import java.util.List;

import javax.lang.model.type.NullType;

import org.springframework.stereotype.Service;

import sg.edu.nus.iss.demoDay1a.model.Person;

@Service
public class PersonService {
    private List<Person>  persons = new ArrayList<Person>();

    public PersonService(){
        persons.add(new Person("Mark", "Kwan"));
        persons.add(new Person("Daryl", "Eddie"));
    }

    public List<Person> getPersons(){
        return this.persons;
    }

    //Create
    public void addPerson(Person newPerson){
        persons.add(new Person(newPerson.getFirstName(), newPerson.getLastName()));
    }

    //Delete
    public void removerPerson(Person personToDelect){

        //find the person in the "persons" list, if found then remove
        Person foundPerson = persons.stream().filter(p -> p.getId().equals(personToDelect.getId())).findAny().orElse(null);
        persons.remove(foundPerson);
    }

    //Update
    public void updatePerson(Person personToUpdate){
        Person foudPerson = persons.stream().filter(p -> p.getId().equals(personToUpdate.getId())).findAny().orElse(null);

        //create new object (why???)
        Person updatePerson = new Person();
        updatePerson.setId(personToUpdate.getId());
        updatePerson.setFirstName(personToUpdate.getFirstName());
        updatePerson.setLastName((personToUpdate.getLastName()));

        //remover original record
        persons.remove(foudPerson);

        //Add into original record
        persons.add(updatePerson);  //cannot add (personToUpdate)???        
    }

    
}
