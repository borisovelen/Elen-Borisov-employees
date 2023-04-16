import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;

public class CSV {
    public static ArrayList<Employees> empList = new ArrayList<>();
    public static void loader(String filename){
        try{
            FileReader fr = new FileReader(filename);
            BufferedReader br = new BufferedReader(fr);
            String line = "";
            String[] lineArr;
            Employees emp;
            while((line=br.readLine())!=null){
                lineArr=line.split(", ");
                if(lineArr[3].equals("NULL")) lineArr[3]=java.time.LocalDate.now().toString();
                if(LocalDate.parse(lineArr[2]).isAfter(LocalDate.parse(lineArr[3]))) throw new DateAfterException();
                emp = new Employees(Integer.parseInt(lineArr[0]),Integer.parseInt(lineArr[1]), LocalDate.parse(lineArr[2]), LocalDate.parse(lineArr[3]));
                empList.add(emp);
            }
            br.close();
            fr.close();
        }catch(DateTimeParseException e){
            System.out.println("Date format is not correct. You must use YYYY-MM-DD format or NULL!");
        }
        catch(DateAfterException e){
            e.getMessage();
        }catch(IOException e){
            e.printStackTrace();
        }
    }
    public static void getEmployees(){
        ArrayList<Pairs> pairsList = new ArrayList<>();
        LocalDate tempStartDate;
        LocalDate tempEndDate;
        long maxDays=0;
        Pairs maxPair=null;
        for(int i=0;i<empList.size()-1;i++){
            Employees one = empList.get(i);
            for(int j=i+1;j<empList.size();j++){
                Employees two = empList.get(j);
                if(one.getEmpID()==two.getEmpID()) continue;
                if(one.getProjectID()==two.getProjectID()){
                    if(one.getDateFrom().isBefore(two.getDateFrom())) tempStartDate=two.getDateFrom();
                    else tempStartDate=one.getDateFrom();
                    if(one.getDateTo().isAfter(two.getDateTo())) tempEndDate=two.getDateTo();
                    else tempEndDate=one.getDateTo();
                    long days = ChronoUnit.DAYS.between(tempStartDate, tempEndDate);
                    pairsList.add(new Pairs(one.getEmpID(), two.getEmpID(), days));
                }
            }
        }
        for(int i=0;i<pairsList.size()-1;i++){
            Pairs one = pairsList.get(i);
            for(int j=i+1;j<pairsList.size();j++) {
                Pairs two = pairsList.get(j);
                if((one.getEmp1()==two.getEmp1()||one.getEmp1()== two.getEmp2())&&(one.getEmp2()==two.getEmp2()||one.getEmp2()== two.getEmp1())){
                    one.setDays(one.getDays()+ two.getDays());
                    pairsList.remove(two);
                }
            }
        }
        for(Pairs p:pairsList){
            if(maxDays<p.getDays()){
                maxDays=p.getDays();
                maxPair=p;
            }
        }
        System.out.println("Most days worked together are employee("+maxPair.getEmp1()+") and employee("+maxPair.getEmp2()+") with "+maxPair.getDays()+" days!");
    }
}

