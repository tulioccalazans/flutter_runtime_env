import 'dart:async';
import 'package:flutter/services.dart';

const _channel = const MethodChannel('io.gitjournal/should_enable_analytics');

Future<bool> shouldEnableAnalytics() async {
  return await _channel.invokeMethod('shouldEnableAnalytics');
}
