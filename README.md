# androidBatteryAnalyzer

Battery Historian is a tool to inspect battery related information and events on an Android device running Android 5.0 Lollipop (API level 21) and later.
here we are divided scenarios like half an hour/overnight and app idle cases and we run this suite like half an hour/one hour/over night from jenkins schehuled run and after the scenario we captured adb bugreport.

Now we hit battery historian(that is running on docker -- run -p <port>:9999 gcr.io/android-battery-historian/stable:3.0 --port 9999 locally/or any server) with captured bugreport as a parameter and store the response that is come from historian api and exoract the desired data like total consumption,running services  info, partial wakelock info/full wakelock info ,service count etc and send this data in opentsdb(we can also send this data in any other DB as per requirement) and visualize it on dashboard like grafana.


What We are Capturing:
Battery used by app
Major service running and its launch count
Wakelock name and its count that were not released

# Visualization
![](https://github.com/Vishvnath96/androidBatteryAnalyzer/blob/res/for/integration/batteryusage.png)

Running services Info:

![](https://github.com/Vishvnath96/androidBatteryAnalyzer/blob/res/for/integration/servicesinfobattery.png)
