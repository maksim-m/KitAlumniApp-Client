package edu.kit.isco.kitalumniapp.dbObjects;

import android.content.ContentValues;

/**
 * Dao for Tags
 * Created by Andre on 04.02.2015.
 */

public class DataAccessTag implements DataAccessObject {

    public static final DataAccessTag DATA_ADMINISTRATION    	= new DataAccessTag("Employee in Data Administration");
    public static final DataAccessTag TRAINEE 			 		= new DataAccessTag("Trainee");
    public static final DataAccessTag CLERK				 		= new DataAccessTag("Clerk");
    public static final DataAccessTag GRADUAND	 		 		= new DataAccessTag("Graduand");
    public static final DataAccessTag DOCTORAND 		 		= new DataAccessTag("Doctorand");
    public static final DataAccessTag ENGINEER			 		= new DataAccessTag("FH/BA Engineer");
    public static final DataAccessTag INDUSTRIAL				= new DataAccessTag("Industrial Job");
    public static final DataAccessTag SALES_OCCUPATION	 		= new DataAccessTag("Sales Occupation");
    public static final DataAccessTag THRESHOLD_WORKER 			= new DataAccessTag("Threshold Worker");
    public static final DataAccessTag PROFESSOR			 		= new DataAccessTag("Professor");
    public static final DataAccessTag TECHNICAL_EMPLOYEE		= new DataAccessTag("Technical Employee");
    public static final DataAccessTag STUDENT_RESEARCH_PROJECT 	= new DataAccessTag("Student Research Project");
    public static final DataAccessTag ADMINISTRATION 			= new DataAccessTag("Administration");
    public static final DataAccessTag SCIENTIST 				= new DataAccessTag("Scientist");
    public static final DataAccessTag OTHERS 					= new DataAccessTag("Others");

    private long Id;

    /**
     * Name der Jobkategorie, beispielsweise Informatik oder Mathematik.
     */
    private String name;

    public DataAccessTag() {}
    public DataAccessTag(String name) { this.name = name; }

    public long getId() { return this.Id; }
    public void setId(long Id) { this.Id = Id; }
    public String getName() { return this.name; }
    public void setName(String name) { this.name = name; }

    @Override
    public String toString() {
        return new StringBuffer("ID : ").append(this.Id)
                .append(" Name : ").append(this.name)
                .toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        DataAccessTag that = (DataAccessTag) o;

        if (name != null ? !name.equals(that.name) : that.name != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return name != null ? name.hashCode() : 0;
    }
}
