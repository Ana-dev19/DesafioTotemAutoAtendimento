package DesafioTotem.SOLID.model1.app

import DesafioTotem.SOLID.model1.model.ItemCardapio
import DesafioTotem.SOLID.model1.processing.ProcessadorPedido
import DesafioTotem.SOLID.model1.processing.ProcessadorPedidoFastFood
import DesafioTotem.SOLID.model1.repository.Cardapio
import DesafioTotem.SOLID.model1.service.CarrinhoDeCompras
import DesafioTotem.SOLID.model1.util.GeradorCodigoPedido

class FastFood (
    private val cardapio: Cardapio,
    private val carrinho: CarrinhoDeCompras,
    private val geradorCodigoPedido: GeradorCodigoPedido,
    private val processadorPedido: ProcessadorPedido
) {
    fun adicionarAoCarrinho(usuario: String, codigoItem: String, quantidade: Int) {
        val item = cardapio.getItem(codigoItem)
        if (item != null) {
            carrinho.adicionarAoCarrinho(usuario, item, quantidade, item.preco * quantidade)
        }
    }

    fun editarItemCarrinho(usuario: String, codigoItem: String, novaQuantidade: Int) {
        carrinho.editarItem(usuario, codigoItem, novaQuantidade)
    }

    fun excluirItemCarrinho(usuario: String, codigoItem: String) {
        carrinho.excluirItem(usuario, codigoItem)
    }

    fun calcularValorTotalCarrinho(usuario: String): Double {
        return carrinho.calcularValorTotal(usuario)
    }

    fun processarPedido(usuario: String): String {
        return processadorPedido.processarPedido(usuario)
    }

    fun getCarrinho(usuario: String): List<Triple<ItemCardapio, Int, Double>> {
        return carrinho.getCarrinho(usuario)
    }
}

fun main() {
    // Simulação de uso dos métodos do FastFood
    val cardapio = Cardapio()
    val carrinho = CarrinhoDeCompras()
    val geradorCodigoPedido = GeradorCodigoPedido()
    val processadorPedido = ProcessadorPedidoFastFood(carrinho, geradorCodigoPedido)
    val fastFood = FastFood(cardapio, carrinho, geradorCodigoPedido, processadorPedido)

    val usuario = "john_doe"

    // Adicionar itens ao carrinho
    fastFood.adicionarAoCarrinho(usuario, "1", 2)
    fastFood.adicionarAoCarrinho(usuario, "3", 1)

    println("Carrinho do usuário $usuario:")
    fastFood.getCarrinho(usuario).forEach { (item, quantidade, preco) ->
        println("${item.nome} x $quantidade - R$ $preco")
    }

    // Editar item no carrinho
    fastFood.editarItemCarrinho(usuario, "1", 3)

    println("Carrinho do usuário $usuario após edição:")
    fastFood.getCarrinho(usuario).forEach { (item, quantidade, preco) ->
        println("${item.nome} x $quantidade - R$ $preco")
    }

    // Excluir item do carrinho
    fastFood.excluirItemCarrinho(usuario, "3")

    println("Carrinho do usuário $usuario após exclusão:")
    fastFood.getCarrinho(usuario).forEach { (item, quantidade, preco) ->
        println("${item.nome} x $quantidade - R$ $preco")
    }

    // Calcular valor total do carrinho
    val totalCarrinho = fastFood.calcularValorTotalCarrinho(usuario)
    println("Total do carrinho: R$ $totalCarrinho")

    // Processar pedido
    val pedido = fastFood.processarPedido(usuario)
    println("Pedido: $pedido")
}

