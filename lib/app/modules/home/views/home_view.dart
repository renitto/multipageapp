import 'package:flutter/material.dart';

import 'package:get/get.dart';

import '../controllers/home_controller.dart';

class HomeView extends GetView<HomeController> {
  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        title: Text('Flutter App'),
        centerTitle: true,
      ),
      body: Center(
        child: Column(
          mainAxisAlignment: MainAxisAlignment.center,
          children: [
            Text(
              'Flutter Home View',
              style: TextStyle(fontSize: 20),
            ),
            SizedBox(
              height: 30,
            ),
            ElevatedButton(
                onPressed: controller.openNativeScreen,
                child: Text('Open Android Native Screen'))
          ],
        ),
      ),
    );
  }
}
