package io.qualityplus.flutter.common;

public enum FlutterBy {
  VALUE_KEY("ByValueKey"),
  TYPE("ByType"),
  TOOL_TIP("ByTooltipMessage"),
  TEXT("ByText"),
  SEMANTICS_LABEL("BySemanticsLabel"),
  ANCESTOR("Ancestor"),
  DESCENDANT("Descendant");

  private String type;

  FlutterBy(String type) {
    this.type = type;
  }

  @Override
  public String toString() {
    return type;
  }
}
