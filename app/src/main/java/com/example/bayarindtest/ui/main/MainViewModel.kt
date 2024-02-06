package com.example.bayarindtest.ui.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MainViewModel : ViewModel() {


    private val _possiblyPays = MutableLiveData<MutableList<String>>()
    val possiblyPay : LiveData<MutableList<String>> get()= _possiblyPays
    private val _loading = MutableLiveData<Boolean>()
    val isLoading : LiveData<Boolean> get()= _loading


    fun findPossibly(amount: String) {
        _loading.value = true
        val nominal = listOf(100, 200, 500, 1000, 2000, 5000, 10000, 20_000, 50_000, 100_000)
        val collectDistinctValue = mutableListOf<String>()
        if(amount.toInt() < 100_000){
            val caraBayars = getPossiblyPays(amount.toInt(), nominal)
            for (i in caraBayars.size - 1 downTo 0) {
                val sum = caraBayars[i].sum()
                if(!collectDistinctValue.contains(sum.toString())){
                    collectDistinctValue.add(sum.toString())
                }
            }
        }
        collectDistinctValue.add(MainUtils.UANG_PAS)
        _loading.value = false
        _possiblyPays.value = collectDistinctValue

    }


    fun getPossiblyPays(amount: Int, denominations: List<Int>): List<List<Int>> {
        val reversedDenomination = denominations.reversed()
        val results = mutableListOf<List<Int>>()
        val current = mutableListOf<Int>()
        getCombination(amount, reversedDenomination, 0, current, results)
        return results
    }

    private fun getCombination(amount: Int, denominations: List<Int>, idx: Int, current: MutableList<Int>, results: MutableList<List<Int>>) {
        if (idx == denominations.size) {
            return
        }
        if (amount <= 0) {
            results.add(ArrayList(current))
            return
        }

        current.add(denominations[idx])
        getCombination(amount - denominations[idx], denominations, idx, current, results)
        current.removeAt(current.size - 1)

        getCombination(amount, denominations, idx + 1, current, results)
    }


}