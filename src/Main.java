import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Scanner;
import java.time.LocalDate;
import java.time.Month;
import java.time.Period;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        boolean running = true;

        while (running) {
            System.out.println("Vyberte operaci:");
            System.out.println("1. Zjištění přestupného roku podle zadaného roku");
            System.out.println("2. Zjištění přestupného roku podle aktuálního data");
            System.out.println("3. Vlastní formát data a času podle systémového času");
            System.out.println("4. Zbývající čas do určitého data");
            System.out.println("5. Konec programu");
            int choice = sc.nextInt();

            switch (choice) {
                case 1 -> {
                    System.out.println("Zadejte rok:");
                    int year = sc.nextInt();
                    boolean leapYear = isLeapYear(year);
                    System.out.println("Rok " + year + " je " + (leapYear ? "přestupný." : "není přestupný."));
                }
                case 2 -> {
                    int currentYear = Calendar.getInstance().get(Calendar.YEAR);
                    boolean currentYearLeap = isLeapYear(currentYear);
                    System.out.println("Aktuální rok " + currentYear + " je " + (currentYearLeap ? "přestupný." : "není přestupný."));
                    if (!currentYearLeap) {
                        int nextLeapYear = findNextLeapYear(currentYear);
                        System.out.println("Další přestupný rok bude " + nextLeapYear + ".");
                    }
                }
                case 3 -> {
                    long currentTimeMillis = System.currentTimeMillis();
                    SimpleDateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy HH:mm:ss");
                    String formattedTime = dateFormat.format(currentTimeMillis);
                    System.out.println("Aktuální formát datumu a času: " + formattedTime);
                }
                case 4 -> {
                    System.out.println("Zadejte den, měsíc a rok:");
                    int day = sc.nextInt();
                    int month = sc.nextInt();
                    int targetYear = sc.nextInt();
                    calculateTimeUntilDate(day, month, targetYear);
                }
                case 5 -> running = false;
                default -> System.out.println("Neplatná volba, zkuste to znovu.");
            }
        }
    }

    public static boolean isLeapYear(int year) {
        return (year % 4 == 0 && year % 100 != 0) || (year % 400 == 0);
    }

    public static int findNextLeapYear(int currentYear) {
        int year = currentYear + 1;
        while (!isLeapYear(year)) {
            year++;
        }
        return year;
    }

    public static void calculateTimeUntilDate(int day, int month, int targetYear) {
        LocalDate currentDate = LocalDate.now();
        LocalDate targetDate = LocalDate.of(targetYear, Month.of(month), day);

        Period period = Period.between(currentDate, targetDate);

        int years = period.getYears();
        int months = period.getMonths();
        int days = period.getDays();

        System.out.println("Do zadaného data zbývá:");
        System.out.println(years + " let");
        System.out.println(months + " měsíců");
        System.out.println(days + " dní");
    }
}
