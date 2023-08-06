package io.qualityplus.flutter.driver;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonPrimitive;
import java.util.Base64;
import java.util.HashMap;
import java.util.Map;
import org.openqa.selenium.remote.RemoteWebElement;

public class FlutterElement extends RemoteWebElement {

  private final Map<String, Object> rawMap;
  private final Gson gson = new Gson();

  protected FlutterElement(final Map<String, Object> rawMap) {
    this.rawMap = rawMap;
    id = serialize(rawMap);
  }

  protected Map<String, Object> getRawMap() {
    return rawMap;
  }

  private String serialize(final Map<String, Object> rawMap) {
    final JsonPrimitive localInstance = new JsonPrimitive(false);
    Map<String, Object> tempMap = new HashMap<>();
    rawMap.forEach(
        (key, value) -> {
          if (value instanceof String || value instanceof Integer || value instanceof Boolean) {
            tempMap.put(key, new JsonPrimitive(String.valueOf(value)));
          }else if (value instanceof Map) {
            tempMap.put(key, gson.toJson(value));
          }  else if (value instanceof JsonElement) {
            tempMap.put(key, value);
          } else {
            tempMap.put(key, localInstance);
          }
        });
    String stringifyJson = gson.toJson(tempMap);
    return Base64.getEncoder().encodeToString(stringifyJson.getBytes());
  }

}
