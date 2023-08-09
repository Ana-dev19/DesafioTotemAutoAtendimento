package DesafioTotem.SOLID.model1.service

import DesafioTotem.SOLID.model1.model.ItemCardapio

class CarrinhoDeCompras {
    private val carrinho = mutableMapOf<String, MutableList<Triple<ItemCardapio, Int, Double>>>()

    fun adicionarAoCarrinho(usuario: String, item: ItemCardapio, quantidade: Int, preco: Double) {
        if (usuario !in carrinho) {
            carrinho[usuario] = mutableListOf()
        }
        carrinho[usuario]?.add(Triple(item, quantidade, preco))
    }
}
fun editarItem(usuario: String, codigo: String, novaQuantidade: Int) {
    val carrinhoUsuario = carrinho[usuario]
    if (carrinhoUsuario != null) {
        val itemParaEditar = carrinhoUsuario.find { it.first.codigo == codigo }
        if (itemParaEditar != null) {
            val (item, _, preco) = itemParaEditar
            carrinhoUsuario.removeIf { it.first.codigo == codigo }
            carrinhoUsuario.add(Triple(item, novaQuantidade, preco))
        }
    }
}

fun excluirItem(usuario: String, codigo: String) {
    val carrinhoUsuario = carrinho[usuario]
    if (carrinhoUsuario != null) {
        carrinho[usuario]?.removeIf { it.first.codigo == codigo }
    }
}

fun calcularValorTotal(usuario: String): Double {
    return carrinho[usuario]?.sumByDouble { it.first.preco * it.second } ?: 0.0
}

fun getCarrinho(usuario: String): List<Triple<ItemCardapio, Int, Double>> {
    return carrinho[usuario] ?: emptyList()
}
