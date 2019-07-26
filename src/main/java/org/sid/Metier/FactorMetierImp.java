package org.sid.Metier;


import org.sid.dao.EventProjetRepository;
import org.sid.dao.FactorProjetRepository;
import org.sid.dao.FactorRepository;
import org.sid.dao.SourceDangerRepository;
import org.sid.entities.EventProjet;
import org.sid.entities.Factor;
import org.sid.entities.FactorProjet;
import org.sid.entities.SourceDanger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
public class FactorMetierImp implements FactorMetier {
    @Autowired
    private EventProjetRepository eventProjetRepository;
    @Autowired
    private FactorRepository FR;
    @Autowired
    private SourceDangerRepository SDR;
    @Autowired
    private EventProjetMetier eventProjetMetier;

    @Override
    public Factor getFactorById(Long id) {

        return FR.findById(id).get();
    }

    @Override
    public Factor saveFactor(Factor f) {
List<EventProjet> eventProjets=eventProjetRepository.findAll();

        System.out.println(eventProjets.size());

        System.out.println("houni !!!!");

        int n=FR.findAll().size();
        System.out.println(n);

        System.out.println(eventProjets.size());

        for (int l=0;l<eventProjets.size();l++) {
            System.out.println("houni !!!!");

EventProjet eventProjet=eventProjets.get(l);
            System.out.println("houni !!!!");

System.out.println(eventProjet);

double mtx[][]=new double[n+1][n+1];
            System.out.println("lehneee !!!!");

for(int i=0;i<n;i++) {
    for(int j=0;j<n;j++) {
        System.out.println("lehneee !!!!");

        mtx[i][j] =eventProjet.getMtx()[i][j];
    }
}
            System.out.println("r5aj ml for 1 !!!!");


for (int i=0;i<n+1;i++) {
    mtx[n][i]=1;
    mtx[i][n]=1;
}
            System.out.println("r5aj ml for 2 !!!!");

eventProjet.setMtx(mtx);

int n1=n*(n+1)/2;
double comp[]=new double[n1];
int p=0;
for (int i=0;i<n+1;i++){
    for (int j=0;j<n+1;j++){
        if(j>i){
            comp[p]=mtx[i][j];
            p++;
        }
    }
}
            System.out.println("r5aj ml for 3 !!!!");

eventProjet.setCompArray(comp);



            System.out.println("wsol lehnee !!!!");

eventProjetRepository.save(eventProjet);

            System.out.println("wsol!!!!");
            FR.save(f);


            eventProjetMetier.methodeAHP(eventProjet.getId());
            eventProjetRepository.save(eventProjet);




        }



        return FR.save(f);

    }

    @Override
    public List<Factor> listFactors() {
        return FR.findAll();
    }
    @Autowired
    private FactorProjetRepository factorProjetRepository;

    @Override
    public void deleteFactor(Long id) {



        Factor factor=getFactorById(id);

        System.out.println("w9efffffffffff");

            List<Factor> factors= FR.findAll();
            int ind=-1;
            for(  int i=0;i<factors.size();i++ ){
                if(id==factors.get(i).getId()){
                    ind=i;
                }

            }
            System.out.println("0000000");
            System.out.println(ind);
        System.out.println("1111111111");

        List<EventProjet> eventProjets=eventProjetRepository.findAll();
            System.out.println(eventProjets.size());
        int size=factors.size();
        System.out.println("22222222222");

        eventProjetMetier.removeAssociationFactor(getFactorById(id));

        System.out.println("33333333333");


        FR.delete(factor);

        System.out.println("4444444444444");

        for(int l=0;l<eventProjets.size();l++){
                EventProjet eventProjet=eventProjets.get(l);
double mtxe[][]=new double [size-1][size-1];
int ling=0;


for (int i=0;i<size;i++){
    int col=0;
    if(i!=ind){
        System.out.println(i);
    for(int j=0;j<size;j++){
                if(j!=ind){
                    mtxe[ling][col]=eventProjet.getMtx()[i][j];
                    col++; } }
        ling++; } }
eventProjet.setMtx(mtxe);
                int n=size-1;
                int n1=n*(n+1)/2;
                double comp[]=new double[n1];
                int p=0;
                for (int i=0;i<n;i++){
                    for (int j=0;j<n;j++){
                        if(j>i){
                            comp[p]=mtxe[i][j];
                            p++;
                        }
                    }
                }

                eventProjet.setCompArray(comp);

                eventProjetRepository.save(eventProjet);
                eventProjetMetier.methodeAHP(eventProjet.getId());
                eventProjetRepository.save(eventProjet);


            }









    }


    @Override
     public Factor addSourceToFactor(Long idFactor, SourceDanger sourceDanger) {

        System.out.println(idFactor);
        System.out.println(sourceDanger);

        SDR.save(sourceDanger);
        System.out.println(sourceDanger);

        Factor factor=FR.findById(idFactor).get();
        System.out.println(factor);

        factor.getSourceDangers().add(sourceDanger);
        System.out.println(factor);

        return FR.save(factor);



    }

    @Override
    public void deleteSourceFactor(Long idFactor, Long idSource) {
        Factor factor=FR.findById(idFactor).get();
        SourceDanger sourceDanger=SDR.findById(idSource).get();
        factor.getSourceDangers().remove(sourceDanger);
        this.FR.save(factor);

    }

    @Override
    public Long nbreSource(Long id) {
           if(FR.findById(id).get().getSourceDangers().size()!=0) {
                    return id;
           }

           else{return Long.valueOf(-1);}
    }


    @Override
    public List<Long> listnbreSource() {
        List<Long> list=new ArrayList<Long>();
        List<Factor> factorList=FR.findAll();
        for (Factor f:factorList) {
            if(f.getSourceDangers().size()!=0){list.add(f.getId());}
        }
            return list;
        }

    @Override
    public Factor modifierFactor(Long id, Factor factor) {
        factor.setId(id);
        return FR.save(factor);
    }

}



