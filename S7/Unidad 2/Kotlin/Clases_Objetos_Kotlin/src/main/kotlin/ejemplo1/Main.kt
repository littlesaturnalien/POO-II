package ejemplo1

fun main() {

    var smartHome : SmartHome = SmartHome(
        SmartTvDevice("Android TV", "Entertainment"),
        SmartLightDevice("Google Light", "Utility")
    )

    smartHome.printSmartTvInfo()
    smartHome.printSmartLightInfo()

    smartHome.turnOnTv()
    smartHome.turnOnLight()

    smartHome.decreaseTvVolume()
    smartHome.changeTvChannelToPrevious()
    smartHome.decreaseLightBrightness()

    smartHome.turnOffAllDevices()
}