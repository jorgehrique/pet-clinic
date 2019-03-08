package petclinic.controllers;

import model.Owner;
import org.apache.logging.log4j.util.PropertySource;
import org.hibernate.mapping.Collection;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import services.OwnerService;

import java.util.*;

@RequestMapping("owners")
@Controller
public class OwnerController {

    private final OwnerService ownerService;

    public OwnerController(OwnerService ownerService) {
        this.ownerService = ownerService;
    }

    @RequestMapping({"", "index", "index.html"})
    public String listOwners(Model model) {
        SortedSet<Owner> sortedOwners = ownerSorter(ownerService.findAll());
        model.addAttribute("owners", sortedOwners);
        return "owners/index";
    }

    private SortedSet<Owner> ownerSorter(Set<Owner> owners){
        SortedSet<Owner> sortedOwners = new TreeSet<>(Comparator.comparing(e-> e.getFirstName()));
        sortedOwners.addAll(owners);
        return sortedOwners;
    }
}
