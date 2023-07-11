package org.example;

import org.kie.api.KieServices;
import org.kie.api.builder.KieFileSystem;
import org.kie.api.runtime.KieContainer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


import java.io.IOException;



public class DroolsConfigurator {

    Logger logger = LoggerFactory.getLogger(DroolsConfigurator.class);

    /**
     * Constructor to create the singleton
     *
     */
    public DroolsConfigurator() {

    }

    public KieFileSystem kieFileSystem() throws IOException {
        logger.debug("kieFileSystem()");
        KieServices ks = KieServices.Factory.get();
        KieContainer kc = ks.getKieClasspathContainer();
        KieFileSystem kieFileSystem = ks.newKieFileSystem();

        return kieFileSystem;
    }

    private KieServices kieServices() {
        return KieServices.Factory.get();
    }
    /*
    public KieFileSystem kieFileSystem() throws IOException {
        KieFileSystem kieFileSystem = kieServices().newKieFileSystem();
        for(RuleOperator drlModel:droolsDrlModels){
            kieFileSystem.delete("src/main/resources/" + drlModel.getName() + ".drl");
            if(drlModel.isActive()) {
                kieFileSystem.write("src/main/resources/" + drlModel.getName() + ".drl", drlModel.getResource());
                logger.debug("kieFileSystem.write: " + drlModel.getName());
            }
        }
        logger.debug("kieFileSystem:exit");
        logger.debug("--------------------------------------------------------");
        return kieFileSystem;
    }

    public KieContainer kieContainer(KieFileSystem kieFileSystem) throws IOException {
        logger.debug("kieContainer:enter");
        final KieRepository kieRepository = kieRepository();
        logger.debug("kieRepository: " +kieRepository.toString());
        KieBuilder kieBuilder = kieServices().newKieBuilder(kieFileSystem);
        kieBuilder.buildAll();
        logger.debug("kieContainer:exit");
        return kieServices().newKieContainer(kieRepository.getDefaultReleaseId());
    }

    private KieRepository kieRepository() {
        final KieRepository kieRepository = kieServices().getRepository();
        kieRepository.addKieModule(new KieModule() {
            public ReleaseId getReleaseId() {
                return kieRepository.getDefaultReleaseId();
            }
        });
        return kieRepository;
    }

    private KieServices kieServices() {
        return KieServices.Factory.get();
    }



    @Bean
    public KieContainer getKieContainer() {
        // Load rules from database
        if(kieContainer == null) {
            List<RuleOperator> droolsDrlModels = this.ruleOperatorRepo.findAll();
            kieContainer = this.loadDroolsSessionContainer(droolsDrlModels);
        }
        return kieContainer;
    }

    public KieContainer refreshKieContainer(){
        logger.debug("reloading rules from database");
        List<RuleOperator> droolsDrlModels = this.ruleOperatorRepo.findAll();
        kieContainer = this.loadDroolsSessionContainer(droolsDrlModels);
        return kieContainer;
    }

    public KieContainer loadDroolsSessionContainer(List<RuleOperator> droolsDrlModels){

        long startTime = System.currentTimeMillis();
        // add following if you are using timer in your rules
        //KieSessionConfiguration ksconf = kieServices.newKieSessionConfiguration();
        //ksconf.setOption(TimedRuleExecutionOption.YES);
        KieRepository kr = kieServices.getRepository();

        // Load the rules into the file system
        KieFileSystem kfs = kieServices.newKieFileSystem();
        // Load each rule from rule operator (database)
        for(RuleOperator drlModel:droolsDrlModels){
            kfs.delete("src/main/resources/" + drlModel.getName() + ".drl");
            if(drlModel.isActive()) {
                kfs.write("src/main/resources/" + drlModel.getName() + ".drl", drlModel.getResource());
            }
        }
        // Create the rule builder from the filesystem and build
        KieBuilder kb = kieServices.newKieBuilder(kfs);
        kb.buildAll();
        if (kb.getResults().hasMessages(Message.Level.ERROR)) {
            throw new RuntimeException("Build Errors:\n" + kb.getResults().toString());
        }

        long endTime = System.currentTimeMillis();
        logger.info("Time to build rules : " + (endTime - startTime)  + " ms" );
        startTime = System.currentTimeMillis();
        kieContainer = kieServices.newKieContainer(kr.getDefaultReleaseId());
        endTime = System.currentTimeMillis();
        logger.info("Time to load container: " + (endTime - startTime)  + " ms" );
        return kieContainer;
    }

     */
}
