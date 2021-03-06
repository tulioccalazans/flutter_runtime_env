import 'dart:async';
import 'dart:io';
import 'package:flutter/services.dart';
import 'package:flutter/foundation.dart' as Foundation;

const _channel = const MethodChannel('io.gitjournal/flutter_runtime_env');

Future<bool> inFirebaseTestLab() async {
  if (!Platform.isAndroid) {
    return false;
  }
  try {
    var result = (await _channel.invokeMethod<bool>('inFirebaseTestLab'))!;
    return result;
  } catch (err) {
    return false;
  }
}

bool isInDebugMode() {
  return Foundation.kDebugMode;
}
