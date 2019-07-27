package org.sid;


import com.itextpdf.html2pdf.HtmlConverter;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.extern.java.Log;
import org.apache.tomcat.util.http.fileupload.disk.DiskFileItem;
import org.sid.Metier.FileMetier;
import org.sid.Metier.ProjetMetier;
import org.sid.dao.*;
import org.sid.entities.*;
import org.sid.services.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;
import sun.misc.IOUtils;
import org.springframework.mock.web.MockMultipartFile;
import javax.servlet.ServletContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.io.*;
import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;

import static java.util.logging.Level.INFO;
import static java.util.logging.Level.SEVERE;
@Service
@Log
@RequiredArgsConstructor(onConstructor = @__(@Autowired))

public class Html2PdfServiceImpl implements Html2PdfService {
    private final TemplateEngine templateEngine;
    private static final DateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
@Autowired
private ImpactRepository impactRepository;
    @Autowired
private ProjetRepository projetRepository;
    @Autowired
    private ProjetMetier projetMetier;
    @Autowired
    private FactorRepository factorRepository;
    @Autowired
    private FileMetier fileMetier;
    @Autowired
    private EventProjetRepository eventProjetRepository;
    @Autowired
    private AccountService accountService;
    @Autowired
    private  UndesirableEventRepository undesirableEventRepository;
    private MultipartFile multipartFile;
    DecimalFormat df1 = new DecimalFormat("0.00");

    DateFormat df = new SimpleDateFormat("dd/MM/yy HH:mm:ss");

    @Override
    public org.sid.entities.File html2PdfGenerator(Long idProjet,String username) {
        Projet projet=projetRepository.findById(idProjet).get();
        List<Impact> impacts=impactRepository.findAll();
        List<Factor> factors=factorRepository.findAll();
        AppUser appUser=accountService.loadUserByUsername(username);
        List<UndesirableEvent> undesirableEvents=undesirableEventRepository.findAll();

        Context context = new Context();


        Date today = new Date();
        Calendar cal = Calendar.getInstance();
        cal.setTime(today);
        int dayOfMonth = cal.get(Calendar.DAY_OF_MONTH); // 17
        int month = cal.get(Calendar.MONTH); // 5
        int year = cal.get(Calendar.YEAR); // 2016
        context.setVariable("undesirableEvents",undesirableEvents);

        context.setVariable("impacts",impacts);

        context.setVariable("XY",projet.getXY());

        List<EventProjet> eventAccept=projetMetier.listEventAccept(idProjet);
        context.setVariable("accept",eventAccept);

        List<EventProjet> eventNonAccept=projetMetier.listEventNonAccept(idProjet);
        context.setVariable("nonAccept",eventNonAccept);


        context.setVariable("rank",projet.getOverAllSignificanceRank());


        List<EventProjet> eventProjets1=projet.getEventProjets();
        List<EventProjet> eventProjets=eventProjets1;

        for(int i=0;i<eventProjets.size();i++){
           // eventProjets.get(i).setId(null);
            double weights[] = new double[eventProjets.get(i).getWeights().length];
            for (int j=0;j<eventProjets.get(i).getWeights().length;j++){
             //  eventProjets.get(i).getWeights()[j]=
                weights[j]=(double)Math.round(eventProjets.get(i).getWeights()[j]*100)/100;
                       // Double.parseDouble(df1.format(eventProjets.get(i).getWeights()[j]))
            }
            eventProjets.get(i).setWeights(weights);
            eventProjets.get(i).setFractionTotal((float) Math.round(eventProjets.get(i).getFractionTotal()*100)/100);
            eventProjets.get(i).setLevelRisk((float) Math.round(eventProjets.get(i).getLevelRisk()*100)/100);

        }


        for(int i=0;i<eventProjets.size();i++) {

            double mtx[][]= new double[factors.size()][factors.size()];
            for (int j = 0; j < factors.size(); j++) {
                for (int n = j; n < factors.size(); n++) {
                    if (eventProjets.get(i).getMtx()[j][n] > 0) {
                        mtx[j][n]= (double) Math.round(eventProjets.get(i).getMtx()[j][n] * 100) / 100;
                        mtx[n][j]=(double) Math.round((1/eventProjets.get(i).getMtx()[j][n]) * 100) / 100;
                    }
                }
            }
            eventProjets.get(i).setMtx(mtx);

        }

        Object object=eventProjets;



        context.setVariable("departement",projet.getDepartement());
        context.setVariable("nameProjet",projet.getNameProject());
        context.setVariable("limitAccept",projet.getLimitAccept());
        context.setVariable("event",object);
        context.setVariable("user",projet.getUserCreation());
        context.setVariable("factors",factors);

        context.setVariable("proba",projet.getProbabilityScale());
        context.setVariable("impact",projet.getImpactScale());
        context.setVariable("type",projet.getType());
        context.setVariable("date",sdf.format(today));
       // System.out.println(context.getVariable("event"));
        final String html = templateEngine.process("invoice", context);
        System.out.println("info html");
       log.log(INFO, html);
         System.out.println("info html");
      //  final String DEST2=year+"/"+month+"/"+dayOfMonth+"::"+cal.get(Calendar.HOUR_OF_DAY)+":"+cal.get(Calendar.MINUTE)+":"+cal.get(Calendar.SECOND);

        final String DEST1=projet.getNameProject().replace(' ','-')+"-"+year+"-"+month+"-"+dayOfMonth+"_"+cal.get(Calendar.HOUR_OF_DAY)+"-"+cal.get(Calendar.MINUTE)+"-"+cal.get(Calendar.SECOND)+".pdf";

        final String DEST = "target/"+DEST1;
        try {

            HtmlConverter.convertToPdf(html, new FileOutputStream(DEST));
        System.out.println("try");



          //  File file=new File();
          //  file.setPath(DEST);
          //  fileMetier.saveFile(file);


            File file=new File(DEST);
        System.out.println("file dest);

            // javax.xml.crypto.Data data= (javax.xml.crypto.Data) file;
         //  System.out.println(data);
//fileMetier.handleFileUpload((MultipartFile) file);


         //   projet.getFiles().add(file);
            projetRepository.save(projet);
            InputStream inputStream=new FileInputStream(DEST);
//////////////////////////////////////////////////
            Path path = Paths.get(DEST);
            String name = DEST1;
            String originalFileName = DEST1;

            String contentType = "application/json";
            InputStreamResource inputStreamResource= new InputStreamResource(new FileInputStream(DEST));

            byte[] content = null;
            try {
                content = Files.readAllBytes(path);
                        System.out.println("try 2");

            } catch (final IOException e) {
        System.out.println("exp 2");

            }
            MultipartFile result = new MockMultipartFile(name,
                    originalFileName, contentType, content);

            org.sid.entities.File f=fileMetier.handleFileUpload(result,idProjet);
            f.setDateGener(today.toString());

            f.setUserGener(appUser);
            fileMetier.saveFile(f);
            ///////////////////////////////////////////////////////////////////           //////////////////////////////////////

          //  multipartFile.(DEST1);


            return       f;

            //   return new InputStreamResource(new FileInputStream(DEST));
//return null ;
        } catch (IOException e) {
            log.log(SEVERE, e.getMessage(), e);
                    System.out.println("exp 1");

            return null;
        }
    }



}
