package org.example;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;
import org.drools.model.Drools;
import org.kie.api.KieBase;
import org.kie.api.KieServices;
import org.kie.api.builder.KieFileSystem;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import org.kie.api.runtime.StatelessKieSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

/**
 * Unit test for simple App.
 */
public class AppTest extends TestCase
{
    Logger logger = LoggerFactory.getLogger(AppTest.class);
    /**
     * Create the test case
     *
     * @param testName name of the test case
     */
    public AppTest( String testName )
    {
        super( testName );
    }

    /**
     * @return the suite of tests being tested
     */
    public static Test suite()
    {
        return new TestSuite( AppTest.class );
    }

    /**
     * Rigourous Test :-)
     */
    public void testStateful() throws Exception
    {
        logger.debug("enter testApp()");
        KieServices kieServices = KieServices.Factory.get();
        KieContainer kContainer = kieServices.getKieClasspathContainer();
        KieBase kBase1 = kContainer.getKieBase("KBase1");
        KieSession kieSession1 = kContainer.newKieSession("KSession2_1");

        List lAccounts = new ArrayList();
        Account a1 = new Account();
        a1.setId(1);
        a1.setType("Savings");
        a1.setClosed(false);
        a1.setBalance(100.50);
        lAccounts.add(a1);

        Account a2 = new Account();
        a2.setId(2);
        a2.setType("Savings");
        a2.setClosed(false);
        a2.setBalance(100.50);
        lAccounts.add(a2);

        Account a3 = new Account();
        a3.setId(3);
        a3.setType("Savings");
        a3.setClosed(true);
        a3.setBalance(100.50);
        lAccounts.add(a3);

        Facts facts = new Facts();
        facts.getFactsList().put("Postcode","2204");
        facts.getFactsList().put("Date of birth","10/12/2001");
        facts.getFactsList().put("Valid email address","false");
        facts.getFactsList().put("Accounts", lAccounts);
        facts.setEligible(true);

        kieSession1.insert(facts);
        CustomAgendaFilter caf = new CustomAgendaFilter();
        caf.addFilter("GBL");
        caf.addFilter("CUS");
        caf.addFilter("CNT");
        caf.addFilter("ACCT");
        kieSession1.fireAllRules(caf);
        //kieSession1.fireAllRules();
        kieSession1.destroy();

        System.out.println(facts.getMessage().toString());

        assertTrue( true );
        logger.debug("exit testApp()");
    }
/*
    public void testStateless() throws Exception
    {
        logger.debug("enter testApp()");
        KieServices kieServices = KieServices.Factory.get();
        KieContainer kContainer = kieServices.getKieClasspathContainer();
        KieBase kBase1 = kContainer.getKieBase("KBase1");
        StatelessKieSession kieSession2 = kContainer.newStatelessKieSession("KSession2_2");

        assertTrue( true );
        logger.debug("exit testApp()");
    }*/
}
