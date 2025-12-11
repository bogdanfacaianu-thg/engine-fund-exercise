# Engine Fund Projection – Coding Exercise (Java + Spring Boot)

## Context

This exercise models a simplified **engine fund** for an aircraft group.

- The engine fund is a pot of money that must reach a **target amount** by the time the engine is due for overhaul.
- Each time the aircraft flies, a fixed **engine-fund contribution per hour** is collected.
- Over a full engine cycle (e.g. 10 years or 2000 hours, whichever comes first), you expect to fly a certain number of hours (e.g. 2000 hours, 200 hours/year).
- In reality, the group may fly more or fewer hours per year than planned so the end period could be brought forward by the hours limit or the engine expiring.

You do **not** need any aviation knowledge – think of this as a savings pot that must reach a target by a deadline, funded mainly through a per-hour charge.

## Goal

Implement a REST endpoint that:

1. Computes a **summary of hours** flown so far vs the plan, including whether the hours plan still looks realistic.
2. Projects the **engine fund balance** from now until the end of the period, assuming the current per-hour contribution stays the same.
3. If the projection misses the target fund, suggests:
   - a **cash call now**, and
   - a **new engine-fund-per-hour** rate that would meet the target with no cash call.

The code already contains a nearly working but **messy** implementation in `EngineFundServiceImpl`. You can use the existing behaviour but we encourage you to improve the structure as much as possible if you have time.

## Input Model

Request body (JSON) – `ProjectionRequest`:

```json
{
  "currentFundBalance": 10000.00,
  "targetFund": 45000.00,
  "totalPeriodYears": 10.0,
  "yearsElapsed": 5.0,
  "hoursFlownSoFar": 700.0,
  "targetYearlyHours": 125.0,
  "engineFundPerHour": 30.00
}
```

Fields:

- `currentFundBalance` – money currently in the engine fund (includes all past contributions, cash calls, deductions).
- `targetFund` – desired fund amount at the end of the full period (e.g. 45000).
- `totalPeriodYears` – length of the full engine cycle in years (e.g. 10).
- `yearsElapsed` – how many years have passed since the start of the period (can be fractional, e.g. 5.4).
- `hoursFlownSoFar` – total hours flown in the period so far (already aggregated).
- `targetYearlyHours` – target average hours per year you expect to fly (e.g. 125).
- `engineFundPerHour` – current contribution to the engine fund per flight hour (e.g. 30).

## Derived Values and Formulas

Let:

- `totalPeriodYears` = total length of the engine cycle
- `yearsElapsed` = years passed so far
- `yearsRemaining` = `totalPeriodYears - yearsElapsed`
- `hoursFlownSoFar` = total hours flown so far
- `targetYearlyHours` = target average hours per year you can fly
- `engineFundPerHour` = current per-hour contribution rate
- `currentFundBalance` = current balance in the fund
- `targetFund` = target balance needed at end of period

### 1. Hours Summary

```text
expectedHoursSoFar              = targetYearlyHours * yearsElapsed
differenceHours                 = hoursFlownSoFar - expectedHoursSoFar
actualAverageYearlyHoursSoFar   = hoursFlownSoFar / yearsElapsed          (if yearsElapsed > 0, else 0)

totalTargetHours                = totalPeriodYears * targetYearlyHours
remainingHoursNeeded            = totalTargetHours - hoursFlownSoFar
requiredAverageYearlyHoursRemaining =
    remainingHoursNeeded / yearsRemaining      (if yearsRemaining > 0, else 0)

isHoursPlanRealistic = requiredAverageYearlyHoursRemaining <= targetYearlyHours
```

### 2. Fund Projection (from now to end of period)

For simplicity, we assume we continue to use the **target yearly hours** for the remaining period:

```text
remainingHours           = yearsRemaining * targetYearlyHours
finalProjectedBalance    = currentFundBalance + remainingHours * engineFundPerHour
differenceAtEnd          = finalProjectedBalance - targetFund
```

- If `differenceAtEnd >= 0` → projected to meet or exceed the target fund.
- If `differenceAtEnd < 0`  → projected to be short of the target.

### 3. Suggested Cash Call

If there is a shortfall at the end, the cash call needed **now** to fix it is:

```text
shortfall        = targetFund - finalProjectedBalance
suggestedCashCall = max(shortfall, 0)
```

(If the projected balance is above the target, `suggestedCashCall` is 0.)

Alternatively, you can spread the shortfall over the remaining years:

```text
suggestedYearlyCashCall = suggestedCashCall / yearsRemaining    (if yearsRemaining > 0, else 0)
```

**TODO:** Implement this calculation in the service.

### 4. Suggested New Engine-Fund Per Hour

If you do **not** want to make a cash call, you can instead increase the `engineFundPerHour` for the remaining hours:

```text
remainingHours = yearsRemaining * targetYearlyHours

suggestedEngineFundPerHour =
    (targetFund - currentFundBalance) / remainingHours       (if remainingHours > 0)
```

**TODO:** Implement this calculation in the service.

This is the new per-hour contribution that, combined with the existing fund balance, would reach the target by the end of the period.

## Output Model

Response body – `ProjectionResponse`:

```json
{
  "hoursSummary": {
    "totalPeriodYears": 10.0,
    "yearsElapsed": 5.0,
    "yearsRemaining": 5.0,
    "hoursFlownSoFar": 700.0,
    "expectedHoursSoFar": 625.0,
    "differenceHours": 75.0,
    "actualAverageYearlyHoursSoFar": 140.0,
    "requiredAverageYearlyHoursRemaining": 60.0,
    "targetYearlyHours": 125.0,
    "hoursPlanRealistic": true
  },
  "fundProjection": {
    "currentFundBalance": 10000.00,
    "targetFund": 45000.00,
    "yearsRemaining": 5.0,
    "remainingHours": 625.0,
    "engineFundPerHourCurrent": 30.00,
    "finalProjectedBalance": 28750.00,
    "differenceAtEnd": -16250.00,
    "suggestedCashCall": 16250.00,
    "suggestedYearlyCashCall": 3250.00,
    "suggestedEngineFundPerHour": 56.00
  }
}
```

The above example uses:

- `currentFundBalance` = 10000
- `targetFund` = 45000
- `totalPeriodYears` = 10
- `yearsElapsed` = 5
- `hoursFlownSoFar` = 700
- `targetYearlyHours` = 125
- `engineFundPerHour` = 30

Calculations (for reference):

- `expectedHoursSoFar` = 125 * 5 = 625
- `differenceHours` = 700 - 625 = 75
- `actualAverageYearlyHoursSoFar` = 700 / 5 = 140
- `totalTargetHours` = 10 * 125 = 1250
- `yearsRemaining` = 10 - 5 = 5
- `remainingHoursNeeded` = 1250 - 700 = 550
- `requiredAverageYearlyHoursRemaining` = 550 / 5 = 110 → ≤ 125, realistic
- `remainingHours` (for fund projection) = 5 * 125 = 625
- `finalProjectedBalance` = 10000 + 625 * 30 = 28750
- `differenceAtEnd` = 28750 - 45000 = -16250 (shortfall)
- `suggestedCashCall` = 16250
- `suggestedYearlyCashCall` = 16250 / 5 = 3250
- `suggestedEngineFundPerHour` = (45000 - 10000) / 625 = 56

## Your Tasks

1. **Review the existing implementation** in `EngineFundServiceImpl`.
   - The code is intentionally messy
   - There are bugs in the calculation logic that cause the test to fail.
   - There are TODO comments marking incomplete implementations.

2. **Fix the bugs and complete the TODOs** so that:
   - The test `EngineFundServiceImplTest.exampleScenarioProducesExpectedFinalBalanceAndSuggestions()` passes.
   - All calculations match the formulas documented above.
   - The two TODO items in the service are implemented:
     - `suggestedYearlyCashCall` calculation
     - `suggestedEngineFundPerHour` calculation
   - Monetary values are properly scaled to 2 decimal places using `HALF_UP`.

3. **Expose a Spring Boot REST endpoint**:
   - Method: `POST`
   - Path: `/engine-fund/projection`
   - Request body: `ProjectionRequest` JSON
   - Response body: `ProjectionResponse` JSON

4. **If you have time**, refactor `EngineFundServiceImpl`
