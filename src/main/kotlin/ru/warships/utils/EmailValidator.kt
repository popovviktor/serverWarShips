package ru.warships.utils

fun String.isvalidEmail():Boolean{
    return this.length > 4 && this.contains("@") && this.contains(".")
}