import java.time.Instant;
import java.time.LocalDate;
import java.time.Period;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Date;

public class MyDate {

    Project project;
    LocalDate createOn;
    LocalDate ChangedOn;
    MyDate(Project project){
        this.project=project;
        this.createOn=convertToLocalDateViaMilisecond(project.getCreatedOn());
        this.ChangedOn=convertToLocalDateViaMilisecond(project.getChangedOn());
       
    }
    public LocalDate getCreatedOn(){
        return  createOn;
    }
    public LocalDate getChangedOn(){
        return  ChangedOn;
    }

    private LocalDate getFakeCreatedOn(){
        return  LocalDate.of(createOn.getYear(),createOn.getMonth(),1);
    }
    private LocalDate getFakeChangedOn(){
        return  LocalDate.of(ChangedOn.getYear(),ChangedOn.getMonth(),1);
    }


    public LocalDate convertToLocalDateViaMilisecond(Date dateToConvert) {
        return Instant.ofEpochMilli(dateToConvert.getTime())
                .atZone(ZoneId.systemDefault())
                .toLocalDate();
    }

    public int  findMonthsDifference()
    {

        // find the period between
        // the start and end date
        Period diff
                = Period
                .between(createOn,
                        ChangedOn);

        return  diff.getMonths()+2; //+2 is for and mothns to the line
    }

    public long  findStageDaysDifference()
    {
        LocalDate min=convertToLocalDateViaMilisecond(minDate(project.getProjectStages()));
        LocalDate max=convertToLocalDateViaMilisecond(maxDate(project.getProjectStages()));
        // find the period between
        // the start and end date
        ;

        return  ChronoUnit.DAYS.between(min, max);
    }

    public long  findAllDaysDruration(){

        return  findMonthsDifference()*30;
    }

    public String addMonthLabel(int i){
        return createOn.plusMonths(i).getYear()+" "+createOn.plusMonths(i).getMonth();
    }

    public Date minDate(ArrayList<ProjectStage> array){
        Date earliest=new Date(5000,11,11); // max date
        for(int i=0;i<array.size();i++){
            earliest= least(earliest, array.get(i).getDate()); 
        }
        return earliest;
    }

    public Date maxDate(ArrayList<ProjectStage> array){
        Date earliest=new Date(0,0,0); // min date
        for(int i=0;i<array.size();i++){
            earliest= max(earliest, array.get(i).getDate());
            
        }
        return earliest;
    }

    private  Date least(Date a, Date b) {
        return a == null ? b : (b == null ? a : (a.before(b) ? a : b));
    }

    private  Date max(Date a, Date b) {
        return a == null ? b : (b == null ? a : (a.after(b) ? a : b));
    }

    public long durationToMinDate(){
       return ChronoUnit.DAYS.between(getFakeCreatedOn(), convertToLocalDateViaMilisecond(minDate(project.getProjectStages())));
    }

    public long durationToMaxDate(){
        return ChronoUnit.DAYS.between(getFakeCreatedOn(), convertToLocalDateViaMilisecond(maxDate(project.getProjectStages())));
     }

     private long durationToAdate(Date date){
        return ChronoUnit.DAYS.between(getFakeCreatedOn(), convertToLocalDateViaMilisecond(date));
     }

     public ArrayList<Double> allStagePoint(){
        ArrayList<Double> list=new ArrayList<Double>();
        for(int i=0;i<project.getProjectStages().size();i++){
            list.add((double) (1200*durationToAdate(project.getProjectStages().get(i).getDate())/findAllDaysDruration()));
        }
        return  list;
     }

}
