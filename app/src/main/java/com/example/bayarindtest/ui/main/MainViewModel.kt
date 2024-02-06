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
        amount.replace(Regex("[^0-9]"), "")
        _loading.value = true
        val nominal = listOf(100, 200, 500, 1000, 2000, 5000, 10000, 20_000, 50_000, 100_000)
        val collectDistinctValue = mutableListOf<String>()
        if(amount.toInt() < 100_000){
            val caraBayars = getPossiblyPays(amount.toInt(), nominal).toMutableList().sorted()
            for (item in caraBayars) {
                collectDistinctValue.add(item.toString())
            }
        }
        collectDistinctValue.add(MainUtils.UANG_PAS)
        _loading.value = false
        _possiblyPays.value = collectDistinctValue

    }


    private fun getPossiblyPays(amount: Int, denominations: List<Int>): Set<Int> {
        val reversedDenomination = denominations.reversed()
        val results = mutableSetOf<Int>()
        val current = mutableListOf<Int>()
        getCombination(amount, reversedDenomination, 0, current, results)
        return results
    }


    private fun getCombination(amount: Int, denominations: List<Int>, idx: Int, current: MutableList<Int>, results: MutableSet<Int>) {
        if (idx == denominations.size) {
            return
        }
        if (amount <= 0) {
            results.add(current.sum())
            return
        }
        if(current.size > 20){
            return
        }
        current.add(denominations[idx])
        getCombination(amount - denominations[idx], denominations, idx, current, results)
        current.removeAt(current.size - 1)

        getCombination(amount, denominations, idx + 1, current, results)
    }

}