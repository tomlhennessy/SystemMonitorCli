import java.lang.management.ManagementFactory;
import java.lang.management.OperatingSystemMXBean;
import java.util.Scanner;

public class SystemMonitorCli {

  private static final long BYTES_PER_MB = 1024L * 1024L;
  private static final long BYTES_PER_GB = 1024L * 1024L * 1024L;

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
    System.out.println("--- CPU Info ---");

    int cores = Runtime.getRuntime().availableProcessors();
    System.out.println("Available processors (logical cores): " + cores);

    OperatingSystemMXBean osBean = ManagementFactory.getOperatingSystemMXBean();
    double systemLoad = osBean.getSystemLoadAverage();

    if (systemLoad < 0.0) {
      System.out.println("System load average (1 min): not available on this OS.");
    } else {
      System.out.println("System load average (1 min) " + formatDouble(systemLoad));

      double approxCpuUsage = (systemLoad / cores) * 100.0;
      if (approxCpuUsage < 0.0) {
        approxCpuUsage = 0.0;
      }
      System.out.println(
        "Approx CPU usage (1 min): " + formatDouble(approxCpuUsage) + "% (rough estimate)"
      );
    }

  }

  private void showMemoryInfo() {
    System.out.println("--- Memory Info (JVM heap) ---");

    Runtime runtime = Runtime.getRuntime();

    long totalMemory = runtime.totalMemory();
    long freeMemory = runtime.freeMemory();
    long usedMemory = totalMemory - freeMemory;
    long maxMemory = runtime.maxMemory();

    System.out.println("Used memory: " + bytesToMb(usedMemory) + " MB");
    System.out.println("Free memory: " + bytesToMb(freeMemory) + " MB");
    System.out.println("Total memory: " + bytesToMb(totalMemory) + " MB");
    System.out.println("Max memory: " + bytesToMb(maxMemory) + " MB");
  }

  private void showDiskInfo() {
    System.out.println("--- Disk Usage ---");

    java.io.File[] roots = java.io.File.listRoots();

    if (roots == null || roots.length == 0) {
      System.out.println("No filesystem roots found.");
      return;
    }

    for (java.io.File root : roots) {
      long totalSpace = root.getTotalSpace();
      long freeSpace = root.getFreeSpace();
      long usableSpace = root.getUsableSpace();
      long usedSpace = totalSpace - freeSpace;

      System.out.println("Path: " + root.getAbsolutePath());
      System.out.println("  Total space:  " + bytesToGb(totalSpace) + " GB");
      System.out.println("  Used space:   " + bytesToGb(usedSpace) + " GB");
      System.out.println("  Free space:   " + bytesToGb(freeSpace) + " GB");
      System.out.println("  Usable space: " + bytesToGb(usableSpace) + " GB");
      System.out.println();
    }
  }

  private void runHealthCheck() {
    System.out.println("[TODO] Health check results will be shown here.");
  }

  private String formatDouble(double value) {
    return String.format("%.2f", value);
  }

  private long bytesToMb(long bytes) {
    return bytes / BYTES_PER_MB;
  }

  private long bytesToGb(long bytes) {
    return bytes / BYTES_PER_GB;
  }
}
