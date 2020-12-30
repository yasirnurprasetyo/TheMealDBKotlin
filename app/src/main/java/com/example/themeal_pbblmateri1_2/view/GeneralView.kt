package com.example.themeal_pbblmateri1_2.view
interface GeneralView {
    //menampilkan loading ketika awal memanggil API
    fun showLoading()
    //dieksekusi ketika error
    fun error(error: Throwable?)
    //dieksekusi ketika hasil dari api didapatkan
    fun success(response: Any)
    //ketika selseai error atau sukes
    fun hideLoading()
}