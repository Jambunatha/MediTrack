# MediTrack

A comprehensive **medical appointment tracking system** built in Java that manages doctors, patients, appointments, and billing with thread-safe data operations.

---

## Features

- **Doctor Management** - Register and manage doctors with specialization and consultation fees
- **Patient Management** - Create and maintain patient records with personal and health information
- **Appointment Booking** - Schedule appointments with automatic slot conflict detection
- **Billing System** - Generate bills with tax calculation and discount support
- **CSV Export** - Export all data (doctors, patients, appointments) to CSV files
- **Thread-Safe Operations** - Built-in concurrency using `ConcurrentHashMap` for safe multi-threaded access
- **AI-Powered Specialization** - Suggest medical specialization based on symptoms
- **Input Validation** - Comprehensive validation for all user inputs

---

## Technology Stack

- **Language:** Java 17+
- **Build System:** Standard Java compilation
- **Data Structures:** 
  - `ConcurrentHashMap` for thread-safe data storage
  - `ArrayList` for collections
  - `Optional` for null-safe operations
- **Date/Time:** Java `java.time` package (`LocalDateTime`, `LocalDate`)
- **Decimal Precision:** `BigDecimal` for accurate financial calculations
- **File I/O:** Java NIO (`java.nio.file`)

---

## Project Structure

```
MediTrack/
├── src/
│   └── com/airtribe/meditrack/
│       ├── Main.java                    # Entry point
│       ├── entity/
│       │   ├── Person.java              # Base class for people
│       │   ├── Doctor.java              # Doctor entity
│       │   ├── Patient.java             # Patient entity
│       │   ├── Appointment.java         # Appointment entity
│       │   ├── Bill.java                # Bill entity (implements Payable)
│       │   └── BillSummary.java         # Immutable bill summary
│       ├── service/
│       │   ├── DoctorService.java       # Doctor CRUD operations
│       │   ├── PatientService.java      # Patient CRUD operations
│       │   └── AppointmentService.java  # Appointment management & billing
│       ├── util/
│       │   ├── DataStore.java           # Generic thread-safe data storage
│       │   ├── IdGenerator.java         # Unique ID generation
│       │   ├── Validator.java           # Input validation
│       │   ├── DateUtil.java            # Date/time utilities
│       │   ├── CSVUtil.java             # CSV export utilities
│       │   └── AIHelper.java            # AI specialization suggestions
│       ├── interfaces/
│       │   ├── Payable.java             # Bill payment interface
│       │   └── Searchable.java          # Search interface
│       ├── exception/
│       │   ├── InvalidDataException.java      # Custom exception for invalid data
│       │   └── AppointmentNotFoundException.java # Exception for missing appointments
│       ├── constants/
│       │   └── Constants.java           # Application constants
│       └── test/
│           └── TestRunner.java          # Test suite
├── docs/
│   ├── Setup_Instructions.md            # Setup guide
│   ├── JVM_Report.md                    # JVM architecture & optimization
│   └── README.md                        # This file
└── MediTrack.iml                        # IntelliJ project file
```

---

## Quick Start

### Prerequisites
- **Java 17+** installed
- Any IDE: IntelliJ IDEA, VS Code, Eclipse, or any text editor

### Setup & Run

1. **Install Java 17** from [Azul](https://www.azul.com/downloads/)

2. **Open the project** in your IDE:
   - IntelliJ: File → Open → select `MediTrack` folder
   - VS Code: File → Open Folder → select `MediTrack` folder
   - Eclipse: File → Open Projects from File System → select `MediTrack`

3. **Run Main.java:**
   - Open `src/com/airtribe/meditrack/Main.java`
   - Click Run button or press Ctrl+F5

4. **Expected Output:**
   ```
   Welcome to MediTrack!
   Recommended specialty for symptom 'Mild chest pain': Cardiology
   Booked: Appointment{...}
   Bill: BillSummary{..., finalAmount=1100.00}
   CSV exports generated in project root path.
   ```

5. **CSV Files Generated:**
   - `doctors.csv` - All registered doctors
   - `patient.csv` - All registered patients
   - `appointment.csv` - All scheduled appointments

 **For detailed setup instructions:** See [Setup_Instructions.md](docs/Setup_Instructions.md)

---

## Running Tests

1. Open `src/com/airtribe/meditrack/test/TestRunner.java`
2. Click Run button
3. Expected output:
   ```
   [PASS] Test 1: Add Doctor and Patient
   [PASS] Test 2: Prevent Duplicate Doctor Slot
   [PASS] Test 3: Bill Generation
   
   Manual tests complete. Passed=3, Failed=0
   ```

---

## How to Use

### Creating a Doctor
```java
Doctor doctor = new Doctor(
    IdGenerator.generate("DOC-"),
    "Dr. Meera Singh",
    "9876543210",
    "meera@meditrack.com",
    "Cardiology",
    new BigDecimal("1500")
);
doctorService.addDoctor(doctor);
```

### Creating a Patient
```java
Patient patient = new Patient(
    IdGenerator.generate("PAT-"),
    "John Doe",
    "9988776655",
    "john@email.com",
    DateUtil.parseDate("1993-02-24"),
    "A+"
);
patientService.addPatient(patient);
```

### Booking an Appointment
```java
Appointment appointment = appointmentService.bookAppointment(
    patient.getId(),
    doctor,
    LocalDateTime.now().plusDays(1),
    "Follow-up checkup"
);
```

### Generating a Bill
```java
BillSummary billSummary = appointmentService.completeAndGenerateBill(
    appointment.getId(),
    doctor,
    new BigDecimal("100")  // discount
);
System.out.println(billSummary.getFinalAmount());
```

### Getting AI Recommendation
```java
String specialty = AIHelper.suggestSpecialization("Mild chest pain");
// Output: "Cardiology"
```

---

## Architecture Highlights

### Thread Safety
- All data storage uses `ConcurrentHashMap` via `DataStore<T>`
- Each service maintains isolated thread-safe collections
- No shared mutable state between services

### Data Validation
- Phone numbers: Exactly 10 digits
- Emails: Must contain `@` and `.`
- Dates: Format `yyyy-MM-dd`
- Blood groups: Must be valid (A+, A-, B+, B-, O+, O-, AB+, AB-)

### Immutability
- `BillSummary` is immutable (final fields, no setters)
- Reduces unintended mutations in shared references
- Safe for concurrent access

### BigDecimal Usage
- All financial calculations use `BigDecimal` for precision
- Proper rounding with `HALF_UP` mode
- Accurate tax and discount calculations

---

## Billing Calculation

**Formula:** `(Consultation Fee + Tax) - Discount`

**Example:**
- Consultation Fee: ₹1,000
- Tax Rate: 5%
- Tax Amount: ₹50
- Discount: ₹100
- **Final Amount: ₹950**

---

## Customization

Edit `src/com/airtribe/meditrack/Main.java` to change:

```java
// Change doctor details
Doctor doctor = new Doctor(
    IdGenerator.generate("DOC-"),
    "Dr. Your Name",          // ← Your doctor name
    "9876543210",             // ← 10-digit phone
    "doctor@email.com",       // ← Valid email
    "Cardiology",             // ← Your specialization
    new BigDecimal("1500")    // ← Consultation fee
);

// Change patient details
Patient patient = new Patient(
    IdGenerator.generate("PAT-"),
    "Your Patient Name",      // ← Patient name
    "9988776655",             // ← 10-digit phone
    "patient@email.com",      // ← Valid email
    DateUtil.parseDate("1990-05-15"),  // ← Date (yyyy-MM-dd)
    "O+"                      // ← Blood group
);
```

Save and run again to see results.

---

## Troubleshooting

| Issue                      | Solution                                                  |
|----------------------------|-----------------------------------------------------------|
| IDE doesn't recognize Java | Restart IDE after installing JDK 17                       |
| "Cannot find Main class"   | Make sure you're running `com.airtribe.meditrack.Main`    |
| Compilation errors         | Rebuild project: IDE → Build → Rebuild                    |
| CSV files not created      | Check folder permissions and try running as administrator |
| Phone validation error     | Use exactly 10 digits, no spaces or dashes                |
| Email validation error     | Email must contain `@` and `.` (e.g., `user@domain.com`)  |

---

## Learning Outcomes

This project demonstrates:
- **Object-Oriented Design** - Inheritance, interfaces, polymorphism
- **Concurrency** - Thread-safe collections, atomic operations
- **Data Validation** - Input validation patterns
- **Exception Handling** - Custom exceptions, try-catch
- **Immutability** - Design for thread safety
- **File I/O** - NIO, CSV generation
- **Design Patterns** - Service layer, data access objects
- **JVM Concepts** - Class loading, memory areas, garbage collection, JIT compilation

---

## Author

**Jambunatha Koni**

---

## Design Decisions

### 1. **ConcurrentHashMap for Thread Safety**
- Chose `ConcurrentHashMap` over synchronized collections for better performance
- Segment-based locking allows concurrent reads without blocking
- Each service maintains isolated `DataStore<T>` instances to prevent data contention

### 2. **Immutable BillSummary**
- `BillSummary` fields are `final` with no setters
- Prevents accidental mutations in multi-threaded scenarios
- Safe to share across threads without synchronization

### 3. **BigDecimal for Financial Calculations**
- Used instead of `double` to avoid floating-point precision errors
- `HALF_UP` rounding mode ensures consistent, predictable billing
- Example: Tax calculations use `BigDecimal`, not floating-point arithmetic

### 4. **Generic DataStore<T> Pattern**
- Single generic class handles all entity types (Doctor, Patient, Appointment)
- Reduces code duplication across services
- Type-safe operations at compile time

### 5. **Stream API for Slot Conflict Detection**
- Uses `stream().filter().anyMatch()` for appointment slot checking
- Functional approach is more readable and less error-prone
- In-memory filtering is acceptable for small datasets

### 6. **Custom Exceptions**
- `InvalidDataException` for validation failures (fail-fast approach)
- `AppointmentNotFoundException` for lookup failures
- Clear separation of concerns between validation and data access layers

### 7. **CSV Export with NIO**
- `java.nio.file.Files.writeString()` for reliable file operations
- Automatic path separator handling (Windows: `\`, Linux/Mac: `/`)
- UTF-8 encoding by default

### 8. **Static Utility Classes**
- `Validator`, `DateUtil`, `IdGenerator`, `AIHelper` are static helpers
- Private constructors prevent instantiation
- JIT compiler can inline these methods effectively

### 9. **Layered Architecture**
```
Main.java (Entry Point)
    ↓
Service Layer (DoctorService, PatientService, AppointmentService)
    ↓
DataStore<T> (Generic data access)
    ↓
Entities (Doctor, Patient, Appointment, Bill)
```
- Separation of concerns makes code maintainable and testable
- Easy to swap implementations or add persistence layers later

### 10. **Validation at Service Boundary**
- All input validation happens in services before persisting
- Entities assume validated data (no defensive checks in constructors)
- Reduces redundant validation and improves performance

---

## Support

For more information, check these documentation files:

1. **[Setup_Instructions.md](docs/Setup_Instructions.md)** - Step-by-step guide to install Java and run the project in your IDE
2. **[JVM_Report.md](docs/JVM_Report.md)** - Detailed JVM architecture, class loaders, runtime data areas, execution engine, and WORA principle
3. **[Design_Decisions.md](docs/Design_Decisions.md)** - Explanation of architectural choices and design patterns used in MediTrack

---
