package com.personalplayground.timeexplorer;

import java.time.*;
import java.time.temporal.ChronoField;
import java.time.temporal.ChronoUnit;

public class TimeLearningApp {

    public static void main(String[] args) {
        System.out.println("=== Java Time Learning App ===");
        System.out.println();
        System.out.println("Milestone 1: Machine time vs Human time (basic overview)");
        System.out.println("-------------------------------------------------------");
        System.out.println();

        // 1) Explain the idea in plain text
        System.out.println("HUMAN TIME vs MACHINE TIME");
        System.out.println("--------------------------");
        System.out.println("Human time: what people care about:");
        System.out.println("  - Calendar dates (years, months, days)");
        System.out.println("  - Local clock times (hours, minutes, seconds)");
        System.out.println("  - Time zones (Asia/Manila, Europe/Paris, etc.)");
        System.out.println();
        System.out.println("Machine time: what computers care about:");
        System.out.println("  - A continuous timeline");
        System.out.println("  - Usually stored as 'seconds since 1970-01-01T00:00Z' (the epoch)");
        System.out.println("  - Time zone independent (UTC-based)");
        System.out.println();

        // 2) Show basic machine-time and human-time types
        Instant nowInstant = Instant.now();                     // machine time (UTC)
        LocalDate today = LocalDate.now();                      // human date, no time, no zone
        LocalDateTime localNow = LocalDateTime.now();           // human date+time, no zone
        ZonedDateTime manilaNow = ZonedDateTime.now(ZoneId.of("Asia/Manila")); // human+zone

        System.out.println("SOME BASIC java.time TYPES");
        System.out.println("--------------------------");
        System.out.println("Instant (machine time, UTC)     : " + nowInstant);
        System.out.println("LocalDate (human date only)     : " + today);
        System.out.println("LocalDateTime (date + time)     : " + localNow);
        System.out.println("ZonedDateTime (date+time+zone)  : " + manilaNow);
        System.out.println();

        System.out.println("Notice:");
        System.out.println("  - Instant is global, time-zone neutral (always UTC).");
        System.out.println("  - LocalDate/LocalDateTime do NOT store time zone.");
        System.out.println("  - ZonedDateTime combines date, time, and a ZoneId.");
        System.out.println();
        System.out.println("In later milestones, we will use these types to explore");
        System.out.println("durations, periods, time zones, DST, formatting, and more.");

        // NEW: Milestone 2 demo
        demoMachineTimeWithInstantAndDuration();

        // NEW: Milestone 3 demo
        demoHumanTimeWithPeriod();

        // NEW: Milestone 4 demo
        demoLocalDateTimeCreationAndAdjustment();

        // NEW: Milestone 5 demo
        demoTemporalAndChronoUnits();
    }

    private static void demoMachineTimeWithInstantAndDuration() {
        System.out.println();
        System.out.println("=== Milestone 2: Instant & Duration (Machine time intervals) ===");
        System.out.println();

        // 1) Show Instants on the machine timeline (UTC)
        Instant now = Instant.now();
        Instant tenSecondsLater = now.plusSeconds(10);
        Instant oneHourEarlier = now.minusSeconds(3600);

        System.out.println("MACHINE TIMELINE (Instant)");
        System.out.println("---------------------------");
        System.out.println("Current Instant (now)          : " + now);
        System.out.println("10 seconds later               : " + tenSecondsLater);
        System.out.println("1 hour earlier                 : " + oneHourEarlier);
        System.out.println();
        System.out.println("Notice: all Instants are in UTC, independent of time zone.");
        System.out.println();

        // 2) Create Durations using factory methods
        Duration fiveSeconds = Duration.ofSeconds(5);
        Duration twoMinutes = Duration.ofMinutes(2);
        Duration threeHours = Duration.ofHours(3);

        System.out.println("DURATION OBJECTS (exact time amounts)");
        System.out.println("--------------------------------------");
        System.out.println("5 seconds duration   : " + fiveSeconds);
        System.out.println("2 minutes duration   : " + twoMinutes);
        System.out.println("3 hours duration     : " + threeHours);
        System.out.println();
        System.out.println("Duration is a machine-time amount: seconds + nanoseconds.");
        System.out.println("It does NOT know about months or years.");
        System.out.println();

        // 3) Duration.between(start, end) – time between two Instants
        Instant start = Instant.now();
        Instant simulatedEnd = start.plusMillis(1234); // pretend 1.234 seconds later
        Duration elapsed = Duration.between(start, simulatedEnd);

        System.out.println("ELAPSED TIME BETWEEN TWO INSTANTS");
        System.out.println("---------------------------------");
        System.out.println("Start Instant        : " + start);
        System.out.println("Simulated end Instant: " + simulatedEnd);
        System.out.println("Elapsed Duration     : " + elapsed);
        System.out.println("Elapsed millis       : " + elapsed.toMillis());
        System.out.println("Elapsed seconds      : " + elapsed.getSeconds());
        System.out.println();

        // 4) Using Duration to move on the timeline
        Duration thirtyMinutes = Duration.ofMinutes(30);
        Instant meetingInstant = Instant.now().plus(thirtyMinutes);

        System.out.println("USING DURATION TO MOVE ON THE TIMELINE");
        System.out.println("--------------------------------------");
        System.out.println("Now (Instant)            : " + Instant.now());
        System.out.println("Meeting in 30 minutes    : " + meetingInstant);
        System.out.println();
        System.out.println("This is common for timeouts, schedules, and expirations.");
        System.out.println();

        // 5) Convert Duration into different units
        Duration example = Duration.ofHours(2).plusMinutes(15); // 2h 15m
        long seconds = example.getSeconds();
        long millis = example.toMillis();
        long minutesTotal = example.toMinutes();

        System.out.println("CONVERTING A DURATION");
        System.out.println("---------------------");
        System.out.println("Example duration (2h15m) : " + example);
        System.out.println("Total seconds            : " + seconds);
        System.out.println("Total millis             : " + millis);
        System.out.println("Total minutes (rounded)  : " + minutesTotal);
        System.out.println();
        System.out.println("Remember:");
        System.out.println("  - Duration = exact time on the machine timeline.");
        System.out.println("  - Good for timeouts, expirations, and precise intervals.");
        System.out.println("  - For '1 year and 1 day' style rules, we will use Period (human time).");
    }

    private static void demoHumanTimeWithPeriod() {
        System.out.println();
        System.out.println("=== Milestone 3: Period & LocalDate (Human time intervals) ===");
        System.out.println();

        // 1) Basic LocalDate examples (human dates)
        LocalDate today = LocalDate.now();
        LocalDate nextWeek = today.plusWeeks(1);
        LocalDate nextMonth = today.plusMonths(1);
        LocalDate nextYear = today.plusYears(1);

        System.out.println("HUMAN DATES WITH LocalDate");
        System.out.println("--------------------------");
        System.out.println("Today                 : " + today);
        System.out.println("One week from today   : " + nextWeek);
        System.out.println("One month from today  : " + nextMonth);
        System.out.println("One year from today   : " + nextYear);
        System.out.println();
        System.out.println("LocalDate works with calendar concepts: years, months, days.");
        System.out.println("It does NOT store time of day or time zone.");
        System.out.println();

        // 2) Creating Periods (human intervals)
        Period tenDays = Period.ofDays(10);
        Period twoMonths = Period.ofMonths(2);
        Period oneYearOneDay = Period.of(1, 0, 1); // 1 year, 0 months, 1 day

        System.out.println("PERIOD OBJECTS (human calendar amounts)");
        System.out.println("---------------------------------------");
        System.out.println("10 days period               : " + tenDays);
        System.out.println("2 months period              : " + twoMonths);
        System.out.println("1 year and 1 day period      : " + oneYearOneDay);
        System.out.println();
        System.out.println("Period = years/months/days. No fixed length in seconds.");
        System.out.println("Example: '1 month' can be 28, 29, 30, or 31 days depending on the month.");
        System.out.println();

        // 3) Using Period with LocalDate
        LocalDate contractStart = LocalDate.of(2025, 1, 15);
        Period contractLength = Period.ofYears(1).plusDays(10); // '1 year and 10 days'
        LocalDate contractEnd = contractStart.plus(contractLength);

        System.out.println("USING PERIOD WITH LocalDate (contracts, subscriptions)");
        System.out.println("------------------------------------------------------");
        System.out.println("Contract start date         : " + contractStart);
        System.out.println("Contract length             : " + contractLength);
        System.out.println("Contract end date           : " + contractEnd);
        System.out.println();
        System.out.println("This matches how humans talk: '1 year and 10 days from Jan 15, 2025'.");
        System.out.println();

        // 4) Period.between(start, end) – human difference between two dates
        LocalDate birthDate = LocalDate.of(2000, 5, 20);
        LocalDate nowDate = LocalDate.now();
        Period age = Period.between(birthDate, nowDate);

        System.out.println("PERIOD.BETWEEN(start, end) – HUMAN DIFFERENCES");
        System.out.println("----------------------------------------------");
        System.out.println("Birth date                : " + birthDate);
        System.out.println("Today                     : " + nowDate);
        System.out.println("Age                       : " + age);
        System.out.println("Age (Y/M/D)               : "
                + age.getYears() + " years, "
                + age.getMonths() + " months, "
                + age.getDays() + " days");
        System.out.println();

        // 5) Compare Duration vs Period conceptually
        System.out.println("DURATION vs PERIOD – SUMMARY");
        System.out.println("----------------------------");
        System.out.println("Duration:");
        System.out.println("  - Machine time.");
        System.out.println("  - Exact seconds + nanos.");
        System.out.println("  - Good for timeouts, 'wait 30 seconds', etc.");
        System.out.println();
        System.out.println("Period:");
        System.out.println("  - Human calendar time.");
        System.out.println("  - Years, months, days.");
        System.out.println("  - Good for contracts, subscriptions, '1 year and 1 day' rules.");
        System.out.println();
        System.out.println("Important:");
        System.out.println("  - Duration and Period are both TemporalAmount implementations,");
        System.out.println("    but they represent different ideas and are used in different contexts.");
    }

    private static void demoLocalDateTimeCreationAndAdjustment() {
        System.out.println();
        System.out.println("=== Milestone 4: LocalDate, LocalTime, LocalDateTime (creation & adjustment) ===");
        System.out.println();

        // 1) Creating LocalDate instances
        LocalDate today = LocalDate.now();
        LocalDate specificDate = LocalDate.of(2025, 12, 25); // year, month (int), day
        LocalDate specificDateWithEnum = LocalDate.of(2025, java.time.Month.DECEMBER, 25);

        System.out.println("CREATING LocalDate (date only)");
        System.out.println("--------------------------------");
        System.out.println("Today (from system clock)        : " + today);
        System.out.println("Specific date (2025-12-25)       : " + specificDate);
        System.out.println("Same date using Month enum       : " + specificDateWithEnum);
        System.out.println();
        System.out.println("LocalDate = calendar date only (year, month, day).");
        System.out.println("No time of day, no time zone.");
        System.out.println();

        // 2) Creating LocalTime instances
        LocalTime nowTime = LocalTime.now();
        LocalTime morningTime = LocalTime.of(9, 30);           // 09:30
        LocalTime withSeconds = LocalTime.of(9, 30, 15);       // 09:30:15
        LocalTime withNanos = LocalTime.of(9, 30, 15, 123000000); // 09:30:15.123

        System.out.println("CREATING LocalTime (time only)");
        System.out.println("--------------------------------");
        System.out.println("Current time (from system clock) : " + nowTime);
        System.out.println("Morning time (09:30)             : " + morningTime);
        System.out.println("With seconds (09:30:15)          : " + withSeconds);
        System.out.println("With nanos (09:30:15.123)        : " + withNanos);
        System.out.println();
        System.out.println("LocalTime = clock time only (hour, minute, second, nano).");
        System.out.println("No date, no time zone.");
        System.out.println();

        // 3) Creating LocalDateTime by combining date and time
        LocalDateTime nowDateTime = LocalDateTime.now();
        LocalDateTime meeting = LocalDateTime.of(2025, 3, 10, 14, 0); // 2025-03-10T14:00
        LocalDateTime fromDateAndTime = LocalDateTime.of(today, morningTime);

        System.out.println("CREATING LocalDateTime (date + time, no zone)");
        System.out.println("---------------------------------------------");
        System.out.println("Current LocalDateTime             : " + nowDateTime);
        System.out.println("Meeting (2025-03-10 14:00)        : " + meeting);
        System.out.println("Combining today + 09:30           : " + fromDateAndTime);
        System.out.println();
        System.out.println("LocalDateTime = LocalDate + LocalTime.");
        System.out.println("Still NO zone information here.");
        System.out.println();

        // 4) Accessors: reading parts of date/time
        System.out.println("ACCESSING FIELDS FROM LocalDateTime");
        System.out.println("------------------------------------");
        System.out.println("From meeting: " + meeting);
        System.out.println("  Year        : " + meeting.getYear());
        System.out.println("  Month       : " + meeting.getMonth());       // enum
        System.out.println("  Day of month: " + meeting.getDayOfMonth());
        System.out.println("  Day of week : " + meeting.getDayOfWeek());
        System.out.println("  Hour        : " + meeting.getHour());
        System.out.println("  Minute      : " + meeting.getMinute());
        System.out.println();
        System.out.println("Accessors let you pull out specific fields.");
        System.out.println();

        // 5) Adjustments: plus / minus / withXxx
        LocalDateTime original = LocalDateTime.of(2025, 1, 31, 10, 0);
        LocalDateTime plusFiveDays = original.plusDays(5);
        LocalDateTime minusTwoWeeks = original.minusWeeks(2);
        LocalDateTime withNewYear = original.withYear(2030);
        LocalDateTime withNewMonth = original.withMonth(2); // February

        System.out.println("ADJUSTING LocalDateTime (immutability demo)");
        System.out.println("-------------------------------------------");
        System.out.println("Original                  : " + original);
        System.out.println("Plus 5 days               : " + plusFiveDays);
        System.out.println("Minus 2 weeks             : " + minusTwoWeeks);
        System.out.println("With year changed to 2030 : " + withNewYear);
        System.out.println("With month changed to 2   : " + withNewMonth);
        System.out.println();
        System.out.println("Notice: LocalDateTime is IMMUTABLE.");
        System.out.println("Each operation returns a NEW instance; 'original' stays the same.");
        System.out.println();

        // 6) A realistic example: first day of next month at 09:30
        LocalDate firstDayOfNextMonth = today.plusMonths(1).withDayOfMonth(1);
        LocalTime officeStartTime = LocalTime.of(9, 30);
        LocalDateTime firstWorkDayNextMonth = LocalDateTime.of(firstDayOfNextMonth, officeStartTime);

        System.out.println("REALISTIC EXAMPLE: 'First day of next month at 09:30'");
        System.out.println("------------------------------------------------------");
        System.out.println("Today                        : " + today);
        System.out.println("First day of next month      : " + firstDayOfNextMonth);
        System.out.println("Office start time            : " + officeStartTime);
        System.out.println("Combined LocalDateTime       : " + firstWorkDayNextMonth);
        System.out.println();
        System.out.println("This is typical business logic: scheduling next-month events,");
        System.out.println("billing dates, reminders, etc., in human calendar terms.");
    }

    private static void demoTemporalAndChronoUnits() {
        System.out.println();
        System.out.println("=== Milestone 5: Temporal, ChronoUnit, ChronoField (generic date/time math) ===");
        System.out.println();

        // 1) Using plus(amount, unit) with ChronoUnit
        LocalDate invoiceDate = LocalDate.of(2025, 2, 20);
        LocalDate paymentDueDate = invoiceDate.plus(30, ChronoUnit.DAYS);
        LocalDate reminderDate = paymentDueDate.minus(3, ChronoUnit.DAYS);

        System.out.println("GENERIC plus(amount, unit) USING ChronoUnit");
        System.out.println("-------------------------------------------");
        System.out.println("Invoice date               : " + invoiceDate);
        System.out.println("Payment due (30 days later): " + paymentDueDate);
        System.out.println("Reminder (3 days before)   : " + reminderDate);
        System.out.println();
        System.out.println("Here we wrote: invoiceDate.plus(30, ChronoUnit.DAYS)");
        System.out.println("Same idea works for other units like MONTHS, YEARS, WEEKS, etc.");
        System.out.println();

        // 2) ChronoUnit.between(start, end) – differences in chosen units
        LocalDate startDate = LocalDate.of(2025, 1, 1);
        LocalDate endDate = LocalDate.of(2025, 3, 15);
        long daysBetween = ChronoUnit.DAYS.between(startDate, endDate);
        long weeksBetween = ChronoUnit.WEEKS.between(startDate, endDate);
        long monthsBetween = ChronoUnit.MONTHS.between(startDate, endDate);

        System.out.println("ChronoUnit.between(start, end)");
        System.out.println("--------------------------------");
        System.out.println("Start date         : " + startDate);
        System.out.println("End date           : " + endDate);
        System.out.println("Days between       : " + daysBetween);
        System.out.println("Weeks between      : " + weeksBetween);
        System.out.println("Months between     : " + monthsBetween);
        System.out.println();
        System.out.println("Note:");
        System.out.println("  - between(...) is [start, end) (start inclusive, end exclusive).");
        System.out.println("  - Months/weeks are based on calendar rules, not fixed seconds.");
        System.out.println();

        // 3) Reading fields using ChronoField
        LocalDate sampleDate = LocalDate.of(2025, 12, 25);
        int dayOfYear = sampleDate.get(ChronoField.DAY_OF_YEAR);
        int dayOfMonth = sampleDate.get(ChronoField.DAY_OF_MONTH);
        int dayOfWeekNumber = sampleDate.get(ChronoField.DAY_OF_WEEK); // 1 = Monday ... 7 = Sunday (ISO)

        System.out.println("Reading fields with ChronoField");
        System.out.println("--------------------------------");
        System.out.println("Sample date                  : " + sampleDate);
        System.out.println("Day of year                  : " + dayOfYear);
        System.out.println("Day of month                 : " + dayOfMonth);
        System.out.println("Day of week (number, ISO)    : " + dayOfWeekNumber);
        System.out.println();
        System.out.println("ChronoField is an enum of fields like YEAR, MONTH_OF_YEAR, DAY_OF_MONTH, etc.");
        System.out.println("You can use get(ChronoField.X) on many Temporal types like LocalDate, LocalDateTime, ZonedDateTime.");
        System.out.println();

        // 4) Duration & Period both implement TemporalAmount (conceptual demo)
        Duration shippingTime = Duration.ofHours(36);    // machine time amount
        Period gracePeriod = Period.ofDays(7);          // human calendar amount

        LocalDateTime orderDateTime = LocalDateTime.of(2025, 4, 10, 9, 0);
        LocalDateTime shippedBy = orderDateTime.plus(shippingTime); // add Duration
        LocalDateTime cancelUntil = orderDateTime.plus(gracePeriod); // add Period

        System.out.println("Duration & Period as TemporalAmount");
        System.out.println("------------------------------------");
        System.out.println("Order date/time            : " + orderDateTime);
        System.out.println("Shipping time (Duration)   : " + shippingTime);
        System.out.println("Shipped by                 : " + shippedBy);
        System.out.println("Grace period (Period)      : " + gracePeriod);
        System.out.println("Cancel allowed until       : " + cancelUntil);
        System.out.println();
        System.out.println("Conceptually:");
        System.out.println("  - Temporal          = something on the timeline (LocalDate, LocalDateTime, ZonedDateTime, etc.).");
        System.out.println("  - TemporalAmount    = an amount to add/subtract (Duration for machine time, Period for human time).");
        System.out.println("  - plus(amount, unit) and between(...) use ChronoUnit to specify units.");
        System.out.println();

        // 5) Important notes about unsupported units
        System.out.println("Important: not every Temporal supports every unit.");
        System.out.println("Example: Instant supports seconds, millis, etc., but not MONTHS/YEAR.");
        System.out.println("If you try unsupported combinations, you may get UnsupportedTemporalTypeException.");
        System.out.println("So always choose the right type + right units for your scenario.");
    }


}
