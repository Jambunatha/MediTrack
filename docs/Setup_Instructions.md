# Setup Instructions — MediTrack

Follow these simple steps to run the MediTrack project.

---

## Step 1 — Install Java (JDK 17)

1. Download JDK 17 from: https://www.azul.com/downloads/
2. Select: **Java Version** → 17, **Operating System** → Windows, **Architecture** → x86 64-bit, **Package Type** → JDK
3. Install it by running the `.msi` file

---

## Step 2 — Open the Project in an IDE

Choose any editor you prefer:

### Option A: IntelliJ IDEA (Recommended)
1. Open **IntelliJ IDEA**
2. Click **Open** and select the `MediTrack` folder
3. Wait for indexing to complete

### Option B: VS Code
1. Open **VS Code**
2. Click **File → Open Folder** and select the `MediTrack` folder
3. Install the **Extension Pack for Java** extension (if not already installed)

### Option C: Eclipse
1. Open **Eclipse**
2. Click **File → Open Projects from File System**
3. Select the `MediTrack` folder

---

## Step 3 — Run the Main Class

1. In your editor, open this file: `src/com/airtribe/meditrack/Main.java`
2. Right-click on the file or look for a **Run** button (depends on your editor)
3. Select **Run** or press the Run button

You should see output like:
```
Welcome to MediTrack!
Recommended specialty for symptom 'Mild chest pain': Cardiology
Booked: Appointment{...}
Bill: BillSummary{..., finalAmount=1100.00}
CSV exports generated in project root path.
```

Three CSV files will be created in the project root: `doctors.csv`, `patient.csv`, `appointment.csv`

---

## Step 4 — Run the Tests (Optional)

1. In your editor, open this file: `src/com/airtribe/meditrack/test/TestRunner.java`
2. Right-click on the file or look for a **Run** button
3. Select **Run** or press the Run button

You should see output like:
```
[PASS] Test 1: Add Doctor and Patient
[PASS] Test 2: Prevent Duplicate Doctor Slot
[PASS] Test 3: Bill Generation

Manual tests complete. Passed=3, Failed=0
```

---

## Step 5 — Modify Data (Optional)

1. Open `src/com/airtribe/meditrack/Main.java`
2. Find the `Doctor` and `Patient` blocks and change values:

```java
Doctor doctor = new Doctor(
    IdGenerator.generate("DOC-"),
    "Dr. Your Name",         // ← change doctor name
    "9876543210",            // ← change phone (10 digits)
    "doctor@email.com",      // ← change email
    "Cardiology",            // ← change specialization
    new BigDecimal("1500")   // ← change fee
);

Patient patient = new Patient(
    IdGenerator.generate("PAT-"),
    "Patient Name",          // ← change patient name
    "9988776655",            // ← change phone (10 digits)
    "patient@email.com",     // ← change email
    DateUtil.parseDate("1993-02-24"), // ← change date (yyyy-MM-dd)
    "A+"                     // ← change blood group
);
```

3. Save the file
4. Run Main class again (Step 3)

---

## Quick Troubleshooting

| Issue                      | Solution                                                                  |
|----------------------------|---------------------------------------------------------------------------|
| IDE doesn't recognize Java | Make sure JDK 17 is installed and restart the IDE                         |
| "Main class not found"     | Make sure you're opening `src/com/airtribe/meditrack/Main.java` correctly |
| Compilation errors         | Check that all files are in `src` folder and IDE has indexed them         |
| CSV files not created      | Check that the project root folder has write permissions                  |
