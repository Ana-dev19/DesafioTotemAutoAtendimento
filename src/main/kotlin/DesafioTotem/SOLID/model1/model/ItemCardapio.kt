package DesafioTotem.SOLID.model1.model

interface ItemCardapio {
    val codigo: String
    val nome: String
    val preco: Double
}
interface LancheItem : ItemCardapio
interface BebidaItem : ItemCardapio
interface SobremesaItem : ItemCardapio