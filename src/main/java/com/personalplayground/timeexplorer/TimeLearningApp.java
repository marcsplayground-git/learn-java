package com.personalplayground.timeexplorer;

import java.sql.Timestamp;
import java.time.*;
import java.time.temporal.ChronoField;
import java.time.temporal.ChronoUnit;
import java.time.temporal.TemporalAdjusters;

import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.time.format.DateTimeParseException;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class TimeLearningApp {

    // Small service that depends on Clock instead of calling now() directly
    static class SubscriptionService {
        private final Clock clock;

        SubscriptionService(Clock clock) {
            this.clock = clock;
        }

        /**
         * Returns the current date according to the injected clock.
         */
        LocalDate getToday() {
            return LocalDate.now(clock);
        }

        /**
         * Calculates the expiration date for a subscription that lasts a given number of months.
         * For example: start today, valid for 'months' months.
         */
        LocalDate calculateExpiryDate(int months) {
            LocalDate startDate = LocalDate.now(clock);
            return startDate.plusMonths(months);
        }
    }

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

        // NEW: Milestone 6 demo
        demoTemporalAdjusters();

        // NEW: Milestone 7 demo
        demoTimeZonesAndZonedDateTime();

        // NEW: Milestone 8 demo
        demoClockAndTestability();

        // NEW: Milestone 9 demo
        demoFormattingAndParsing();

        // NEW: Milestone 10 demo
        demoLegacyInteropAndTimeBetween();
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

    private static void demoTemporalAdjusters() {
        System.out.println();
        System.out.println("=== Milestone 6: TemporalAdjuster – smart date rules (strategy pattern) ===");
        System.out.println();

        LocalDate today = LocalDate.now();

        System.out.println("TODAY");
        System.out.println("-----");
        System.out.println("Today is                : " + today);
        System.out.println("Day of week             : " + today.getDayOfWeek());
        System.out.println();

        // 1) Built-in TemporalAdjusters
        LocalDate firstDayOfMonth = today.with(TemporalAdjusters.firstDayOfMonth());
        LocalDate lastDayOfMonth = today.with(TemporalAdjusters.lastDayOfMonth());
        LocalDate firstDayOfNextMonth = today.with(TemporalAdjusters.firstDayOfNextMonth());
        LocalDate nextMonday = today.with(TemporalAdjusters.next(DayOfWeek.MONDAY));
        LocalDate previousOrSameFriday = today.with(TemporalAdjusters.previousOrSame(DayOfWeek.FRIDAY));

        System.out.println("BUILT-IN TEMPORALADJUSTERS");
        System.out.println("---------------------------");
        System.out.println("First day of this month      : " + firstDayOfMonth);
        System.out.println("Last day of this month       : " + lastDayOfMonth);
        System.out.println("First day of next month      : " + firstDayOfNextMonth);
        System.out.println("Next Monday                  : " + nextMonday);
        System.out.println("Previous or same Friday      : " + previousOrSameFriday);
        System.out.println();
        System.out.println("Pattern: date.with(TemporalAdjusters.someRule(...))");
        System.out.println("This applies a 'strategy' to your date and returns a NEW date.");
        System.out.println();

        // 2) Example: recurring meeting rule – 'next or same Wednesday'
        LocalDate nextOrSameWednesday = today.with(TemporalAdjusters.nextOrSame(DayOfWeek.WEDNESDAY));

        System.out.println("RECURRING MEETING EXAMPLE: 'Next or same Wednesday'");
        System.out.println("----------------------------------------------------");
        System.out.println("Today                      : " + today + " (" + today.getDayOfWeek() + ")");
        System.out.println("Next or same Wednesday     : " + nextOrSameWednesday + " (" + nextOrSameWednesday.getDayOfWeek() + ")");
        System.out.println();
        System.out.println("You can imagine this for recurring weekly meetings, standups, etc.");
        System.out.println();
    }

    private static void demoTimeZonesAndZonedDateTime() {
        System.out.println();
        System.out.println("=== Milestone 7: Time zones & ZonedDateTime ===");
        System.out.println();

        // 1) Show some ZoneIds
        ZoneId systemZone = ZoneId.systemDefault();
        ZoneId manilaZone = ZoneId.of("Asia/Manila");
        ZoneId parisZone = ZoneId.of("Europe/Paris");
        ZoneId newYorkZone = ZoneId.of("America/New_York");

        System.out.println("BASIC ZONE IDS");
        System.out.println("--------------");
        System.out.println("System default zone      : " + systemZone);
        System.out.println("Manila zone              : " + manilaZone);
        System.out.println("Paris zone               : " + parisZone);
        System.out.println("New York zone            : " + newYorkZone);
        System.out.println();
        System.out.println("ZoneId uses region-based IDs (IANA), not raw offsets.");
        System.out.println("This captures full DST history and political changes over time.");
        System.out.println();

        // 2) LocalDateTime vs ZonedDateTime (same local time in different zones)
        LocalDateTime meetingLocal = LocalDateTime.of(2025, 3, 10, 9, 0); // 09:00 local wall-clock

        ZonedDateTime meetingInManila = meetingLocal.atZone(manilaZone);
        ZonedDateTime meetingInParis = meetingLocal.atZone(parisZone);
        ZonedDateTime meetingInNewYork = meetingLocal.atZone(newYorkZone);

        System.out.println("LocalDateTime vs ZonedDateTime");
        System.out.println("--------------------------------");
        System.out.println("Meeting LocalDateTime (no zone)  : " + meetingLocal);
        System.out.println("Meeting in Manila                 : " + meetingInManila);
        System.out.println("Meeting in Paris                  : " + meetingInParis);
        System.out.println("Meeting in New York               : " + meetingInNewYork);
        System.out.println();
        System.out.println("Same local time 09:00, but different actual instants on the");
        System.out.println("global timeline because of different offsets.");
        System.out.println();

        // 3) Instant <-> ZonedDateTime (store as Instant, view in zones)
        Instant nowInstant = Instant.now();

        ZonedDateTime nowInManila = nowInstant.atZone(manilaZone);
        ZonedDateTime nowInParis = nowInstant.atZone(parisZone);
        ZonedDateTime nowInNewYork = nowInstant.atZone(newYorkZone);

        System.out.println("CONVERTING Instant TO ZonedDateTime");
        System.out.println("-----------------------------------");
        System.out.println("Instant (UTC, machine time) : " + nowInstant);
        System.out.println("Same instant in Manila       : " + nowInManila);
        System.out.println("Same instant in Paris        : " + nowInParis);
        System.out.println("Same instant in New York     : " + nowInNewYork);
        System.out.println();
        System.out.println("Pattern:");
        System.out.println("  - Store and compute in Instant/UTC.");
        System.out.println("  - Convert to ZonedDateTime at the edges (input/output, UI).");
        System.out.println();

        // 4) Converting between zones (same instant, different local time)
        ZonedDateTime manilaView = ZonedDateTime.of(2025, 3, 10, 9, 0, 0, 0, manilaZone);
        ZonedDateTime parisViewSameInstant = manilaView.withZoneSameInstant(parisZone);
        ZonedDateTime newYorkViewSameInstant = manilaView.withZoneSameInstant(newYorkZone);

        System.out.println("CONVERTING BETWEEN ZONES (same instant)");
        System.out.println("---------------------------------------");
        System.out.println("Original (Manila)         : " + manilaView);
        System.out.println("Same instant in Paris     : " + parisViewSameInstant);
        System.out.println("Same instant in New York  : " + newYorkViewSameInstant);
        System.out.println();
        System.out.println("withZoneSameInstant(...) keeps the point on the timeline");
        System.out.println("the same, but changes the local date/time representation.");
        System.out.println();

        // 5) Date-based vs time-based math on ZonedDateTime near DST
        // Example around a DST transition in New York (month/day may vary by year,
        // but concept is what matters).
        ZonedDateTime nyBeforeDst = ZonedDateTime.of(2025, 3, 9, 1, 30, 0, 0, newYorkZone);
        ZonedDateTime nyPlusOneDay = nyBeforeDst.plusDays(1);
        ZonedDateTime nyPlus24Hours = nyBeforeDst.plusHours(24);

        System.out.println("DATE-BASED vs TIME-BASED MATH ON ZonedDateTime (DST example)");
        System.out.println("-------------------------------------------------------------");
        System.out.println("New York before DST change   : " + nyBeforeDst);
        System.out.println("Plus 1 calendar day          : " + nyPlusOneDay);
        System.out.println("Plus 24 hours (exact timeline): " + nyPlus24Hours);
        System.out.println();
        System.out.println("Important idea:");
        System.out.println("  - plusDays(1) = 'next calendar day' in that zone.");
        System.out.println("  - plusHours(24) = 'exact 24 hours on the UTC timeline'.");
        System.out.println("Near DST changes, these may land on different local times.");
        System.out.println();

        System.out.println("Key reminders about time zones & DST:");
        System.out.println("  - Time zones change over time (political decisions).");
        System.out.println("  - DST creates gaps (non-existent local times) and overlaps");
        System.out.println("    (same local time appears twice).");
        System.out.println("  - For cross-zone logic, prefer Instant or ZonedDateTime.");
        System.out.println("  - Avoid using plain LocalDateTime for cross-zone scheduling.");
        System.out.println();
    }

    private static void demoClockAndTestability() {
        System.out.println();
        System.out.println("=== Milestone 8: Clock & testability (never hide 'now') ===");
        System.out.println();

        // 1) Different clock implementations
        Clock systemUtcClock = Clock.systemUTC();
        Clock manilaClock = Clock.system(ZoneId.of("Asia/Manila"));

        System.out.println("DIFFERENT CLOCK IMPLEMENTATIONS");
        System.out.println("--------------------------------");
        System.out.println("System UTC clock           : " + systemUtcClock);
        System.out.println("System Manila clock        : " + manilaClock);
        System.out.println();
        System.out.println("Clock encapsulates:");
        System.out.println("  - 'Current instant' source");
        System.out.println("  - Time zone (for now(clock) on LocalDate/LocalDateTime/ZonedDateTime)");
        System.out.println();

        Instant nowFromUtcClock = Instant.now(systemUtcClock);
        Instant nowFromManilaClock = Instant.now(manilaClock);

        System.out.println("Instant from UTC clock     : " + nowFromUtcClock);
        System.out.println("Instant from Manila clock  : " + nowFromManilaClock);
        System.out.println();

        // 2) Using Clock with LocalDate.now(clock)
        LocalDate todayUtc = LocalDate.now(systemUtcClock);
        LocalDate todayManila = LocalDate.now(manilaClock);

        System.out.println("LocalDate.now(clock) examples");
        System.out.println("------------------------------");
        System.out.println("Today according to UTC clock      : " + todayUtc);
        System.out.println("Today according to Manila clock   : " + todayManila);
        System.out.println();
        System.out.println("This is how you make your 'today' depend on an explicit clock.");
        System.out.println();

        // 3) Fixed clock for deterministic behavior (great for tests)
        Instant fixedInstant = Instant.parse("2025-01-01T00:00:00Z");
        Clock fixedUtcClock = Clock.fixed(fixedInstant, ZoneId.of("UTC"));

        Instant instant1 = Instant.now(fixedUtcClock);
        Instant instant2 = Instant.now(fixedUtcClock);

        LocalDate fixedDateUtc = LocalDate.now(fixedUtcClock);

        System.out.println("FIXED CLOCK (deterministic, great for tests)");
        System.out.println("--------------------------------------------");
        System.out.println("Fixed instant              : " + fixedInstant);
        System.out.println("Instant 1 from fixed clock : " + instant1);
        System.out.println("Instant 2 from fixed clock : " + instant2);
        System.out.println("LocalDate from fixed clock : " + fixedDateUtc);
        System.out.println();
        System.out.println("Notice:");
        System.out.println("  - Every call to Instant.now(fixedClock) returns the SAME value.");
        System.out.println("  - Tests using a fixed clock are stable and reproducible.");
        System.out.println();

        // 4) Using SubscriptionService with different clocks
        SubscriptionService realService = new SubscriptionService(manilaClock);
        SubscriptionService testService = new SubscriptionService(fixedUtcClock);

        LocalDate realToday = realService.getToday();
        LocalDate realExpiry = realService.calculateExpiryDate(3); // 3 months from 'now' (real)

        LocalDate testToday = testService.getToday();
        LocalDate testExpiry = testService.calculateExpiryDate(3); // 3 months from fixed instant

        System.out.println("USING CLOCK-BASED SERVICE (SubscriptionService)");
        System.out.println("-----------------------------------------------");
        System.out.println("Real clock (Manila)");
        System.out.println("  Today          : " + realToday);
        System.out.println("  3-month expiry : " + realExpiry);
        System.out.println();
        System.out.println("Test clock (fixed UTC at 2025-01-01)");
        System.out.println("  Today          : " + testToday);
        System.out.println("  3-month expiry : " + testExpiry);
        System.out.println();
        System.out.println("In tests, you would assert that:");
        System.out.println("  - testToday == 2025-01-01");
        System.out.println("  - testExpiry == 2025-04-01");
        System.out.println("And these values will NEVER change as long as you use the same fixed clock.");
        System.out.println();

        // 5) Summary of the rule
        System.out.println("SUMMARY: NEVER HIDE 'NOW' DEEP IN YOUR LOGIC");
        System.out.println("--------------------------------------------");
        System.out.println("Rules of thumb:");
        System.out.println("  1) Do NOT sprinkle LocalDate.now() / Instant.now() everywhere.");
        System.out.println("  2) Instead, inject a Clock into your services.");
        System.out.println("  3) Use system clocks in production:");
        System.out.println("       - Clock.systemUTC()");
        System.out.println("       - Clock.system(ZoneId.of(\"Asia/Manila\"))");
        System.out.println("  4) Use Clock.fixed(...) or Clock.offset(...) in tests.");
        System.out.println("  5) This makes time and time zone explicit and testable.");
        System.out.println();
    }

    private static void demoFormattingAndParsing() {
        System.out.println();
        System.out.println("=== Milestone 9: Formatting & parsing with DateTimeFormatter ===");
        System.out.println();

        LocalDate today = LocalDate.now();
        LocalDateTime nowDateTime = LocalDateTime.now();
        ZonedDateTime nowZoned = ZonedDateTime.now(ZoneId.of("Asia/Manila"));
        Instant nowInstant = Instant.now();

        // 1) ISO formatters (good defaults, no pattern needed)
        System.out.println("1) ISO FORMATTERS");
        System.out.println("-----------------");
        System.out.println("LocalDate.toString() (ISO_LOCAL_DATE)         : " + today);
        System.out.println("LocalDateTime.toString() (ISO_LOCAL_DATE_TIME): " + nowDateTime);
        System.out.println("ZonedDateTime.toString() (ISO_ZONED_DATE_TIME): " + nowZoned);
        System.out.println("Instant.toString() (ISO_INSTANT)              : " + nowInstant);
        System.out.println();
        System.out.println("These are already ISO-8601 compliant.");
        System.out.println("DateTimeFormatter.ISO_LOCAL_DATE etc. match these defaults.");
        System.out.println();

        DateTimeFormatter isoLocalDate = DateTimeFormatter.ISO_LOCAL_DATE;
        String formattedDate = today.format(isoLocalDate);
        LocalDate parsedBack = LocalDate.parse(formattedDate, isoLocalDate);

        System.out.println("Formatting LocalDate using ISO_LOCAL_DATE      : " + formattedDate);
        System.out.println("Parsing back into LocalDate                    : " + parsedBack);
        System.out.println();

        // 2) Localized styles (SHORT, MEDIUM, LONG, FULL) with Locale
        System.out.println("2) LOCALIZED FORMATTING");
        System.out.println("------------------------");

        DateTimeFormatter shortDateTimePh = DateTimeFormatter
                .ofLocalizedDateTime(FormatStyle.SHORT)
                .withLocale(new Locale("en", "PH"));

        DateTimeFormatter mediumDateTimeUs = DateTimeFormatter
                .ofLocalizedDateTime(FormatStyle.MEDIUM)
                .withLocale(Locale.US);

        String phShort = nowZoned.format(shortDateTimePh);
        String usMedium = nowZoned.format(mediumDateTimeUs);

        System.out.println("Asia/Manila SHORT date-time (en-PH) : " + phShort);
        System.out.println("Asia/Manila MEDIUM date-time (en-US): " + usMedium);
        System.out.println();
        System.out.println("Localized formatters depend on:");
        System.out.println("  - FormatStyle (SHORT/MEDIUM/LONG/FULL)");
        System.out.println("  - Locale (language + country)");
        System.out.println();

        // 3) Custom pattern-based formatting
        System.out.println("3) CUSTOM PATTERN-BASED FORMATTING");
        System.out.println("-----------------------------------");

        DateTimeFormatter customPattern = DateTimeFormatter.ofPattern("uuuu-MM-dd HH:mm:ss");
        String customFormatted = nowZoned.format(customPattern);

        System.out.println("Pattern: 'uuuu-MM-dd HH:mm:ss'");
        System.out.println("Formatted ZonedDateTime       : " + customFormatted);
        System.out.println();
        System.out.println("Common pattern symbols:");
        System.out.println("  - uuuu = year");
        System.out.println("  - MM   = month (01-12)");
        System.out.println("  - dd   = day of month");
        System.out.println("  - HH   = hour (00-23)");
        System.out.println("  - mm   = minute");
        System.out.println("  - ss   = second");
        System.out.println("For full list, see Java docs for DateTimeFormatter patterns.");
        System.out.println();

        // 4) Parsing strings into temporal types
        System.out.println("4) PARSING STRINGS INTO TEMPORALS");
        System.out.println("----------------------------------");

        String dateStr = "2025-12-31";
        String dateTimeStr = "2025-12-31 23:45:10";
        String instantStr = "2025-12-31T15:45:10Z";

        LocalDate parsedDate = LocalDate.parse(dateStr, DateTimeFormatter.ISO_LOCAL_DATE);
        LocalDateTime parsedDateTime = LocalDateTime.parse(dateTimeStr, customPattern);
        Instant parsedInstant = Instant.parse(instantStr); // uses ISO_INSTANT by default

        System.out.println("Input date string       : " + dateStr);
        System.out.println("Parsed as LocalDate     : " + parsedDate);
        System.out.println();
        System.out.println("Input date-time string  : " + dateTimeStr);
        System.out.println("Parsed as LocalDateTime : " + parsedDateTime);
        System.out.println();
        System.out.println("Input instant string    : " + instantStr);
        System.out.println("Parsed as Instant       : " + parsedInstant);
        System.out.println();

        // 5) Handling parse errors safely with try-catch
        System.out.println("5) HANDLING PARSE ERRORS");
        System.out.println("-------------------------");

        String badDate = "2025-13-40"; // invalid month/day

        try {
            LocalDate invalid = LocalDate.parse(badDate, DateTimeFormatter.ISO_LOCAL_DATE);
            System.out.println("This line should not print. Parsed: " + invalid);
        } catch (DateTimeParseException e) {
            System.out.println("Failed to parse '" + badDate + "': " + e.getMessage());
        }

        System.out.println();
        System.out.println("Always validate or handle parsing, especially for user input.");
        System.out.println("DateTimeParseException tells you what went wrong.");
        System.out.println();

        // 6) Summary of formatting/parsing best practices
        System.out.println("SUMMARY: FORMATTING & PARSING RULES OF THUMB");
        System.out.println("--------------------------------------------");
        System.out.println("  1) Prefer ISO formats for APIs and storage (machine-friendly).");
        System.out.println("  2) Use localized formatters only at the UI boundary.");
        System.out.println("  3) Use DateTimeFormatter.ofPattern(...) when you must match");
        System.out.println("     a specific string format (e.g., legacy systems).");
        System.out.println("  4) Be careful when parsing: always handle DateTimeParseException.");
        System.out.println("  5) Remember: toString() of java.time types is already ISO in most cases.");
        System.out.println();
    }

    private static void demoLegacyInteropAndTimeBetween() {
        System.out.println();
        System.out.println("=== Milestone 10: Legacy interop + time-between patterns + recap ===");
        System.out.println();

        // ------------------------------------------------------------
        // PART 1: java.util.Date <-> Instant / ZonedDateTime
        // ------------------------------------------------------------
        System.out.println("PART 1: java.util.Date <-> Instant / ZonedDateTime");
        System.out.println("---------------------------------------------------");

        Date legacyDate = new Date(); // current moment in legacy API
        Instant instantFromLegacy = legacyDate.toInstant();
        ZonedDateTime legacyAsZonedManila = instantFromLegacy.atZone(ZoneId.of("Asia/Manila"));

        System.out.println("java.util.Date (legacy)         : " + legacyDate);
        System.out.println("As Instant (machine time, UTC)  : " + instantFromLegacy);
        System.out.println("As ZonedDateTime in Asia/Manila : " + legacyAsZonedManila);
        System.out.println();

        Instant someInstant = Instant.parse("2025-12-31T15:00:00Z");
        Date backToLegacy = Date.from(someInstant);

        System.out.println("From Instant back to Date       : " + backToLegacy);
        System.out.println();
        System.out.println("Remember:");
        System.out.println("  - java.util.Date really represents an Instant (a point on the timeline).");
        System.out.println("  - Its toString() uses the default time zone, which can be confusing.");
        System.out.println("  - Prefer Instant/ZonedDateTime in new code, only use Date at boundaries (old APIs).");
        System.out.println();

        // ------------------------------------------------------------
        // PART 2: java.util.Calendar <-> ZonedDateTime
        // ------------------------------------------------------------
        System.out.println("PART 2: java.util.Calendar <-> ZonedDateTime");
        System.out.println("------------------------------------------------");

        Calendar calendar = Calendar.getInstance(); // default time zone
        Instant calendarInstant = calendar.toInstant();
        ZoneId calendarZone = calendar.getTimeZone().toZoneId();
        ZonedDateTime calendarAsZoned = calendarInstant.atZone(calendarZone);

        System.out.println("Calendar time zone             : " + calendar.getTimeZone());
        System.out.println("Calendar as Instant            : " + calendarInstant);
        System.out.println("Calendar as ZonedDateTime      : " + calendarAsZoned);
        System.out.println();
        System.out.println("Again, prefer ZonedDateTime/Instant for new logic.");
        System.out.println("Calendar is mutable and more error-prone; use only when required.");
        System.out.println();

        // ------------------------------------------------------------
        // PART 3: java.sql.Date / java.sql.Timestamp interop
        // ------------------------------------------------------------
        System.out.println("PART 3: java.sql.Date / java.sql.Timestamp interop");
        System.out.println("---------------------------------------------------");

        // sql.Date <-> LocalDate
        java.sql.Date sqlDate = java.sql.Date.valueOf("2025-12-31");
        LocalDate localFromSqlDate = sqlDate.toLocalDate();
        java.sql.Date sqlFromLocalDate = java.sql.Date.valueOf(localFromSqlDate);

        System.out.println("java.sql.Date                  : " + sqlDate);
        System.out.println("As LocalDate                   : " + localFromSqlDate);
        System.out.println("Back to java.sql.Date          : " + sqlFromLocalDate);
        System.out.println();
        System.out.println("sql.Date is meant for a DATE column (no time-of-day).");
        System.out.println("Use LocalDate in your domain model, convert at the JDBC boundary.");
        System.out.println();

        // sql.Timestamp <-> Instant / LocalDateTime
        Timestamp sqlTimestamp = Timestamp.from(Instant.parse("2025-12-31T15:30:00Z"));
        Instant instantFromTs = sqlTimestamp.toInstant();
        LocalDateTime ldtFromTs = sqlTimestamp.toLocalDateTime();
        Timestamp tsFromLdt = Timestamp.valueOf(ldtFromTs);

        System.out.println("java.sql.Timestamp             : " + sqlTimestamp);
        System.out.println("As Instant                     : " + instantFromTs);
        System.out.println("As LocalDateTime               : " + ldtFromTs);
        System.out.println("Back to Timestamp              : " + tsFromLdt);
        System.out.println();
        System.out.println("sql.Timestamp usually maps to TIMESTAMP/TIMESTAMP WITH TIME ZONE-like columns.");
        System.out.println("In domain code:");
        System.out.println("  - Prefer Instant for pure machine/business logic.");
        System.out.println("  - Or ZonedDateTime if you care about a specific time zone.");
        System.out.println();

        // ------------------------------------------------------------
        // PART 4: Time between two temporals – Duration, Period, ChronoUnit
        // ------------------------------------------------------------
        System.out.println("PART 4: Time between two temporals");
        System.out.println("-----------------------------------");

        // 4a) LocalDate: Period.between and ChronoUnit.DAYS.between
        LocalDate date1 = LocalDate.of(2025, 1, 1);
        LocalDate date2 = LocalDate.of(2025, 3, 15);

        Period periodBetween = Period.between(date1, date2);
        long daysBetween = ChronoUnit.DAYS.between(date1, date2);
        long monthsBetweenDates = ChronoUnit.MONTHS.between(date1, date2);

        System.out.println("LocalDate 1                   : " + date1);
        System.out.println("LocalDate 2                   : " + date2);
        System.out.println("Period.between(d1, d2)        : " + periodBetween);
        System.out.println("  -> Years                    : " + periodBetween.getYears());
        System.out.println("  -> Months                   : " + periodBetween.getMonths());
        System.out.println("  -> Days                     : " + periodBetween.getDays());
        System.out.println("ChronoUnit.DAYS.between       : " + daysBetween);
        System.out.println("ChronoUnit.MONTHS.between     : " + monthsBetweenDates);
        System.out.println();
        System.out.println("Remember:");
        System.out.println("  - Period.between gives a Y/M/D triple (human calendar view).");
        System.out.println("  - ChronoUnit.DAYS.between gives a single number of days.");
        System.out.println("  - between(start, end) is [start, end) (start inclusive, end exclusive).");
        System.out.println();

        // 4b) ZonedDateTime / Instant: Duration.between and ChronoUnit
        ZonedDateTime z1 = ZonedDateTime.of(2025, 3, 9, 1, 0, 0, 0, ZoneId.of("America/New_York"));
        ZonedDateTime z2 = z1.plusDays(1); // same local wall-clock time, different offset around DST

        Duration durationBetween = Duration.between(z1.toInstant(), z2.toInstant());
        long hoursBetween = ChronoUnit.HOURS.between(z1, z2);

        System.out.println("ZonedDateTime 1 (NY)          : " + z1);
        System.out.println("ZonedDateTime 2 (NY, +1 day)  : " + z2);
        System.out.println("Duration between instants     : " + durationBetween);
        System.out.println("ChronoUnit.HOURS.between      : " + hoursBetween);
        System.out.println();
        System.out.println("Near DST changes, '1 calendar day' is not always 24 hours.");
        System.out.println("Duration/ChronoUnit work on the exact timeline; Period/plusDays work on the calendar.");
        System.out.println();

        // ------------------------------------------------------------
        // PART 5: Final mental model recap
        // ------------------------------------------------------------
        System.out.println("PART 5: Mental model recap for java.time");
        System.out.println("----------------------------------------");
        System.out.println("1) Machine vs human time");
        System.out.println("   - Machine: Instant + Duration (exact seconds/nanos, UTC-based).");
        System.out.println("   - Human: LocalDate/LocalTime/LocalDateTime + Period (calendar rules).");
        System.out.println();
        System.out.println("2) Zones & offsets");
        System.out.println("   - ZoneId (Asia/Manila, Europe/Paris, etc.) has full DST/history rules.");
        System.out.println("   - ZonedDateTime = LocalDateTime + ZoneId + resolved ZoneOffset.");
        System.out.println("   - For cross-zone logic, prefer Instant/ZonedDateTime.");
        System.out.println();
        System.out.println("3) Generic math & strategies");
        System.out.println("   - ChronoUnit / ChronoField for generic plus/between/get operations.");
        System.out.println("   - TemporalAdjuster for reusable date rules (e.g., next working day).");
        System.out.println();
        System.out.println("4) Clock & testability");
        System.out.println("   - Inject Clock into services instead of hardcoding now().");
        System.out.println("   - Use system clocks in prod, fixed clocks in tests.");
        System.out.println();
        System.out.println("5) Formatting & parsing");
        System.out.println("   - Use ISO for APIs/storage (DateTimeFormatter.ISO_*).");
        System.out.println("   - Use localized formats at UI boundaries.");
        System.out.println("   - Use patterns only when you must match specific formats.");
        System.out.println();
        System.out.println("6) Legacy interop");
        System.out.println("   - Convert Date/Calendar/Timestamp to Instant/ZonedDateTime/LocalDateTime at the edges.");
        System.out.println("   - Write new business logic using java.time types only.");
        System.out.println();
        System.out.println("If you follow these ideas, you avoid most date/time bugs:");
        System.out.println("   - Wrong offsets");
        System.out.println("   - DST surprises");
        System.out.println("   - Confusing legacy types");
        System.out.println("And your code becomes easier to read, test, and reason about.");
        System.out.println();
    }

}
