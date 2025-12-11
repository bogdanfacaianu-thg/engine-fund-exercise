# Engine Fund Projection – Coding Exercise (Java + Spring Boot)

## Context

This exercise models a simplified **engine fund** for an aircraft group.

- The engine fund is a pot of money that must reach a **target amount** by the time the engine is due for overhaul.
- Each time the aircraft flies, a fixed **engine-fund contribution per hour** is collected.
- Over a full engine cycle (e.g. 10 years), you expect to fly a certain number of hours (e.g. 2000 hours, 200 hours/year).
- In reality, the group may fly more or fewer hours per year than planned.

You do **not** need any aviation knowledge – think of this as a savings pot that must reach a target by a deadline, funded mainly through a per-hour charge.

## Goal

Implement a REST endpoint that:

1. Computes a **summary of hours** flown so far vs the plan, including whether the hours plan still looks realistic.
2. Projects the **engine fund balance** from now until the end of the period, assuming the current per-hour contribution stays the same.
3. If the projection misses the target fund, suggests:
   - a **cash call now**, and
   - a **new engine-fund-per-hour** rate that would meet the target with no cash call.

The code already contains a nearly working but **messy** implementation in `EngineFundServiceImpl`. You can use the existing behaviour but we encourage you to improve the structure if you have time.

## Input Model

Request body (JSON) – `ProjectionRequest`:

```json
{
  "currentFundBalance": 10000.00,
  "targetFund": 45000.00,
  "totalPeriodYears": 10.0,
  "yearsElapsed": 5.0,
  "hoursFlownSoFar": 700.0,
  "plannedAverageYearlyHours": 200.0,
  "maxRealisticYearlyHours": 125.0,
  "engineFundPerHour": 30.00
}
```

Fields:

- `currentFundBalance` – money currently in the engine fund (includes all past contributions, cash calls, deductions).
- `targetFund` – desired fund amount at the end of the full period (e.g. 45000).
- `totalPeriodYears` – length of the full engine cycle in years (e.g. 10).
- `yearsElapsed` – how many years have passed since the start of the period (can be fractional, e.g. 5.4).
- `hoursFlownSoFar` – total hours flown in the period so far (already aggregated).
- `plannedAverageYearlyHours` – planned average hours per year (e.g. 200 → 2000 hours over 10 years).
- `maxRealisticYearlyHours` – an upper bound of what you realistically think you can fly per year (e.g. 125).
- `engineFundPerHour` – current contribution to the engine fund per flight hour (e.g. 30).

## Derived Values and Formulas

Let:

- `Y_total` = `totalPeriodYears`
- `Y_elapsed` = `yearsElapsed`
- `Y_remaining` = `Y_total - Y_elapsed`
- `H_plan` = `plannedAverageYearlyHours`
- `H_so_far` = `hoursFlownSoFar`
- `H_max` = `maxRealisticYearlyHours`
- `EFH` = `engineFundPerHour`
- `CF` = `currentFundBalance`
- `TF` = `targetFund`

### 1. Hours Summary

```text
expectedHoursSoFar              = H_plan * Y_elapsed
differenceHours                 = H_so_far - expectedHoursSoFar
actualAverageYearlyHoursSoFar   = H_so_far / Y_elapsed          (if Y_elapsed > 0, else 0)

totalPlannedHours               = Y_total * H_plan
yearsRemaining                  = Y_total - Y_elapsed
remainingHoursNeeded            = totalPlannedHours - H_so_far
requiredAverageYearlyHoursRemaining =
    remainingHoursNeeded / yearsRemaining      (if yearsRemaining > 0, else 0)

isHoursPlanRealistic = requiredAverageYearlyHoursRemaining <= H_max
```

### 2. Fund Projection (from now to end of period)

For simplicity, we assume we continue to use the **planned average yearly hours** for the remaining period:

```text
remainingHours           = yearsRemaining * H_plan
finalProjectedBalance    = CF + remainingHours * EFH
differenceAtEnd          = finalProjectedBalance - TF
```

- If `differenceAtEnd >= 0` → projected to meet or exceed the target fund.
- If `differenceAtEnd < 0`  → projected to be short of the target.

### 3. Suggested Cash Call

If there is a shortfall at the end, the cash call needed **now** to fix it is:

```text
shortfall        = TF - finalProjectedBalance
suggestedCashCall = max(shortfall, 0)
```

(If the projected balance is above the target, `suggestedCashCall` is 0.)

### 4. Suggested New Engine-Fund Per Hour

If you do **not** want to make a cash call, you can instead increase the `engineFundPerHour` for the remaining hours:

```text
remainingHoursForTarget = yearsRemaining * H_plan

suggestedEngineFundPerHour =
    (TF - CF) / remainingHoursForTarget       (if remainingHoursForTarget > 0)
```

This is the new per-hour contribution that, combined with the existing fund balance, would reach the target by the end of the period.

> Note: In real life, you might cap this by a maximum acceptable hourly price. For this exercise, it is enough to compute the raw number.

## Output Model

Response body – `ProjectionResponse`:

```json
{
  "hoursSummary": {
    "totalPeriodYears": 10.0,
    "yearsElapsed": 5.0,
    "yearsRemaining": 5.0,
    "hoursFlownSoFar": 700.0,
    "plannedAverageYearlyHours": 200.0,
    "expectedHoursSoFar": 1000.0,
    "differenceHours": -300.0,
    "actualAverageYearlyHoursSoFar": 140.0,
    "requiredAverageYearlyHoursRemaining": 260.0,
    "maxRealisticYearlyHours": 125.0,
    "hoursPlanRealistic": false
  },
  "fundProjection": {
    "currentFundBalance": 10000.00,
    "targetFund": 45000.00,
    "yearsRemaining": 5.0,
    "plannedAverageYearlyHours": 200.0,
    "remainingHours": 1000.0,
    "engineFundPerHourCurrent": 30.00,
    "finalProjectedBalance": 40000.00,
    "differenceAtEnd": -5000.00,
    "suggestedCashCall": 5000.00,
    "suggestedEngineFundPerHour": 35.00
  }
}
```

The above example uses:

- `currentFundBalance` = 10000  
- `targetFund` = 45000  
- `totalPeriodYears` = 10  
- `yearsElapsed` = 5  
- `hoursFlownSoFar` = 700  
- `plannedAverageYearlyHours` = 200  
- `maxRealisticYearlyHours` = 125  
- `engineFundPerHour` = 30

Calculations (for reference):

- `expectedHoursSoFar` = 200 * 5 = 1000  
- `differenceHours` = 700 - 1000 = -300  
- `actualAverageYearlyHoursSoFar` = 700 / 5 = 140  
- `totalPlannedHours` = 10 * 200 = 2000  
- `yearsRemaining` = 10 - 5 = 5  
- `remainingHoursNeeded` = 2000 - 700 = 1300  
- `requiredAverageYearlyHoursRemaining` = 1300 / 5 = 260 → > 125, not realistic  
- `remainingHours` (for fund projection) = 5 * 200 = 1000  
- `finalProjectedBalance` = 10000 + 1000 * 30 = 40000  
- `differenceAtEnd` = 40000 - 45000 = -5000 (shortfall)  
- `suggestedCashCall` = 5000  
- `suggestedEngineFundPerHour` = (45000 - 10000) / 1000 = 35

## Your Tasks

1. **Understand the existing implementation** in `EngineFundServiceImpl`.
   - It is intentionally not very clean: mixed responsibilities, weak naming, and duplicated logic.
2. Implement or fix the logic so that:
   - The response matches the formulas and example above.
   - Monetary values are rounded to 2 decimal places using `HALF_UP`.
3. Expose a Spring Boot REST endpoint:
   - Method: `POST`
   - Path: `/engine-fund/projection`
   - Request body: `ProjectionRequest` JSON
   - Response body: `ProjectionResponse` JSON
4. Add at least **one unit test** that asserts:
   - The example input above produces the expected `finalProjectedBalance`, `differenceAtEnd`, `suggestedCashCall`, and `suggestedEngineFundPerHour`.
5. **If you have time**, refactor `EngineFundServiceImpl`:
   - Improve naming.
   - Extract smaller methods.
   - Separate hours-related calculations from money-related ones.
   - Avoid unnecessary type conversions.

You do not need to handle validation or error responses in a sophisticated way – simple checks and exceptions are fine for this exercise.
