import 'package:flutter/material.dart';

import 'package:get/get.dart';

import '../controllers/second_screen_controller.dart';

class SecondScreenView extends GetView<SecondScreenController> {
  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        title: Text('Flutter Screen 2'),
        centerTitle: true,
      ),
      body: Center(
        child: Text(
          'Flutter Second Screen',
          style: TextStyle(fontSize: 20),
        ),
      ),
    );
  }
}
