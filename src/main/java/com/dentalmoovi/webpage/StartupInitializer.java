package com.dentalmoovi.webpage;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.dentalmoovi.webpage.models.Categories;
import com.dentalmoovi.webpage.models.Roles;
import com.dentalmoovi.webpage.repositories.ICategoriesRep;
import com.dentalmoovi.webpage.repositories.IRolesRep;

import jakarta.annotation.PostConstruct;

@Component
public class StartupInitializer {

    @Autowired
    private IRolesRep rolesRep;

    @Autowired
    private ICategoriesRep categoriesRep;
    
    @PostConstruct
    public void init(){

        Set<Roles> rolesSet = new HashSet<>();

        rolesSet.add( new Roles(null, "USER", null) );
        rolesSet.add( new Roles(null, "ADMIN", null) );
        rolesRep.saveAll(rolesSet);

        Set<Categories> categoriesSet = new HashSet<>();

        // Parent categories
        Categories desechables = new Categories(null, "DESECHABLES", null, null);
        Categories cuidadoDental = new Categories(null, "HIGIENE ORAL", null, null);
        Categories instrumentacionDental = new Categories(null, "INSTRUMENTACIÓN DENTAL", null, null);
        Categories ortodoncia = new Categories(null, "ORTODONCIA", null, null);
        Categories ortopedia = new Categories(null, "ORTOPEDIA", null, null);
        Categories rehabilitacionOral = new Categories(null, "REHABILITACIÓN ORAL", null, null);
        categoriesSet.addAll(List.of(desechables, cuidadoDental, instrumentacionDental, ortodoncia, ortopedia, rehabilitacionOral));

        //Especial Sub-categories
        Categories brakets = new Categories(null, "BRACKETS", ortodoncia, null);

        // Sub-categories
        // Dental instrumentation sub-categories
        categoriesSet.add(new Categories(null, "HOJA DE BISTURÍ", instrumentacionDental, null));
        categoriesSet.add(new Categories(null, "JERINGAS",  instrumentacionDental, null));
        categoriesSet.add(new Categories(null, "MANGOS PARA BISTURÍ",  instrumentacionDental, null));
        categoriesSet.add(new Categories(null, "PINZAS",  instrumentacionDental, null));
        categoriesSet.add(new Categories(null, "PORTA AGUJAS", instrumentacionDental, null));
        categoriesSet.add(new Categories(null, "TIJERAS", instrumentacionDental, null));

        // Orthodontics sub-categories
        categoriesSet.add(new Categories(null, "ALAMBRES", ortodoncia, null));
        categoriesSet.add(new Categories(null, "ARCOS", ortodoncia, null));
        categoriesSet.add(new Categories(null, "AUXILIARES", ortodoncia, null));
        categoriesSet.add(brakets);
        categoriesSet.add(new Categories(null, "DISTALIZADOR", ortodoncia, null));
        categoriesSet.add(new Categories(null, "ELASTOMEROS", ortodoncia, null));
        categoriesSet.add(new Categories(null, "INSTRUMENTOS ORTODONTICOS", ortodoncia, null));
        categoriesSet.add(new Categories(null, "MICROIMPLANTES", ortodoncia, null));
        categoriesSet.add(new Categories(null, "PINZAS", ortodoncia, null));
        categoriesSet.add(new Categories(null, "TUBOS", ortodoncia, null));

        // Brakets sub-categories
        categoriesSet.add(new Categories(null, "CARRIERE", brakets, null));
        categoriesSet.add(new Categories(null, "DELTA FORCE", brakets, null));
        categoriesSet.add(new Categories(null, "ESTANDAR", brakets, null));
        categoriesSet.add(new Categories(null, "MBT", brakets, null));
        categoriesSet.add(new Categories(null, "ROTH", brakets, null));

        // Oral rehabilitation sub-categories
        categoriesSet.add(new Categories(null, "ADHESIVOS", rehabilitacionOral, null));
        categoriesSet.add(new Categories(null, "BISACRÍLICOS", rehabilitacionOral, null));
        categoriesSet.add(new Categories(null, "CEMENTO", rehabilitacionOral, null));
        categoriesSet.add(new Categories(null, "COMPOMEROS", rehabilitacionOral, null));
        categoriesSet.add(new Categories(null, "POSTES", rehabilitacionOral, null));
        categoriesSet.add(new Categories(null, "PROVICIONALES", rehabilitacionOral, null));
        categoriesSet.add(new Categories(null, "REBASES", rehabilitacionOral, null));
        categoriesSet.add(new Categories(null, "RECONSTRUCTOR", rehabilitacionOral, null));
        categoriesSet.add(new Categories(null, "RESINAS", rehabilitacionOral, null));

        categoriesRep.saveAll(categoriesSet);
    }
}
