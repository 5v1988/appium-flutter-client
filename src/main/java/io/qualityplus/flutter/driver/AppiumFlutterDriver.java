package io.qualityplus.flutter.driver;

import com.google.common.collect.ImmutableMap;
import io.appium.java_client.AppiumClientConfig;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.remote.SupportsContextSwitching;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;
import io.qualityplus.flutter.command.FlutterCommand;
import io.qualityplus.flutter.common.Command;
import io.qualityplus.flutter.common.FlutterBy;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.remote.HttpCommandExecutor;
import org.openqa.selenium.remote.http.HttpClient.Factory;

public class AppiumFlutterDriver extends AppiumDriver implements FlutterFinder, SupportsContextSwitching {
  public AppiumFlutterDriver(HttpCommandExecutor executor,
      Capabilities capabilities) {
    super(executor, capabilities);
  }

  public AppiumFlutterDriver(AppiumClientConfig clientConfig,
      Capabilities capabilities) {
    super(clientConfig, capabilities);
  }

  public AppiumFlutterDriver(URL remoteAddress, Capabilities capabilities) {
    super(remoteAddress, capabilities);
  }

  public AppiumFlutterDriver(URL remoteAddress,
      Factory httpClientFactory,
      Capabilities capabilities) {
    super(remoteAddress, httpClientFactory, capabilities);
  }

  public AppiumFlutterDriver(AppiumDriverLocalService service,
      Capabilities capabilities) {
    super(service, capabilities);
  }

  public AppiumFlutterDriver(AppiumDriverLocalService service,
      Factory httpClientFactory, Capabilities capabilities) {
    super(service, httpClientFactory, capabilities);
  }

  public AppiumFlutterDriver(AppiumServiceBuilder builder,
      Capabilities capabilities) {
    super(builder, capabilities);
  }

  public AppiumFlutterDriver(AppiumServiceBuilder builder,
      Factory httpClientFactory, Capabilities capabilities) {
    super(builder, httpClientFactory, capabilities);
  }

  public AppiumFlutterDriver(Factory httpClientFactory, Capabilities capabilities) {
    super(httpClientFactory, capabilities);
  }

  public AppiumFlutterDriver(Capabilities capabilities) {
    super(capabilities);
  }

  public AppiumFlutterDriver(URL remoteSessionAddress, String platformName, String automationName) {
    super(remoteSessionAddress, platformName, automationName);
  }

  /**
   * <p>This method is used to find element by one of locating strategies such as ByValueKey,
   * ByText etc. and value to be used</p>
   *
   * @param by    — A type of locating strategy to find
   * @param using — A value to be used
   * @return — return a flutter element that matches a given locating type and value
   */

  public FlutterElement findElement(FlutterBy by, String using) {
    FlutterElement element;
    switch (by) {
      case VALUE_KEY:
        element = new FlutterElement(ImmutableMap.of(
            FINDER_TYPE, by.toString(),
            "keyValueType", FINDER_TYPE_STRING,
            "keyValueString", using
        ));
        element.setParent(this);
        element.setFileDetector(keys -> null);
        return element;

      case TYPE:
        element = new FlutterElement(ImmutableMap.of(
            FINDER_TYPE, by.toString(),
            "type", using
        ));
        element.setParent(this);
        element.setFileDetector(keys -> null);
        return element;

      case TEXT:
        element = new FlutterElement(ImmutableMap.of(
            FINDER_TYPE, by.toString(),
            "text", using
        ));
        element.setParent(this);
        element.setFileDetector(keys -> null);
        return element;

      case SEMANTICS_LABEL:
        element = new FlutterElement(ImmutableMap.of(
            FINDER_TYPE, by.toString(),
            "isRegExp", false,
            "label", using
        ));
        element.setParent(this);
        element.setFileDetector(keys -> null);
        return element;

      case TOOL_TIP:
        element = new FlutterElement(ImmutableMap.of(
            "finderType", by.toString(),
            "text", using
        ));
        element.setParent(this);
        element.setFileDetector(keys -> null);
        return element;
      case PAGE_BACK:
        element = new FlutterElement(ImmutableMap.of(
            FINDER_TYPE, by.toString()
        ));
        element.setParent(this);
        element.setFileDetector(keys -> null);
        return element;
      default:
        return null;
    }
  }

  /**
   * A method to find an ancestor flutter element given one of its children flutter elements
   *
   * @param of             a child element using which an ancestor is to be found
   * @param matching       — a target ancestor flutter element that is to be matched
   * @param matchRoot
   * @param firstMatchOnly — a boolean value to decide whether to return first match only or not
   * @return
   */

  public FlutterElement findAncestorElement(FlutterElement of, FlutterElement matching,
      boolean matchRoot, boolean firstMatchOnly) {
    Map<String, Object> matchIdentifier = new HashMap<>(ImmutableMap.of(
        FINDER_TYPE, FlutterBy.ANCESTOR.toString(),
        "matchRoot", matchRoot,
        "firstMatchOnly", firstMatchOnly
    ));
    matchIdentifier.put("of", of.getRawMap());
    matchIdentifier.put("matching", matching.getRawMap());
    FlutterElement element = new FlutterElement(matchIdentifier);
    element.setParent(this);
    element.setFileDetector(keys -> null);
    return element;
  }

  /**
   * A method to find an descendant flutter element given one of its ancestor flutter elements
   *
   * @param of             a child element using which a descendant is to be found
   * @param matching       — a target descendant flutter element that is to be matched
   * @param matchRoot
   * @param firstMatchOnly — a boolean value to decide whether to return first match only or not
   * @return
   */

  public FlutterElement findDescendantElement(FlutterElement of, FlutterElement matching,
      boolean matchRoot, boolean firstMatchOnly) {
    Map<String, Object> matchIdentifier = new HashMap<>(ImmutableMap.of(
        FINDER_TYPE, FlutterBy.DESCENDANT.toString(),
        "matchRoot", matchRoot,
        "firstMatchOnly", firstMatchOnly
    ));
    matchIdentifier.put("of", of.getRawMap());
    matchIdentifier.put("matching", matching.getRawMap());
    FlutterElement element = new FlutterElement(matchIdentifier);
    element.setParent(this);
    element.setFileDetector(keys -> null);
    return element;
  }

  /**
   * This method is get all available contexts in the current session
   * @return — Returns all available contexts such as FLUTTER, NATIVE_APP
   */

  public Set<String> getContexts(){
    Set<String> contexts = ((SupportsContextSwitching) this).getContextHandles();
    return contexts;
  }

  /**
   * Given the context is valid for the current session, this method helps to switch to the given context
   * @param to — Namely FLUTTER, NATIVE_APP
   * Use case: When you want to avail all appium features on Flutter driver, this may come in handy
   */
  public void switchToContext(String to) {
    Set<String> contexts = ((SupportsContextSwitching) this).getContextHandles();
    for (String context : contexts) {
      if (context.contains(to)) {
        ((SupportsContextSwitching) this).context(context);
        return;
      }
    }
  }

  /**
   * This renders the current tree of structure of the screen
   * @return
   */
  public String getRenderTree(){
    return new FlutterCommand(this)
        .execute(Command.GET_RENDER_TREE).toString();
  }

  /**
   * Wait until the given element being passed is visible on the screen
   * @param el — an element to wait for
   * @param timeout — a timeout in ms
   */
  public void waitFor(FlutterElement el, int timeout){
    new FlutterCommand(this)
        .execute(Command.WAIT_FOR, el, timeout);
  }

}

