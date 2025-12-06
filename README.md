# System Monitor CLI
A lightweight Java commmand-line tool that reports system metrics and performs a basic health check.

Build to demonstrate practical systems knowledge:
- CPU info (core count, 1-minute load, approximate CPU usage)
- JVM memory usage
- Disk usage per filesystem root
- Health check with threshold warnings
- Timestamped INFO/WARN log output

## Features
1. CPU Info
	•	Logical core count
	•	1-minute system load
	•	Estimated CPU usage percentage

2. Memory Info (JVM)
	•	Used, free, total, and max heap
	•	Shown in MB for readability

3. Disk Usage
	•	Per-root filesystem reporting
	•	Total, free, used, usable space (GB)

4. Quick Health Check
Applies simple thresholds:
	•	CPU > 75% → WARN
	•	Free JVM heap < 256MB → WARN
	•	Disk free < 15% → WARN

Prints OK/WARN per section + an Overall result.
Also appends the result to system-monitor.log.

## Logging
Every health check is written as one line:
```
YYYY-MM-DD HH:MM:SS [LEVEL] HealthCheck: CPU=X%(OK/WARN); MemoryRemaining=YMB(...); DiskMinFree=Z%(...); Overall=...
```

## How to Build & Run
**Compile**
From the project root:
```bash
javac -d out src/SystemMonitorCli.java
```
Run
```bash
java -cp out SystemMonitorCli
```

## Project Structure
```
SystemMonitorCli/
  src/
    SystemMonitorCli.java
  out/
    SystemMonitorCli.class
  system-monitor.log
  .gitignore
  README.md
```

## Possible Improvements
	•	Export metrics in Prometheus format
	•	Add OSHI for richer native metrics
	•	Add scheduling (--interval 5s)
	•	Add unit tests for health check thresholds
	•	Package as runnable JAR

