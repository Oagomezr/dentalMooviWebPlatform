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
        Categories desechables = new Categories(null, "Desechables", null, null);
        Categories cuidadoDental = new Categories(null, "Higiene oral", null, null);
        Categories instrumentacionDental = new Categories(null, "Instrumentación dental", null, null);
        Categories ortodoncia = new Categories(null, "Ortodoncia", null, null);
        Categories ortopedia = new Categories(null, "Ortopedia", null, null);
        Categories rehabilitacionOral = new Categories(null, "Rehabilitación Oral", null, null);
        categoriesSet.addAll(List.of(desechables, cuidadoDental, instrumentacionDental, ortodoncia, ortopedia, rehabilitacionOral));

        // Sub-categories
        // Dental instrumentation sub-categories
        categoriesSet.add( new Categories(null, "Forceps", instrumentacionDental, null) );
        categoriesSet.add( new Categories(null, "Hoja de bisturí", null, null) );
        categoriesSet.add( new Categories(null, "Jeringas",  instrumentacionDental, null) );
        categoriesSet.add( new Categories(null, "Mangos para bisturí",  instrumentacionDental, null) );
        categoriesSet.add( new Categories(null, "Pinzas",  instrumentacionDental, null) );
        categoriesSet.add( new Categories(null, "Porta agujas", instrumentacionDental, null) );
        categoriesSet.add( new Categories(null, "Tijeras", instrumentacionDental, null) );

        // Orthodontics sub-categories
        categoriesSet.add( new Categories(null, "Alambres", ortodoncia, null) );
        categoriesSet.add( new Categories(null, "Arcos", ortodoncia, null) );
        categoriesSet.add( new Categories(null, "Auxiliares", ortodoncia, null) );
        categoriesSet.add( new Categories(null, "Brackets", ortodoncia, null) );
        categoriesSet.add( new Categories(null, "Distalizador", ortodoncia, null) );
        categoriesSet.add( new Categories(null, "Elastomeros", ortodoncia, null) );
        categoriesSet.add( new Categories(null, "Instrumentos ortodonticos", ortodoncia, null) );
        categoriesSet.add( new Categories(null, "Microimplantes", ortodoncia, null) );
        categoriesSet.add( new Categories(null, "Pinzas", ortodoncia, null) );
        categoriesSet.add( new Categories(null, "Tubos", ortodoncia, null) );

        // Oral rehabilitation sub-categories
        categoriesSet.add( new Categories(null, "Adhesivos", rehabilitacionOral, null) );
        categoriesSet.add( new Categories(null, "Bisacrylicos", rehabilitacionOral, null) );
        categoriesSet.add( new Categories(null, "Cemento", rehabilitacionOral, null) );
        categoriesSet.add( new Categories(null, "Compomeros", rehabilitacionOral, null) );
        categoriesSet.add( new Categories(null, "Postes", rehabilitacionOral, null) );
        categoriesSet.add( new Categories(null, "Provicionales", rehabilitacionOral, null) );
        categoriesSet.add( new Categories(null, "Rebases", rehabilitacionOral, null) );
        categoriesSet.add( new Categories(null, "Reconstructor", rehabilitacionOral, null) );
        categoriesSet.add( new Categories(null, "Resinas", rehabilitacionOral, null) );

        //categoriesSet.add( new Categories(null, null, rehabilitacionOral, null) );

        categoriesRep.saveAll(categoriesSet);
    }
}
