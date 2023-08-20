## Appium Flutter Java Binding

**Introduction**

This library helps to write Flutter tests in Java
for [Appium Flutter Driver](https://github.com/appium-userland/appium-flutter-driver). It mainly
offers binding to find elements on mobile app using various locating strategies, and also executes
custom flutter commands This [page](https://github.com/appium-userland/appium-flutter-driver)
contains all necessary details in preparing Flutter app under test.

**Installation**

Pre-requisites:
1. Install Appium by following the steps mentioned [here](https://appium.io/docs/en/2.0/quickstart/install/)
2. Install Appium Flutter Driver from source as below:
    ```npm
    appium driver install --source=npm appium-flutter-driver
    ```
3. Flutter app under test needs to be compiled in debug or profile mode and Flutter Driver extension
also needs to be enabled by adding this line `enableFlutterDriverExtension()` before calling app's 
run method in `main.dart`
4. Include flutter_driver from Flutter SDK as dev dependency

This library, Appium Flutter Binding which is based on Java can be installed by adding the following
dependency on the project

```xml
<dependency>
    <groupId>io.github.5v1988</groupId>
    <artifactId>appium-flutter-client</artifactId>
    <version>1.0.4</version>
</dependency>
```

```groovy
implementation group: 'io.github.5v1988', name: 'appium-flutter-client', version: '1.0.4'
```

Once the above steps are done, Appium Flutter Driver can be instantiated by passing in necessary
mobile capabilities, including `AutomationName` as `Flutter`. The below code snippet is one such 
example for the reference.

```java
    AppiumDriverLocalService service = new AppiumServiceBuilder()
    .usingAnyFreePort().build();
    service.start();
    if (service == null || !service.isRunning()) {
    throw new AppiumServerHasNotBeenStartedLocallyException(
    "An appium server node is not started!");
    }
    BaseOptions options = new BaseOptions()
    .setPlatformName("Android")
    .setAutomationName("Flutter")
    .amend("appium:app", "/path/to/app")
    .amend("autoGrantPermissions", "true");
    AppiumFlutterDriver driver = new AppiumFlutterDriver(service.getUrl(), options);
```

As for locating an element, there are many ways by which the flutter elements can be located, and 
the most prominent one is `ByValueKey`, which is equivalent to `id` in web context. If in case, you 
don't see it for the elements, it's better to talk with the developers to add `Key` for input elements
, and all the available locating strategies are listed down as below:

```java
  VALUE_KEY("ByValueKey"),
  TYPE("ByType"),
  TOOL_TIP("ByTooltipMessage"),
  TEXT("ByText"),
  SEMANTICS_LABEL("BySemanticsLabel"),
  ANCESTOR("Ancestor"),
  DESCENDANT("Descendant"),
  PAGE_BACK("PageBack");
```

Adding some code snippets to locate elements using this library.

```java
//This is by key
private FlutterElement getEmailTextBox() {
  return driver.findElement(FlutterBy.VALUE_KEY, "KEYS.loginTextbox");
}

//This is by descendant
private FlutterElement getFirstItemFromList() {
    return driver.findDescendantElement(
    driver.findElement(FlutterBy.TYPE, "DefaultRefreshIndicator"),
    driver.findElement(FlutterBy.TYPE, "ListItem"),
    false,
    true);
    }
```

Using FlutterCommand from this library, you could able to execute them by passing in necessary parameters
that each command takes in. Status of all implemented Flutter commands can be tracked from [here](https://github.com/appium-userland/appium-flutter-driver)

The below is the snippet to scroll for a flutter element until it's visible on the screen.

```java
protected FlutterCommand command = new FlutterCommand(driver);
command.execute(Command.SCROLL_INFO_VIEW, element, ImmutableMap.of("alignment", 0.1));;
```