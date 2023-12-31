package io.qualityplus.flutter.driver;

import io.qualityplus.flutter.common.FlutterBy;

public interface FlutterFinder {

  String FINDER_TYPE = "finderType";
  String FINDER_TYPE_STRING = "String";
  String FINDER_TYPE_INT = "int";

  public FlutterElement findElement(FlutterBy by, String using);

  public FlutterElement findAncestorElement(FlutterElement of, FlutterElement matching,
      boolean matchRoot, boolean firstMatchOnly);

  public FlutterElement findDescendantElement(FlutterElement of, FlutterElement matching,
      boolean matchRoot, boolean firstMatchOnly);

}
