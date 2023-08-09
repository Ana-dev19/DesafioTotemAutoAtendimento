package DesafioTotem
class FastFood {
    private var pedidoCounter = 1
    data class ItemCardapio(val codigo: String, val nome: String, val preco: Double)


    val cardapioLanches = mutableMapOf(
        "1" to ItemCardapio("1", "X-burger", 10.0),
        "2" to ItemCardapio("2", "X-salada", 12.0)
    )
    val cardapioBebidas = mutableMapOf(
        "1" to ItemCardapio("3", "Refrigerante Coca-Cola", 8.0),
        "2" to ItemCardapio("4","Refrigerante Sprite", 8.0),
        "3" to ItemCardapio("5", "Suco de Maracuja", 6.0),
        "4" to ItemCardapio("6", "Suco de Goiaba", 6.0)
    )
    val cardapioSobremesas = mutableMapOf(
        "1" to ItemCardapio("7", "Sorvete", 5.0),
        "2" to ItemCardapio("8", "Torta de Chocolate", 7.0)
    )

    val carrinho = mutableMapOf<String, MutableList<Triple<ItemCardapio, Int, Double>>>()

    fun adicionarAoCarrinho(usuario: String, item: ItemCardapio, quantidade: Int, preco: Double) {
        if (usuario !in carrinho) {
            carrinho[usuario] = mutableListOf()
        }
        carrinho[usuario]?.add(Triple(item, quantidade, preco))
    }

    fun editarItem(usuario: String, codigo: String, novaQuantidade: Int) {
        val carrinhoUsuario = carrinho[usuario]
        if (carrinhoUsuario != null){
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

    fun gerarCodigoPedido(): String {
        val codigoPedido = pedidoCounter.toString().padStart(6,'0')
        pedidoCounter++
        return codigoPedido()
    }

     fun codigoPedido(): String {
         val codigoPedido = "PEDIDO-${pedidoCounter.toString().padStart(4, '1')}"
         pedidoCounter++
         val pedidos = null
         val usuario = 0
         pedidos?.set(usuario, codigoPedido)
         return codigoPedido

    }

    fun getCarrinho(usuario: String): List<Triple<ItemCardapio,Int,Double>> {
        return carrinho[usuario]?: emptyList()

    }
}
