package com.example.ht1

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import org.json.JSONObject.NULL

class ItemsViewModel(val state: SavedStateHandle): ViewModel() {

    companion object{
        private val KEY = "countLiveData"
    }


    private var _count: MutableLiveData<Int> = state.getLiveData(KEY)
    val count: LiveData<Int>
        get() = _count

    init {
        println("init")
        _count = state.getLiveData(KEY)
        println("_count.value = "+_count.value.toString())
        println("state[KEY] = "+state[KEY])
        _count.value = state[KEY]
        if(_count.value == null)
            _count.value = 3
        println(state.keys().toString())
        println("_count.value = "+_count.value.toString())
    }

    fun addItem(){
        _count.value = _count.value!!.inc()
        println("_count.value = "+_count.value.toString())

    }
    fun saveList(){
        state[KEY] = _count.value
        println("сохранено " + _count.value)
        println("state[KEY] = "+state[KEY])
    }

}