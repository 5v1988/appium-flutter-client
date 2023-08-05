package io.qualityplus.flutter.driver;

import com.google.common.collect.ImmutableMap;
import io.appium.java_client.AppiumClientConfig;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;
import io.qualityplus.flutter.common.FlutterBy;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.remote.HttpCommandExecutor;
import org.openqa.selenium.remote.http.HttpClient.Factory;

public class AppiumFlutterDriver extends AppiumDriver implements FlutterFinder {

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
      default:
        return null;
    }
  }

  public FlutterElement findElementByAncestor(FlutterElement of, FlutterElement matching,
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

  public FlutterElement findElementByDescendant(FlutterElement of, FlutterElement matching,
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
}

