import java.util.Scanner;

public class SystemMonitorCli {

  public static void main(String[] args) {
    SystemMonitorCli app = new SystemMonitorCli();
    app.run();
  }

  private void run() {
    Scanner scanner = new Scanner(System.in);
    boolean running = true;

    while (running) {
      printMenu();
      System.out.print("Choose an option: ");
      String choice = scanner.nextLine().trim();

      switch (choice) {
        case "1" -> showCpuInfo();
        case "2" -> showMemoryInfo();
        case "3" -> showDiskInfo();
        case "4" -> runHealthCheck();
        case "5" -> {
          System.out.println("Exiting system monitor...");
          running = false;
        }
        default -> System.out.println("Invalid option. Please try again.");

      }

      System.out.println();
    }

    scanner.close();
  }

  private void printMenu() {
    System.out.println("=== System Monitor CLI ===");
    System.out.println("1. Show CPU info");
    System.out.println("2. Show memory info");
    System.out.println("3. Show disk usage");
    System.out.println("4. Run quick health check");
    System.out.println("5. Exit");
  }

  private void showCpuInfo() {
    System.out.println("[TODO] CPU info will be shown here.");
  }

  private void showMemoryInfo() {
    System.out.println("[TODO] Memory info will be shown here.");
  }

  private void showDiskInfo() {
    System.out.println("[TODO] Disk info will be shown here.");
  }

  private void runHealthCheck() {
    System.out.println("[TODO] Health check results will be shown here.");
  }
}
