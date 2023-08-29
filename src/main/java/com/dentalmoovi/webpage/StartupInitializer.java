package com.dentalmoovi.webpage;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.dentalmoovi.webpage.models.entities.Categories;
import com.dentalmoovi.webpage.models.entities.Images;
import com.dentalmoovi.webpage.models.entities.Products;
import com.dentalmoovi.webpage.models.entities.Roles;
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
        Categories desechables = new Categories(null, "DESECHABLES", null, 1, null, null);
        Categories cuidadoDental = new Categories(null, "HIGIENE ORAL", null , 1, null, null);
        Categories instrumentacionDental = new Categories(null, "INSTRUMENTACIÓN DENTAL", null , 1, null, null);
            // Dental instrumentation sub-categories
            Categories hojaBisturi = new Categories(null, "HOJA DE BISTURÍ", null, 1, instrumentacionDental, null);
            Categories jeringas = new Categories(null, "JERINGAS", null, 1,  instrumentacionDental, null);
            Categories mangoBisturi = new Categories(null, "MANGOS PARA BISTURÍ", null, 1,  instrumentacionDental, null);
            Categories pinzasDental = new Categories(null, "PINZAS DENTALES", null, 1,  instrumentacionDental, null);
            Categories portaAgujas = new Categories(null, "PORTA AGUJAS", null, 1, instrumentacionDental, null);
            Categories tijeras = new Categories(null, "TIJERAS", null, 1, instrumentacionDental, null);
            categoriesSet.addAll(List.of(hojaBisturi, jeringas, mangoBisturi, pinzasDental, portaAgujas, tijeras));
        Categories ortodoncia = new Categories(null, "ORTODONCIA", null , 1, null, null);
            // Orthodontics sub-categories
            Categories alambres = new Categories(null, "ALAMBRES", null, 1, ortodoncia, null);
            Categories arcos = new Categories(null, "ARCOS", null, 1, ortodoncia, null);
            Categories auxiliares = new Categories(null, "AUXILIARES", null, 1, ortodoncia, null);
            Categories brakets = new Categories(null, "BRACKETS", null, 1, ortodoncia, null);
                // Brakets sub-categories
                Categories carriere = new Categories(null, "CARRIERE", null, 1, brakets, null);
                Categories deltaForce = new Categories(null, "DELTA FORCE", null, 1, brakets, null);
                //Categories estandar = new Categories(null, "ESTANDAR", null, 1, brakets, null);
                Categories mbt = new Categories(null, "MBT", null, 1, brakets, null);
                Categories roth = new Categories(null, "ROTH", null, 1, brakets, null);
                categoriesSet.addAll(List.of(carriere, deltaForce, /* estandar, */ mbt, roth));
            Categories distalizador = new Categories(null, "DISTALIZADOR", null, 1, ortodoncia, null);
            Categories elastomeros = new Categories(null, "ELASTOMEROS", null, 1, ortodoncia, null);
            Categories instrumentosOrtodonticos = new Categories(null, "INSTRUMENTOS ORTODONTICOS", null, 1, ortodoncia, null);
            Categories microImplantes = new Categories(null, "MICROIMPLANTES", null, 1, ortodoncia, null);
            Categories pinzasOrtodoncia = new Categories(null, "PINZAS ORTODONCIA", null, 1, ortodoncia, null);
            Categories tubos = new Categories(null, "TUBOS", null, 1, ortodoncia, null);
            categoriesSet.addAll(List.of(alambres, arcos, auxiliares, brakets, distalizador, elastomeros, instrumentosOrtodonticos, microImplantes, pinzasOrtodoncia, tubos));
        Categories ortopedia = new Categories(null, "ORTOPEDIA", null , 1, null, null);
        Categories rehabilitacionOral = new Categories(null, "REHABILITACIÓN ORAL", null , 1, null, null);
            // Oral rehabilitation sub-categories
            Categories adhesivos = new Categories(null, "ADHESIVOS", null, 1, rehabilitacionOral, null);
            Categories bisacrilicos = new Categories(null, "BISACRÍLICOS", null, 1, rehabilitacionOral, null);
            Categories cemento = new Categories(null, "CEMENTO", null, 1, rehabilitacionOral, null);
            Categories compomeros = new Categories(null, "COMPOMEROS", null, 1, rehabilitacionOral, null);
            Categories postes = new Categories(null, "POSTES", null, 1, rehabilitacionOral, null);
            Categories provisionales = new Categories(null, "PROVICIONALES", null, 1, rehabilitacionOral, null);
            Categories rebases = new Categories(null, "REBASES", null, 1, rehabilitacionOral, null);
            Categories reconstructor = new Categories(null, "RECONSTRUCTOR", null, 1, rehabilitacionOral, null);
            Categories resinas = new Categories(null, "RESINAS", null, 1, rehabilitacionOral, null);
            categoriesSet.addAll(List.of(adhesivos, bisacrilicos, cemento, compomeros, postes, provisionales, rebases, reconstructor, resinas));
        categoriesSet.addAll(List.of(desechables, cuidadoDental, instrumentacionDental, ortodoncia, ortopedia, rehabilitacionOral));

        categoriesRep.saveAll(categoriesSet);

        Set<Products> products = new HashSet<>();

        Products adhesivo1 = new Products(null, "Adhesivo1", 10000, "description adhesivo1", 4, 1, true, adhesivos, null, null);
        Products adhesivo2 = new Products(null, "Adhesivo2", 12000, "description adhesivo2", 4, 1, true, adhesivos, null, null);
        Products alambre1 = new Products(null, "Alambre1", 10000, "description alambre1", 4, 1, true, alambres, null, null);
        Products alambre2 = new Products(null, "Alambre2", 12000, "description alambre2", 4, 1, true, alambres, null, null);
        Products arco1 = new Products(null, "Arco1", 10000, "description arco1", 4, 1, true, arcos, null, null);
        Products arco2 = new Products(null, "Arco2", 12000, "description arco2", 4, 1, true, arcos, null, null);
        Products auxiliar1 = new Products(null, "Auxiliar1", 12000, "description Auxiliar1", 4, 1, true, auxiliares, null, null);
        Products auxiliar2 = new Products(null, "auxiliar2", 12000, "description auxiliar2", 4, 1, true, auxiliares, null, null);
        Products bisacrilico1 = new Products(null, "bisacrilico1", 12000, "description bisacrilico1", 4, 1, true, bisacrilicos, null, null);
        Products bisacrilico2 = new Products(null, "bisacrilico2", 12000, "description bisacrilico2", 4, 1, true, bisacrilicos, null, null);
        Products carrierBraket1 = new Products(null, "carrierBraket1", 12000, "description carrierBraket1", 4, 1, true, brakets, null, null);
        Products cemento1 = new Products(null, "cemento1", 12000, "description cemento1", 4, 1, true, cemento, null, null);
        Products cepilloOral = new Products(null, "cepilloOral", 12000, "description cepilloOral", 4, 1, true, cuidadoDental, null, null);
        Products compomero1 = new Products(null, "compomero1", 12000, "description compomero1", 4, 1, true, compomeros, null, null);
        Products compomero2 = new Products(null, "compomero2", 12000, "description compomero2", 4, 1, false, compomeros, null, null);
        Products deltaForceBraket1 = new Products(null, "deltaForceBraket1", 12000, "description deltaForceBraket1", 4, 1, true, brakets, null, null);
        Products distalizador1 = new Products(null, "distalizador1", 12000, "description distalizador1", 4, 1, true, distalizador, null, null);
        Products distalizador2 = new Products(null, "distalizador2", 12000, "description distalizador2", 4, 1, true, distalizador, null, null);
        Products elastomeros1 = new Products(null, "elastomeros1", 12000, "description elastomeros1", 4, 1, true, elastomeros, null, null);
        Products elastomeros2 = new Products(null, "elastomeros2", 12000, "description elastomeros2", 4, 1, true, elastomeros, null, null);
        Products estandarBraket1 = new Products(null, "estandarBraket1", 12000, "description estandarBraket1", 4, 1, true, brakets, null, null);
        Products estandarBraket2 = new Products(null, "estandarBraket2", 12000, "description estandarBraket2", 4, 1, true, brakets, null, null);
        Products guantesLatex = new Products(null, "guantesLatex", 12000, "description guantesLatex", 4, 1, true, desechables, null, null);
        Products hojaBisturi1 = new Products(null, "hojaBisturi1", 12000, "description hojaBisturi1", 4, 1, true, hojaBisturi, null, null);
        Products hojaBisturi2 = new Products(null, "hojaBisturi2", 12000, "description hojaBisturi2", 4, 1, true, hojaBisturi, null, null);
        Products instrumentoOrtodoncia1 = new Products(null, "instrumentoOrtodoncia1", 12000, "description instrumentoOrtodoncia1", 4, 1, true, instrumentosOrtodonticos, null, null);
        Products instrumentoOrtodoncia2 = new Products(null, "instrumentoOrtodoncia2", 12000, "description instrumentoOrtodoncia2", 4, 1, true, instrumentosOrtodonticos, null, null);
        Products jeringa1 = new Products(null, "jeringa1", 12000, "description jeringa1", 4, 1, true, jeringas, null, null);
        Products jeringa2 = new Products(null, "jeringa2", 12000, "description jeringa2", 4, 1, true, jeringas, null, null);
        Products mango1 = new Products(null, "mango1", 12000, "description mango1", 4, 1, true, mangoBisturi, null, null);
        Products mango2 = new Products(null, "mango2", 12000, "description mango2", 4, 1, true, mangoBisturi, null, null);
        Products mBTBraket1 = new Products(null, "mBTBraket1", 12000, "description mBTBraket1", 4, 1, true, mbt, null, null);
        Products mBTBraket2 = new Products(null, "mBTBraket2", 12000, "description mBTBraket2", 4, 1, true, mbt, null, null);
        Products ortopedia1 = new Products(null, "ortopedia1", 12000, "description ortopedia1", 4, 1, true, ortopedia, null, null);
        Products ortopedia2 = new Products(null, "ortopedia2", 12000, "description ortopedia2", 4, 1, true, ortopedia, null, null);
        Products pinzaDental1 = new Products(null, "pinzaDental1", 12000, "description pinzaDental1", 4, 1, true, pinzasDental, null, null);
        Products pinzaDental2 = new Products(null, "pinzaDental2", 12000, "description pinzaDental2", 4, 1, true, pinzasDental, null, null);
        Products pinzaOrtodoncia1 = new Products(null, "pinzaOrtodoncia1", 12000, "description pinzaOrtodoncia1", 4, 1, true, pinzasOrtodoncia, null, null);
        Products pinzaOrtodoncia2 = new Products(null, "pinzaOrtodoncia2", 12000, "description pinzaOrtodoncia2", 4, 1, true, pinzasOrtodoncia, null, null);
        Products portaAguja1 = new Products(null, "portaAguja1", 12000, "description portaAguja1", 4, 1, true, portaAgujas, null, null);
        Products portaAguja2 = new Products(null, "portaAguja2", 12000, "description portaAguja2", 4, 1, true, portaAgujas, null, null);
        Products protectorCepillo = new Products(null, "protectorCepillo", 12000, "description protectorCepillo", 4, 1, true, cuidadoDental, null, null);
        Products provisional1 = new Products(null, "provicionales1", 12000, "description provicionales1", 4, 1, true, provisionales, null, null);
        Products provisional2 = new Products(null, "provicionales2", 12000, "description provicionales2", 4, 1, true, provisionales, null, null);
        Products rebase1 = new Products(null, "rebase1", 12000, "description rebase1", 4, 1, true, rebases, null, null);
        Products rebase2 = new Products(null, "rebase2", 12000, "description rebase2", 4, 1, true, rebases, null, null);
        Products reconstructor1 = new Products(null, "reconstructor1", 12000, "description reconstructor1", 4, 1, true, reconstructor, null, null);
        Products reconstructor2 = new Products(null, "reconstructor2", 12000, "description reconstructor2", 4, 1, true, reconstructor, null, null);
        Products resina1 = new Products(null, "resina1", 12000, "description resina1", 4, 1, true, resinas, null, null);
        Products resina2 = new Products(null, "resina2", 12000, "description resina2", 4, 1, true, resinas, null, null);
        Products rothBraket1 = new Products(null, "rothBraket1", 12000, "description rothBraket1", 4, 1, true, roth, null, null);
        Products rothBraket2 = new Products(null, "rothBraket2", 12000, "description rothBraket2", 4, 1, true, roth, null, null);
        Products tapaBocas1 = new Products(null, "tapabocas1", 12000, "description tapabocas1", 4, 1, true, desechables, null, null);
        Products tigera1 = new Products(null, "tigera1", 12000, "description tigera1", 4, 1, true, tijeras, null, null);
        Products tubo1 = new Products(null, "tubo1", 12000, "description tubo1", 4, 1, true, tubos, null, null);
        Products tubo2 = new Products(null, "tubo2", 12000, "description tubo2", 4, 1, true, tubos, null, null);

        List<Images> images = new ArrayList<>();
        Images adhesivo1Image= new Images(null, "adhesivo1Image", "jpeg", imageUtils.loadImageData("C:\\Users\\dj-os\\OneDrive\\Documentos\\Spring\\web-page\\dont-used\\example-images\\adhesivo1.jpg"), adhesivo1);
        Images adhesivo12Image= new Images(null, "adhesivo1-2Image", "jpeg", imageUtils.loadImageData("C:\\Users\\dj-os\\OneDrive\\Documentos\\Spring\\web-page\\dont-used\\example-images\\adhesivo2.jpg"), adhesivo1);
        Images adhesivo13Image= new Images(null, "adhesivo1-3Image", "jpeg", imageUtils.loadImageData("C:\\Users\\dj-os\\OneDrive\\Documentos\\Spring\\web-page\\dont-used\\example-images\\alambre1.jpeg"), adhesivo1);
        Images adhesivo14Image= new Images(null, "adhesivo1-4Image", "jpeg", imageUtils.loadImageData("C:\\Users\\dj-os\\OneDrive\\Documentos\\Spring\\web-page\\dont-used\\example-images\\alambre2.jpg"), adhesivo1);

        Images adhesivo2Image= new Images(null, "adhesivo2Image", "jpeg", imageUtils.loadImageData("C:\\Users\\dj-os\\OneDrive\\Documentos\\Spring\\web-page\\dont-used\\example-images\\adhesivo2.jpg"), adhesivo2);
        Images alambre1Image= new Images(null, "alambre1Image", "jpeg", imageUtils.loadImageData("C:\\Users\\dj-os\\OneDrive\\Documentos\\Spring\\web-page\\dont-used\\example-images\\alambre1.jpeg"), alambre1);
        Images alambre2Image= new Images(null, "alambre2Image", "jpeg", imageUtils.loadImageData("C:\\Users\\dj-os\\OneDrive\\Documentos\\Spring\\web-page\\dont-used\\example-images\\alambre2.jpg"), alambre2);
        Images arco1Image= new Images(null, "arco1Image", "jpeg", imageUtils.loadImageData("C:\\Users\\dj-os\\OneDrive\\Documentos\\Spring\\web-page\\dont-used\\example-images\\arco1.jpg"), arco1);
        Images arco2Image= new Images(null, "arco2Image", "jpeg", imageUtils.loadImageData("C:\\Users\\dj-os\\OneDrive\\Documentos\\Spring\\web-page\\dont-used\\example-images\\arco2.jpg"), arco2);
        Images auxiliar1Image= new Images(null, "auxiliar1Image", "jpeg", imageUtils.loadImageData("C:\\Users\\dj-os\\OneDrive\\Documentos\\Spring\\web-page\\dont-used\\example-images\\auxiliar1.jpg"), auxiliar1);
        Images auxiliar2Image= new Images(null, "auxiliar2Image", "jpeg", imageUtils.loadImageData("C:\\Users\\dj-os\\OneDrive\\Documentos\\Spring\\web-page\\dont-used\\example-images\\auxiliar2.jpg"), auxiliar2);
        Images bisacrilico1Image= new Images(null, "bisacrilico1Image", "jpeg", imageUtils.loadImageData("C:\\Users\\dj-os\\OneDrive\\Documentos\\Spring\\web-page\\dont-used\\example-images\\bisacrilico1.jpg"), bisacrilico1);
        Images bisacrilico2Image= new Images(null, "bisacrilico2Image", "jpeg", imageUtils.loadImageData("C:\\Users\\dj-os\\OneDrive\\Documentos\\Spring\\web-page\\dont-used\\example-images\\bisacrilico2.jpg"), bisacrilico2);
        Images carrierBraket1Image= new Images(null, "carrierBraket1Image", "jpeg", imageUtils.loadImageData("C:\\Users\\dj-os\\OneDrive\\Documentos\\Spring\\web-page\\dont-used\\example-images\\carrierBraket1.jpg"), carrierBraket1);
        Images cemento1Image= new Images(null, "cemento1Image", "jpeg", imageUtils.loadImageData("C:\\Users\\dj-os\\OneDrive\\Documentos\\Spring\\web-page\\dont-used\\example-images\\cemento1.jpg"), cemento1);
        Images cepilloOralImage= new Images(null, "cepilloOralImage", "jpeg", imageUtils.loadImageData("C:\\Users\\dj-os\\OneDrive\\Documentos\\Spring\\web-page\\dont-used\\example-images\\CEPILLO-ORAL.jpg"), cepilloOral);
        Images compomero1Image= new Images(null, "compomero1Image", "png", imageUtils.loadImageData("C:\\Users\\dj-os\\OneDrive\\Documentos\\Spring\\web-page\\dont-used\\example-images\\compomero1.png"), compomero1);
        Images deltaForceBraket1Image= new Images(null, "deltaForceBraket1Image", "jpeg", imageUtils.loadImageData("C:\\Users\\dj-os\\OneDrive\\Documentos\\Spring\\web-page\\dont-used\\example-images\\dentalForceBraket1.jpg"), deltaForceBraket1);
        Images distalizador1Image= new Images(null, "distalizador1Image", "jpeg", imageUtils.loadImageData("C:\\Users\\dj-os\\OneDrive\\Documentos\\Spring\\web-page\\dont-used\\example-images\\distalizador1.jpg"), distalizador1);
        Images distalizador2Image= new Images(null, "distalizador2Image", "jpeg", imageUtils.loadImageData("C:\\Users\\dj-os\\OneDrive\\Documentos\\Spring\\web-page\\dont-used\\example-images\\distalizador2.jpg"), distalizador2);
        Images elastomeros1Image= new Images(null, "elastomeros1Image", "jpeg", imageUtils.loadImageData("C:\\Users\\dj-os\\OneDrive\\Documentos\\Spring\\web-page\\dont-used\\example-images\\elastomero1.jpg"), elastomeros1);
        Images elastomeros2Image= new Images(null, "elastomeros2Image", "jpeg", imageUtils.loadImageData("C:\\Users\\dj-os\\OneDrive\\Documentos\\Spring\\web-page\\dont-used\\example-images\\elastomero2.jpg"), elastomeros2);
        Images estandarBraket1Image= new Images(null, "estandarBraket1Image", "jpeg", imageUtils.loadImageData("C:\\Users\\dj-os\\OneDrive\\Documentos\\Spring\\web-page\\dont-used\\example-images\\estandarBraket1.jpg"), estandarBraket1);
        Images estandarBraket2Image= new Images(null, "estandarBraket2Image", "jpeg", imageUtils.loadImageData("C:\\Users\\dj-os\\OneDrive\\Documentos\\Spring\\web-page\\dont-used\\example-images\\estandarBraket2.jpg"), estandarBraket2);
        Images guantesLatexImage= new Images(null, "guantesLatexImage", "jpeg", imageUtils.loadImageData("C:\\Users\\dj-os\\OneDrive\\Documentos\\Spring\\web-page\\dont-used\\example-images\\Guantes-latex.jpg"), guantesLatex);
        Images hojaBisturi1Image= new Images(null, "hojaBisturi1Image", "jpeg", imageUtils.loadImageData("C:\\Users\\dj-os\\OneDrive\\Documentos\\Spring\\web-page\\dont-used\\example-images\\hojaBisturi1.jpg"), hojaBisturi1);
        Images hojaBisturi2Image= new Images(null, "hojaBisturi2Image", "jpeg", imageUtils.loadImageData("C:\\Users\\dj-os\\OneDrive\\Documentos\\Spring\\web-page\\dont-used\\example-images\\hojaBisturi2.jpg"), hojaBisturi2);
        Images instrumentoOrtodoncia1Image= new Images(null, "instrumentoOrtodoncia1Image", "jpeg", imageUtils.loadImageData("C:\\Users\\dj-os\\OneDrive\\Documentos\\Spring\\web-page\\dont-used\\example-images\\instrumentoOrtodoncia1.jpg"), instrumentoOrtodoncia1);
        Images instrumentoOrtodoncia2Image= new Images(null, "instrumentoOrtodoncia2Image", "jpeg", imageUtils.loadImageData("C:\\Users\\dj-os\\OneDrive\\Documentos\\Spring\\web-page\\dont-used\\example-images\\instrumentoOrtodoncia2.jpg"), instrumentoOrtodoncia2);
        Images jeringa1Image= new Images(null, "jeringa1Image", "jpeg", imageUtils.loadImageData("C:\\Users\\dj-os\\OneDrive\\Documentos\\Spring\\web-page\\dont-used\\example-images\\jeringa1.jpg"), jeringa1);
        Images jeringa2Image= new Images(null, "jeringa2Image", "jpeg", imageUtils.loadImageData("C:\\Users\\dj-os\\OneDrive\\Documentos\\Spring\\web-page\\dont-used\\example-images\\jeringa2.jpg"), jeringa2);
        Images mango1Image= new Images(null, "mango1Image", "jpeg", imageUtils.loadImageData("C:\\Users\\dj-os\\OneDrive\\Documentos\\Spring\\web-page\\dont-used\\example-images\\mango1.jpg"), mango1);
        Images mango2Image= new Images(null, "mango2Image", "jpeg", imageUtils.loadImageData("C:\\Users\\dj-os\\OneDrive\\Documentos\\Spring\\web-page\\dont-used\\example-images\\mango2.jpg"), mango2);
        Images mBTBraket1Image= new Images(null, "MBTBraket1Image", "jpeg", imageUtils.loadImageData("C:\\Users\\dj-os\\OneDrive\\Documentos\\Spring\\web-page\\dont-used\\example-images\\MBTBraket1.jpg"), mBTBraket1);
        Images mBTBraket2Image= new Images(null, "MBTBraket2Image", "jpeg", imageUtils.loadImageData("C:\\Users\\dj-os\\OneDrive\\Documentos\\Spring\\web-page\\dont-used\\example-images\\MBTBraket2.jpg"), mBTBraket2);
        Images ortopedia1Image= new Images(null, "ortopedia1Image", "jpeg", imageUtils.loadImageData("C:\\Users\\dj-os\\OneDrive\\Documentos\\Spring\\web-page\\dont-used\\example-images\\ortopedia1.jpg"), ortopedia1);
        Images ortopedia2Image= new Images(null, "ortopedia2Image", "jpeg", imageUtils.loadImageData("C:\\Users\\dj-os\\OneDrive\\Documentos\\Spring\\web-page\\dont-used\\example-images\\ortopedia2.jpg"), ortopedia2);
        Images pinza1Image= new Images(null, "pinza1Image", "jpeg", imageUtils.loadImageData("C:\\Users\\dj-os\\OneDrive\\Documentos\\Spring\\web-page\\dont-used\\example-images\\pinza1.jpg"), pinzaDental1);
        Images pinza2Image= new Images(null, "pinza2Image", "jpeg", imageUtils.loadImageData("C:\\Users\\dj-os\\OneDrive\\Documentos\\Spring\\web-page\\dont-used\\example-images\\pinza2.jpg"), pinzaDental2);
        Images pinza1Ortodoncia1Image= new Images(null, "pinza1Ortodoncia1Image", "jpeg", imageUtils.loadImageData("C:\\Users\\dj-os\\OneDrive\\Documentos\\Spring\\web-page\\dont-used\\example-images\\pinzaOrtodoncia1.jpg"), pinzaOrtodoncia1);
        Images pinza2Ortodoncia1Image= new Images(null, "pinza2Ortodoncia1Image", "jpeg", imageUtils.loadImageData("C:\\Users\\dj-os\\OneDrive\\Documentos\\Spring\\web-page\\dont-used\\example-images\\pinzaOrtodoncia2.jpg"), pinzaOrtodoncia2);
        Images portaaguja1Image= new Images(null, "portaaguja1Image", "jpeg", imageUtils.loadImageData("C:\\Users\\dj-os\\OneDrive\\Documentos\\Spring\\web-page\\dont-used\\example-images\\portaaguja1.jpg"), portaAguja1);
        Images portaaguja2Image= new Images(null, "portaaguja2Image", "jpeg", imageUtils.loadImageData("C:\\Users\\dj-os\\OneDrive\\Documentos\\Spring\\web-page\\dont-used\\example-images\\portaaguja2.jpg"), portaAguja2);
        Images protectorCepilloImage= new Images(null, "protectorCepilloImage", "jpeg", imageUtils.loadImageData("C:\\Users\\dj-os\\OneDrive\\Documentos\\Spring\\web-page\\dont-used\\example-images\\protectorCepillo.jpg"), protectorCepillo);
        Images provisional1Image= new Images(null, "provisional1Image", "jpeg", imageUtils.loadImageData("C:\\Users\\dj-os\\OneDrive\\Documentos\\Spring\\web-page\\dont-used\\example-images\\provisional1.jpg"), provisional1);
        Images provisional2Image= new Images(null, "provisional2Image", "jpeg", imageUtils.loadImageData("C:\\Users\\dj-os\\OneDrive\\Documentos\\Spring\\web-page\\dont-used\\example-images\\provisional2.jpg"), provisional2);
        Images rebase1Image= new Images(null, "rebase1Image", "png", imageUtils.loadImageData("C:\\Users\\dj-os\\OneDrive\\Documentos\\Spring\\web-page\\dont-used\\example-images\\rebase1.png"), rebase1);
        Images rebase2Image= new Images(null, "rebase2Image", "jpeg", imageUtils.loadImageData("C:\\Users\\dj-os\\OneDrive\\Documentos\\Spring\\web-page\\dont-used\\example-images\\rebase2.jpg"), rebase2);
        Images reconstructor1Image= new Images(null, "reconstructor1Image", "jpeg", imageUtils.loadImageData("C:\\Users\\dj-os\\OneDrive\\Documentos\\Spring\\web-page\\dont-used\\example-images\\reconstructor1.jpg"), reconstructor1);
        Images reconstructor2Image= new Images(null, "reconstructor2Image", "jpeg", imageUtils.loadImageData("C:\\Users\\dj-os\\OneDrive\\Documentos\\Spring\\web-page\\dont-used\\example-images\\reconstructor2.jpg"), reconstructor2);
        Images resina1Image= new Images(null, "resina1Image", "jpeg", imageUtils.loadImageData("C:\\Users\\dj-os\\OneDrive\\Documentos\\Spring\\web-page\\dont-used\\example-images\\resina1.jpg"), resina1);
        Images resina2Image= new Images(null, "resina2Image", "jpeg", imageUtils.loadImageData("C:\\Users\\dj-os\\OneDrive\\Documentos\\Spring\\web-page\\dont-used\\example-images\\resina2.jpg"), resina2);
        Images rothBraket1Image= new Images(null, "rothBraket1Image", "jpeg", imageUtils.loadImageData("C:\\Users\\dj-os\\OneDrive\\Documentos\\Spring\\web-page\\dont-used\\example-images\\rothBraket1.jpg"), rothBraket1);
        Images rothBraket2Image= new Images(null, "rothBraket2Image", "jpeg", imageUtils.loadImageData("C:\\Users\\dj-os\\OneDrive\\Documentos\\Spring\\web-page\\dont-used\\example-images\\rothBraket2.jpg"), rothBraket2);
        Images tigera1Image= new Images(null, "tigera1Image", "jpeg", imageUtils.loadImageData("C:\\Users\\dj-os\\OneDrive\\Documentos\\Spring\\web-page\\dont-used\\example-images\\tigera1.jpg"), tigera1);
        Images tubo1Image= new Images(null, "tubo1Image", "png", imageUtils.loadImageData("C:\\Users\\dj-os\\OneDrive\\Documentos\\Spring\\web-page\\dont-used\\example-images\\tubo1.png"), tubo1);
        Images tubo2Image= new Images(null, "tubo2Image", "jpeg", imageUtils.loadImageData("C:\\Users\\dj-os\\OneDrive\\Documentos\\Spring\\web-page\\dont-used\\example-images\\tubo2.jpg"), tubo2);


        images.addAll(List.of(
            adhesivo1Image, adhesivo2Image, alambre1Image, alambre2Image, arco1Image, arco2Image, auxiliar1Image,
            auxiliar2Image, bisacrilico1Image, bisacrilico2Image, carrierBraket1Image, cemento1Image, cepilloOralImage,
            compomero1Image, deltaForceBraket1Image, distalizador1Image, distalizador2Image, elastomeros1Image,
            elastomeros2Image, estandarBraket1Image, estandarBraket2Image, guantesLatexImage, hojaBisturi1Image,
            hojaBisturi2Image, instrumentoOrtodoncia1Image, instrumentoOrtodoncia2Image, jeringa1Image, jeringa2Image,
            mango1Image, mango2Image, mBTBraket1Image, mBTBraket2Image, ortopedia1Image, ortopedia2Image, pinza1Image,
            pinza2Image, pinza1Ortodoncia1Image, pinza2Ortodoncia1Image, portaaguja1Image, portaaguja2Image,
            protectorCepilloImage, provisional1Image, provisional2Image, rebase1Image, rebase2Image, reconstructor1Image,
            reconstructor2Image, resina1Image, resina2Image, rothBraket1Image, rothBraket2Image, tigera1Image,
            tubo1Image, tubo2Image, adhesivo12Image, adhesivo13Image, adhesivo14Image
        ));

        products.addAll(List.of(
            adhesivo1, adhesivo2, alambre1, alambre2, arco1, arco2, auxiliar1, auxiliar2, bisacrilico1,
            bisacrilico2, carrierBraket1, cemento1, cepilloOral, compomero1, compomero2, deltaForceBraket1,
            distalizador1, distalizador2, elastomeros1, elastomeros2, estandarBraket1, estandarBraket2,
            guantesLatex, hojaBisturi1, hojaBisturi2, instrumentoOrtodoncia1, instrumentoOrtodoncia2,
            jeringa1, jeringa2, mango1, mango2, mBTBraket1, mBTBraket2, ortopedia1, ortopedia2, pinzaDental1,
            pinzaDental2, pinzaOrtodoncia1, pinzaOrtodoncia2, portaAguja1, portaAguja2, protectorCepillo,
            provisional1, provisional2, rebase1, rebase2, reconstructor1, reconstructor2,
            resina1, resina2, rothBraket1, rothBraket2, tapaBocas1, tigera1, tubo1, tubo2
        ));

        productsRep.saveAll(products);

        imagesRep.saveAll(images);

        adhesivo1.setPrincipalImage(adhesivo1Image);
        adhesivo2.setPrincipalImage(adhesivo2Image);
        alambre1.setPrincipalImage(alambre1Image);
        alambre2.setPrincipalImage(alambre2Image);
        arco1.setPrincipalImage(arco1Image);
        arco2.setPrincipalImage(arco2Image);
        auxiliar1.setPrincipalImage(auxiliar1Image);
        auxiliar2.setPrincipalImage(auxiliar2Image);
        bisacrilico1.setPrincipalImage(bisacrilico1Image);
        bisacrilico2.setPrincipalImage(bisacrilico2Image);
        carrierBraket1.setPrincipalImage(carrierBraket1Image);
        cemento1.setPrincipalImage(cemento1Image);
        cepilloOral.setPrincipalImage(cepilloOralImage);
        compomero1.setPrincipalImage(compomero1Image);
        deltaForceBraket1.setPrincipalImage(deltaForceBraket1Image);
        distalizador1.setPrincipalImage(distalizador1Image);
        distalizador2.setPrincipalImage(distalizador2Image);
        elastomeros1.setPrincipalImage(elastomeros1Image);
        elastomeros2.setPrincipalImage(elastomeros2Image);
        estandarBraket1.setPrincipalImage(estandarBraket1Image);
        estandarBraket2.setPrincipalImage(estandarBraket2Image);
        guantesLatex.setPrincipalImage(guantesLatexImage);
        hojaBisturi1.setPrincipalImage(hojaBisturi1Image);
        hojaBisturi2.setPrincipalImage(hojaBisturi2Image);
        instrumentoOrtodoncia1.setPrincipalImage(instrumentoOrtodoncia1Image);
        instrumentoOrtodoncia2.setPrincipalImage(instrumentoOrtodoncia2Image);
        jeringa1.setPrincipalImage(jeringa1Image);
        jeringa2.setPrincipalImage(jeringa2Image);
        mango1.setPrincipalImage(mango1Image);
        mango2.setPrincipalImage(mango2Image);
        mBTBraket1.setPrincipalImage(mBTBraket1Image);
        mBTBraket2.setPrincipalImage(mBTBraket2Image);
        ortopedia1.setPrincipalImage(ortopedia1Image);
        ortopedia2.setPrincipalImage(ortopedia2Image);
        pinzaDental1.setPrincipalImage(pinza1Image);
        pinzaDental2.setPrincipalImage(pinza2Image);
        pinzaOrtodoncia1.setPrincipalImage(pinza1Ortodoncia1Image);
        pinzaOrtodoncia1.setPrincipalImage(pinza2Ortodoncia1Image);
        portaAguja1.setPrincipalImage(portaaguja1Image);
        portaAguja2.setPrincipalImage(portaaguja2Image);
        protectorCepillo.setPrincipalImage(protectorCepilloImage);
        provisional1.setPrincipalImage(provisional1Image);

        provisional2.setPrincipalImage(provisional2Image);
        rebase1.setPrincipalImage(rebase1Image);
        rebase2.setPrincipalImage(rebase2Image);
        reconstructor1.setPrincipalImage(reconstructor1Image);
        reconstructor2.setPrincipalImage(reconstructor2Image);
        resina1.setPrincipalImage(resina1Image);
        resina2.setPrincipalImage(resina2Image);
        rothBraket1.setPrincipalImage(rothBraket1Image);
        rothBraket2.setPrincipalImage(rothBraket2Image);

        tigera1.setPrincipalImage(tigera1Image);
        tubo1.setPrincipalImage(tubo1Image);
        tubo2.setPrincipalImage(tubo2Image);

        productsRep.saveAll(products);
    }   
}
