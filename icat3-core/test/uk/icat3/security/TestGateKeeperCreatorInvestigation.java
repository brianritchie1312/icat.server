/*
 * TestDatafileManager.java
 *
 * Created on 07 March 2007, 12:42
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package uk.icat3.security;

import uk.icat3.investigationmanager.*;
import java.util.Random;
import junit.framework.JUnit4TestAdapter;

import org.apache.log4j.Logger;
import uk.icat3.exceptions.ICATAPIException;
import static org.junit.Assert.*;
import org.junit.Test;
import uk.icat3.entity.Investigation;
import uk.icat3.entity.Keyword;
import uk.icat3.exceptions.InsufficientPrivilegesException;
import uk.icat3.util.AccessType;
import uk.icat3.util.BaseTestClassTX;
import static uk.icat3.util.TestConstants.*;

/**
 *
 * @author gjd37
 */
public class TestGateKeeperCreatorInvestigation extends TestGateKeeperUtil {
    
    private static Logger log = Logger.getLogger(TestGateKeeperCreatorInvestigation.class);
    private Random random = new Random();
    
    /**
     * Tests creator on valid investigation for read
     *
     * ACTION_SELECT  - Y
     */
    @Test
    public void testCreatorReadOnInvestigation() throws ICATAPIException {
        log.info("Testing  user: "+CREATOR_USER+ " for reading investigation Id: "+VALID_INVESTIGATION_ID_FOR_CREATOR);
        
        Investigation investigation = getInvestigation(true);
        
        GateKeeper.performAuthorisation(CREATOR_USER, investigation, AccessType.READ, em);
        
        //no exception
        assertTrue("This should be true", true);
    }
    
    /**
     * Tests creator on valid investigation for delete
     *
     * ACTION_DELETE - Y
     */
    @Test
    public void testCreatorDeleteOnInvestigation() throws ICATAPIException {
        log.info("Testing  user: "+CREATOR_USER+ " for deleting investigation Id: "+VALID_INVESTIGATION_ID_FOR_CREATOR);
        
        Investigation investigation = getInvestigation(true);
        
        GateKeeper.performAuthorisation(CREATOR_USER, investigation, AccessType.DELETE, em);
        
        //no exception
        assertTrue("This should be true", true);
    }
    
    /**
     * Tests creator on valid investigation for download
     *
     * ACTION_DOWNLOAD - Y
     */
    @Test
    public void testCreatorDownloadOnInvestigation() throws ICATAPIException {
        log.info("Testing  user: "+CREATOR_USER+ " for download investigation Id: "+VALID_INVESTIGATION_ID_FOR_CREATOR);
        
        Investigation investigation = getInvestigation(true);
        
        GateKeeper.performAuthorisation(CREATOR_USER, investigation, AccessType.DOWNLOAD, em);
        
        //no exception
        assertTrue("This should be true", true);
    }
    
    /**
     * Tests creator on valid investigation for remove (cos investigation this test remove root)
     *
     * ACTION_REMOVE_ROOT - N
     */
    @Test(expected=InsufficientPrivilegesException.class)
    public void testCreatorRemoveOnInvestigation() throws ICATAPIException {
        log.info("Testing  user: "+CREATOR_USER+ " for remove investigation Id: "+VALID_INVESTIGATION_ID_FOR_CREATOR);
        
        Investigation investigation = getInvestigation(true);
        
        try {
            GateKeeper.performAuthorisation(CREATOR_USER, investigation, AccessType.REMOVE, em);
        } catch (InsufficientPrivilegesException ex) {
            log.warn("caught: "+ex.getClass()+" "+ex.getMessage());
            assertTrue("Exception must contain 'does not have permission'", ex.getMessage().contains("does not have permission"));
            throw ex;
        }
    }
    
    /**
     * Tests creator on valid investigation for update
     *
     * ACTION_UPDATE - Y
     */
    @Test
    public void testCreatorUpdateOnInvestigation() throws ICATAPIException {
        log.info("Testing  user: "+CREATOR_USER+ " for update investigation Id: "+VALID_INVESTIGATION_ID_FOR_CREATOR);
        
        Investigation investigation = getInvestigation(true);
        
        GateKeeper.performAuthorisation(CREATOR_USER, investigation, AccessType.UPDATE, em);
        
        //no exception
        assertTrue("This should be true", true);
    }
    
    /**
     * Tests creator on valid investigation for insert (cos investigation this test insert root)
     *
     * * ACTION_ROOT_INSERT - N
     */
    @Test(expected=InsufficientPrivilegesException.class)
    public void testCreatorInsertOnInvestigation() throws ICATAPIException {
        log.info("Testing  user: "+CREATOR_USER+ " for insert on investigation Id: "+VALID_INVESTIGATION_ID_FOR_CREATOR);
        
        Investigation investigation = getInvestigation(true);
        
        try {
            GateKeeper.performAuthorisation(CREATOR_USER, investigation, AccessType.CREATE, em);
        } catch (InsufficientPrivilegesException ex) {
            log.warn("caught: "+ex.getClass()+" "+ex.getMessage());
            assertTrue("Exception must contain 'does not have permission'", ex.getMessage().contains("does not have permission"));
            throw ex;
        }
    }
    
    /**
     * Tests creator on valid investigation for insert (cos investigation this test insert root)
     *
     * ACTION_ROOT_INSERT - Y (set null in inv_id for ICAT_ADMIN_USER+"_investigation)
     */
    @Test
    public void testCreatorInvestigationInsertOnInvestigation() throws ICATAPIException {
        log.info("Testing  user: "+CREATOR_USER+ " for insert on investigation Id: "+VALID_INVESTIGATION_ID_FOR_CREATOR);
        
        Investigation investigation = getInvestigation(false);
        
        GateKeeper.performAuthorisation(CREATOR_USER+"_investigation", investigation, AccessType.CREATE, em);
        
        //no exception
        assertTrue("This should be true", true);
    }
    
    /**
     * Tests creator on valid investigation for update
     *
     * ACTION_SET_FA - N
     */
    @Test(expected=InsufficientPrivilegesException.class)
    public void testCreatorSetFAOnInvestigation() throws ICATAPIException {
        log.info("Testing  user: "+CREATOR_USER+ " for set FA on investigation Id: "+VALID_INVESTIGATION_ID_FOR_CREATOR);
        
        Investigation investigation = getInvestigation(true);
        
        try {
            GateKeeper.performAuthorisation(CREATOR_USER, investigation, AccessType.SET_FA, em);
        } catch (InsufficientPrivilegesException ex) {
            log.warn("caught: "+ex.getClass()+" "+ex.getMessage());
            assertTrue("Exception must contain 'does not have permission'", ex.getMessage().contains("does not have permission"));
            throw ex;
        }
    }
    
    //now try for insert and remove of none investigation objects to test insert and remove
    /**
     * Tests creator on valid investigation for insert keyword
     *
     * ACTION_INSERT - Y
     */
    @Test
    public void testCreatorInsertKeywordOnInvestigation() throws ICATAPIException {
        log.info("Testing  user: "+CREATOR_USER+ " for insert keyword on investigation Id: "+VALID_INVESTIGATION_ID_FOR_CREATOR);
        
        Investigation investigation = getInvestigation(true);
        Keyword keyword = new Keyword();
        keyword.setInvestigation(investigation);
        
        GateKeeper.performAuthorisation(CREATOR_USER, keyword, AccessType.CREATE, em);
        
        //no exception
        assertTrue("This should be true", true);
    }
    
    /**
     * Tests creator on valid investigation for update keyword
     *
     * ACTION_UPDATE - Y
     */
    @Test
    public void testCreatorUpdateKeywordOnInvestigation() throws ICATAPIException {
        log.info("Testing  user: "+CREATOR_USER+ " for update keyword on investigation Id: "+VALID_INVESTIGATION_ID_FOR_CREATOR);
        
        Investigation investigation = getInvestigation(true);
        Keyword keyword = new Keyword();
        keyword.setInvestigation(investigation);
        
        GateKeeper.performAuthorisation(CREATOR_USER, keyword, AccessType.UPDATE, em);
        
        //no exception
        assertTrue("This should be true", true);
    }
    
    /**
     * Tests creator on valid investigation for update keyword
     *
     * ACTION_REMOVE - N
     */
    @Test(expected=InsufficientPrivilegesException.class)
    public void testCreatorRemoveKeywordOnInvestigation() throws ICATAPIException {
        log.info("Testing  user: "+CREATOR_USER+ " for remove keyword on investigation Id: "+VALID_INVESTIGATION_ID_FOR_CREATOR);
        
        Investigation investigation = getInvestigation(true);
        Keyword keyword = new Keyword();
        keyword.setInvestigation(investigation);
        
        try {
            GateKeeper.performAuthorisation(CREATOR_USER, keyword, AccessType.REMOVE, em);
        } catch (InsufficientPrivilegesException ex) {
            log.warn("caught: "+ex.getClass()+" "+ex.getMessage());
            assertTrue("Exception must contain 'does not have permission'", ex.getMessage().contains("does not have permission"));
            throw ex;
        }
    }
    
    public static junit.framework.Test suite(){
        return new JUnit4TestAdapter(TestGateKeeperCreatorInvestigation.class);
    }
}