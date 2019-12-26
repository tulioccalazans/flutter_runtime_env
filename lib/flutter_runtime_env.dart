import 'dart:async';
import 'package:flutter/services.dart';
import 'package:flutter/foundation.dart' as Foundation;

const _channel = const MethodChannel('io.gitjournal/flutter_runtime_env');

Future<bool> inFirebaseTestLab() async {
  try {
    var result = await _channel.invokeMethod('inFirebaseTestLab');
    return result;
  } catch (err) {
    return false;
  }
}

bool isInDebugMode() {
  return Foundation.kDebugMode;
}
