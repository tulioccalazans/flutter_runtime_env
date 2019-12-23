package io.gitjournal.should_enable_analytics;

import android.content.Context;
import android.provider.Settings;
import androidx.annotation.NonNull;

import io.flutter.embedding.engine.plugins.FlutterPlugin;
import io.flutter.plugin.common.MethodCall;
import io.flutter.plugin.common.MethodChannel;
import io.flutter.plugin.common.MethodChannel.MethodCallHandler;
import io.flutter.plugin.common.MethodChannel.Result;
import io.flutter.plugin.common.PluginRegistry.Registrar;

/** ShouldEnableAnalyticsPlugin */
public class ShouldEnableAnalyticsPlugin implements FlutterPlugin, MethodCallHandler {
    final static String CHANNEL_NAME = "io.gitjournal/should_enable_analytics";
    private Context context;
    private MethodChannel channel;

    @Override
    public void onAttachedToEngine(@NonNull FlutterPluginBinding flutterPluginBinding) {
        channel = new MethodChannel(flutterPluginBinding.getFlutterEngine().getDartExecutor(), CHANNEL_NAME);
        context = flutterPluginBinding.getApplicationContext();
        channel.setMethodCallHandler(this);
    }

    public static void registerWith(Registrar registrar) {
        ShouldEnableAnalyticsPlugin instance = new ShouldEnableAnalyticsPlugin();
        instance.channel = new MethodChannel(registrar.messenger(), CHANNEL_NAME);
        instance.context = registrar.context();
        instance.channel.setMethodCallHandler(instance);
    }

    @Override
    public void onMethodCall(@NonNull MethodCall call, @NonNull Result result) {
        if (call.method.equals("shouldEnableAnalytics")) {
            String testLabSetting = Settings.System.getString(context.getContentResolver(), "firebase.test.lab");
            if ("true".equals(testLabSetting)) {
                return result.success(false);
            }

            if (BuildConfig.DEBUG) {
                return result.success(false);
            }

            // Emulators
            if (Build.FINGERPRINT.contains("generic")) {
                return result.success(false);
            }

            result.success(true);
            return;
        } else {
            result.notImplemented();
        }
    }

    @Override
    public void onDetachedFromEngine(@NonNull FlutterPluginBinding binding) {
    }
}
