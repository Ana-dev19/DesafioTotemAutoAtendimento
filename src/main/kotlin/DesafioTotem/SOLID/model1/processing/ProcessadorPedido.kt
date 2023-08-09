package DesafioTotem.SOLID.model1.processing

import DesafioTotem.SOLID.model1.service.CarrinhoDeCompras
import DesafioTotem.SOLID.model1.util.GeradorCodigoPedido

interface ProcessadorPedido {
    fun processarPedido(usuario:String): String
}
class ProcessadorPedidoFastFood(private val carrinho: CarrinhoDeCompras, private val geradorCodigoPedido: GeradorCodigoPedido) : ProcessadorPedido {
    override fun processarPedido(usuario: String): String {
        val total = carrinho.calcularValorTotal(usuario)
        val codigoPedido = geradorCodigoPedido.gerarCodigoPedido()
        // Simulação de um processo de pagamento ou finalização do pedido
        return "Pedido $codigoPedido - Total: R$ $total"
    }
}