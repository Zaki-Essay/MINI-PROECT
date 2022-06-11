package com.gsnotes.web.controllers;


import com.gsnotes.bo.*;
import com.gsnotes.bo.Module;
import com.gsnotes.services.IEnseignantService;
import com.gsnotes.services.IExportDataService;
import com.gsnotes.services.IModuleService;
import com.gsnotes.services.INiveauService;
import com.gsnotes.utils.export.ExcelExporterByModule;
import com.gsnotes.web.models.ExportAllModel;
import com.gsnotes.web.models.ExportModule;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.stereotype.Controller;
import org.springframework.util.StreamUtils;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.method.annotation.StreamingResponseBody;
import org.springframework.web.servlet.view.RedirectView;

import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.Path;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

@Controller
@RequestMapping("/export")
public class ExportController {


    @Autowired
    IModuleService moduleService;

    @Autowired
    IEnseignantService enseignantService;

    @Autowired
    INiveauService niveauService;

    @Autowired
    IExportDataService exportDataService;








    @PostMapping("/dataByModule")
    public void exportDataByModule(@ModelAttribute("exportModule") ExportModule exportModule, HttpServletResponse response) throws IOException {

        //get the session
        String session = exportModule.getSession();


        //the name of the module
        String nomModule = exportModule.getNomModule();

        //get module
        Module module = moduleService.getModuleByTitre(nomModule);


        //prepare the year
        Date d=new Date();
        int year=d.getYear();
        int currentYear=year+1900;
        int previousYear=year+1899;
        String annee=previousYear+"/"+currentYear;


        //determiner le semestre
        //TODO..
        String semestre="Printemps";




        //get enseignant Enseignant
        String enseignantName = enseignantService.getEnseignantNameById(module.getEnseignant().getIdUtilisateur());




        //get classe alise:
        String Classe = module.getNiveau().getAlias();


        //number of elements
        int numberOfElements = module.getElements().size();



        List<Etudiant> etudiants = new ArrayList<>();
        if("Normale".equals(session)){

            //get inscription  modules
            List<InscriptionModule> inscriptionModules = module.getInscriptionModules();

            //get inscription annule
            List<InscriptionAnnuelle> inscriptionAnnuelles = new ArrayList<>();


            for (InscriptionModule inscriptionModule:inscriptionModules) {


                    inscriptionAnnuelles.add(inscriptionModule.getInscriptionAnnuelle());


            }



            //we get inscription module than inscription annulle

            for (InscriptionAnnuelle inscriptionAnnuelle :inscriptionAnnuelles) {

                if(inscriptionAnnuelle.getAnnee()==(d.getYear()+1900)){

                    etudiants.add(inscriptionAnnuelle.getEtudiant());


                }


            }

        }else{
            //get the element or list of elements
            List<Element> elements = module.getElements();

            if (numberOfElements==1){

                List<InscriptionModule> inscriptionModules = module.getInscriptionModules();

                List<InscriptionAnnuelle> inscriptionAnnuelles= new ArrayList<>();



                //get just les inscription module qui ont un note de rattraoage
                for (InscriptionModule inscriptionModule:inscriptionModules) {

                    if(inscriptionModule.getNoteSN()<12){
                        inscriptionAnnuelles.add(inscriptionModule.getInscriptionAnnuelle());

                    }
                }



                //we get just les etudiants de cett annee
                for (InscriptionAnnuelle inscriptionAnnuelle:inscriptionAnnuelles) {


                    if(inscriptionAnnuelle.getAnnee()==(d.getYear()+1900)){
                        etudiants.add(inscriptionAnnuelle.getEtudiant());

                    }



                }






            }else if(numberOfElements>=2){

              //je suppose qu'il exist 2 element (it want some generalization )


                //the first element
                List<InscriptionMatiere> inscriptionMatieresFirstElement = elements.get(0).getInscriptionMatieres();

                //the seconde element
                List<InscriptionMatiere> inscriptionMatieresForSecondeElement = elements.get(1).getInscriptionMatieres();



                //get list des inscription annu
                List<InscriptionAnnuelle> inscriptionAnnuelles = new ArrayList<>();


                for (int i = 0; i < inscriptionMatieresFirstElement.size(); i++) {
                    for (int j = 0; j < inscriptionMatieresForSecondeElement.size(); j++){

                        if(inscriptionMatieresFirstElement.get(i).getInscriptionAnnuelle().getIdInscription()==inscriptionMatieresForSecondeElement.get(j).getInscriptionAnnuelle().getIdInscription()
                                && (inscriptionMatieresFirstElement.get(i).getNoteSN()*inscriptionMatieresFirstElement.get(i).getCoefficient()+inscriptionMatieresForSecondeElement.get(j).getNoteSN()*inscriptionMatieresForSecondeElement.get(j).getCoefficient())<12){

                            inscriptionAnnuelles.add(inscriptionMatieresFirstElement.get(i).getInscriptionAnnuelle());


                        }



                    }




                }

                for (InscriptionAnnuelle inscriptionAnnuelle :inscriptionAnnuelles) {

                    if(inscriptionAnnuelle.getAnnee()==(d.getYear()+1900) && !etudiants.contains(inscriptionAnnuelle.getEtudiant())){
                        etudiants.add(inscriptionAnnuelle.getEtudiant());
                    }


                }



/*
                for (InscriptionMatiere inscriptionMatiere: inscriptionMatieresFirstElement) {

                    if (inscriptionMatiere.getNoteSN()<12){
                        inscriptionAnnuelles1.add(inscriptionMatiere.getInscriptionAnnuelle());

                    }

                }

                //remplir les etudiants qui'ont des rattrapage dans le premmier element
                for (InscriptionAnnuelle inscriptionAnnuelle :inscriptionAnnuelles1) {

                    if(inscriptionAnnuelle.getAnnee()==(d.getYear()+1900)){
                        etudiants.add(inscriptionAnnuelle.getEtudiant());
                    }




                }





                //get list des inscription annu
                List<InscriptionAnnuelle> inscriptionAnnuelles2 = new ArrayList<>();

                for (InscriptionMatiere inscriptionMatiere: inscriptionMatieresForSecondeElement) {

                    if (inscriptionMatiere.getNoteSN()<12){
                        inscriptionAnnuelles2.add(inscriptionMatiere.getInscriptionAnnuelle());

                    }

                }

                //remplir les etudiants qui'ont des rattrapage dans le premmier element
                for (InscriptionAnnuelle inscriptionAnnuelle :inscriptionAnnuelles2) {

                    if(inscriptionAnnuelle.getAnnee()==(d.getYear()+1900) && !etudiants.contains(inscriptionAnnuelle.getEtudiant())){
                        etudiants.add(inscriptionAnnuelle.getEtudiant());
                    }


                }*/



            }
        }


        response.setContentType("application/octet-stream");
        DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss");
        String currentDateTime = dateFormatter.format(new Date());

        String headerKey = "Content-Disposition";
        String headerValue = "attachment; filename=users_" + currentDateTime + ".xlsx";
        response.setHeader(headerKey, headerValue);

        ExcelExporterByModule excelExporterByModule = exportDataService.prepareDataExport( session, nomModule, annee, enseignantName, semestre,  Classe, etudiants,numberOfElements);

        excelExporterByModule.export(response);


    }



    @PostMapping("/allData")
    public void exportAllData(@ModelAttribute("exportAllModel") ExportAllModel exportAllModel, HttpServletResponse response) throws IOException{
        //la session
        String session = exportAllModel.getSession();

        //le nom du niveau
        String niveauName  =  exportAllModel.getNomNiveau();

        //get niveau
        Niveau niveau = niveauService.getNiveauByAlias(niveauName);


        //prepare the year
        Date d=new Date();
        int year=d.getYear();
        int currentYear=year+1900;

        int previousYear=year+1899;
        String annee=previousYear+"/"+currentYear;


        //determiner le semestre
        //TODO.. the semstre is exsactly the rang in inscription module ou bien inscription matire

        String semestre="Printemps";


        //list of module
        List<Module> modules =  niveau.getModules();



        //get classe alise:
        String Classe = niveau.getAlias();


        List<String> listOfFileName = new ArrayList<>();
        //the path to the files
        String pathDir = "C:\\Users\\SuperElectro\\IdeaProjects\\gs_notes_app_boot\\src\\main\\resources\\ExcelFiles\\";


        if ("Normale".equals(session)){


            for (int i = 0; i < modules.size(); i++) {

                Module module = modules.get(i);
                String nomModule = module.getTitre();

                //get enseignant Enseignant
                String enseignantName = enseignantService.getEnseignantNameById(module.getEnseignant().getIdUtilisateur());



                //number of elements
                int numberOfElements = module.getElements().size();


                //les inscription module
                List<InscriptionModule> inscriptionModules = module.getInscriptionModules();

                //les inscription annulle
                List<InscriptionAnnuelle> inscriptionAnnuelles= new ArrayList<>();



                //get just les inscription module qui ont un note de rattraoage
                for (InscriptionModule inscriptionModule:inscriptionModules) {

                    inscriptionAnnuelles.add(inscriptionModule.getInscriptionAnnuelle());


                }


                List<Etudiant> etudiants = new ArrayList<>();
                for (InscriptionAnnuelle inscriptionAnnuelle :inscriptionAnnuelles) {

                    if(inscriptionAnnuelle.getAnnee()==(d.getYear()+1900)){

                        etudiants.add(inscriptionAnnuelle.getEtudiant());


                    }


                }

                //we prapare the data
                ExcelExporterByModule excelExporterByModule = exportDataService.prepareDataExport( session, nomModule, annee, enseignantName, semestre,  Classe, etudiants,numberOfElements);


                //the file name
                DateFormat dateFormatter = new SimpleDateFormat("yyyy_MM_dd_HH_mm_ss");
                String currentDateTime = dateFormatter.format(new Date());
                String filesName = "users_"+i+"_"+ currentDateTime;

                //add list of file in list that we zip later
                listOfFileName.add(pathDir+filesName+".xlsx");




                excelExporterByModule.exportAllFiles(filesName);





            }

        }else{


            for (int i = 0; i < modules.size(); i++) {
                List<Etudiant> etudiants = new ArrayList<>();




                Module module = modules.get(i);

                String nomModule = module.getTitre();

                //get enseignant Enseignant
                String enseignantName = enseignantService.getEnseignantNameById(module.getEnseignant().getIdUtilisateur());



                //number of elements
                int numberOfElements = module.getElements().size();






                if(module.getElements().size()==1){


                    List<InscriptionModule> inscriptionModules = module.getInscriptionModules();

                    List<InscriptionAnnuelle> inscriptionAnnuelles= new ArrayList<>();



                    //get just les inscription module qui ont un note de rattraoage
                    for (InscriptionModule inscriptionModule:inscriptionModules) {

                        if(inscriptionModule.getNoteSN()<12){
                            inscriptionAnnuelles.add(inscriptionModule.getInscriptionAnnuelle());

                        }
                    }





                    //we get just les etudiants de cett annee
                    for (InscriptionAnnuelle inscriptionAnnuelle:inscriptionAnnuelles) {


                        if(inscriptionAnnuelle.getAnnee()==(d.getYear()+1900)){
                            etudiants.add(inscriptionAnnuelle.getEtudiant());

                        }



                    }

                    //we prapare the data
                    ExcelExporterByModule excelExporterByModule = exportDataService.prepareDataExport( session, nomModule, annee, enseignantName, semestre,  Classe, etudiants,numberOfElements);


                    //the file name
                    DateFormat dateFormatter = new SimpleDateFormat("yyyy_MM_dd_HH_mm_ss");
                    String currentDateTime = dateFormatter.format(new Date());
                    String filesName = "users_"+i+"_"+ currentDateTime;

                    //add list of file in list that we zip later
                    listOfFileName.add(pathDir+filesName+".xlsx");




                    excelExporterByModule.exportAllFiles(filesName);






                }else if(module.getElements().size()>=2){

                    List<Element> elements = modules.get(i).getElements();

                    //je suppose qu'il exist 2 element (it want some generalization )


                    //the first element
                    List<InscriptionMatiere> inscriptionMatieresFirstElement = elements.get(0).getInscriptionMatieres();

                    //the seconde element
                    List<InscriptionMatiere> inscriptionMatieresForSecondeElement = elements.get(1).getInscriptionMatieres();



                    //get list des inscription annu
                    List<InscriptionAnnuelle> inscriptionAnnuelles = new ArrayList<>();


                    for (int k = 0; k < inscriptionMatieresFirstElement.size(); k++) {
                        for (int j = 0; j < inscriptionMatieresForSecondeElement.size(); j++){

                            if(inscriptionMatieresFirstElement.get(k).getInscriptionAnnuelle().getIdInscription()==inscriptionMatieresForSecondeElement.get(j).getInscriptionAnnuelle().getIdInscription()
                                    && (inscriptionMatieresFirstElement.get(k).getNoteSN()*inscriptionMatieresFirstElement.get(k).getCoefficient()+inscriptionMatieresForSecondeElement.get(j).getNoteSN()*inscriptionMatieresForSecondeElement.get(j).getCoefficient())<12){

                                inscriptionAnnuelles.add(inscriptionMatieresFirstElement.get(k).getInscriptionAnnuelle());


                            }



                        }



                    }

                    for (InscriptionAnnuelle inscriptionAnnuelle :inscriptionAnnuelles) {

                        if(inscriptionAnnuelle.getAnnee()==(d.getYear()+1900) && !etudiants.contains(inscriptionAnnuelle.getEtudiant())){
                            etudiants.add(inscriptionAnnuelle.getEtudiant());
                        }


                    }

                    //we prapare the data
                    ExcelExporterByModule excelExporterByModule = exportDataService.prepareDataExport( session, nomModule, annee, enseignantName, semestre,  Classe, etudiants,numberOfElements);


                    //the file name
                    DateFormat dateFormatter = new SimpleDateFormat("yyyy_MM_dd_HH_mm_ss");
                    String currentDateTime = dateFormatter.format(new Date());
                    String filesName = "users_"+i+"_"+ currentDateTime;

                    //add list of file in list that we zip later
                    listOfFileName.add(pathDir+filesName+".xlsx");




                    excelExporterByModule.exportAllFiles(filesName);


                }

            }
        }






        //generate zip file the user






        //we redercet to the same page

        response.setContentType("application/zip");
        response.setHeader("Content-Disposition", "attachment; filename=download.zip");
        try(ZipOutputStream zipOutputStream = new ZipOutputStream(response.getOutputStream())) {
            for(String fileName : listOfFileName) {
                FileSystemResource fileSystemResource = new FileSystemResource(fileName);
                ZipEntry zipEntry = new ZipEntry(fileSystemResource.getFilename());
                zipEntry.setSize(fileSystemResource.contentLength());
                zipEntry.setTime(System.currentTimeMillis());

                zipOutputStream.putNextEntry(zipEntry);

                StreamUtils.copy(fileSystemResource.getInputStream(), zipOutputStream);
                zipOutputStream.closeEntry();
            }
            zipOutputStream.finish();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }


        //delete all execel files in local dir

        for (int i = 0; i < listOfFileName.size(); i++) {

            deleteFile(listOfFileName.get(i));

        }


    }

    private void deleteFile(String fileName){
        Path path = FileSystems.getDefault().getPath(fileName);
        try {
            Files.delete(path);
        } catch (NoSuchFileException x) {
            System.err.format("%s: no such" + " file or directory%n", path);
        } catch (IOException x) {
            System.err.println(x);
        }
    }
}
