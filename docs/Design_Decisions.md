# Design Decisions

- **Layered modules**: Entities hold data, services encapsulate workflow, utils provide reusable helpers.
- **Generic store**: `DataStore<T>` centralizes in-memory persistence and demonstrates generic programming.
- **Interfaces for contracts**: `Searchable<T>` and `Payable` define behavior for services and billing.
- **Immutability in billing view**: `BillSummary` is immutable to prevent accidental mutation of financial outputs.
- **Validation boundary**: `Validator` guards entity creation and updates to fail fast on invalid input.
- **Extensibility**: `AIHelper` is isolated so recommendation logic can later be replaced with external AI/API integration.
