# Factory Method Pattern — Notification Sender

## The Problem

A user completes an action (order placed, password reset). Based on their preference
you need to send a notification — EMAIL, SMS, or PUSH. Each has totally different
dependencies (JavaMailSender, Twilio, Firebase) and sending logic.

Naive approach:

```java
if (channel.equals("EMAIL")) {
    mailSender.send(...);
} else if (channel.equals("SMS")) {
    twilioClient.create(...);
} else if (channel.equals("PUSH")) {
    firebase.send(...);
}
```

Every new channel = touch this method. Caller knows about all concrete senders.

## Why Factory Method

Factory Method defines an interface for **creating an object**, but lets the factory
decide which class to instantiate. The caller only ever sees the product interface —
it never references a concrete sender directly.

```
NotificationService
    calls → factory.create("EMAIL")
              returns → EmailNotificationSender (as NotificationSender)
    calls → .send(request)
```

`NotificationService` has zero knowledge of `EmailNotificationSender`,
`SmsNotificationSender`, or `PushNotificationSender`.

## Factory Method vs Strategy — The Real Difference

They look identical in Spring because DI blurs the line, but the **intent** differs:

| | Factory Method | Strategy |
|--|---------------|----------|
| Pattern type | Creational | Behavioral |
| Core question | *What object to create?* | *What algorithm to run?* |
| Focus | Object creation is the responsibility | Behavior swapping is the responsibility |
| Caller does | `factory.create(type)` → then uses the product | Holds a strategy and calls it directly |
| Real-world tell | You'd describe it as "get me the right sender" | You'd describe it as "swap the algorithm" |

In this codebase: you **ask the factory** to give you a sender.
In the Strategy pattern: the registry **resolves which behavior to execute**.

## Adding a New Channel

Create one class, annotate it. Nothing else changes.

```java
@Component
public class WhatsAppNotificationSender implements NotificationSender {

    @Override
    public String channel() { return "WHATSAPP"; }

    @Override
    public void send(NotificationRequest request) {
        // whatsappClient.send(...)
    }
}
```

## Package Structure

```
factory/
  sender/       ← NotificationSender interface + Email/Sms/Push implementations
  factory/      ← NotificationSenderFactory (the factory method)
  service/      ← NotificationService (client — uses factory, never concrete senders)
  controller/   ← REST endpoint
  dto/          ← NotificationRequest / NotificationResult records
  exception/    ← UnsupportedChannelException
```

## API

```
POST /api/v1/notifications
{
  "channel": "EMAIL",
  "to":      "user@example.com",
  "message": "Your order #42 has been confirmed"
}
```
