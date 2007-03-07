/*
 * TestInvalidUser.java
 *
 * Created on 07 March 2007, 12:42
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package uk.icat3.search;

import java.util.Collection;
import junit.framework.JUnit4TestAdapter;
import org.junit.Test;
import org.apache.log4j.Logger;
import uk.icat3.util.BaseTestClass;
import static org.junit.Assert.*;
import static uk.icat3.util.TestConstants.*;
import uk.icat3.util.TestConstants;
import uk.icat3.entity.Investigation;
/**
 *
 * @author gjd37
 */
public class TestInvalidUser extends BaseTestClass {
    
    private static Logger log = Logger.getLogger(TestInvalidUser.class);
    
    /**
     * Tests whether the number of instruments is correct
     */
    @Test public void testInstrumentSearch(){
        log.info("Testing invalid user: "+INVALID_USER+ " for number of instruments");
        
        //get the number of instruments
        Collection<String> instrumentsInDB = (Collection<String>)executeListResultCmd("SELECT  DISTINCT i.name from Instrument i");
        
        //get all the instruments
        Collection<String> instruments = InvestigationSearch.listAllInstruments(INVALID_USER, em);
        
        log.trace("number searched is: "+instruments.size()+", number in DB: "+instrumentsInDB.size());
        assertNotNull("Must not be an null collection of instruments", instruments);
        assertSame("Number of instruments searched is different to number in DB",instrumentsInDB.size(),instruments.size());
        
    }
    
    /**
     * Tests whether the user has any investigations and that he has access to the number of investigations
     * with no investigator
     */
    @Test public void testUserInvestigations(){
        log.info("Testing invalid user: "+INVALID_USER+ ", should be associated with no investigations");
        
        //search for users own investigations
        Collection<Investigation> userInvestigations = InvestigationSearch.getUsersInvestigations(INVALID_USER, em);
        log.trace("Investigations for user "+INVALID_USER+" is "+userInvestigations.size());
        
        assertNotNull("Must not be an null collection", userInvestigations);
        assertEquals("Collection 'getUsersInvestigations()' should be zero size", 0 , userInvestigations.size());
        
    }
    
    /**
     * Tests whether the user has any investigations and that he has access to the number of investigations
     * with no investigator
     */
    @Test public void testKeywords(){
        log.info("Testing invalid user: "+INVALID_USER+ ", should be associated with keywords for only empty investigations investigators");
        
        //get the number of keywords
      /*  Collection<String> keywordsInDB = (Collection<String>)executeNativeListResultCmd("select distinct(lower(name)) as name from keyword order by name asc");
       
        //clear manager, because of alot of keywords
        em.clear();
       
        //search all keywords
        Collection<String> keywords = KeywordSearch.getAllKeywords(INVALID_USER, em);
       
        assertNotNull("Must not be an null collection of keywords", keywords);
        assertSame("Number of keywords searched is different to number in DB",keywordsInDB.size(),keywords.size());
       
         //clear manager
        em.clear();*/
        
        //get number of keywords user can see
       /* Collection<String> keywordsUserInDB = (Collection<String>)executeListResultCmd("SE");
        
        //clear manager, because of alot of keywords
        em.clear();
        
        //search all keywords
        Collection<String> keywordsForUser = KeywordSearch.getKeywordsForUser(INVALID_USER, em);
        
        assertNotNull("Must not be an null collection of user keywords", keywordsForUser);
        assertSame("Number of user keywords searched for user is different to number in DB",keywordsUserInDB.size(),keywordsForUser.size());
        
        //clear manager
        em.clear();*/
        
        
        //get number of keywords user can see starting with isis
       /* Collection<String> keywordsUserInDB = (Collection<String>)executeListResultCmd("SE");
        
        //clear manager, because of alot of keywords
        em.clear();
        
        //search all keywords
        Collection<String> keywordsForUser = KeywordSearch.getKeywordsForUser(INVALID_USER, "isis", em);
        
        assertNotNull("Must not be an null collection of user keywords", keywordsForUser);
        assertSame("Number of user keywords searched for user is different to number in DB",keywordsUserInDB.size(),keywordsForUser.size());
        
        //clear manager
        em.clear();*/
    }
    
    /**
     * Tests whether the user has any investigations and that he has access to the number of investigations
     * with no investigator
     */
    @Test public void testKeywordSearch(){
        log.info("Testing invalid user: "+INVALID_USER+ ", should be associated with no investigations");
        
         //get the number of keywords
       /* Long investigations = (Long)executeSingleResultCmd("SELECT count(i) FROM Investigation i where i.investigatorCollection IS EMPTY ");
       
        
        //search for users own investigations
        Collection<Investigation> searchedInvestigations = InvestigationSearch.searchByKeyword(INVALID_USER,"%", 0, 100000, em);
        log.trace("Investigations for user "+INVALID_USER+" is "+searchedInvestigations.size());
        
        assertNotNull("Must not be an null collection", searchedInvestigations);
        assertEquals("Collection 'searchByKeyword()' should be zero size", investigations , searchedInvestigations.size());
        
        //search by user id
        /*Collection<Investigation> searchedUserIdInvestigations = InvestigationSearch.searchByUserID(INVALID_USER,"JAMES-JAMES", em);
        log.trace("Investigations for user "+INVALID_USER+" is "+searchedUserIdInvestigations.size());
        
        assertNotNull("Must not be an null collection", searchedUserIdInvestigations);
        assertEquals("Collection 'searchByKeyword()' should be zero size", 0 , searchedUserIdInvestigations.size());
        
        
        //search by surname
        Collection<Investigation> searchedSurnameInvestigations = InvestigationSearch.searchByUserSurname(INVALID_USER,"HEALY", em);
        log.trace("Investigations for user "+INVALID_USER+" is "+searchedSurnameInvestigations.size());
        
        assertNotNull("Must not be an null collection", searchedSurnameInvestigations);
        assertEquals("Collection 'searchByKeyword()' should be zero size", 0 , searchedSurnameInvestigations.size());*/
    }
    
    
    
    
    public static junit.framework.Test suite(){
        return new JUnit4TestAdapter(TestInvalidUser.class);
    }
    
}
