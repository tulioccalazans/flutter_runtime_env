import 'dart:async';
import 'package:flutter/services.dart';
import 'package:flutter/foundation.dart' as Foundation;

const _channel = const MethodChannel('io.gitjournal/should_enable_analytics');

Future<bool> shouldEnableAnalytics() async {
  if (isInDebugMode()) {
    return false;
  }
  return await _channel.invokeMethod('shouldEnableAnalytics');
}

bool isInDebugMode() {
  return Foundation.kDebugMode;
}
