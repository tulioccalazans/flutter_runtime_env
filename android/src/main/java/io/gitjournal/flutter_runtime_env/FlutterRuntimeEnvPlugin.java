package io.gitjournal.flutter_runtime_env;

import android.content.Context;
import android.provider.Settings;
import androidx.annotation.NonNull;

import io.flutter.embedding.engine.plugins.FlutterPlugin;
import io.flutter.plugin.common.MethodCall;
import io.flutter.plugin.common.MethodChannel;
import io.flutter.plugin.common.MethodChannel.MethodCallHandler;
import io.flutter.plugin.common.MethodChannel.Result;
import io.flutter.plugin.common.PluginRegistry.Registrar;

/** FlutterRuntimeEnvPlugin */
public class FlutterRuntimeEnvPlugin implements FlutterPlugin, MethodCallHandler {
    final static String CHANNEL_NAME = "io.gitjournal/flutter_runtime_env";
    private Context context;
    private MethodChannel channel;

    @Override
    public void onAttachedToEngine(@NonNull FlutterPluginBinding flutterPluginBinding) {
        channel = new MethodChannel(flutterPluginBinding.getFlutterEngine().getDartExecutor(), CHANNEL_NAME);
        context = flutterPluginBinding.getApplicationContext();
        channel.setMethodCallHandler(this);
    }

    public static void registerWith(Registrar registrar) {
        FlutterRuntimeEnvPlugin instance = new FlutterRuntimeEnvPlugin();
        instance.channel = new MethodChannel(registrar.messenger(), CHANNEL_NAME);
        instance.context = registrar.context();
        instance.channel.setMethodCallHandler(instance);
    }

    @Override
    public void onMethodCall(@NonNull MethodCall call, @NonNull Result result) {
        if (call.method.equals("inFirebaseTestLab")) {
            String testLabSetting = Settings.System.getString(context.getContentResolver(), "firebase.test.lab");
            if ("true".equals(testLabSetting)) {
                result.success(true);
                return;
            }

            result.success(false);
            return;
        } else {
            result.notImplemented();
        }
    }

    @Override
    public void onDetachedFromEngine(@NonNull FlutterPluginBinding binding) {
    }
}
