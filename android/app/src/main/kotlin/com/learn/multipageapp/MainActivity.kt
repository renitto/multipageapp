package com.learn.multipageapp

import android.app.Activity
import android.content.Intent
import android.util.Log
//import androidx.activity.result.contract.ActivityResultContracts
import androidx.annotation.NonNull
import io.flutter.embedding.android.FlutterActivity
import io.flutter.embedding.engine.FlutterEngine
import io.flutter.plugin.common.MethodChannel


class MainActivity : FlutterActivity() {
    private lateinit var result: MethodChannel.Result
    private val CHANNEL = "com.learn.multipageapp/nativeRouteChannel"
    private val METHOD_NATIVE_ROUTE = "nativeRoute"

    override fun configureFlutterEngine(@NonNull flutterEngine: FlutterEngine) {
        super.configureFlutterEngine(flutterEngine)

        Log.d("flutter channel", ">> configureFlutterEngine()")

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
    }

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

}
