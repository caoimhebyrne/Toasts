![image](.github/images/image.png)

## Toasts

Toasts is a Fabric library which makes creating and displaying 'toasts' in Minecraft easier.

### Usage

**Creating and showing a toast**

```java
new BasicToastBuilder()
        .title("My toast")
        .description("Hello World") // Only required option
        .build()
        .show();
```

**Adding an icon to a toast**

```java
new BasicToastBuilder()
        .title("My toast")
        .description("Hello World") // Only required option
        .icon(new Identifier("textures/item/diamond_sword.png"))
        .build()
        .show();
```

**Dismissing the toast after a certain amount of time**

```java
// This toast will dismiss after 2000 ms
new BasicToastBuilder()
        .title("My toast")
        .description("Hello World") // Only required option
        .displayTime(2000)
        .build()
        .show();
```

**Executing code when the toast dismisses**

```java
new BasicToastBuilder()
        .title("My toast")
        .description("Hello World") // Only required option
        .decayHandler(()->System.out.println("Toast completed!"))
        .build()
        .show();
```
