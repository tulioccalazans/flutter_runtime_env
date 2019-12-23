import 'dart:async';
import 'package:flutter/material.dart';

import 'package:flutter_runtime_env/flutter_runtime_env.dart';

void main() => runApp(MyApp());

class MyApp extends StatefulWidget {
  @override
  _MyAppState createState() => _MyAppState();
}

class _MyAppState extends State<MyApp> {
  bool _shouldBeEnabled = false;

  @override
  void initState() {
    super.initState();
    initPlatformState();
  }

  // Platform messages are asynchronous, so we initialize in an async method.
  Future<void> initPlatformState() async {
    var result = await shouldEnableAnalytics();
    setState(() {
      _shouldBeEnabled = result;
    });
  }

  @override
  Widget build(BuildContext context) {
    return MaterialApp(
      home: Scaffold(
        appBar: AppBar(
          title: const Text('Should Enable Analytics'),
        ),
        body: Center(
          child: Text('Should Analytics be Enabled: $_shouldBeEnabled\n'),
        ),
      ),
    );
  }
}
