package com.khs.dagger2

import dagger.Component

@Component
interface SmartPhoneComponent {
    fun getSmartPhone() : SmartPhone
}