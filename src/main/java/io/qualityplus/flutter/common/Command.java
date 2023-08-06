package io.qualityplus.flutter.common;

public enum Command {
  HEALTH_CHECK("flutter:checkHealth"),
  CLEAR_TIME_LINE("flutter:clearTimeline"),
  FORCE_GC("flutter:forceGC"),
  GET_RENDER_OBJECT_DIAGNOSTICS("flutter:getRenderObjectDiagnostics"),
  GET_SEMANTICS_ID("flutter:getSemanticsId"),
  GET_RENDER_TREE("flutter:getRenderTree"),
  WAIT_FOR_ABSENT("flutter:waitForAbsent"),
  SCROLL_UNTIL_VISIBLE("flutter:scrollUntilVisible"),
  SCROLL("flutter:scroll"),
  SCROLL_INFO_VIEW("flutter:scrollIntoView"),
  WAIT_FOR("flutter:waitFor");

  private String command;

  Command(String command) {
    this.command = command;
  }

  @Override
  public String toString() {
    return command;
  }
}
