public class Main {
    public static void main(String[] args) {
        String filename ="src/employers.csv";
        CSV.loader(filename);
        CSV.getEmployees();
    }
}