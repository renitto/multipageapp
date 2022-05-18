# Flutter Multi Page Application

A Flutter project to showcase multi page navigation from Flutter to Native and Native to Flutter
Screens.

## Communication Details - Flutter with Native

The communication between flutter and native code using **platform channel**. From Flutter app, we
have to send messages to a host on iOS or Android parts of the app over a platform channel. The host
listens on the platform channel and receives the message. It then uses any platform-specific APIs
using the native programming language and sends back a response to the Flutter portion of the app.

![Alt text](/assets/platform_channel_arch.png?raw=true "Platform Channel - Architecture overview")

## Component Details

The project contains mainly 4 screens

1. Flutter Home Screen
2. Flutter Second Screen
3. Android MainActivity Screen
4. Android FirstScreenActivity Screen

## Routing Details

![alt-text-1](/assets/screen_one.png "Flutter Home Screen") ![alt-text-1](/assets/screen_two.png "Android FirstScreenActivity Screen") ![alt-text-1](/assets/screen_three.png "Flutter Second Screen")

### 1. Flutter Home Screen

Flutter Home Screen is the starting point of the flutter application. This screen contains a simple
ElevatedButton. onclick a method is called to communicate with Native android code. **Method Channel
API** is used for communication.

Method channel and Method are defined

```
 static const String routeMethodChannelName = 'com.learn.multipageapp/nativeRouteChannel';
  static const String routeMethodName = 'nativeRoute';
```

Method invoked to communicate with native code

```
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
```

### 2. Android MainActivity Screen

On the Native Android, in MainActivity **setMethodCallHandler** used to handle the Method Channel
API call from Flutter code base.

```
  MethodChannel(flutterEngine.dartExecutor.binaryMessenger, CHANNEL).setMethodCallHandler {
            // Note: this method is invoked on the main thread.
                call,
                result ->
            if (call.method == METHOD_NATIVE_ROUTE) {
                Log.d("flutter channel", ">> nativeRoute()")

                this.result = result

                val intent = Intent(this, FirstScreenActivity::class.java)
                startActivityForResult(intent, 0)

            } else {
                Log.d("Flutter channel", "error: Method not Implemented")
                result.notImplemented()
            }
        }
```

### 3. Android FirstScreenActivity Screen

In FirstScreenActivity on the button click, return Intent is invoked.

```
 //            return to MainActivity
            val returnIntent = Intent()
            setResult(Activity.RESULT_OK, returnIntent) // OK! (use whatever code you want)
            finish()
```

and on **MainActivity** , **onActivityResult** is overridden with the routing logic back to the
flutter screen.

```
  override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        Log.i("Flutter channel Native", "--> onActivityResult() : resultCode= $resultCode")
        // Check that it is the SecondActivity with an OK result

        if (resultCode == Activity.RESULT_OK) {
            Log.i("Flutter channel Native", "--> onActivityResult(): ResultCode ok ")
            //return with flutter route name
            result.success("second-screen")

        } else {
            result.error("error", data?.getStringExtra("result"), null)
        }
    }
```

and from the flutter **HomeController** routing to the **Flutter Second Screen** is done using
following logic

```
   //routing logic here
      if(result.toString() == 'second-screen'){
        Get.toNamed(Routes.SECOND_SCREEN);
      }
```




