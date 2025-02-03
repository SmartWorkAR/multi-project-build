package com.example.client

import kotlin.experimental.ExperimentalNativeApi

@OptIn(ExperimentalNativeApi::class)
actual fun getPlatformName(): String = "${Platform.osFamily} ${Platform.cpuArchitecture}"