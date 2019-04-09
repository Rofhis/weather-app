package com.rofhiwa.weatherapp.data.db.listener

interface DatabaseListener<T> {
    fun onInsert(result: Boolean) {}
    fun onSelect(data: T?) {}
    fun onSelectMany(dataList: MutableList<T>?) {}
    fun onUpdate(result: Boolean) {}
    fun onDelete(result: Boolean) {}
}