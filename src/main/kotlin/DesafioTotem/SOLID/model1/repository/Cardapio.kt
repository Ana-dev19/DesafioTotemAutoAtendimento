package DesafioTotem.SOLID.model1.repository

import DesafioTotem.SOLID.model1.model.BebidaItem
import DesafioTotem.SOLID.model1.model.ItemCardapio
import DesafioTotem.SOLID.model1.model.LancheItem
import DesafioTotem.SOLID.model1.model.SobremesaItem

class Cardapio {
    val cardapioLanches = mutableMapOf<String, LancheItem>()
    val cardapioBebidas = mutableMapOf<String, BebidaItem>()
    val cardapioSobremesas = mutableMapOf<String, SobremesaItem>()

    fun adicionarItem(codigo: String, item: ItemCardapio) {
        when (item) {
            is LancheItem -> cardapioLanches[codigo] = item
            is BebidaItem -> cardapioBebidas[codigo] = item
            is SobremesaItem -> cardapioSobremesas[codigo] = item
        }
    }

    fun getItem(codigo: String): ItemCardapio? {
        return cardapioLanches[codigo] ?: cardapioBebidas[codigo] ?: cardapioSobremesas[codigo]
    }
}
