#!/bin/bash

/D/php/php.exe manifest.php $1 > ../app/src/main/AndroidManifest.xml

for i in `eval echo {1..$1}`
do
 /D/php/php.exe activity.php $i > ../app/src/main/java/com/javacakegames/octagon/MainActivity$i.java
 echo $i
done
