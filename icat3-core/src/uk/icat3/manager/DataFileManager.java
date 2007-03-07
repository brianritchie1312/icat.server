/*
 * DataFileManager.java
 *
 * Created on 27 February 2007, 13:32
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package uk.icat3.manager;

import java.util.ArrayList;
import java.util.Collection;
import javax.persistence.EntityManager;
import javax.persistence.EntityNotFoundException;
import org.apache.log4j.Logger;
import uk.icat3.entity.Datafile;
import uk.icat3.entity.Dataset;
import uk.icat3.exceptions.InsufficientPrivilegesException;
import uk.icat3.security.GateKeeper;
import uk.icat3.util.AccessType;

/**
 *
 * @author gjd37
 */
public class DataFileManager extends ManagerUtil {
    
    static Logger log = Logger.getLogger(DataFileManager.class);
    
    /**
     * Deletes the data file for a user depending if the user's id has delete permissions to delete the data file
     *
     * @param userId
     * @param dataFile
     * @param manager
     * @throws javax.persistence.EntityNotFoundException if entity does not exist in database
     * @throws uk.icat3.exceptions.InsufficientPrivilegesException if user has insufficient privileges to the object
     */
    public void deleteDataFile(String userId, Datafile dataFile, EntityManager manager) throws EntityNotFoundException, InsufficientPrivilegesException{
        
        deleteDataFile(userId, dataFile.getId(), manager);
        
    }
    
    /**
     * Deletes the data file with ID, for a users depending if the users id has delete permissions to delete the data file from
     * the ID
     *
     * @param userId
     * @param dataFileId
     * @param manager
     * @throws javax.persistence.EntityNotFoundException if entity does not exist in database
     * @throws uk.icat3.exceptions.InsufficientPrivilegesException if user has insufficient privileges to the object
     */
    public void deleteDataFile(String userId, Long dataFileId, EntityManager manager) throws EntityNotFoundException, InsufficientPrivilegesException{
        log.trace("deleteDataFile("+userId+", "+dataFileId+", EntityManager)");
        
        Datafile dataFile = checkDataFile(dataFileId, manager);
        
        //check user has delete access
        GateKeeper.performAuthorisation(userId, dataFile, AccessType.DELETE, manager);
        
        //delete DataFile (Cascade is true);
        //TODO might have to remove all the datafiles first
        manager.remove(dataFile);
        
    }
    
    /**
     *
     * Deletes the collection of files for a users depending if the users id has delete permissions to delete the files from
     * their ids
     *
     * @param userId
     * @param dataFileIds
     * @param manager
     * @throws javax.persistence.EntityNotFoundException if entity does not exist in database
     * @throws uk.icat3.exceptions.InsufficientPrivilegesException if user has insufficient privileges to the object
     */
    public void deleteDataFiles(String userId, Collection<Long> dataFileIds, EntityManager manager) throws EntityNotFoundException, InsufficientPrivilegesException{
        log.trace("deleteDataFiles("+userId+", "+dataFileIds+", EntityManager)");
        
        for(Long dataFileId : dataFileIds){
            deleteDataFile(userId, dataFileId, manager);
        }
    }
    
    // TODO:  added boolean to avoid erausre name clash with above method
    
    /**
     * Deletes the collection of data files for a users depending if the users id has delete permissions to delete the data file
     *  
     * @param userId
     * @param dataFiles
     * @param manager
     * @throws javax.persistence.EntityNotFoundException if entity does not exist in database
     * @throws uk.icat3.exceptions.InsufficientPrivilegesException if user has insufficient privileges to the object
     * @return
     */
    public boolean deleteDataFiles(String userId, Collection<Datafile> dataFiles, EntityManager manager) throws EntityNotFoundException, InsufficientPrivilegesException{
        log.trace("deleteDataFiles("+userId+", "+dataFiles+", EntityManager)");
        
        for(Datafile dataFile : dataFiles){
            deleteDataFile(userId, dataFile, manager);
        }
        
        return true;
    }
    
    /**
     * Updates a data file depending on weather the user has permission to update this data file
     *
     * @param userId
     * @param dataFile
     * @param manager
     * @throws javax.persistence.EntityNotFoundException if entity does not exist in database
     * @throws uk.icat3.exceptions.InsufficientPrivilegesException if user has insufficient privileges to the object
     */
    public void updateDataFile(String userId, Datafile dataFile, EntityManager manager) throws EntityNotFoundException, InsufficientPrivilegesException{
        log.trace("updateDataFile("+userId+", "+dataFile+", EntityManager)");
        
        //check to see if DataFile exists, dont need the returned DataFile as merging
        checkDataFile(dataFile.getId(), manager);
        
        //check user has update access
        GateKeeper.performAuthorisation(userId, dataFile, AccessType.UPDATE, manager);
        
        manager.merge(dataFile);
        
    }
    
    /**
     * Adds a data file to the list a files for a data set, depending if the user has update permission on the data set
     *
     * @param userId
     * @param dataFile
     * @param datasetId
     * @param manager
     * @throws javax.persistence.EntityNotFoundException if entity does not exist in database
     * @throws uk.icat3.exceptions.InsufficientPrivilegesException if user has insufficient privileges to the object
     */
    public void addDataFile(String userId, Datafile dataFile, Long datasetId, EntityManager manager) throws EntityNotFoundException, InsufficientPrivilegesException{
        log.trace("addDataFile("+userId+", "+dataFile+" "+datasetId+", EntityManager)");
        
        Collection<Datafile> dataFiles = new ArrayList<Datafile>();
        dataFiles.add(dataFile);
        
        addDataFiles(userId, dataFiles, datasetId, manager);
    }
    
    /**
     *
     *  Adds a collection of data files to the list a files for a data set, depending if the user has update permission on the data set
     *
     * @param userId
     * @param dataFiles
     * @param datasetId
     * @param manager
     * @throws javax.persistence.EntityNotFoundException if entity does not exist in database
     * @throws uk.icat3.exceptions.InsufficientPrivilegesException if user has insufficient privileges to the object
     */
    public void addDataFiles(String userId, Collection<Datafile> dataFiles, Long datasetId, EntityManager manager) throws EntityNotFoundException, InsufficientPrivilegesException{
        log.trace("addDataFile("+userId+", "+dataFiles+" "+datasetId+", EntityManager)");
        
        //check dataset exist
        Dataset dataset  = DataSetManager.checkDataSet(datasetId, manager);
        
        //check user has update access
        GateKeeper.performAuthorisation(userId, dataset, AccessType.UPDATE, manager);
        
        for(Datafile dataFile : dataFiles){
            dataset.addDataFile(dataFile);
        }
    }
    
    /**
     * Gets the data file objects from a list of data file ids, depending if the users has access to read the data files
     *
     * @param userId
     * @param dataFileIds
     * @param manager
     * @throws javax.persistence.EntityNotFoundException if entity does not exist in database
     * @throws uk.icat3.exceptions.InsufficientPrivilegesException if user has insufficient privileges to the object
     * @return
     */
    public Collection<Datafile> getDataFiles(String userId, Collection<Long> dataFileIds, EntityManager manager) throws EntityNotFoundException, InsufficientPrivilegesException{
        log.trace("getDataFile("+userId+", "+dataFileIds+" EntityManager)");
        
        Collection<Datafile> dataFiles = new ArrayList<Datafile>();
        
        for(Long dataFileId : dataFileIds) {
            
            //check DataFile exist
            Datafile dataFile  = checkDataFile(dataFileId, manager);
            
            //check user has read access
            GateKeeper.performAuthorisation(userId, dataFile, AccessType.READ, manager);
            
            //add to arraylist
            dataFiles.add(dataFile);
        }
        
        return dataFiles;
    }
    
    
    /**
     * Gets a data file object from a list of data file id, depending if the users has access to read the data file
     *
     * @param userId
     * @param dataFileId
     * @param manager
     * @throws javax.persistence.EntityNotFoundException if entity does not exist in database
     * @throws uk.icat3.exceptions.InsufficientPrivilegesException if user has insufficient privileges to the object
     * @return
     */
    public Collection<Datafile> getDataFile(String userId, Long dataFileId, EntityManager manager) throws EntityNotFoundException, InsufficientPrivilegesException{
        Collection<Long> dataFiles = new ArrayList<Long>();
        dataFiles.add(dataFileId);
        
        return getDataFiles(userId, dataFiles, manager);
    }
    
}