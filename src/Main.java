import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Scanner;

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
                case 1:
                    System.out.println("Zadejte rok:");
                    int year = sc.nextInt();
                    boolean leapYear = isLeapYear(year);
                    System.out.println("Rok " + year + " je " + (leapYear ? "přestupný." : "není přestupný."));
                    break;
                case 2:
                    int currentYear = Calendar.getInstance().get(Calendar.YEAR);
                    boolean currentYearLeap = isLeapYear(currentYear);
                    System.out.println("Aktuální rok " + currentYear + " je " + (currentYearLeap ? "přestupný." : "není přestupný."));
                    if (!currentYearLeap) {
                        int nextLeapYear = findNextLeapYear(currentYear);
                        System.out.println("Další přestupný rok bude " + nextLeapYear + ".");
                    }
                    break;
                case 3:
                    long currentTimeMillis = System.currentTimeMillis();
                    SimpleDateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy HH:mm:ss");
                    String formattedTime = dateFormat.format(currentTimeMillis);
                    System.out.println("Aktuální formát datumu a času: " + formattedTime);
                    break;
                case 4:
                    System.out.println("Zadejte den, měsíc a rok:");
                    int day = sc.nextInt();
                    int month = sc.nextInt();
                    int targetYear = sc.nextInt();
                    calculateTimeUntilDate(day, month, targetYear);
                    break;
                case 5:
                    running = false;
                    break;
                default:
                    System.out.println("Neplatná volba, zkuste to znovu.");
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
        Calendar now = Calendar.getInstance();
        Calendar target = Calendar.getInstance();
        target.set(targetYear, month - 1, day);

        long timeDiffMillis = target.getTimeInMillis() - now.getTimeInMillis();
        long seconds = timeDiffMillis / 1000;
        long minutes = seconds / 60;
        long hours = minutes / 60;
        long days = hours / 24;
        long months = days / 30;
        long years = months / 12;

        System.out.println("Do zadaného data zbývá:");
        System.out.println(years + " let");
        System.out.println(months + " měsíců");
        System.out.println(days + " dní");
        System.out.println(hours % 24 + " hodin");
        System.out.println(minutes % 60 + " minut");
        System.out.println(seconds % 60 + " sekund");
    }
}
