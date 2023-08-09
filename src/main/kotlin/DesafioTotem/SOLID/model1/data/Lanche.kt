package DesafioTotem.SOLID.model1.data

import DesafioTotem.SOLID.model1.model.BebidaItem
import DesafioTotem.SOLID.model1.model.LancheItem
import DesafioTotem.SOLID.model1.model.SobremesaItem


data class Lanche(override val codigo: String, override val nome: String, override val preco: Double) : LancheItem
data class Bebida(override val codigo: String, override val nome: String, override val preco: Double) : BebidaItem
data class Sobremesa(override val codigo: String, override val nome: String, override val preco: Double) : SobremesaItem

