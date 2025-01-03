# Transactional Outbox pattern

The Transactional Outbox pattern addresses the problem of reliably publishing events when changes are made to a database, especially in distributed systems. It ensures that events are not lost even if the message broker is temporarily unavailable.

1.  **Client Request:** A client makes a request to the `OrderService` (e.g., to place an order).

2.  **Transaction Start:** The `OrderService` starts a database transaction. This is crucial for atomicity.

3.  **Data Persistence and Outbox Entry:** *Within the same transaction*, the `OrderService` does two things:
    *   It inserts the order data into the `Order Table`.
    *   It inserts an *outbox message* into the `Outbox Table`. This message contains the event type (e.g., "OrderCreated") and the event payload (the order data).

4.  **Transaction Commit:** The transaction is committed. This ensures that *both* the order data and the outbox message are persisted atomically. If the transaction fails for any reason, neither is saved, preventing inconsistencies.

5.  **Client Response:** The `OrderService` responds to the client.

6.  **Message Relay and Polling:** A separate process, the `MessageRelay`, periodically polls the `Outbox Table` for unprocessed messages.

7.  **Message Publishing:** For each unprocessed message, the `MessageRelay` publishes the message to the `MessageBroker`.

8.  **Message Acknowledgement and Outbox Update:** Once the message is successfully published (and acknowledged by the message broker), the `MessageRelay` updates the `Outbox Table` to mark the message as processed.

**Key Advantages of the Transactional Outbox:**

*   **Atomicity:** Guarantees that either both the database update and the message publishing succeed, or neither does. This prevents data loss and inconsistencies.
*   **Reliability:** Ensures that messages are eventually delivered, even if the message broker is temporarily down. The `MessageRelay` will retry publishing until it succeeds.
*   **Decoupling:** Decouples the `OrderService` from the message broker. The `OrderService` doesn't need to know the details of the message broker or handle retry logic.

