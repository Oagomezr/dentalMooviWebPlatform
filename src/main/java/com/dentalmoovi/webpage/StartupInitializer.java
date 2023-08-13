package com.dentalmoovi.webpage;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.dentalmoovi.webpage.models.Categories;
import com.dentalmoovi.webpage.models.Images;
import com.dentalmoovi.webpage.models.Products;
import com.dentalmoovi.webpage.models.Roles;
import com.dentalmoovi.webpage.repositories.ICategoriesRep;
import com.dentalmoovi.webpage.repositories.IImagesRep;
import com.dentalmoovi.webpage.repositories.IProductsRep;
import com.dentalmoovi.webpage.repositories.IRolesRep;
import com.dentalmoovi.webpage.utils.ImageUtils;

import jakarta.annotation.PostConstruct;

@Component
public class StartupInitializer {

    @Autowired
    private IRolesRep rolesRep;

    @Autowired
    private ICategoriesRep categoriesRep;

    @Autowired
    private IProductsRep productsRep;

    @Autowired
    private IImagesRep imagesRep;

    @Autowired
    private ImageUtils imageUtils;
    
    @PostConstruct
    public void init(){

        //Roles Part ----------------------------------------------------------------------------------------------
        Set<Roles> rolesSet = new HashSet<>();

        rolesSet.add( new Roles(null, "USER", null) );
        rolesSet.add( new Roles(null, "ADMIN", null) );
        rolesRep.saveAll(rolesSet);

        // Categories Part ------------------------------------------------------------------------------------
        Set<Categories> categoriesSet = new HashSet<>();

        // Parent categories
        Categories desechables = new Categories(null, "DESECHABLES", null, null);
        Categories cuidadoDental = new Categories(null, "HIGIENE ORAL", null, null);
        Categories instrumentacionDental = new Categories(null, "INSTRUMENTACIÓN DENTAL", null, null);
            // Dental instrumentation sub-categories
            Categories hojaBisturi = new Categories(null, "HOJA DE BISTURÍ", instrumentacionDental, null);
            Categories jeringas = new Categories(null, "JERINGAS",  instrumentacionDental, null);
            Categories mangoBisturi = new Categories(null, "MANGOS PARA BISTURÍ",  instrumentacionDental, null);
            Categories pinzasDental = new Categories(null, "PINZAS",  instrumentacionDental, null);
            Categories portaAgujas = new Categories(null, "PORTA AGUJAS", instrumentacionDental, null);
            Categories tijeras = new Categories(null, "TIJERAS", instrumentacionDental, null);
            categoriesSet.addAll(List.of(hojaBisturi, jeringas, mangoBisturi, pinzasDental, portaAgujas, tijeras));
        Categories ortodoncia = new Categories(null, "ORTODONCIA", null, null);
            // Orthodontics sub-categories
            Categories alambres = new Categories(null, "ALAMBRES", ortodoncia, null);
            Categories arcos = new Categories(null, "ARCOS", ortodoncia, null);
            Categories auxiliares = new Categories(null, "AUXILIARES", ortodoncia, null);
            Categories brakets = new Categories(null, "BRACKETS", ortodoncia, null);
                // Brakets sub-categories
                Categories carriere = new Categories(null, "CARRIERE", brakets, null);
                Categories deltaForce = new Categories(null, "DELTA FORCE", brakets, null);
                Categories estandar = new Categories(null, "ESTANDAR", brakets, null);
                Categories mbt = new Categories(null, "MBT", brakets, null);
                Categories roth = new Categories(null, "ROTH", brakets, null);
                categoriesSet.addAll(List.of(carriere, deltaForce, estandar, mbt, roth));
            Categories distalizador = new Categories(null, "DISTALIZADOR", ortodoncia, null);
            Categories elastomeros = new Categories(null, "ELASTOMEROS", ortodoncia, null);
            Categories instrumentosOrtodonticos = new Categories(null, "INSTRUMENTOS ORTODONTICOS", ortodoncia, null);
            Categories microImplantes = new Categories(null, "MICROIMPLANTES", ortodoncia, null);
            Categories pinzasOrtodoncia = new Categories(null, "PINZAS", ortodoncia, null);
            Categories tubos = new Categories(null, "TUBOS", ortodoncia, null);
            categoriesSet.addAll(List.of(alambres, arcos, auxiliares, brakets, distalizador, elastomeros, instrumentosOrtodonticos, microImplantes, pinzasOrtodoncia, tubos));
        Categories ortopedia = new Categories(null, "ORTOPEDIA", null, null);
        Categories rehabilitacionOral = new Categories(null, "REHABILITACIÓN ORAL", null, null);
            // Oral rehabilitation sub-categories
            Categories adhesivos = new Categories(null, "ADHESIVOS", rehabilitacionOral, null);
            Categories bisacrilicos = new Categories(null, "BISACRÍLICOS", rehabilitacionOral, null);
            Categories cemento = new Categories(null, "CEMENTO", rehabilitacionOral, null);
            Categories compomeros = new Categories(null, "COMPOMEROS", rehabilitacionOral, null);
            Categories postes = new Categories(null, "POSTES", rehabilitacionOral, null);
            Categories provisionales = new Categories(null, "PROVICIONALES", rehabilitacionOral, null);
            Categories rebases = new Categories(null, "REBASES", rehabilitacionOral, null);
            Categories reconstructor = new Categories(null, "RECONSTRUCTOR", rehabilitacionOral, null);
            Categories resinas = new Categories(null, "RESINAS", rehabilitacionOral, null);
            categoriesSet.addAll(List.of(adhesivos, bisacrilicos, cemento, compomeros, postes, provisionales, rebases, reconstructor, resinas));
        categoriesSet.addAll(List.of(desechables, cuidadoDental, instrumentacionDental, ortodoncia, ortopedia, rehabilitacionOral));

        categoriesRep.saveAll(categoriesSet);

        Set<Products> products = new HashSet<>();

        Products adhesivo1 = new Products(null, "Adhesivo1", 10000, "description adhesivo1", 4, rehabilitacionOral, null);
        Products adhesivo2 = new Products(null, "Adhesivo2", 12000, "description adhesivo2", 4, rehabilitacionOral, null);
        Products alambre1 = new Products(null, "Alambre1", 10000, "description alambre1", 4, ortodoncia, null);
        Products alambre2 = new Products(null, "Alambre2", 12000, "description alambre2", 4, ortodoncia, null);
        Products arco1 = new Products(null, "Arco1", 10000, "description arco1", 4, ortodoncia, null);
        Products arco2 = new Products(null, "Arco2", 12000, "description arco2", 4, ortodoncia, null);
        Products auxiliar1 = new Products(null, "Auxiliar1", 12000, "description Auxiliar1", 4, ortodoncia, null);
        Products auxiliar2 = new Products(null, "auxiliar2", 12000, "description auxiliar2", 4, ortodoncia, null);
        Products bisacrilico1 = new Products(null, "bisacrilico1", 12000, "description bisacrilico1", 4, rehabilitacionOral, null);
        Products bisacrilico2 = new Products(null, "bisacrilico2", 12000, "description bisacrilico2", 4, rehabilitacionOral, null);
        Products carrierBraket1 = new Products(null, "carrierBraket1", 12000, "description carrierBraket1", 4, brakets, null);
        Products cemento1 = new Products(null, "cemento1", 12000, "description cemento1", 4, rehabilitacionOral, null);
        Products cepilloOral = new Products(null, "cepilloOral", 12000, "description cepilloOral", 4, cuidadoDental, null);
        Products compomero1 = new Products(null, "compomero1", 12000, "description compomero1", 4, rehabilitacionOral, null);
        Products compomero2 = new Products(null, "compomero2", 12000, "description compomero2", 4, rehabilitacionOral, null);
        Products deltaForceBraket1 = new Products(null, "deltaForceBraket1", 12000, "description deltaForceBraket1", 4, brakets, null);
        Products distalizador1 = new Products(null, "distalizador1", 12000, "description distalizador1", 4, ortodoncia, null);
        Products distalizador2 = new Products(null, "distalizador2", 12000, "description distalizador2", 4, ortodoncia, null);
        Products elastomeros1 = new Products(null, "elastomeros1", 12000, "description elastomeros1", 4, ortodoncia, null);
        Products elastomeros2 = new Products(null, "elastomeros2", 12000, "description elastomeros2", 4, ortodoncia, null);
        Products estandarBraket1 = new Products(null, "estandarBraket1", 12000, "description estandarBraket1", 4, brakets, null);
        Products estandarBraket2 = new Products(null, "estandarBraket2", 12000, "description estandarBraket2", 4, brakets, null);
        Products guantesLatex = new Products(null, "guantesLatex", 12000, "description guantesLatex", 4, desechables, null);
        Products hojaBisturi1 = new Products(null, "hojaBisturi1", 12000, "description hojaBisturi1", 4, hojaBisturi, null);
        Products hojaBisturi2 = new Products(null, "hojaBisturi2", 12000, "description hojaBisturi2", 4, hojaBisturi, null);
        Products instrumentoOrtodoncia1 = new Products(null, "instrumentoOrtodoncia1", 12000, "description instrumentoOrtodoncia1", 4, ortodoncia, null);
        Products instrumentoOrtodoncia2 = new Products(null, "instrumentoOrtodoncia2", 12000, "description instrumentoOrtodoncia2", 4, ortodoncia, null);
        Products jeringa1 = new Products(null, "jeringa1", 12000, "description jeringa1", 4, instrumentacionDental, null);
        Products jeringa2 = new Products(null, "jeringa2", 12000, "description jeringa2", 4, instrumentacionDental, null);
        Products mango1 = new Products(null, "mango1", 12000, "description mango1", 4, instrumentacionDental, null);
        Products mango2 = new Products(null, "mango2", 12000, "description mango2", 4, instrumentacionDental, null);
        Products mBTBraket1 = new Products(null, "mBTBraket1", 12000, "description mBTBraket1", 4, brakets, null);
        Products mBTBraket2 = new Products(null, "mBTBraket2", 12000, "description mBTBraket2", 4, brakets, null);
        Products ortopedia1 = new Products(null, "ortopedia1", 12000, "description ortopedia1", 4, ortopedia, null);
        Products ortopedia2 = new Products(null, "ortopedia2", 12000, "description ortopedia2", 4, ortopedia, null);
        Products pinzaDental1 = new Products(null, "pinzaDental1", 12000, "description pinzaDental1", 4, instrumentacionDental, null);
        Products pinzaDental2 = new Products(null, "pinzaDental2", 12000, "description pinzaDental2", 4, instrumentacionDental, null);
        Products pinzaOrtodoncia1 = new Products(null, "pinzaOrtodoncia1", 12000, "description pinzaOrtodoncia1", 4, ortodoncia, null);
        Products pinzaOrtodoncia2 = new Products(null, "pinzaOrtodoncia2", 12000, "description pinzaOrtodoncia2", 4, ortodoncia, null);
        Products portaAguja1 = new Products(null, "portaAguja1", 12000, "description portaAguja1", 4, instrumentacionDental, null);
        Products portaAguja2 = new Products(null, "portaAguja2", 12000, "description portaAguja2", 4, instrumentacionDental, null);
        Products protectorCepillo = new Products(null, "protectorCepillo", 12000, "description protectorCepillo", 4, cuidadoDental, null);
        Products provicionales1 = new Products(null, "provicionales1", 12000, "description provicionales1", 4, rehabilitacionOral, null);
        Products provicionales2 = new Products(null, "provicionales2", 12000, "description provicionales2", 4, rehabilitacionOral, null);
        Products rebase1 = new Products(null, "rebase1", 12000, "description rebase1", 4, rehabilitacionOral, null);
        Products rebase2 = new Products(null, "rebase2", 12000, "description rebase2", 4, rehabilitacionOral, null);
        Products reconstructor1 = new Products(null, "reconstructor1", 12000, "description reconstructor1", 4, rehabilitacionOral, null);
        Products reconstructor2 = new Products(null, "reconstructor2", 12000, "description reconstructor2", 4, rehabilitacionOral, null);
        Products resina1 = new Products(null, "resina1", 12000, "description resina1", 4, rehabilitacionOral, null);
        Products resina2 = new Products(null, "resina2", 12000, "description resina2", 4, rehabilitacionOral, null);
        Products rothBraket1 = new Products(null, "rothBraket1", 12000, "description rothBraket1", 4, brakets, null);
        Products rothBraket2 = new Products(null, "rothBraket2", 12000, "description rothBraket2", 4, brakets, null);
        Products tapabocas1 = new Products(null, "tapabocas1", 12000, "description tapabocas1", 4, desechables, null);
        Products tigera1 = new Products(null, "tigera1", 12000, "description tigera1", 4, instrumentacionDental, null);
        Products tubo1 = new Products(null, "tubo1", 12000, "description tubo1", 4, ortodoncia, null);
        Products tubo2 = new Products(null, "tubo2", 12000, "description tubo2", 4, ortodoncia, null);
        products.addAll(List.of(adhesivo1, adhesivo2, alambre1, alambre2, arco1, arco2, auxiliar1, auxiliar2, bisacrilico1,
                                bisacrilico2, carrierBraket1, cemento1, cepilloOral, compomero1, compomero2, deltaForceBraket1,
                                distalizador1, distalizador2, elastomeros1, elastomeros2, estandarBraket1, estandarBraket2,
                                guantesLatex, guantesLatex, hojaBisturi1, hojaBisturi2, instrumentoOrtodoncia1, instrumentoOrtodoncia2,
                                jeringa1, jeringa2, mango1, mango2, mBTBraket1, mBTBraket2, ortopedia1, ortopedia2, pinzaDental1,
                                pinzaDental2, pinzaOrtodoncia1, pinzaOrtodoncia2,portaAguja1, portaAguja2, protectorCepillo,
                                provicionales1, provicionales2, rebase1, rebase2, reconstructor1, reconstructor2,
                                resina1, resina2, rothBraket1, rothBraket2, tapabocas1, tigera1, tubo1, tubo2));
        productsRep.saveAll(products);

        Set<Images> images = new HashSet<>();
        Images adhesivo1Image = new Images(null, "adhesivo1Image", "jpg", imageUtils.loadImageData("C:\\Users\\dj-os\\OneDrive\\Documentos\\Spring\\web-page\\dont-used\\example-images\\adhesivo1.jpg"), adhesivo1);

        images.addAll(List.of(adhesivo1Image));

        imagesRep.saveAll(images);
    }   
}
