<?php
    if ($argc <= 1) die("Specify number of octagons pl0x.");
    echo '<?xml version="1.0" encoding="utf-8"?>'
?>

<manifest xmlns:android="http://schemas.android.com/apk/res/android">

    <application
        android:icon="@mipmap/ic_launcher"
        android:label="Octagon"
        android:theme="@android:style/Theme.Black.NoTitleBar.Fullscreen">
        <?php
            for ($i = 1; $i <= $argv[1]; $i++) { ?>
<activity
            android:name=".MainActivity<?= $i ?>"
            android:label="<?php printf('%06d', $i) ?>"
            android:exported="true"
            android:screenOrientation="landscape">
            <intent-filter>
                <action android:name="android.intent.action.MAIN" />
                <category android:name="android.intent.category.LAUNCHER" />
            </intent-filter>
        </activity>
        <?php
            }
        ?>
</application>

</manifest>