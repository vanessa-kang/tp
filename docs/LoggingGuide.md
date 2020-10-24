# Logging Guide for PlanNUS

* Table of contents
{:toc}
## Using Logging Tool in PlanNUS

`LoggingTool` is available to use for accessible logging at the package `src.main.java.seedu.duke.global`. `LoggingTool`
constructor takes in a parameter `String` and `FileHandler`. `String` represents `loggerName` and `FileHandler` represents
the object `fh`, which is the _java API_. `initialise()` will automatically return a fully configured `Logger` object
with the following parameters :

* `addHandler(fh)`
  * `logger` will log to an external file as defined by `fh`
* `setUseParentHandlers(false)`
  * Disables logging on the console output
* `setLevel(Level.INFO)`
  * Any message logged `Level.INFO` and above will be logged

After initialising, the `logger` can be used as per _java API_ constraints. Below shows an example code snippet that can be used to initialise a `logger`:

```
FileHandler fh = new FileHandler(<YOUR_LOG_FILE_NAME>);
Logger logger = new LoggingTool(<YOUR_LOGGER_NAME>,fh).initialize();
```

## End of logging guide

## Extra Guides and Information

* [**About Us**](https://ay2021s1-cs2113t-f12-1.github.io/tp/AboutUs.html)
* **Configuration guide**
* [**Developer guide**](https://ay2021s1-cs2113t-f12-1.github.io/tp/DeveloperGuide.html)
* **DevOps guide**
* **Documentation guide**
* [**Testing guide**](https://ay2021s1-cs2113t-f12-1.github.io/tp/TestingGuide.html)
* [**User guide**](https://ay2021s1-cs2113t-f12-1.github.io/tp/UserGuide.html)