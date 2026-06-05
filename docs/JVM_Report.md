# JVM Report

## Memory model usage

- Objects (`Doctor`, `Patient`, `Appointment`, `Bill`) are allocated on the heap.
- Primitive locals and references are used in stack frames during service method execution.
- The immutable `BillSummary` helps reduce unintended mutation in shared references.

## Collections and concurrency

- `DataStore<T>` uses `ConcurrentHashMap` for thread-safe CRUD operations.
- Appointment slot checks are done via stream filtering on in-memory collections.

## Exception handling

- Input validation uses `InvalidDataException` for fail-fast behavior.
- Lookup failures for appointments use `AppointmentNotFoundException`.

## I/O behavior

- `CSVUtil` writes UTF-8 encoded CSV files using NIO (`Files.write`).

## Possible JVM optimizations

- Escape analysis may stack-allocate short-lived objects in some runtime scenarios.
- JIT can inline small utility methods (`Validator`, `IdGenerator`) after warm-up.

