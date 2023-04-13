public class DateAfterException extends Exception{
    @Override
    public String getMessage(){
        return "DateFrom must be before DateTo";
    }
}
