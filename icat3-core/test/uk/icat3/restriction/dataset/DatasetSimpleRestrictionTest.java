/*
 * This code is developed in Institut Laue-Langevin (France).
 * Its goal is the implementation of parameter search into ICAT Web Service
 * 
 * Created on 6 juil. 2010
 */

package uk.icat3.restriction.dataset;

import uk.icat3.exceptions.EmptyListParameterException;
import uk.icat3.exceptions.NoParameterTypeException;
import uk.icat3.exceptions.NoParametersException;
import uk.icat3.exceptions.NoSearchableParameterException;
import uk.icat3.exceptions.NullParameterException;
import uk.icat3.exceptions.ParameterNoExistsException;
import uk.icat3.exceptions.ParameterSearchException;
import uk.icat3.search.parameter.util.ParameterSearch;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import junit.framework.JUnit4TestAdapter;
import org.junit.Test;
import uk.icat3.entity.Dataset;
import uk.icat3.exceptions.RestrictionException;
import uk.icat3.parametersearch.BaseParameterSearchTest;
import static org.junit.Assert.*;
import uk.icat3.restriction.RestrictionComparisonCondition;
import uk.icat3.restriction.RestrictionComparisonCondition;
import uk.icat3.restriction.RestrictionCondition;
import uk.icat3.restriction.RestrictionLogicalCondition;
import uk.icat3.restriction.RestrictionLogicalCondition;
import uk.icat3.restriction.RestrictionOperator;
import uk.icat3.restriction.RestrictionOperator;
import uk.icat3.restriction.attribute.RestrictionAttributes;
import uk.icat3.search.DatasetSearch;
import uk.icat3.util.DatasetInclude;
import uk.icat3.util.LogicalOperator;
import uk.icat3.util.Queries;

/**
 *
 * @author cruzcruz
 */
public class DatasetSimpleRestrictionTest extends BaseParameterSearchTest {

//    @Test
//    public void datafilesIncludeDatafileTest () throws NoParameterTypeException, RestrictionException, NoParametersException, ParameterSearchException {
//        // Restriction condition
//        RestrictionComparisonCondition restriction1 = new RestrictionComparisonCondition(
//                RestrictionAttributes.DATASET_NAME, RestrictionOperator.CONTAIN, "gation 2");
//        RestrictionLogicalCondition restricLog = new RestrictionLogicalCondition(LogicalOperator.OR)
//                .add(RestrictionCondition.Not(restriction1))
//                .add(new RestrictionComparisonCondition(
//                    RestrictionAttributes.DATASET_NAME, RestrictionOperator.END_WITH, "blue"))
//                ;
//        // Parameter condition
//        ParameterLogicalCondition op1 = new ParameterLogicalCondition(LogicalOperator.OR);
//
//        op1.add(pcDataset.get(0));
//        op1.add(pcDataset.get(1));
//        op1.add(pcSample.get(0));
//        op1.add(pcDatafile.get(1));
//
//        List<Dataset> li = (List<Dataset>) DatasetSearch
//                .searchByParameterCondition(VALID_USER_FOR_DATASET, op1
//                        , restricLog, DatasetInclude.DATASET_DATAFILES_AND_PARAMETERS, 1, -1, em);
//
//       assertEquals("Results of Datasets incorrect.", 1, li.size());
//       assertTrue("Dataset name should be 'dataset_1', not " + li.get(0).getTitle(),
//               (li.get(0).getTitle().contains("dataset_1")));
//       assertEquals("Number of Results of Datafiles of 'dataset_1' are incorrect.",
//               2, li.get(0).getDatafileCollection().size());
//    }

    @Test
    public void differentsAttr () throws NoParameterTypeException, RestrictionException, NoParametersException, ParameterSearchException {

        RestrictionComparisonCondition restriction1 = new RestrictionComparisonCondition(
                RestrictionAttributes.DATASET_NAME, RestrictionOperator.START_WITH, "dataset");

        RestrictionLogicalCondition restrLog = new RestrictionLogicalCondition(LogicalOperator.AND)
                .add(restriction1)
                .add(new RestrictionComparisonCondition(
                RestrictionAttributes.DATAFILE_NAME, RestrictionOperator.END_WITH, ""))
                .add(new RestrictionComparisonCondition(
                RestrictionAttributes.DATASET_NAME, RestrictionOperator.END_WITH, ""))
                .add(new RestrictionComparisonCondition(
                RestrictionAttributes.SAMPLE_NAME, RestrictionOperator.END_WITH, ""))
                ;

        // Dataset search
        List<Dataset> li = (List<Dataset>) DatasetSearch
                .searchByRestriction(VALID_USER_FOR_INVESTIGATION, restrLog, em);

        assertEquals("Results of Datasets incorrect.", 3, li.size());
//        assertEquals("Number of Results of Datafiles of 'dataset_1' are incorrect.",
//               2, li.get(1).getDatafileCollection().size());
    }

    @Test
    public void restrictionConditionTest () throws ParameterSearchException, RestrictionException {
        // Restriction comparison condition
        RestrictionComparisonCondition restriction1 = new RestrictionComparisonCondition(
                RestrictionAttributes.DATASET_NAME, RestrictionOperator.CONTAIN, "dataset");
        // Restricction logical condition
        RestrictionLogicalCondition restricLog = new RestrictionLogicalCondition(LogicalOperator.AND)
                .add(restriction1)
                .add(new RestrictionComparisonCondition(
                    RestrictionAttributes.DATASET_NAME, RestrictionOperator.END_WITH, "red"))
                ;
        restricLog.setOrderByAsc(RestrictionAttributes.DATASET_NAME);
        // Parameter conditions
        List<Dataset> li = (List<Dataset>) DatasetSearch
                .searchByRestriction(VALID_USER_FOR_INVESTIGATION
                        , restricLog, DatasetInclude.NONE, 1, -1, em);

       assertEquals("Results of Datasets incorrect.", 1, li.size());
       assertTrue("Dataset name incorrect.", li.get(0).getName().contains("dataset_2"));
    }

    @Test
    public void betweenTest () throws ParameterSearchException, RestrictionException {
        // Restricction logical condition
        RestrictionLogicalCondition restricLog = new RestrictionLogicalCondition(LogicalOperator.AND)
                .add(new RestrictionComparisonCondition(
                    RestrictionAttributes.DATASET_NAME, RestrictionOperator.END_WITH, "blue"))
                ;
        restricLog.setOrderByAsc(RestrictionAttributes.DATASET_NAME);
        // Dataset search
        List<Dataset> li = (List<Dataset>) DatasetSearch
                .searchByRestriction(VALID_USER_FOR_INVESTIGATION
                        , restricLog, DatasetInclude.NONE, 1, -1, em);

       assertEquals("Results of Datasets incorrect.", 2, li.size());
       assertTrue("Dataset name incorrect.", li.get(0).getName().contains("dataset_1"));
    }

    @Test
    public void restrictionCondition2Test () throws ParameterSearchException, RestrictionException {
        // Restricction logical condition
        RestrictionLogicalCondition restricLog = new RestrictionLogicalCondition(LogicalOperator.AND)
                .add(new RestrictionLogicalCondition(LogicalOperator.OR)
                    .add (new RestrictionComparisonCondition(
                    RestrictionAttributes.DATASET_NAME, RestrictionOperator.END_WITH, "blue"))
                    .add (new RestrictionComparisonCondition(
                    RestrictionAttributes.DATASET_NAME, RestrictionOperator.END_WITH, "red"))

                    )
                ;
        restricLog.setOrderByAsc(RestrictionAttributes.DATASET_NAME);
        // Dataset search
        List<Dataset> li = (List<Dataset>) DatasetSearch
                .searchByRestriction(VALID_USER_FOR_INVESTIGATION
                        , restricLog, DatasetInclude.NONE, 1, -1, em);

       assertEquals("Results of Datasets incorrect.", 3, li.size());
       assertTrue("Dataset name incorrect.", li.get(0).getName().contains("dataset_1"));
    }

    @Test
    public void restrictionComparisonTest () throws NoParameterTypeException, RestrictionException, NoParametersException, ParameterSearchException {

        RestrictionComparisonCondition restriction1 = new RestrictionComparisonCondition(
                RestrictionAttributes.DATASET_NAME, RestrictionOperator.END_WITH, "blue");

        List<Dataset> li = (List<Dataset>) DatasetSearch
                .searchByRestriction(VALID_USER_FOR_INVESTIGATION, restriction1
                , DatasetInclude.NONE, 1, -1, em);

        assertEquals("Results of datasets incorrect.", 2, li.size());
//        assertEquals("Number of Results of Datasets of 'dataset_1' are incorrect.",
//               2, li.get(0).getDatasetCollection().size());
    }

    @Test
    public void restrictionLogicalConditionTest () throws NoParameterTypeException, RestrictionException, ParameterSearchException {
        // Restriction condition
        RestrictionComparisonCondition restriction1 = new RestrictionComparisonCondition(
                RestrictionAttributes.DATASET_NAME, RestrictionOperator.CONTAIN, "dataset");
        RestrictionLogicalCondition restricLog = new RestrictionLogicalCondition(LogicalOperator.AND)
                .add(restriction1)
                .add(new RestrictionComparisonCondition(
                    RestrictionAttributes.DATASET_NAME, RestrictionOperator.END_WITH, "blue"))
                ;
        List<Dataset> li = (List<Dataset>) DatasetSearch
                .searchByRestriction(VALID_USER_FOR_INVESTIGATION, restricLog, DatasetInclude.NONE, 1, -1, em);

       assertEquals("Results of Datasets incorrect.", 2, li.size());
       assertTrue("Dataset name incorrect.", li.get(0).getName().contains("dataset_1"));
    }

    @Test
    public void returnIdsTest () throws NoParameterTypeException, NoParametersException, ParameterSearchException, EmptyListParameterException, NoSearchableParameterException, NullParameterException, ParameterNoExistsException, RestrictionException {

        List li = (List) DatasetSearch
                .searchByRestriction(VALID_USER_FOR_INVESTIGATION, null
                , DatasetInclude.ALL_DATASET_ID
                , Queries.NO_LIMITED_RESULTS
                , Queries.NO_LIMITED_RESULTS, em);

        assertTrue("Results of datasets should be at least 1, not " + li.size(), (li.size() > 2));
        assertTrue("Object should be Long, not " + li.get(0).getClass().getName()
                , Long.class == li.get(0).getClass());
    }

    public static junit.framework.Test suite(){
        return new JUnit4TestAdapter(DatasetSimpleRestrictionTest.class);
    }
}
