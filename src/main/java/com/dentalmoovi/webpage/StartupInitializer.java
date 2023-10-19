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


//all of this file it's only test data
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

        Roles user = new Roles();
        user.setNameRole("USER");
        Roles admin = new Roles();
        admin.setNameRole("ADMIN");

        rolesSet.add( user );
        rolesSet.add( admin );
        rolesRep.saveAll(rolesSet);

        // Categories Part ------------------------------------------------------------------------------------
        Set<Categories> categoriesSet = new HashSet<>();

        // Parent categories
        Categories desechables = setCategory("DESECHABLES", 1, null);
        Categories cuidadoDental = setCategory("HIGIENE ORAL", 1, null);
        Categories instrumentacionDental = setCategory("INSTRUMENTACIÓN DENTAL", 1, null);
            // Dental instrumentation sub-categories
            Categories hojaBisturi = setCategory("HOJA DE BISTURÍ", 1, instrumentacionDental);
            Categories jeringas = setCategory("JERINGAS", 1,  instrumentacionDental);
            Categories mangoBisturi = setCategory("MANGOS PARA BISTURÍ", 1,  instrumentacionDental);
            Categories pinzasDental = setCategory("PINZAS DENTALES", 1,  instrumentacionDental);
            Categories portaAgujas = setCategory("PORTA AGUJAS", 1, instrumentacionDental);
            Categories tijeras = setCategory("TIJERAS", 1, instrumentacionDental);
            categoriesSet.addAll(List.of(hojaBisturi, jeringas, mangoBisturi, pinzasDental, portaAgujas, tijeras));
        Categories ortodoncia = setCategory("ORTODONCIA", 1, null);
            // Orthodontics sub-categories
            Categories alambres = setCategory("ALAMBRES", 1, ortodoncia);
            Categories arcos = setCategory("ARCOS", 1, ortodoncia);
            Categories auxiliares = setCategory("AUXILIARES", 1, ortodoncia);
            Categories brakets = setCategory("BRACKETS", 1, ortodoncia);
                // Brakets sub-categories
                Categories carriere = setCategory("CARRIERE", 1, brakets);
                Categories deltaForce = setCategory("DELTA FORCE", 1, brakets);
                //Categories estandar = setCategory("ESTANDAR", 1, brakets);
                Categories mbt = setCategory("MBT", 1, brakets);
                Categories roth = setCategory("ROTH", 1, brakets);
                categoriesSet.addAll(List.of(carriere, deltaForce, /* estandar, */ mbt, roth));
            Categories distalizador = setCategory("DISTALIZADOR", 1, ortodoncia);
            Categories elastomeros = setCategory("ELASTOMEROS", 1, ortodoncia);
            Categories instrumentosOrtodonticos = setCategory("INSTRUMENTOS ORTODONTICOS", 1, ortodoncia);
            Categories microImplantes = setCategory("MICROIMPLANTES", 1, ortodoncia);
            Categories pinzasOrtodoncia = setCategory("PINZAS ORTODONCIA", 1, ortodoncia);
            Categories tubos = setCategory("TUBOS", 1, ortodoncia);
            categoriesSet.addAll(List.of(alambres, arcos, auxiliares, brakets, distalizador, elastomeros, instrumentosOrtodonticos, microImplantes, pinzasOrtodoncia, tubos));
        Categories ortopedia = setCategory("ORTOPEDIA", 1, null);
        Categories rehabilitacionOral = setCategory("REHABILITACIÓN ORAL", 1, null);
            // Oral rehabilitation sub-categories
            Categories adhesivos = setCategory("ADHESIVOS", 1, rehabilitacionOral);
            Categories bisacrilicos = setCategory("BISACRÍLICOS", 1, rehabilitacionOral);
            Categories cemento = setCategory("CEMENTO", 1, rehabilitacionOral);
            Categories compomeros = setCategory("COMPOMEROS", 1, rehabilitacionOral);
            Categories postes = setCategory("POSTES", 1, rehabilitacionOral);
            Categories provisionales = setCategory("PROVICIONALES", 1, rehabilitacionOral);
            Categories rebases = setCategory("REBASES", 1, rehabilitacionOral);
            Categories reconstructor = setCategory("RECONSTRUCTOR", 1, rehabilitacionOral);
            Categories resinas = setCategory("RESINAS", 1, rehabilitacionOral);
            categoriesSet.addAll(List.of(adhesivos, bisacrilicos, cemento, compomeros, postes, provisionales, rebases, reconstructor, resinas));
        categoriesSet.addAll(List.of(desechables, cuidadoDental, instrumentacionDental, ortodoncia, ortopedia, rehabilitacionOral));

        categoriesRep.saveAll(categoriesSet);

        Set<Products> products = new HashSet<>();

        Products adhesivo1 = setProduct("Adhesivo1", 10000, "description adhesivo1", 4, 1, true, adhesivos);
        Products adhesivo2 = setProduct("Adhesivo2", 12000, "description adhesivo2", 4, 1, true, adhesivos);
        Products alambre1 = setProduct("Alambre1", 10000, "description alambre1", 4, 1, true, alambres);
        Products alambre2 = setProduct("Alambre2", 12000, "description alambre2", 4, 1, true, alambres);
        Products arco1 = setProduct("Arco1", 10000, "description arco1", 4, 1, true, arcos);
        Products arco2 = setProduct("Arco2", 12000, "description arco2", 4, 1, true, arcos);
        Products auxiliar1 = setProduct("Auxiliar1", 12000, "description Auxiliar1", 4, 1, true, auxiliares);
        Products auxiliar2 = setProduct("auxiliar2", 12000, "description auxiliar2", 4, 1, true, auxiliares);
        Products bisacrilico1 = setProduct("bisacrilico1", 12000, "description bisacrilico1", 4, 1, true, bisacrilicos);
        Products bisacrilico2 = setProduct("bisacrilico2", 12000, "description bisacrilico2", 4, 1, true, bisacrilicos);
        Products carrierBraket1 = setProduct("carrierBraket1", 12000, "description carrierBraket1", 4, 1, true, brakets);
        Products cemento1 = setProduct("cemento1", 12000, "description cemento1", 4, 1, true, cemento);
        Products cepilloOral = setProduct("cepilloOral", 12000, "description cepilloOral", 4, 1, true, cuidadoDental);
        Products compomero1 = setProduct("compomero1", 12000, "description compomero1", 4, 1, true, compomeros);
        Products compomero2 = setProduct("compomero2", 12000, "description compomero2", 4, 1, false, compomeros);
        Products deltaForceBraket1 = setProduct("deltaForceBraket1", 12000, "description deltaForceBraket1", 4, 1, true, brakets);
        Products distalizador1 = setProduct("distalizador1", 12000, "description distalizador1", 4, 1, true, distalizador);
        Products distalizador2 = setProduct("distalizador2", 12000, "description distalizador2", 4, 1, true, distalizador);
        Products elastomeros1 = setProduct("elastomeros1", 12000, "description elastomeros1", 4, 1, true, elastomeros);
        Products elastomeros2 = setProduct("elastomeros2", 12000, "description elastomeros2", 4, 1, true, elastomeros);
        Products estandarBraket1 = setProduct("estandarBraket1", 12000, "description estandarBraket1", 4, 1, true, brakets);
        Products estandarBraket2 = setProduct("estandarBraket2", 12000, "description estandarBraket2", 4, 1, true, brakets);
        Products guantesLatex = setProduct("guantesLatex", 12000, "description guantesLatex", 4, 1, true, desechables);
        Products hojaBisturi1 = setProduct("hojaBisturi1", 12000, "description hojaBisturi1", 4, 1, true, hojaBisturi);
        Products hojaBisturi2 = setProduct("hojaBisturi2", 12000, "description hojaBisturi2", 4, 1, true, hojaBisturi);
        Products instrumentoOrtodoncia1 = setProduct("instrumentoOrtodoncia1", 12000, "description instrumentoOrtodoncia1", 4, 1, true, instrumentosOrtodonticos);
        Products instrumentoOrtodoncia2 = setProduct("instrumentoOrtodoncia2", 12000, "description instrumentoOrtodoncia2", 4, 1, true, instrumentosOrtodonticos);
        Products jeringa1 = setProduct("jeringa1", 12000, "description jeringa1", 4, 1, true, jeringas);
        Products jeringa2 = setProduct("jeringa2", 12000, "description jeringa2", 4, 1, true, jeringas);
        Products mango1 = setProduct("mango1", 12000, "description mango1", 4, 1, true, mangoBisturi);
        Products mango2 = setProduct("mango2", 12000, "description mango2", 4, 1, true, mangoBisturi);
        Products mBTBraket1 = setProduct("mBTBraket1", 12000, "description mBTBraket1", 4, 1, true, mbt);
        Products mBTBraket2 = setProduct("mBTBraket2", 12000, "description mBTBraket2", 4, 1, true, mbt);
        Products ortopedia1 = setProduct("ortopedia1", 12000, "description ortopedia1", 4, 1, true, ortopedia);
        Products ortopedia2 = setProduct("ortopedia2", 12000, "description ortopedia2", 4, 1, true, ortopedia);
        Products pinzaDental1 = setProduct("pinzaDental1", 12000, "description pinzaDental1", 4, 1, true, pinzasDental);
        Products pinzaDental2 = setProduct("pinzaDental2", 12000, "description pinzaDental2", 4, 1, true, pinzasDental);
        Products pinzaOrtodoncia1 = setProduct("pinzaOrtodoncia1", 12000, "description pinzaOrtodoncia1", 4, 1, true, pinzasOrtodoncia);
        Products pinzaOrtodoncia2 = setProduct("pinzaOrtodoncia2", 12000, "description pinzaOrtodoncia2", 4, 1, true, pinzasOrtodoncia);
        Products portaAguja1 = setProduct("portaAguja1", 12000, "description portaAguja1", 4, 1, true, portaAgujas);
        Products portaAguja2 = setProduct("portaAguja2", 12000, "description portaAguja2", 4, 1, true, portaAgujas);
        Products protectorCepillo = setProduct("protectorCepillo", 12000, "description protectorCepillo", 4, 1, true, cuidadoDental);
        Products provisional1 = setProduct("provicionales1", 12000, "description provicionales1", 4, 1, true, provisionales);
        Products provisional2 = setProduct("provicionales2", 12000, "description provicionales2", 4, 1, true, provisionales);
        Products rebase1 = setProduct("rebase1", 12000, "description rebase1", 4, 1, true, rebases);
        Products rebase2 = setProduct("rebase2", 12000, "description rebase2", 4, 1, true, rebases);
        Products reconstructor1 = setProduct("reconstructor1", 12000, "description reconstructor1", 4, 1, true, reconstructor);
        Products reconstructor2 = setProduct("reconstructor2", 12000, "description reconstructor2", 4, 1, true, reconstructor);
        Products resina1 = setProduct("resina1", 12000, "description resina1", 4, 1, true, resinas);
        Products resina2 = setProduct("resina2", 12000, "description resina2", 4, 1, true, resinas);
        Products rothBraket1 = setProduct("rothBraket1", 12000, "description rothBraket1", 4, 1, true, roth);
        Products rothBraket2 = setProduct("rothBraket2", 12000, "description rothBraket2", 4, 1, true, roth);
        Products tapaBocas1 = setProduct("tapabocas1", 12000, "description tapabocas1", 4, 1, true, desechables);
        Products tigera1 = setProduct("tigera1", 12000, "description tigera1", 4, 1, true, tijeras);
        Products tubo1 = setProduct("tubo1", 12000, "description tubo1", 4, 1, true, tubos);
        Products tubo2 = setProduct("tubo2", 12000, "description tubo2", 4, 1, true, tubos);

        List<Images> images = new ArrayList<>();
        Images adhesivo1Image= setImage("adhesivo1Image", "jpeg", imageUtils.loadImageData("C:\\Users\\dj-os\\OneDrive\\Documentos\\Spring\\web-page\\dont-used\\example-images\\adhesivo1.jpg"), adhesivo1);
        Images adhesivo12Image= setImage("adhesivo1-2Image", "jpeg", imageUtils.loadImageData("C:\\Users\\dj-os\\OneDrive\\Documentos\\Spring\\web-page\\dont-used\\example-images\\adhesivo2.jpg"), adhesivo1);
        Images adhesivo13Image= setImage("adhesivo1-3Image", "jpeg", imageUtils.loadImageData("C:\\Users\\dj-os\\OneDrive\\Documentos\\Spring\\web-page\\dont-used\\example-images\\alambre1.jpeg"), adhesivo1);
        Images adhesivo14Image= setImage("adhesivo1-4Image", "jpeg", imageUtils.loadImageData("C:\\Users\\dj-os\\OneDrive\\Documentos\\Spring\\web-page\\dont-used\\example-images\\alambre2.jpg"), adhesivo1);

        Images adhesivo2Image= setImage("adhesivo2Image", "jpeg", imageUtils.loadImageData("C:\\Users\\dj-os\\OneDrive\\Documentos\\Spring\\web-page\\dont-used\\example-images\\adhesivo2.jpg"), adhesivo2);
        Images alambre1Image= setImage("alambre1Image", "jpeg", imageUtils.loadImageData("C:\\Users\\dj-os\\OneDrive\\Documentos\\Spring\\web-page\\dont-used\\example-images\\alambre1.jpeg"), alambre1);
        Images alambre2Image= setImage("alambre2Image", "jpeg", imageUtils.loadImageData("C:\\Users\\dj-os\\OneDrive\\Documentos\\Spring\\web-page\\dont-used\\example-images\\alambre2.jpg"), alambre2);
        Images arco1Image= setImage("arco1Image", "jpeg", imageUtils.loadImageData("C:\\Users\\dj-os\\OneDrive\\Documentos\\Spring\\web-page\\dont-used\\example-images\\arco1.jpg"), arco1);
        Images arco2Image= setImage("arco2Image", "jpeg", imageUtils.loadImageData("C:\\Users\\dj-os\\OneDrive\\Documentos\\Spring\\web-page\\dont-used\\example-images\\arco2.jpg"), arco2);
        Images auxiliar1Image= setImage("auxiliar1Image", "jpeg", imageUtils.loadImageData("C:\\Users\\dj-os\\OneDrive\\Documentos\\Spring\\web-page\\dont-used\\example-images\\auxiliar1.jpg"), auxiliar1);
        Images auxiliar2Image= setImage("auxiliar2Image", "jpeg", imageUtils.loadImageData("C:\\Users\\dj-os\\OneDrive\\Documentos\\Spring\\web-page\\dont-used\\example-images\\auxiliar2.jpg"), auxiliar2);
        Images bisacrilico1Image= setImage("bisacrilico1Image", "jpeg", imageUtils.loadImageData("C:\\Users\\dj-os\\OneDrive\\Documentos\\Spring\\web-page\\dont-used\\example-images\\bisacrilico1.jpg"), bisacrilico1);
        Images bisacrilico2Image= setImage("bisacrilico2Image", "jpeg", imageUtils.loadImageData("C:\\Users\\dj-os\\OneDrive\\Documentos\\Spring\\web-page\\dont-used\\example-images\\bisacrilico2.jpg"), bisacrilico2);
        Images carrierBraket1Image= setImage("carrierBraket1Image", "jpeg", imageUtils.loadImageData("C:\\Users\\dj-os\\OneDrive\\Documentos\\Spring\\web-page\\dont-used\\example-images\\carrierBraket1.jpg"), carrierBraket1);
        Images cemento1Image= setImage("cemento1Image", "jpeg", imageUtils.loadImageData("C:\\Users\\dj-os\\OneDrive\\Documentos\\Spring\\web-page\\dont-used\\example-images\\cemento1.jpg"), cemento1);
        Images cepilloOralImage= setImage("cepilloOralImage", "jpeg", imageUtils.loadImageData("C:\\Users\\dj-os\\OneDrive\\Documentos\\Spring\\web-page\\dont-used\\example-images\\CEPILLO-ORAL.jpg"), cepilloOral);
        Images compomero1Image= setImage("compomero1Image", "png", imageUtils.loadImageData("C:\\Users\\dj-os\\OneDrive\\Documentos\\Spring\\web-page\\dont-used\\example-images\\compomero1.png"), compomero1);
        Images deltaForceBraket1Image= setImage("deltaForceBraket1Image", "jpeg", imageUtils.loadImageData("C:\\Users\\dj-os\\OneDrive\\Documentos\\Spring\\web-page\\dont-used\\example-images\\dentalForceBraket1.jpg"), deltaForceBraket1);
        Images distalizador1Image= setImage("distalizador1Image", "jpeg", imageUtils.loadImageData("C:\\Users\\dj-os\\OneDrive\\Documentos\\Spring\\web-page\\dont-used\\example-images\\distalizador1.jpg"), distalizador1);
        Images distalizador2Image= setImage("distalizador2Image", "jpeg", imageUtils.loadImageData("C:\\Users\\dj-os\\OneDrive\\Documentos\\Spring\\web-page\\dont-used\\example-images\\distalizador2.jpg"), distalizador2);
        Images elastomeros1Image= setImage("elastomeros1Image", "jpeg", imageUtils.loadImageData("C:\\Users\\dj-os\\OneDrive\\Documentos\\Spring\\web-page\\dont-used\\example-images\\elastomero1.jpg"), elastomeros1);
        Images elastomeros2Image= setImage("elastomeros2Image", "jpeg", imageUtils.loadImageData("C:\\Users\\dj-os\\OneDrive\\Documentos\\Spring\\web-page\\dont-used\\example-images\\elastomero2.jpg"), elastomeros2);
        Images estandarBraket1Image= setImage("estandarBraket1Image", "jpeg", imageUtils.loadImageData("C:\\Users\\dj-os\\OneDrive\\Documentos\\Spring\\web-page\\dont-used\\example-images\\estandarBraket1.jpg"), estandarBraket1);
        Images estandarBraket2Image= setImage("estandarBraket2Image", "jpeg", imageUtils.loadImageData("C:\\Users\\dj-os\\OneDrive\\Documentos\\Spring\\web-page\\dont-used\\example-images\\estandarBraket2.jpg"), estandarBraket2);
        Images guantesLatexImage= setImage("guantesLatexImage", "jpeg", imageUtils.loadImageData("C:\\Users\\dj-os\\OneDrive\\Documentos\\Spring\\web-page\\dont-used\\example-images\\Guantes-latex.jpg"), guantesLatex);
        Images hojaBisturi1Image= setImage("hojaBisturi1Image", "jpeg", imageUtils.loadImageData("C:\\Users\\dj-os\\OneDrive\\Documentos\\Spring\\web-page\\dont-used\\example-images\\hojaBisturi1.jpg"), hojaBisturi1);
        Images hojaBisturi2Image= setImage("hojaBisturi2Image", "jpeg", imageUtils.loadImageData("C:\\Users\\dj-os\\OneDrive\\Documentos\\Spring\\web-page\\dont-used\\example-images\\hojaBisturi2.jpg"), hojaBisturi2);
        Images instrumentoOrtodoncia1Image= setImage("instrumentoOrtodoncia1Image", "jpeg", imageUtils.loadImageData("C:\\Users\\dj-os\\OneDrive\\Documentos\\Spring\\web-page\\dont-used\\example-images\\instrumentoOrtodoncia1.jpg"), instrumentoOrtodoncia1);
        Images instrumentoOrtodoncia2Image= setImage("instrumentoOrtodoncia2Image", "jpeg", imageUtils.loadImageData("C:\\Users\\dj-os\\OneDrive\\Documentos\\Spring\\web-page\\dont-used\\example-images\\instrumentoOrtodoncia2.jpg"), instrumentoOrtodoncia2);
        Images jeringa1Image= setImage("jeringa1Image", "jpeg", imageUtils.loadImageData("C:\\Users\\dj-os\\OneDrive\\Documentos\\Spring\\web-page\\dont-used\\example-images\\jeringa1.jpg"), jeringa1);
        Images jeringa2Image= setImage("jeringa2Image", "jpeg", imageUtils.loadImageData("C:\\Users\\dj-os\\OneDrive\\Documentos\\Spring\\web-page\\dont-used\\example-images\\jeringa2.jpg"), jeringa2);
        Images mango1Image= setImage("mango1Image", "jpeg", imageUtils.loadImageData("C:\\Users\\dj-os\\OneDrive\\Documentos\\Spring\\web-page\\dont-used\\example-images\\mango1.jpg"), mango1);
        Images mango2Image= setImage("mango2Image", "jpeg", imageUtils.loadImageData("C:\\Users\\dj-os\\OneDrive\\Documentos\\Spring\\web-page\\dont-used\\example-images\\mango2.jpg"), mango2);
        Images mBTBraket1Image= setImage("MBTBraket1Image", "jpeg", imageUtils.loadImageData("C:\\Users\\dj-os\\OneDrive\\Documentos\\Spring\\web-page\\dont-used\\example-images\\MBTBraket1.jpg"), mBTBraket1);
        Images mBTBraket2Image= setImage("MBTBraket2Image", "jpeg", imageUtils.loadImageData("C:\\Users\\dj-os\\OneDrive\\Documentos\\Spring\\web-page\\dont-used\\example-images\\MBTBraket2.jpg"), mBTBraket2);
        Images ortopedia1Image= setImage("ortopedia1Image", "jpeg", imageUtils.loadImageData("C:\\Users\\dj-os\\OneDrive\\Documentos\\Spring\\web-page\\dont-used\\example-images\\ortopedia1.jpg"), ortopedia1);
        Images ortopedia2Image= setImage("ortopedia2Image", "jpeg", imageUtils.loadImageData("C:\\Users\\dj-os\\OneDrive\\Documentos\\Spring\\web-page\\dont-used\\example-images\\ortopedia2.jpg"), ortopedia2);
        Images pinza1Image= setImage("pinza1Image", "jpeg", imageUtils.loadImageData("C:\\Users\\dj-os\\OneDrive\\Documentos\\Spring\\web-page\\dont-used\\example-images\\pinza1.jpg"), pinzaDental1);
        Images pinza2Image= setImage("pinza2Image", "jpeg", imageUtils.loadImageData("C:\\Users\\dj-os\\OneDrive\\Documentos\\Spring\\web-page\\dont-used\\example-images\\pinza2.jpg"), pinzaDental2);
        Images pinza1Ortodoncia1Image= setImage("pinza1Ortodoncia1Image", "jpeg", imageUtils.loadImageData("C:\\Users\\dj-os\\OneDrive\\Documentos\\Spring\\web-page\\dont-used\\example-images\\pinzaOrtodoncia1.jpg"), pinzaOrtodoncia1);
        Images pinza2Ortodoncia1Image= setImage("pinza2Ortodoncia1Image", "jpeg", imageUtils.loadImageData("C:\\Users\\dj-os\\OneDrive\\Documentos\\Spring\\web-page\\dont-used\\example-images\\pinzaOrtodoncia2.jpg"), pinzaOrtodoncia2);
        Images portaaguja1Image= setImage("portaaguja1Image", "jpeg", imageUtils.loadImageData("C:\\Users\\dj-os\\OneDrive\\Documentos\\Spring\\web-page\\dont-used\\example-images\\portaaguja1.jpg"), portaAguja1);
        Images portaaguja2Image= setImage("portaaguja2Image", "jpeg", imageUtils.loadImageData("C:\\Users\\dj-os\\OneDrive\\Documentos\\Spring\\web-page\\dont-used\\example-images\\portaaguja2.jpg"), portaAguja2);
        Images protectorCepilloImage= setImage("protectorCepilloImage", "jpeg", imageUtils.loadImageData("C:\\Users\\dj-os\\OneDrive\\Documentos\\Spring\\web-page\\dont-used\\example-images\\protectorCepillo.jpg"), protectorCepillo);
        Images provisional1Image= setImage("provisional1Image", "jpeg", imageUtils.loadImageData("C:\\Users\\dj-os\\OneDrive\\Documentos\\Spring\\web-page\\dont-used\\example-images\\provisional1.jpg"), provisional1);
        Images provisional2Image= setImage("provisional2Image", "jpeg", imageUtils.loadImageData("C:\\Users\\dj-os\\OneDrive\\Documentos\\Spring\\web-page\\dont-used\\example-images\\provisional2.jpg"), provisional2);
        Images rebase1Image= setImage("rebase1Image", "png", imageUtils.loadImageData("C:\\Users\\dj-os\\OneDrive\\Documentos\\Spring\\web-page\\dont-used\\example-images\\rebase1.png"), rebase1);
        Images rebase2Image= setImage("rebase2Image", "jpeg", imageUtils.loadImageData("C:\\Users\\dj-os\\OneDrive\\Documentos\\Spring\\web-page\\dont-used\\example-images\\rebase2.jpg"), rebase2);
        Images reconstructor1Image= setImage("reconstructor1Image", "jpeg", imageUtils.loadImageData("C:\\Users\\dj-os\\OneDrive\\Documentos\\Spring\\web-page\\dont-used\\example-images\\reconstructor1.jpg"), reconstructor1);
        Images reconstructor2Image= setImage("reconstructor2Image", "jpeg", imageUtils.loadImageData("C:\\Users\\dj-os\\OneDrive\\Documentos\\Spring\\web-page\\dont-used\\example-images\\reconstructor2.jpg"), reconstructor2);
        Images resina1Image= setImage("resina1Image", "jpeg", imageUtils.loadImageData("C:\\Users\\dj-os\\OneDrive\\Documentos\\Spring\\web-page\\dont-used\\example-images\\resina1.jpg"), resina1);
        Images resina2Image= setImage("resina2Image", "jpeg", imageUtils.loadImageData("C:\\Users\\dj-os\\OneDrive\\Documentos\\Spring\\web-page\\dont-used\\example-images\\resina2.jpg"), resina2);
        Images rothBraket1Image= setImage("rothBraket1Image", "jpeg", imageUtils.loadImageData("C:\\Users\\dj-os\\OneDrive\\Documentos\\Spring\\web-page\\dont-used\\example-images\\rothBraket1.jpg"), rothBraket1);
        Images rothBraket2Image= setImage("rothBraket2Image", "jpeg", imageUtils.loadImageData("C:\\Users\\dj-os\\OneDrive\\Documentos\\Spring\\web-page\\dont-used\\example-images\\rothBraket2.jpg"), rothBraket2);
        Images tigera1Image= setImage("tigera1Image", "jpeg", imageUtils.loadImageData("C:\\Users\\dj-os\\OneDrive\\Documentos\\Spring\\web-page\\dont-used\\example-images\\tigera1.jpg"), tigera1);
        Images tubo1Image= setImage("tubo1Image", "png", imageUtils.loadImageData("C:\\Users\\dj-os\\OneDrive\\Documentos\\Spring\\web-page\\dont-used\\example-images\\tubo1.png"), tubo1);
        Images tubo2Image= setImage("tubo2Image", "jpeg", imageUtils.loadImageData("C:\\Users\\dj-os\\OneDrive\\Documentos\\Spring\\web-page\\dont-used\\example-images\\tubo2.jpg"), tubo2);


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

    private Images setImage(String name, String contentType, byte[] data, Products product){
        Images img = new Images();
        img.setName(name);
        img.setContentType(contentType);
        img.setData(data);
        img.setProduct(product);
        return img;
    }

    private Products setProduct(String name, double price, String description, int stock, int updates, boolean openToPublic, Categories category){
        Products product = new Products();
        product.setNameProduct(name);
        product.setUnitPrice(price);
        product.setDescription(description);
        product.setStock(stock);
        product.setNumberUpdates(updates);
        product.setOpenToPublic(openToPublic);
        product.setCategory(category);
        return product;
    }

    private Categories setCategory(String name, int updates, Categories parent){
        Categories category = new Categories();
        category.setName(name);
        category.setNumberUpdates(updates);
        category.setParentCategory(parent);
        return category;
    }
}
