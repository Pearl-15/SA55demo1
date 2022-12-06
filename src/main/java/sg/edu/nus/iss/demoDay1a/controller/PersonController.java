package sg.edu.nus.iss.demoDay1a.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import sg.edu.nus.iss.demoDay1a.model.Person;
import sg.edu.nus.iss.demoDay1a.service.PersonService;

@Controller
@RequestMapping("/persons")
public class PersonController {
    private List<Person> personList = new ArrayList<Person>();

    @Autowired
    PersonService personService;

    // Autowoired is equvalent to ???
    

    @Value("${welcome.message}")
    private String welcomeMessage;

    @Value("${person.list.header}")
    private String headerMessage;

    @GetMapping(value = { "/", "/home" })
    public String index(Model model) {

        model.addAttribute("message", welcomeMessage);
        return "home";

    }

    // Responsebody = data that come back (obj type)
    @GetMapping(value = "/testRetrieveAllPerson", produces = "application/json")
    public @ResponseBody List<Person> getAllPersons() {

        //
        personList = personService.getPersons();

        return personList;
    }

    @GetMapping(value = "/list")
    public String personList(Model model){
        personList = personService.getPersons();
        model.addAttribute("persons", personList);
        model.addAttribute("listOfPersons", headerMessage);

        return "personList";
    }

    @PostMapping(value = "/update")
    //@ModelAttribute is the data that passed from View when click Update Button
    public String updatePerson(@ModelAttribute(value="per") Person p,Model model){
        model.addAttribute("per", p);
        return "personEdit";

    }

    @PostMapping(value = "/updatePerson")  
    public String updatePersonRecord(@ModelAttribute(value="person") Person p){ //why we doesn't use value = person at somewhere
        personService.updatePerson(p);
        return "redirect:/persons/list";
    }

    @PostMapping(value= "/deletePerson")
    public String deletePerson(@ModelAttribute(value = "person")Person p){
        personService.removerPerson(p);
        return "redirect: /persons/list";
    }




}
