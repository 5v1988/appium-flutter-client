package io.qualityplus.flutter.command;

import io.qualityplus.flutter.common.Command;
import io.qualityplus.flutter.driver.AppiumFlutterDriver;

public class FlutterCommand {

  private AppiumFlutterDriver driver;

  public FlutterCommand(AppiumFlutterDriver driver) {
    this.driver = driver;
  }

  /**
   * <p>This method executes flutter command for a given list of parameters</p>
   * <p>When a return value is not null for a command, it can be assigned to a local variable to
   * determine the right type so can be used further</p>
   *
   * @param flutterCommand — takes a command Enum value
   * @param params — The parameter list that typically be passed to `executeScript` method
   *               — https://github.com/appium-userland/appium-flutter-driver
   * @param <T>
   * @return — generally returns generic type based on the command executed
   */

  @SuppressWarnings("unchecked")
  public <T> T execute(Command flutterCommand, Object... params) {
    if (flutterCommand.equals(Command.HEALTH_CHECK) || flutterCommand.equals(
        Command.CLEAR_TIME_LINE) || flutterCommand.equals(Command.FORCE_GC)
        || flutterCommand.equals(Command.GET_RENDER_TREE)) {
      return (T) driver.executeScript(flutterCommand.toString());
    } else if (flutterCommand.equals(Command.GET_SEMANTICS_ID) || flutterCommand.equals(
        Command.WAIT_FOR_ABSENT) || flutterCommand.equals(
        Command.WAIT_FOR)) {
      return (T) driver.executeScript(flutterCommand.toString(), params[0]);
    } else if (flutterCommand.equals(Command.GET_RENDER_OBJECT_DIAGNOSTICS)
        || flutterCommand.equals(Command.SCROLL_INFO_VIEW) || flutterCommand.equals(
        Command.SCROLL) || flutterCommand.equals(
        Command.SCROLL_UNTIL_VISIBLE)) {
      return (T) driver.executeScript(flutterCommand.toString(), params[0], params[1]);
    } else {
      throw new IllegalStateException("Unexpected flutter command: " + flutterCommand);
    }

  }
}
