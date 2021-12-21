![image](.github/images/image.png)

## Toasts

Toasts is a Fabric library which makes creating and displaying 'toasts' in Minecraft easier.

### Usage

**Creating and showing a toast**

```
BasicToast.create("Hello World!").show();
```

**Adding an icon to a toast**

```
BasicToast.create("Hello World!", new Identifier("mymod", "textures/toast.png").show();
```

**Dismissing the toast after a certain amount of time**

```
// This toast will dismiss after 2000 ms
BasicToast.create("Hello World!", 2000).show();
```

**Executing code when the toast dismisses**

```
// The lambda function will run after 5000ms (the default dismissal time)
BasicToast.create("Hello World!", () -> System.out.println("Toast completed!")).show();
```

### Examples

**Showing a simple toast via a command**

```
CommandRegistrationCallback.EVENT.register((dispatcher, dedicated) ->
    dispatcher.register(literal("test").executes(context -> {
        BasicToast.create("Hello World!").show();
        return 1;
    }))
);
```
