import java.time.LocalDate;

public class Employees {
    private int EmpID;
    private int ProjectID;
    private LocalDate DateFrom;
    private LocalDate DateTo;

    public Employees(int empID, int projectID, LocalDate dateFrom, LocalDate dateTo) {
        EmpID = empID;
        ProjectID = projectID;
        DateFrom = dateFrom;
        DateTo = dateTo;
    }

    public int getEmpID() {
        return EmpID;
    }

    public void setEmpID(int empID) {
        EmpID = empID;
    }

    public int getProjectID() {
        return ProjectID;
    }

    public void setProjectID(int projectID) {
        ProjectID = projectID;
    }

    public LocalDate getDateFrom() {
        return DateFrom;
    }

    public void setDateFrom(LocalDate dateFrom) {
        DateFrom = dateFrom;
    }

    public LocalDate getDateTo() {
        return DateTo;
    }

    public void setDateTo(LocalDate dateTo) {
        DateTo = dateTo;
    }
}
