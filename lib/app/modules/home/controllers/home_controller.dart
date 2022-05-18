import 'package:flutter/services.dart';
import 'package:get/get.dart';
import 'package:multipageapp/app/routes/app_pages.dart';

import '../../../constants/constants.dart';

class HomeController extends GetxController {
  static const routeMethodChannel =
      MethodChannel(Constants.routeMethodChannelName);

  Future<Null> openNativeScreen() async {
    try {
      var result =
          await routeMethodChannel.invokeMethod(Constants.routeMethodName);
      printInfo(info: "Result >>>> $result");
      //routing logic here
      if(result.toString() == 'second-screen'){
        Get.toNamed(Routes.SECOND_SCREEN);
      }
    } on Exception catch (e) {}
  }
}
