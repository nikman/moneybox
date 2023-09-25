package ru.niku.uikit

class PayLoadModel (
    var id: Int = 0,
    var name: String = "",
    var amount: Double = 0.0,
    var category: String = "",
    //var time: Timestamp
): BaseValueModel()